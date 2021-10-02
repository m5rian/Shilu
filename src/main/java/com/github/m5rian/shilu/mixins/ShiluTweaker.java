package com.github.m5rian.shilu.mixins;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* Tweaker Used to Start Mixin Bootstrap - called in Launch Arguments */
public class ShiluTweaker implements ITweaker {


    /**
     * This stores the Minecraft launch arguments.
     */
    private final List<String> launchArgs = new ArrayList<>();

    /**
     * This handles the launch arguments passed towards minecraft.
     *
     * @param args      The launch arguments.
     * @param gameDir   The game directory (ie: .minecraft).
     * @param assetsDir The assets' directory.
     * @param profile   The game profile.
     */
    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArgs.addAll(args);

        if (!args.contains("--version") && profile != null) {
            launchArgs.add("--version");
            launchArgs.add(profile);
        }

        if (!args.contains("--assetDir") && assetsDir != null) {
            launchArgs.add("--assetDir");
            launchArgs.add(assetsDir.getAbsolutePath());
        }

        if (!args.contains("--gameDir") && gameDir != null) {
            launchArgs.add("--gameDir");
            launchArgs.add(gameDir.getAbsolutePath());
        }
    }


    /**
     * Inject into the MC class loader
     *
     * @param classLoader The class loader
     */
    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        // Retrieve the default mixin environment and register the config file
        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.shilu.json");

        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[0]);
    }
}