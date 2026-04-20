package com.lilos.client.mixin;

import com.lilos.client.gui.CustomPauseScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.PauseScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class MixinPauseScreen {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(boolean bl, CallbackInfo ci) {
        // This mixin can be used to modify the pause screen behavior
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(com.mojang.blaze3d.matrix.MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        // Custom rendering for pause screen
    }
}
