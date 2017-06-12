package fr.minuskube.mcdo;

import fr.minuskube.mcdo.listeners.InteractListener;
import fr.minuskube.mcdo.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class McDonald extends JavaPlugin {

    private static McDonald instance;

    private Config config;
    private List<Building> buildings = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        this.config = new Config(getConfig());
        this.config.load();

        this.buildings.addAll(config.getBuildings());
        this.buildings.forEach(Building::init);

        List<Listener> listeners = Arrays.asList(
                new JoinQuitListener(),
                new InteractListener()
        );

        PluginManager pm = Bukkit.getPluginManager();
        listeners.forEach(listener -> pm.registerEvents(listener, this));
    }

    @Override
    public void onDisable() {
        this.buildings.forEach(Building::disable);
        this.buildings = null;

        instance = null;
    }

    public List<Building> getBuildings() { return buildings; }
    public void registerBuilding(Building building) { this.buildings.add(building); }
    public void unregisterBuilding(Building building) { this.buildings.remove(building); }

    public Config config() { return config; }

    public static McDonald instance() { return instance; }

}