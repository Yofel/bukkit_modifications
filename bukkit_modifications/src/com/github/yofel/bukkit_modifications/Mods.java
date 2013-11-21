package com.github.yofel.bukkit_modifications;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Mods extends JavaPlugin {
    
    private final VehicleListener v = new VehicleListener();
    
    @Override
    public void onEnable() {
        getLogger().info("bukkit_mods enabled");
        getServer().getPluginManager().registerEvents(v, this);
        getServer().getPluginManager().registerEvents(new FurnaceBurnListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        //getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        
        
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.FLINT), Material.GRAVEL));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.SAND), Material.DIRT));

        // recycling
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.GOLD_INGOT, 1), Material.GOLD_HELMET));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.GOLD_INGOT, 2), Material.GOLD_CHESTPLATE));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.GOLD_INGOT, 2), Material.GOLD_LEGGINGS));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.GOLD_INGOT, 1), Material.GOLD_BOOTS));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 1), Material.IRON_HELMET));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 2), Material.IRON_CHESTPLATE));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 2), Material.IRON_LEGGINGS));
        getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 1), Material.IRON_BOOTS));

        // coal blocks to diamond
        ShapedRecipe cb2d = new ShapedRecipe(new ItemStack(Material.DIAMOND, 1));
        cb2d.shape("CCC", "CCC", "CCC");
        cb2d.setIngredient('C', Material.COAL_BLOCK);

        getServer().addRecipe(cb2d);

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