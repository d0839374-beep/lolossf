package com.lilos.client.mixin;

import com.lilos.client.gui.CustomMainMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        // This mixin can be used to modify the title screen behavior
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(com.mojang.blaze3d.matrix.MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        // Optional: Replace title screen with custom one when needed
        // Minecraft.getInstance().setScreen(new CustomMainMenuScreen());
        // ci.cancel();
    }
}
