package com.github.yofel.bukkit_modifications;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Mods extends JavaPlugin {
    
    private final VehicleListener v = new VehicleListener();
    private final FurnaceBurnListener f = new FurnaceBurnListener();
    private final InventoryClickListener il = new InventoryClickListener();
    
    @Override
    public void onEnable() {
        getLogger().info("bukkit_mods enabled");
        getServer().getPluginManager().registerEvents(v, this);
        getServer().getPluginManager().registerEvents(f, this);
        getServer().getPluginManager().registerEvents(il, this);
        
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.FLINT), Material.GRAVEL));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.SAND), Material.DIRT));
        
    }

    @Override
    public void onDisable() {
        getLogger().info("bukkit_mods disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        
        // FIXME: something's broken with the command permissions I think
        if (cmd.getName().equalsIgnoreCase("mcspeed")) {
            
            if (args.length > 0)
            {
                try
                {
                    v.setSpeedMultiplier(Double.parseDouble(args[0]));
                    sender.sendMessage("Set speed multiplier to " + v.getSpeedMultiplier());
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