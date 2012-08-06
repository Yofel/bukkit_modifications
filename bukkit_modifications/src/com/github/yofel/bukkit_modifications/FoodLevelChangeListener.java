package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

public class FoodLevelChangeListener implements Listener {
    
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        
        Logger log = Bukkit.getLogger();
        
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            log.info("new: " + e.getFoodLevel());
            log.info("old: " + p.getFoodLevel());
            if (e.getFoodLevel() > p.getFoodLevel()) {
                if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                    log.info("material in hand: " + p.getItemInHand());
                    // as they're now stackable we need to make sure we don't use all up at once
                    ItemStack soup = p.getItemInHand();
                    
                    soup = new ItemStack(Material.MUSHROOM_SOUP, soup.getAmount() - 1);
                    int pos = p.getInventory().getHeldItemSlot();
                    log.info("pos: " + pos);
                    p.getInventory().setItem(pos, soup);
                    log.info("new inv:" + p.getInventory().getItem(pos));
                    p.getInventory().addItem(new ItemStack(Material.BOWL, 1));
                    
                    // FIXME: why do I end up with an empty hand?
                    
                }
            }
        }
        
    }

}
