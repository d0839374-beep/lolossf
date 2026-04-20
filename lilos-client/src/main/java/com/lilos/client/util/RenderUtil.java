package com.lilos.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import com.mojang.blaze3d.systems.RenderSystem;

public class RenderUtil {
    
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Tessellator tessellator = Tessellator.getInstance();
    private static final BufferBuilder bufferBuilder = tessellator.getBuilder();

    public static void drawRect(double x, double y, double width, double height, int color) {
        double x1 = x + width;
        double y1 = y + height;

        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);

        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(x, y1, 0).color(r, g, b, a).endVertex();
        bufferBuilder.vertex(x1, y1, 0).color(r, g, b, a).endVertex();
        bufferBuilder.vertex(x1, y, 0).color(r, g, b, a).endVertex();
        bufferBuilder.vertex(x, y, 0).color(r, g, b, a).endVertex();
        tessellator.end();

        RenderSystem.shadeModel(7424);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);

        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(right, top, 0).color(f1, f2, f3, f).endVertex();
        bufferBuilder.vertex(left, top, 0).color(f1, f2, f3, f).endVertex();
        bufferBuilder.vertex(left, bottom, 0).color(f5, f6, f7, f4).endVertex();
        bufferBuilder.vertex(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.end();

        RenderSystem.shadeModel(7424);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawLine(double x1, double y1, double x2, double y2, int color, float width) {
        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(width);

        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(x1, y1, 0).color(r, g, b, a).endVertex();
        bufferBuilder.vertex(x2, y2, 0).color(r, g, b, a).endVertex();
        tessellator.end();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawCircle(double x, double y, double radius, int color) {
        double circles = 50;
        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        bufferBuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(x, y, 0).color(r, g, b, a).endVertex();

        for (int i = 0; i <= circles; i++) {
            double angle = (Math.PI * 2 * i) / circles;
            double px = x + Math.cos(angle) * radius;
            double py = y + Math.sin(angle) * radius;
            bufferBuilder.vertex(px, py, 0).color(r, g, b, a).endVertex();
        }

        tessellator.end();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawRoundedRect(double x, double y, double width, double height, double radius, int color) {
        // Simple rounded rect implementation
        drawRect(x + radius, y, width - radius * 2, height, color);
        drawRect(x, y + radius, width, height - radius * 2, color);
        
        // Draw corners (simplified as circles)
        drawCircle(x + radius, y + radius, radius, color);
        drawCircle(x + width - radius, y + radius, radius, color);
        drawCircle(x + radius, y + height - radius, radius, color);
        drawCircle(x + width - radius, y + height - radius, radius, color);
    }

    public static int getScreenWidth() {
        return mc.getWindow().getGuiScaledWidth();
    }

    public static int getScreenHeight() {
        return mc.getWindow().getGuiScaledHeight();
    }
}
