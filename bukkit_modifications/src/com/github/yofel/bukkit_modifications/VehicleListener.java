package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class VehicleListener implements Listener {
    
    Logger log = Logger.getLogger("Minecraft");
    
    @EventHandler
    public void onVehicleCreation(VehicleCreateEvent e) {
        if (e.getVehicle().getType() == EntityType.MINECART) {
            Minecart m = (Minecart) e.getVehicle();
            m.setMaxSpeed(0.8D);
            log.info("Mincart placed");
        }

    }

}
