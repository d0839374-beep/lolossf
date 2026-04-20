package com.lilos.client.module;

public abstract class Module {
    private final String name;
    private final String description;
    private boolean enabled;
    private int bind;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.enabled = false;
        this.bind = -1;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }
}
