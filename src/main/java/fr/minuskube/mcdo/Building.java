package fr.minuskube.mcdo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Illusioner;

public class Building {

    private Location location;
    private Illusioner vendor;

    private int foodsLeft;

    public Building(Location location) {
        this.location = location;
        this.foodsLeft = McDonald.instance().config().getMaxFoods();
    }

    public void init() {
        spawnVendor();
    }

    public void disable() {
        this.vendor.remove();
    }

    private void spawnVendor() {
        this.vendor = location.getWorld().spawn(location, Illusioner.class, vendor -> {
            vendor.setCustomName(ChatColor.LIGHT_PURPLE + "McDonald: " + ChatColor.GOLD + foodsLeft
                    + ChatColor.YELLOW + " foods left");

            vendor.setCustomNameVisible(true);
            vendor.setGlowing(true);
            vendor.setAI(false);
            vendor.setInvulnerable(true);
        });
    }

    private void updateFoodsLeft() {
        vendor.setCustomName(ChatColor.LIGHT_PURPLE + "McDonald: " + ChatColor.GOLD + foodsLeft
                + ChatColor.YELLOW + " foods left");
    }

    public Location getLocation() { return location.clone(); }
    public Illusioner getVendor() { return vendor; }

    public int getFoodsLeft() { return foodsLeft; }

    public void addFoods(int foods) {
        this.foodsLeft += foods;
        updateFoodsLeft();
    }

    public void removeFoods(int foods) {
        this.foodsLeft -= foods;
        updateFoodsLeft();
    }

}
