package com.lilos.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Hand;

public class AutoClickerModule extends Module {
    private int timer = 0;
    private final int delay = 5;

    public AutoClickerModule() {
        super("AutoClicker", "Automatically clicks when holding mouse button");
    }

    @Override
    public void onTick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && isEnabled()) {
            timer++;
            if (timer >= delay && mc.options.keyAttack.isDown()) {
                mc.gameMode.attack(mc.player, mc.player.getId());
                mc.player.swing(Hand.MAIN_HAND);
                timer = 0;
            }
        }
    }
}
