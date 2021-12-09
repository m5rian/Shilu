package com.github.m5rian.shilu.mixins.client.gui;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.List;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    /**
     * @author Marian
     */
    @Overwrite
    public void renderScoreboard(ScoreObjective scoreObjective, ScaledResolution scaledResolution) {
        Scoreboard scoreboard = scoreObjective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(scoreObjective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, p_apply_1_ -> p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#")));

        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        int i = Minecraft.getMinecraft().fontRendererObj.getStringWidth(scoreObjective.getDisplayName());

        for (Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
            i = Math.max(i, Minecraft.getMinecraft().fontRendererObj.getStringWidth(s));
        }

        int i1 = collection.size() * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        int j1 = scaledResolution.getScaledHeight() / 2 + i1 / 3;
        int k1 = 3;
        int l1 = scaledResolution.getScaledWidth() - i - k1;
        int j = 0;

        for (Score score1 : collection) {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
            int k = j1 - j * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
            int l = scaledResolution.getScaledWidth() - k1 + 2;
            Gui.drawRect(l1 - 2, k, l, k + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 1342177280);
            Minecraft.getMinecraft().fontRendererObj.drawString(s1, l1, k, 553648127);
            //Minecraft.getMinecraft().fontRendererObj.drawString(s2, l - Minecraft.getMinecraft().fontRendererObj.getStringWidth(s2), k, 553648127);

            if (j == collection.size()) {
                String s3 = scoreObjective.getDisplayName();
                Gui.drawRect(l1 - 2, k - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT - 1, l, k - 1, 1610612736);
                Gui.drawRect(l1 - 2, k - 1, l, k, 1342177280);
                Minecraft.getMinecraft().fontRendererObj.drawString(s3, l1 + i / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(s3) / 2, k - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 553648127);
            }
        }
    }

}
