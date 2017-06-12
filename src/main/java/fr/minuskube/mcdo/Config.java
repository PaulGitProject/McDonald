package fr.minuskube.mcdo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private FileConfiguration config;

    private List<Building> buildings = new ArrayList<>();
    private int maxFoods;

    public Config(FileConfiguration config) {
        this.config = config;
    }

    public void load() {
        this.maxFoods = config.getInt("max-foods");

        ConfigurationSection secBuilds = config.getConfigurationSection("buildings");

        for(String key : secBuilds.getKeys(false)) {
            ConfigurationSection secBuild = secBuilds.getConfigurationSection(key);

            World world = Bukkit.getWorld(secBuild.getString("world"));
            double x = secBuild.getDouble("x");
            double y = secBuild.getDouble("y");
            double z = secBuild.getDouble("z");
            float yaw = (float) secBuild.getDouble("yaw");

            Building building = new Building(new Location(world, x, y, z, yaw, 0));
            buildings.add(building);
        }
    }

    public List<Building> getBuildings() { return buildings; }
    public int getMaxFoods() { return maxFoods; }

}
