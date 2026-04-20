package com.lilos.client.core;

import com.lilos.client.hud.HUDRenderer;
import com.lilos.client.module.ModuleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("lilosclient")
public class LilosClient {
    public static final String MOD_ID = "lilosclient";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    public static LilosClient INSTANCE;
    public ModuleManager moduleManager;
    public HUDRenderer hudRenderer;
    
    private long lastFrameTime = 0;
    public int currentFPS = 0;
    private int frameCount = 0;
    private long fpsUpdateTime = 0;

    public LilosClient() {
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        
        this.moduleManager = new ModuleManager();
        this.hudRenderer = new HUDRenderer();
        
        LOGGER.info("LILOS CLIENT initialized!");
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Client setup complete");
            MinecraftForgeClient.registerRenderType(null); // Placeholder for custom render types
        });
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            updateFPS();
            moduleManager.onTick();
        }
    }

    private void updateFPS() {
        long currentTime = System.currentTimeMillis();
        frameCount++;
        
        if (currentTime - fpsUpdateTime >= 1000L) {
            currentFPS = frameCount;
            frameCount = 0;
            fpsUpdateTime = currentTime;
        }
        
        lastFrameTime = currentTime;
    }

    public int getFPS() {
        return currentFPS;
    }

    public long getLastFrameTime() {
        return lastFrameTime;
    }
}
