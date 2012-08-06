package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    
    Logger log = Bukkit.getLogger();
    
    @EventHandler
    public boolean onInventoryClick(InventoryClickEvent e) {
        
        // FIXME: so far it's only handling direct clicks
        if (! e.isShiftClick()) {
            
            if (e.getCursor().getType() != Material.AIR
                    && e.getCurrentItem().getType() != Material.AIR
                    && e.getCurrentItem().getData().equals(e.getCurrentItem().getData())
                    && e.getCurrentItem().getDurability() == e.getCursor().getDurability()) {
                
                if (e.getCurrentItem().getType() == Material.POTION
                        || e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
                    
                    int newAmountInInventory = e.getCurrentItem().getAmount() +
                            e.getCursor().getAmount();
                    if (newAmountInInventory > 64) newAmountInInventory = 64;
                    
                    int newAmountInHand = e.getCursor().getAmount() -
                           (newAmountInInventory - e.getCurrentItem().getAmount());
                    
                    if (newAmountInInventory < 0) log.warning("amount < 0, this shouldn't happen!");
                    
                    e.setCurrentItem(new ItemStack(e.getCurrentItem().getType(),
                            newAmountInInventory,
                            e.getCurrentItem().getDurability()));
                    e.setCursor(new ItemStack(e.getCursor().getType(),
                            newAmountInHand,
                            e.getCursor().getDurability()));
                    
                    e.setCancelled(true);
                    
                    return true;
                }
            }
        }
        else {
            // cursor is AIR
            log.info("Current data: " + e.getCurrentItem().getData());
            log.info("Current durability: " + e.getCurrentItem().getDurability());
            
            // TODO: look through every slot and try to fill them up
        }
        
        return false;
    }

}
