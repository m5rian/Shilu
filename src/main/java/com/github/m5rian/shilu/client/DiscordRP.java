package com.github.m5rian.shilu.client;

import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.GuiSwitchEvent;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import org.apache.logging.log4j.LogManager;

/**
 * @author Marian
 * The Discord rich presence class.
 */
public class DiscordRP {
    private boolean running = true; // Is the application running?
    private Long timestamp = 0L; // Time since when the user is doing x.

    private final String clientId = "876552340075851817"; // The application to connect to the rich presence.

    /**
     * Start the rich presence.
     */
    public void start() {
        LogManager.getLogger().info("Starting Discord rich presence");

        this.timestamp = System.currentTimeMillis();
        final DiscordEventHandlers eventHandler = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                LogManager.getLogger(getClass()).info("Logged in as " + user.username + "#" + user.discriminator);
            }
        }).build();

        DiscordRPC.discordInitialize(this.clientId, eventHandler, true);

        new Thread(() -> {
            while (this.running) {
                DiscordRPC.discordRunCallbacks();
            }
        }, "Discord rich presence").start();
    }

    /**
     * Shutdown the method carefully.
     */
    public void shutdown() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    /**
     * Update the current presence.
     *
     * @param firstLine      The first line of the activity.
     * @param secondLine     The second line of the activity.
     * @param startTimestamp The start timestamp of the activity.
     */
    public void update(String firstLine, String secondLine, Long startTimestamp) {
        DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(secondLine)
                .setDetails(firstLine)
                .setBigImage("logo", "NotroClient")
                .setStartTimestamps(startTimestamp == null ? this.timestamp : startTimestamp);
        DiscordRPC.discordUpdatePresence(builder.build());
    }

    /**
     * Update the current presence.
     *
     * @param firstLine  The first line of the activity.
     * @param secondLine The second line of the activity.
     */
    public void update(String firstLine, String secondLine) {
        update(firstLine, secondLine, null);
    }


    /**
     * Update the current presence.
     *
     * @param firstLine      The first line of the activity.
     * @param startTimestamp The start timestamp of the activity.
     */
    public void update(String firstLine, Long startTimestamp) {
        update(firstLine, "", startTimestamp);
    }

    /**
     * Update the current presence.
     *
     * @param firstLine The first line of the activity.
     */
    public void update(String firstLine) {
        update(firstLine, "", null);
    }

    /**
     * Runs whenever a GUI window is changed.
     * This updates the Discord rich presence.
     *
     * @param event The {@link GuiSwitchEvent}.
     */
    @EventTarget
    public void onGuiSwitchEvent(GuiSwitchEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (event.getGui() instanceof GuiMainMenu)
            update("In Main Menu");
        else if (event.getGui() instanceof GuiMultiplayer)
            update("In Multiplayer Menu");
        else if (mc.isIntegratedServerRunning())
            update("Playing in Singleplayer");
        else if (!mc.isIntegratedServerRunning() && mc.getCurrentServerData() != null)
            update("Playing on " + mc.getCurrentServerData().serverIP);
    }

}
