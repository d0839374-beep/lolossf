package com.lilos.client.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

public class KillAuraModule extends Module {
    public KillAuraModule() {
        super("KillAura", "Automatically attacks nearby enemies");
    }

    @Override
    public void onTick() {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && isEnabled()) {
            for (Entity entity : player.level.entitiesForRendering()) {
                if (entity instanceof LivingEntity && entity != player) {
                    if (entity instanceof MonsterEntity || entity instanceof PlayerEntity) {
                        double distance = player.distanceTo(entity);
                        if (distance < 4.0) {
                            player.attack(entity);
                            break;
                        }
                    }
                }
            }
        }
    }
}
