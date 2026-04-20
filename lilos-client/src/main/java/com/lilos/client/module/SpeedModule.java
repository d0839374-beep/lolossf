package com.lilos.client.module;

import net.minecraft.entity.player.PlayerEntity;

public class SpeedModule extends Module {
    public SpeedModule() {
        super("Speed", "Increases movement speed");
    }

    @Override
    public void onTick() {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && isEnabled()) {
            player.setSpeed(0.3f);
        }
    }

    @Override
    public void onDisable() {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null) {
            player.setSpeed(0.2f);
        }
    }
}
