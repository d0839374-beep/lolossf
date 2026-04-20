package com.lilos.client.hud;

import com.lilos.client.core.LilosClient;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.math.MathHelper;

public class HUDRenderer {
    private float watermarkAlpha = 0f;
    private boolean watermarkVisible = true;
    private long lastToggleTime = 0;

    public void render(MatrixStack matrixStack, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.options.renderDebug) return;

        FontRenderer font = mc.font;
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        // Animate watermark appearance
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastToggleTime > 500) {
            watermarkVisible = !watermarkVisible;
            lastToggleTime = currentTime;
        }

        float targetAlpha = watermarkVisible ? 1f : 0.8f;
        watermarkAlpha += (targetAlpha - watermarkAlpha) * 0.1f;

        // Render Watermark
        renderWatermark(matrixStack, font, 10, 10, watermarkAlpha);

        // Render FPS Counter
        renderFPSCounter(matrixStack, font, width - 60, 10);

        // Render Module List
        renderModuleList(matrixStack, font, width - 120, 30);
    }

    private void renderWatermark(MatrixStack matrixStack, FontRenderer font, int x, int y, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        String watermark = "LILOS CLIENT";
        int color = getColorWithAlpha(0xFF00FF, alpha);
        
        matrixStack.pushPose();
        font.draw(matrixStack, watermark, x + 1, y + 1, 0x40000000);
        font.draw(matrixStack, watermark, x, y, color);
        matrixStack.popPose();

        // Subtitle
        String subtitle = "v1.0.0 | Modern Forge Client";
        font.draw(matrixStack, subtitle, x, y + 10, 0x80808080);
    }

    private void renderFPSCounter(MatrixStack matrixStack, FontRenderer font, int x, int y) {
        int fps = LilosClient.INSTANCE.getFPS();
        String fpsText = "FPS: " + fps;
        
        int color;
        if (fps >= 60) {
            color = 0x00FF00;
        } else if (fps >= 30) {
            color = 0xFFFF00;
        } else {
            color = 0xFF0000;
        }

        font.draw(matrixStack, fpsText, x, y, color);
    }

    private void renderModuleList(MatrixStack matrixStack, FontRenderer font, int x, int y) {
        int yOffset = 0;
        for (var module : LilosClient.INSTANCE.moduleManager.getModules()) {
            if (module.isEnabled()) {
                String moduleName = module.getName();
                int color = 0xFF00FF;
                font.draw(matrixStack, moduleName, x, y + yOffset, color);
                yOffset += 10;
            }
        }
    }

    private int getColorWithAlpha(int color, float alpha) {
        int a = (int) (alpha * 255);
        return (a << 24) | (color & 0xFFFFFF);
    }
}
