package io.github.puzzle.cosmic.impl.client.crashscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import finalforeach.cosmicreach.BlockGame;
import finalforeach.cosmicreach.RuntimeInfo;
import finalforeach.cosmicreach.io.SaveLocation;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.util.logging.AnsiColours;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static finalforeach.cosmicreach.RuntimeInfo.byteSizeToHumanReadable;

public class FixedCrashscreen {
    public static void crash(long startTime, StringBuilder preStartErr, Throwable ex){
        ProcessBuilder builder;
        ArrayList<ProcessBuilder> builders = new ArrayList<>();
        System.out.print(AnsiColours.RESET);
        long crashTime = System.currentTimeMillis();
        long ranFor = crashTime - startTime;
        long ranForHours = TimeUnit.MILLISECONDS.toHours(ranFor);
        long ranForMins = TimeUnit.MILLISECONDS.toMinutes(ranFor) - (TimeUnit.MILLISECONDS.toHours(ranFor) * 60);
        long ranForSec = TimeUnit.MILLISECONDS.toSeconds(ranFor) - (TimeUnit.MILLISECONDS.toMinutes(ranFor) * 60);
        long seconds = ranFor - (TimeUnit.MILLISECONDS.toSeconds(ranFor) * 1000);
        String ranForTime = ranFor + " ms";
        if (ranForHours > 0) {
            ranForTime = ranForHours + " hours, " + ranForHours + " minutes, " + ranForMins + " seconds";
        } else if (ranForMins > 0) {
            ranForTime = ranForMins + " minutes, " + ranForMins + " seconds";
        } else if (ranForSec > 0) {
            ranForTime = ranForSec + " seconds, " + ranForSec + " ms";
        }
        String title = BlockGame.gameStarted ? "Crash while playing Cosmic Reach" : "Could not start Cosmic Reach";
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        OrderedMap<String, Object> infoItems = new OrderedMap<>();
        infoItems.put("Game started", BlockGame.gameStarted);
        String gameVersion = "unknown";
        try {
            if (Gdx.files == null) {
                Gdx.files = new Lwjgl3Files();
            }
            gameVersion = Gdx.files.internal("build_assets/version.txt").readString();
        } catch (Exception versionFetchException) {
            preStartErr.append(versionFetchException.toString());
        }
        infoItems.put("Game version", gameVersion);
        infoItems.put("Game Variant", RuntimeInfo.gameVariant);
        infoItems.put("Launcher", RuntimeInfo.getLauncherName());
        infoItems.put("Ran for", ranForTime);
        infoItems.put("Current time", ZonedDateTime.now().toString().replace("T", " at "));
        infoItems.put("Operating system", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        infoItems.put("Arch", System.getProperty("os.arch"));
        infoItems.put("Java VM name", System.getProperty("java.vm.name"));
        infoItems.put("Java runtime version", System.getProperty("java.runtime.version"));
        infoItems.put("System user language", System.getProperty("user.language"));
        infoItems.put("CPU model", "unknown");
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("windows")) {
                builders.add(new ProcessBuilder("powershell", "Get-WmiObject", "Win32_Processor", "|", "Select-Object", "-Property" , "Name"));
            } else if (osName.contains("mac")) {
                builders.add(new ProcessBuilder("sysctl", "-a", "machdep.cpu.brand_string"));
            } else {
                builders.addAll(List.of(new ProcessBuilder[]{new ProcessBuilder("lscpu"), new ProcessBuilder("grep", "Model name"), new ProcessBuilder("cut", "-f", "2", "-d", ":"), new ProcessBuilder("awk", "{$1=$1}1")}));
            }
            ArrayList<Process> processes = (ArrayList<Process>) ProcessBuilder.startPipeline(builders);
            Process last = processes.getLast();
            String output = new String(last.getInputStream().readAllBytes()).replace("\n", "");
            if (osName.contains("windows")) {
                output = output.replace("Name", "").trim();
                output = output.replace("----", "").trim();
            }
            infoItems.put("CPU model", output);
        } catch (Exception commandException) {
            commandException.printStackTrace();
        }
        File saveFolder = SaveLocation.getSaveFolder();
        if (saveFolder != null) {
            String freeSpace = byteSizeToHumanReadable(saveFolder.getFreeSpace());
            String totalSpace = byteSizeToHumanReadable(saveFolder.getTotalSpace());
            infoItems.put("Save location free / total space", freeSpace + " / " + totalSpace);
        }
        infoItems.put("Available processors", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
        if (Gdx.app != null) {
            infoItems.put("Native heap use", byteSizeToHumanReadable(Gdx.app.getNativeHeap()));
            infoItems.put("Java heap use", byteSizeToHumanReadable(Gdx.app.getJavaHeap()));
        }
        infoItems.put("Max memory available", byteSizeToHumanReadable(Runtime.getRuntime().maxMemory()));
        infoItems.put("RAM available", "Unknown");
        try {
            if (osName.contains("windows")) {
                builder = new ProcessBuilder("powershell", "[Math]::Round((Get-WmiObject", "Win32_Computersystem", "-ComputerName", "localhost).TotalPhysicalMemory)");
            } else if (osName.contains("mac")) {
                builder = new ProcessBuilder("sysctl", "-n", "hw.memsize");
            } else {
                builder = new ProcessBuilder("grep", "-i", "memtotal", "/proc/meminfo");
            }
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output2 = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    output2.append(line);
                }
            }
            reader.close();
            String ramStr = output2.toString();
            if (osName.contains("windows")) {
                ramStr = ramStr.replace("TotalPhysicalMemory=", "");
            } else if (!osName.contains("mac")) {
                ramStr = ramStr.replace("MemTotal:", "");
            }
            try {
                String n = ramStr.toLowerCase().trim();
                if (n.contains("kb")) {
                    double num = Double.parseDouble(n.replace("kb", "").trim()) * 1024.0d;
                    ramStr = byteSizeToHumanReadable((long) num);
                } else {
                    double num2 = Double.parseDouble(n);
                    ramStr = byteSizeToHumanReadable((long) num2);
                }
            } catch (Exception _) {
            }
            infoItems.put("RAM available", ramStr.trim());
        } catch (Exception _) {
        }
        if (Gdx.graphics != null) {
            infoItems.put("getGLVersion", Gdx.graphics.getGLVersion().getDebugVersionString());
        }
        infoItems.put("Prestart error logs", preStartErr);
        infoItems.put("Exception logs", sw);
        String logText = "";
        for (ObjectMap.Entry<String, Object> entry : infoItems.entries()) {
            if (entry.value != null) {
                String str = entry.value.toString();
                if (!str.isEmpty()) {
                    boolean addLineBreak = str.contains("\n");
                    logText = logText + "* " + entry.key + ": " + (addLineBreak ? "\n" : "") + str + "\n";
                }
            }
        }
        String logText2 = logText.replace("\t", "    ");
        try {
            new File(SaveLocation.getSaveFolderLocation()).mkdirs();
            File errorLogFile = new File(SaveLocation.getSaveFolderLocation() + "/errorLogLatest.txt");
            FileOutputStream fos = new FileOutputStream(errorLogFile);
            fos.write(logText2.getBytes());
            fos.close();
        } catch (Exception fex) {
            fex.printStackTrace();
        }

        CountDownLatch closeStuff = new CountDownLatch(1);

        JFrame frame = new JFrame(title);
        frame.setLocationByPlatform(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closeStuff.countDown();
            }
        });

        JPanel panel = new JPanel();


        panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("If writing a bug report, please copy the following logs (don't just screenshot!):");
        label.setAlignmentX(0.5f);
        panel.add(label);
        JTextArea logTextArea = new JTextArea(logText2);
        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);

        logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logScrollPane.setVisible(true);
        panel.add(logScrollPane, BorderLayout.CENTER);

        frame.add(panel,"Center");
        JPanel buttonPanel = new JPanel();
        JButton openButton = new JButton("Open Cosmic Reach Save Directory");
        JButton copyButton = new JButton("Copy To Clipboard");
        JButton okButton = new JButton("OK");
        buttonPanel.add(openButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(okButton);
        frame.add(buttonPanel, "South");
        openButton.addActionListener(e3 -> {
            try {
                SaveLocation.OpenFolderWithFileManager(SaveLocation.getSaveFolder());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
        copyButton.addActionListener(e4 -> {
            try {
                StringSelection stringSelection = new StringSelection(logText2);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
        okButton.addActionListener(e5 -> {
            frame.dispose();
        });
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
            frame.setIconImages(List.of(
                    ImageIO.read(Objects.requireNonNull(BlockGame.class.getClassLoader().getResourceAsStream("assets/puzzle-loader-core/icons/puzzle-loader-icon-16.png"))),
                    ImageIO.read(Objects.requireNonNull(BlockGame.class.getClassLoader().getResourceAsStream("assets/puzzle-loader-core/icons/puzzle-loader-icon-32.png"))),
                    ImageIO.read(Objects.requireNonNull(BlockGame.class.getClassLoader().getResourceAsStream("assets/puzzle-loader-core/icons/puzzle-loader-icon-64.png"))),
                    ImageIO.read(Objects.requireNonNull(BlockGame.class.getClassLoader().getResourceAsStream("assets/puzzle-loader-core/icons/puzzle-loader-icon-128.png"))),
                    ImageIO.read(Objects.requireNonNull(BlockGame.class.getClassLoader().getResourceAsStream("assets/puzzle-loader-core/icons/puzzle-loader-icon-256.png")
                    ))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.setVisible(true);
        frame.requestFocus();
        ex.printStackTrace();

        try {
            closeStuff.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.exit(1);

    }

}
