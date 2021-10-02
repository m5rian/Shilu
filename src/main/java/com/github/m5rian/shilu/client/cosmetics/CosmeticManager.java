package com.github.m5rian.shilu.client.cosmetics;

import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CosmeticManager {

    private final List<Cosmetic> cosmetics = new ArrayList<>();

    public void registerCosmetics(Cosmetic... cosmetics) {
        this.cosmetics.addAll(Arrays.asList(cosmetics));
    }

    public List<Cosmetic> getCosmetics() {
        return this.cosmetics;
    }

}
