package com.lilos.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

public class PlayerUtil {
    
    public static PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Vector3d getPosition() {
        PlayerEntity player = getPlayer();
        return player != null ? player.position() : Vector3d.ZERO;
    }

    public static float getYaw() {
        PlayerEntity player = getPlayer();
        return player != null ? player.yRot : 0f;
    }

    public static float getPitch() {
        PlayerEntity player = getPlayer();
        return player != null ? player.xRot : 0f;
    }

    public static void setYaw(float yaw) {
        PlayerEntity player = getPlayer();
        if (player != null) {
            player.yRot = yaw;
        }
    }

    public static void setPitch(float pitch) {
        PlayerEntity player = getPlayer();
        if (player != null) {
            player.xRot = pitch;
        }
    }

    public static boolean isMoving() {
        PlayerEntity player = getPlayer();
        return player != null && (player.xxa != 0 || player.zza != 0);
    }

    public static boolean isInLiquid() {
        PlayerEntity player = getPlayer();
        return player != null && (player.isInWater() || player.isInLava());
    }

    public static boolean isOnGround() {
        PlayerEntity player = getPlayer();
        return player != null && player.onGround;
    }

    public static double getSpeed() {
        PlayerEntity player = getPlayer();
        if (player == null) return 0;
        
        Vector3d velocity = player.getDeltaMovement();
        return Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
    }

    public static void setMotion(double x, double y, double z) {
        PlayerEntity player = getPlayer();
        if (player != null) {
            player.setDeltaMovement(x, y, z);
        }
    }

    public static float getHealth() {
        PlayerEntity player = getPlayer();
        return player != null ? player.getHealth() : 0f;
    }

    public static float getMaxHealth() {
        PlayerEntity player = getPlayer();
        return player != null ? player.getMaxHealth() : 20f;
    }

    public static int getPing() {
        PlayerEntity player = getPlayer();
        if (player != null && player.connection != null) {
            return player.connection.getLatency();
        }
        return 0;
    }
}
