package com.lilos.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.PauseScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class CustomPauseScreen extends PauseScreen {
    private Button resumeButton;
    private Button optionsButton;
    private Button disconnectButton;
    private Button clickGuiButton;

    public CustomPauseScreen() {
        super(false);
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int centerX = width / 2;
        int startY = height / 4 + 24;

        resumeButton = addButton(new Button(centerX - buttonWidth / 2, startY, buttonWidth, buttonHeight,
                new StringTextComponent("Resume Game"), btn -> {
            Minecraft.getInstance().setScreen(null);
            Minecraft.getInstance().pauseGame(false);
        }));

        clickGuiButton = addButton(new Button(centerX - buttonWidth / 2, startY + 24, buttonWidth, buttonHeight,
                new StringTextComponent("ClickGUI (Press INSERT)"), btn -> {
            Minecraft.getInstance().setScreen(new ClickGuiScreen());
        }));

        optionsButton = addButton(new Button(centerX - buttonWidth / 2, startY + 48, buttonWidth, buttonHeight,
                new StringTextComponent("Options"), btn -> {
            Minecraft.getInstance().setScreen(new net.minecraft.client.gui.screen.OptionsScreen(this, Minecraft.getInstance().options));
        }));

        disconnectButton = addButton(new Button(centerX - buttonWidth / 2, startY + 72, buttonWidth, buttonHeight,
                new StringTextComponent("Disconnect"), btn -> {
            Minecraft.getInstance().player.networkManager.disconnect(new StringTextComponent("Disconnected"));
            Minecraft.getInstance().setScreen(new CustomMainMenuScreen());
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        // Draw title
        drawCenteredString(matrixStack, font, "GAME PAUSED", width / 2, height / 4 - 24, 0xFF00FF);
        drawCenteredString(matrixStack, font, "LILOS CLIENT", width / 2, height / 4 - 12, 0xFFFFFF);
        
        // Draw info
        drawCenteredString(matrixStack, font, "Press INSERT for ClickGUI", width / 2, height / 4 + 100, 0x80808080);
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
