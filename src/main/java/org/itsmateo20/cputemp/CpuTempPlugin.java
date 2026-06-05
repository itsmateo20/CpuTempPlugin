package org.itsmateo20.cputemp;

import org.bukkit.plugin.java.JavaPlugin;

public class CpuTempPlugin extends JavaPlugin {

    private CpuTempExpansion expansion;

    @Override
    public void onEnable() {
        // Double check PAPI is actually installed/enabled before registering
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.expansion = new CpuTempExpansion(this);
            this.expansion.register();
            getLogger().info("CpuTemp expansion registered successfully.");
        } else {
            getLogger().severe("PlaceholderAPI not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Clean up on plugin shutdown/reload to prevent memory leaks
        if (this.expansion != null) {
            this.expansion.unregister();
        }
        getLogger().info("CpuTemp disabled.");
    }
}