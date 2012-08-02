package com.github.yofel.bukkit_modifications;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Mods extends JavaPlugin {
    
    private final VehicleListener v = new VehicleListener();
    
    @Override
    public void onEnable() {
        getLogger().info("bukkit_mods 0.1 enabled");
        getServer().getPluginManager().registerEvents(v, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("bukkit_mods 0.1 disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        
        if (cmd.getName().equalsIgnoreCase("mcspeed")) {
            
            if (args.length > 0)
            {
                try
                {
                    v.setSpeedMultiplier(Double.parseDouble(args[0]));
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage("Invalid value!");
                }
            }
            else
            {
                sender.sendMessage("Current speed multiplier: " + v.getSpeedMultiplier());
            }
            
            
            return true;
        }

        return false;
    }

}