package com.lilos.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class CustomMainMenuScreen extends Screen {
    private Button singleplayerButton;
    private Button multiplayerButton;
    private Button optionsButton;
    private Button quitButton;

    public CustomMainMenuScreen() {
        super(new StringTextComponent("LILOS CLIENT - Main Menu"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int centerX = width / 2;
        int startY = height / 4 + 48;

        singleplayerButton = addButton(new Button(centerX - buttonWidth / 2, startY, buttonWidth, buttonHeight,
                new StringTextComponent("Singleplayer"), btn -> {
            Minecraft.getInstance().setScreen(new net.minecraft.client.gui.screen.MultiplayerScreen(this));
        }));

        multiplayerButton = addButton(new Button(centerX - buttonWidth / 2, startY + 24, buttonWidth, buttonHeight,
                new StringTextComponent("Multiplayer"), btn -> {
            Minecraft.getInstance().setScreen(new net.minecraft.client.gui.screen.MultiplayerScreen(this));
        }));

        optionsButton = addButton(new Button(centerX - buttonWidth / 2, startY + 48, buttonWidth, buttonHeight,
                new StringTextComponent("Options"), btn -> {
            Minecraft.getInstance().setScreen(new net.minecraft.client.gui.screen.OptionsScreen(this, Minecraft.getInstance().options));
        }));

        quitButton = addButton(new Button(centerX - buttonWidth / 2, startY + 72, buttonWidth, buttonHeight,
                new StringTextComponent("Quit"), btn -> {
            Minecraft.getInstance().stop();
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        // Draw title
        drawCenteredString(matrixStack, font, "LILOS CLIENT", width / 2, height / 4 - 20, 0xFF00FF);
        drawCenteredString(matrixStack, font, "Modern Forge 1.16.5 Client", width / 2, height / 4 - 8, 0xFFFFFF);
        
        // Draw version
        drawString(matrixStack, font, "v1.0.0", 10, height - 20, 0x80808080);
        
        // Draw FPS
        String fpsText = "FPS: " + com.lilos.client.core.LilosClient.INSTANCE.getFPS();
        int fpsColor = com.lilos.client.core.LilosClient.INSTANCE.getFPS() >= 60 ? 0x00FF00 : 
                      (com.lilos.client.core.LilosClient.INSTANCE.getFPS() >= 30 ? 0xFFFF00 : 0xFF0000);
        drawString(matrixStack, font, fpsText, width - 50, height - 20, fpsColor);
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void onClose() {
        // Don't close on escape, just return to game if in game
        if (Minecraft.getInstance().level != null) {
            Minecraft.getInstance().setScreen(null);
        }
    }
}
