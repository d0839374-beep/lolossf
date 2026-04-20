package com.lilos.client.gui;

import com.lilos.client.core.LilosClient;
import com.lilos.client.module.Module;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class ClickGuiScreen extends Screen {
    private int selectedModuleIndex = -1;
    private float scrollOffset = 0f;
    private final int moduleHeight = 25;
    private final int panelWidth = 200;
    private final int panelX = 50;
    private final int panelY = 50;

    public ClickGuiScreen() {
        super(new StringTextComponent("LILOS CLIENT - ClickGUI"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        // Draw main panel background
        fill(matrixStack, panelX, panelY, panelX + panelWidth, panelY + 300, 0x90000000);
        fill(matrixStack, panelX, panelY, panelX + panelWidth, panelY + 25, 0xFF1a1a2e);
        
        // Draw title
        drawCenteredString(matrixStack, font, "LILOS CLIENT", panelX + panelWidth / 2, panelY + 8, 0xFF00FF);
        drawCenteredString(matrixStack, font, "ClickGUI", panelX + panelWidth / 2, panelY + 16, 0xFFFFFF);
        
        // Draw module list
        int yOffset = 35;
        for (Module module : LilosClient.INSTANCE.moduleManager.getModules()) {
            int moduleY = panelY + yOffset - (int)scrollOffset;
            
            if (moduleY > panelY + 25 && moduleY < panelY + 300) {
                boolean isHovered = mouseX >= panelX && mouseX <= panelX + panelWidth &&
                                   mouseY >= moduleY && mouseY <= moduleY + moduleHeight;
                
                int bgColor = isHovered ? 0x40000000 : 0x20000000;
                if (module.isEnabled()) {
                    bgColor = isHovered ? 0x40FF00FF : 0x20FF00FF;
                }
                
                fill(matrixStack, panelX + 5, moduleY, panelX + panelWidth - 5, moduleY + moduleHeight - 2, bgColor);
                
                String moduleName = module.getName();
                int textColor = module.isEnabled() ? 0xFF00FF : 0xFFFFFF;
                drawString(matrixStack, font, moduleName, panelX + 15, moduleY + 8, textColor);
                
                String status = module.isEnabled() ? "[ON]" : "[OFF]";
                drawString(matrixStack, font, status, panelX + panelWidth - 45, moduleY + 8, 
                          module.isEnabled() ? 0x00FF00 : 0xFF0000);
            }
            
            yOffset += moduleHeight;
        }
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int yOffset = 35;
        int index = 0;
        
        for (Module module : LilosClient.INSTANCE.moduleManager.getModules()) {
            int moduleY = panelY + yOffset - (int)scrollOffset;
            
            if (mouseX >= panelX && mouseX <= panelX + panelWidth &&
                mouseY >= moduleY && mouseY <= moduleY + moduleHeight) {
                if (button == 0) {
                    module.toggle();
                }
                selectedModuleIndex = index;
                return true;
            }
            
            yOffset += moduleHeight;
            index++;
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        scrollOffset -= delta * 10;
        scrollOffset = Math.max(0, Math.min(scrollOffset, 
            LilosClient.INSTANCE.moduleManager.getModules().size() * moduleHeight - 265));
        return true;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(null);
    }
}
