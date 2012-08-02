package com.github.yofel.bukkit_modifications;

import java.util.logging.Logger;

import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class VehicleListener implements Listener {
    
    Logger log = Logger.getLogger("Minecraft");
    
    private double speedMultiplier = 4;
    
    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    @EventHandler
    public void onVehicleCreation(VehicleCreateEvent e) {
        if (e.getVehicle() instanceof Minecart) {
            Minecart m = (Minecart) e.getVehicle();
            m.setMaxSpeed(0.4 * speedMultiplier);
            log.info("Mincart placed");
        }

    }

}
