package fr.minuskube.mcdo.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(ChatColor.GOLD + p.getName()
                + ChatColor.YELLOW + " joined the game, he seems very hungry!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(ChatColor.RED + "Ohhh... " + ChatColor.DARK_RED + p.getName()
                + ChatColor.RED + " left our world, he was no more hungry.");
    }

}
