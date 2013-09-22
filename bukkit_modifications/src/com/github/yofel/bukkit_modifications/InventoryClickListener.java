package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    Logger log = Bukkit.getLogger();
    
    private int maxPotionStackSize = 8;

    @EventHandler
    public boolean onInventoryClick(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCursor() == null) return false;
        
        if (e.getCurrentItem().getType() == Material.POTION) {
                //|| e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
            if (!e.isShiftClick()) {

                if (e.getCursor().getType() != Material.AIR
                        && e.getCurrentItem().getType() != Material.AIR
                        && e.getCurrentItem().getData()
                                .equals(e.getCursor().getData())
                        && e.getCurrentItem().getDurability() == e.getCursor()
                                .getDurability()) {

                    int newAmountInInventory;
                    if (e.isLeftClick()) {
                        newAmountInInventory = e.getCurrentItem().getAmount()
                                + e.getCursor().getAmount();
                    } else {
                        newAmountInInventory = e.getCurrentItem().getAmount() + 1;
                    }
                    if (newAmountInInventory > maxPotionStackSize) {
                        newAmountInInventory = maxPotionStackSize;
                        if (e.isRightClick()) {
                            e.setCancelled(true);
                            return true;
                        }
                    }

                    int newAmountInHand;
                    if (e.isLeftClick()) {
                        newAmountInHand = e.getCursor().getAmount()
                                - (newAmountInInventory - e.getCurrentItem()
                                        .getAmount());
                    } else {
                        newAmountInHand = e.getCursor().getAmount() - 1;
                        
                    }

                    if (newAmountInHand < 0) log.warning("amount < 0, this shouldn't happen!");

                    e.getWhoClicked().setItemOnCursor(new ItemStack(
                            e.getCurrentItem().getType(), newAmountInInventory,
                            e.getCurrentItem().getDurability()));
                    e.getWhoClicked().setItemInHand(new ItemStack(e.getCurrentItem().getType(),
                            newAmountInHand, e.getCursor().getDurability()));

                    e.setCancelled(true);

                    return true;
                }
            } else {
                // FIXME: BROKEN
                /*
                // only do this where it makes sense
                switch (e.getInventory().getType()) {
                case CHEST:
                case DISPENSER:
                case PLAYER:
                case ENDER_CHEST:
                case BREWING:
                case MERCHANT:
                case CRAFTING:
                    ItemStack is = e.getCurrentItem();
                    Inventory inv = e.getInventory();
                    log.info("itemstack: " + is);

                    for (int i = 0; i < inv.getSize(); i++) {
                        ItemStack item = inv.getItem(i);
                        log.info("Slot " + i + " has: " + item);
                        
                        if (item != null && item.getType() == is.getType()
                                && item.getDurability() == is.getDurability()
                                && item.getAmount() < 64) {
                            
                            int remainingAmount = 64 - item.getAmount();
                            log.info("current amount:" + is.getAmount());
                            log.info("remaining amount: " + remainingAmount);
                            
                            if (remainingAmount >= is.getAmount()) {
                                inv.setItem(i, new ItemStack(is.getType(),
                                        item.getAmount() + is.getAmount(),
                                        is.getDurability()));
                                e.setCurrentItem(null);
                                return true;
                            } 
                            inv.setItem(i, new ItemStack(is.getType(),
                                    64,
                                    is.getDurability()));
                            is = new ItemStack(is.getType(),
                                    is.getAmount() - remainingAmount,
                                    is.getDurability());

                        }

                    }
                    
                    // as all partly filled slots are now full try to take a new one
                    log.info("first empty: " + inv.firstEmpty());
                    if (inv.firstEmpty() >= 0)
                    {
                        inv.setItem(inv.firstEmpty(), is);
                        is = null;
                    }
                    
                    // all full so leave the rest where it is
                    e.setCurrentItem(is);
                    return true;
                }
                */

            }
        }

        return true;
    }

}
