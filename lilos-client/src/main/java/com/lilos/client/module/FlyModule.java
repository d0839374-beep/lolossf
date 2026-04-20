package com.lilos.client.module;

import net.minecraft.entity.player.PlayerEntity;

public class FlyModule extends Module {
    public FlyModule() {
        super("Fly", "Allows you to fly in survival mode");
    }

    @Override
    public void onTick() {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && isEnabled()) {
            player.abilities.flying = true;
            player.abilities.setFlyingSpeed(0.1f);
        }
    }

    @Override
    public void onDisable() {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && !player.isCreative()) {
            player.abilities.flying = false;
        }
    }
}
