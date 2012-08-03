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
        
        /*
        log.info("Furnace activated with " + event.getFuel().toString());
        
        log.info("Original Burn time: " + event.getBurnTime());
        */
        try {
            
            Furnace furnaceBlock = (Furnace) event.getBlock().getState();

            // Copyright ArmEagle on github
            if (event.getFuel().getType() == Material.LAVA_BUCKET) {
                    org.bukkit.material.Furnace furnace = (org.bukkit.material.Furnace) furnaceBlock
                            .getData();
    
                    Location front = furnaceBlock.getBlock()
                            .getRelative(furnace.getFacing()).getLocation();
                    furnaceBlock.getWorld().dropItem(front,
                            new ItemStack(Material.BUCKET));
            }
            
            // add custom fuels
            else if (event.getFuel().getType() == Material.SEEDS) {
                furnaceBlock.setBurnTime((short) 50);
                // FIXME: the burndown is buggy if the burn time is < 200
            }
            
            else if (event.getFuel().getType() == Material.SULPHUR) {
                furnaceBlock.setBurnTime((short) 200);
                // GUNPOWDER
            
            }
        
        } catch (Exception e) {
            // ignore, furnace probably was no furnace anymore.
        }
    }

}
