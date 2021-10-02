package com.github.m5rian.shilu.client;

import com.github.m5rian.shilu.client.cosmetics.CosmeticManager;
import com.github.m5rian.shilu.client.cosmetics.impl.Cape;
import com.github.m5rian.shilu.client.cosmetics.impl.DogFilter;
import com.github.m5rian.shilu.client.events.EventManager;
import com.github.m5rian.shilu.client.events.EventScheduler;
import com.github.m5rian.shilu.client.hud.HudManager;
import com.github.m5rian.shilu.client.mods.Mods;
import com.github.m5rian.shilu.client.utilities.Logger;
import com.github.m5rian.shilu.client.utilities.Storage;
import net.minecraft.client.renderer.entity.RenderPlayer;

/**
 * @author Marian
 * Represens the Client with all custom tasks.
 */
public class Client {
    /**
     * The name of the Client.
     */
    public static final String NAME = "Shilu";
    /**
     * The current version of the Client.
     */
    public static final String VERSION = "alpha";

    private final DiscordRP discordRichPresence = new DiscordRP(); // The Discord rich presence
    private final HudManager hudManager = new HudManager();
    private final CosmeticManager cosmeticManager = new CosmeticManager();

    public static RenderPlayer playerRenderer = null;
    public static boolean playerIsMoving = false;

    private static final Client INSTANCE = new Client(); // An instance of the Client

    /**
     * A getter method for the instance.
     *
     * @return Returns {@link Client#INSTANCE}.
     */
    public static Client getClient() {
        return INSTANCE;
    }

    /**
     * Runs once the game is started.
     */
    public void init() {
        Logger.info("Starting Client");
        Storage.init();
        discordRichPresence.start(); // Start discord rich presence
        cosmeticManager.registerCosmetics(
                //new Wing(),
                new DogFilter()
                //new Cape()
        );
    }

    /**
     * Runs once the game is launched.
     */
    public void start() {
        Mods.load(hudManager); // Load mods
        // Register events
        EventManager.register(
                new EventScheduler(),
                hudManager,
                discordRichPresence,
                // Mods
                Mods.CPS,
                Mods.TOGGLE_SPRINT,
                Mods.PERSPECTIVE
        );
        Logger.info("Started Client");
    }

    /**
     * Shutdown all client tasks.
     */
    public void shutdown() {
        discordRichPresence.shutdown();
    }

    /**
     * @return Returns {@link Client#discordRichPresence}.
     */
    public DiscordRP getDiscordRichPresence() {
        return discordRichPresence;
    }

    /**
     * @return Returns {@link Client#hudManager}.
     */
    public HudManager getHudManager() {
        return hudManager;
    }

    /**
     * @return Returns {@link Client#cosmeticManager}.
     */
    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }
}
