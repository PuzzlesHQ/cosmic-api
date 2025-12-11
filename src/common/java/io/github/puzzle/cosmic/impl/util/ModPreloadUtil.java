package io.github.puzzle.cosmic.impl.util;

import dev.puzzleshq.mod.api.IModContainer;
import dev.puzzleshq.mod.util.EntrypointPair;
import dev.puzzleshq.puzzleloader.loader.mod.EntrypointContainer;
import dev.puzzleshq.puzzleloader.loader.provider.ProviderException;
import dev.puzzleshq.puzzleloader.loader.provider.lang.ILangProvider;
import dev.puzzleshq.puzzleloader.loader.util.ModFinder;

public class ModPreloadUtil {

    public interface PreLoadCaster {}

    public static void preloadAllMods() {
        for (IModContainer mod : ModFinder.getModsArray()) {
            if (mod.getID().equals("puzzle-loader")) continue;
            if (mod.getID().equals("cosmic-reach")) continue;
            if (mod.getID().equals("puzzle-loader-cosmic")) continue;
            // Gotta resist them all from loading things too early if its a core class
            preloadMod(mod);
        }
    }

    public static void preloadMod(IModContainer container) {
        for (EntrypointPair[] value : container.getEntrypointContainer().getEntrypointMap().values()) {
            for (EntrypointPair pair : value) {
                Object instance = EntrypointContainer.INSTANCE_MAP.get(pair.entrypoint());

                if (instance != null) {
                    continue;
                }

                try {
                    ILangProvider provider = ILangProvider.PROVDERS.get(pair.adapter());
                    if (provider == null) continue;

                    instance = provider.create(
                            container.getInfo(),
                            pair.entrypoint(),
                            PreLoadCaster.class
                    );

                    EntrypointContainer.INSTANCE_MAP.put(pair.entrypoint(), instance);
                } catch (RuntimeException e) {
                    // Don't allow one mod to crash the entire loader
                } catch (ProviderException e) {

                }
            }
        }
    }

    public static boolean preloadMod(String ID) {
        IModContainer mod = ModFinder.getMod(ID);
        if (mod == null) return false;
        if (mod.getID().equals("puzzle-loader")) return false;
        if (mod.getID().equals("cosmic-reach")) return false;
        if (mod.getID().equals("puzzle-loader-cosmic")) return false;
        // Gotta resist them all from loading things too early if its a core class
        preloadMod(mod);
        return true;
    }

}
