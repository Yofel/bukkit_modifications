package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;

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

            /*
            if (event.getFuel().getType() == Material.LAVA_BUCKET) {
            }
            */
            
            // add custom fuels
            if (event.getFuel().getType() == Material.SEEDS) {
                // FIXME: the burndown is buggy if the burn time is < 200
                furnaceBlock.setBurnTime((short) 25);
            }
            
            if (event.getFuel().getType() == Material.SULPHUR) {
                // GUNPOWDER
                furnaceBlock.setBurnTime((short) 200);
            }
            
            if (event.getFuel().getType() == Material.WHEAT) {
                furnaceBlock.setBurnTime((short) 100);
            }
            
            if (event.getFuel().getType() == Material.SUGAR_CANE) {
                furnaceBlock.setBurnTime((short) 100);
            }
            
            if (event.getFuel().getType() == Material.WOODEN_DOOR) {
                // make it equal 4 blocks of wood
                furnaceBlock.setBurnTime((short) 1200);
            }
            
            if (event.getFuel().getType() == Material.VINE) {
                furnaceBlock.setBurnTime((short) 100);
            }
            
            if (event.getFuel().getType() == Material.LEAVES) {
                furnaceBlock.setBurnTime((short) 50);
            }
            
            if (event.getFuel().getType() == Material.WOOL) {
                furnaceBlock.setBurnTime((short) 100);
            }
            
            if (event.getFuel().getType() == Material.FIREBALL) {
                // equals one piece of coal
                // that's too much, but 800 is too low and everything in between
                // has 64 % x != 0
                furnaceBlock.setBurnTime((short) 1600);
            }
            
        
        } catch (Exception e) {
            // ignore, furnace probably was no furnace anymore.
        }
    }

}
