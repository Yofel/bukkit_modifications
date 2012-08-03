package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class FurnaceBurnListener implements Listener {
    
    Logger log = Logger.getLogger("Minecraft");

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        
        log.info("Furnace activated with " + event.getFuel().toString());

        // Copyright ArmEagle on github
        if (event.getFuel().getType() == Material.LAVA_BUCKET) {
            try {
                Furnace furnaceBlock = (Furnace) event.getBlock().getState();
                org.bukkit.material.Furnace furnace = (org.bukkit.material.Furnace) furnaceBlock
                        .getData();

                Location front = furnaceBlock.getBlock()
                        .getRelative(furnace.getFacing()).getLocation();
                furnaceBlock.getWorld().dropItem(front,
                        new ItemStack(Material.BUCKET));
            } catch (Exception e) {
                // ignore, furnace probably was no furnace anymore.
            }
        }
        
        // add custom fuels
        else if (event.getFuel().getType() == Material.SEEDS) {
        }
    }

}
