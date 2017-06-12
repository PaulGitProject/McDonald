package fr.minuskube.mcdo.listeners;

import fr.minuskube.mcdo.McDonald;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        if(e.getHand() != EquipmentSlot.HAND)
            return;

        Player p = e.getPlayer();
        McDonald mcdo = McDonald.instance();

        mcdo.getBuildings().stream()
                .filter(building -> building.getVendor() == e.getRightClicked())
                .findFirst()
                .ifPresent(building -> {
                    e.setCancelled(true);

                    if(building.getFoodsLeft() <= 0) {
                        p.sendMessage(ChatColor.DARK_RED + "Oh no! " + ChatColor.RED + "There is no food left!");
                        p.sendMessage(ChatColor.GOLD + "Come back later...");

                        p.getWorld().playSound(building.getVendor().getLocation(),
                                Sound.ENTITY_VILLAGER_NO, 1, 1);

                        return;
                    }

                    if(!p.getInventory().contains(Material.EMERALD)) {
                        p.sendMessage(ChatColor.RED + "Eh! It's not free!"
                                + ChatColor.DARK_AQUA + " The cost of a big mac is 1 emerald.");

                        p.getWorld().playSound(building.getVendor().getLocation(),
                                Sound.ENTITY_VILLAGER_NO, 1, 1);

                        return;
                    }

                    p.getInventory().removeItem(new ItemStack(Material.EMERALD));
                    p.getInventory().addItem(new ItemStack(Material.BEETROOT_SOUP))
                            .forEach((index, item) -> p.getWorld().dropItemNaturally(p.getLocation(), item));

                    p.getWorld().playSound(building.getVendor().getLocation(),
                            Sound.ENTITY_VILLAGER_YES, 1, 1);

                    building.removeFoods(1);

                    p.sendMessage(ChatColor.DARK_GREEN + "Thanks for buying a big mac! "
                            + ChatColor.GREEN + "Foods left: " + building.getFoodsLeft());
                });
    }

}
