package org.itsmateo20.cputemp;

import org.bukkit.plugin.java.JavaPlugin;

public class CpuTempPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new CpuTempExpansion(this).register();
        getLogger().info("CpuTemp enabled");
    }
}