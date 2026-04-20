package com.lilos.client.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        registerModules();
    }

    private void registerModules() {
        modules.add(new FlyModule());
        modules.add(new ESPModule());
        modules.add(new KillAuraModule());
        modules.add(new SpeedModule());
        modules.add(new AutoClickerModule());
    }

    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
}
