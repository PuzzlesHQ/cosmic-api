package io.github.puzzle.cosmic.impl.mixin.client.crashscreen;



import finalforeach.cosmicreach.lwjgl3.CrashScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.puzzle.cosmic.impl.client.crashscreen.FixedCrashscreen.crash;

@Mixin(CrashScreen.class)
public class CrashScreenMixin {


    /**
     * @author replet
     * @reason Fix buggyness
     */
    @Overwrite
    public static void showCrash(long startTime, StringBuilder preStartErr, Throwable ex){
        crash(startTime,preStartErr,ex);
    }
}
