package com.github.yofel.bukkit_modifications;

import org.bukkit.plugin.java.JavaPlugin;

public class Mods extends JavaPlugin {
    @Override
    public void onEnable() {
	getLogger().info("bukkit_mods 0.1 enabled");
	getServer().getPluginManager().registerEvents(new VehicleListener(),
		this);
    }

    @Override
    public void onDisable() {
	getLogger().info("bukkit_mods 0.1 disabled");
    }

}