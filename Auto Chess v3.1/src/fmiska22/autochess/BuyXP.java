package fmiska22.autochess;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class BuyXP implements Listener {
	
	@EventHandler
	public void OnButtonPress1(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
				ConfigManager ConfigManager;
				ConfigManager = new ConfigManager();
				ConfigManager.Arenas();
				for(int i=0;i<ConfigManager.getArenas().getInt("ArenaCount");i++) {
				ItemStack emerald = new ItemStack(Material.EMERALD, 1);
				if(e.getClickedBlock().getLocation().equals(ConfigManager.getArenas().getLocation("Arenas."+i+".Buttons.XpButtons.0"))||e.getClickedBlock().getLocation().equals(ConfigManager.getArenas().getLocation("Arenas."+i+".Buttons.XpButtons.1"))) {
					if(e.getPlayer().getInventory().containsAtLeast(emerald, 1)) {
					e.getPlayer().getInventory().removeItem(emerald);
					e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "Congratulations! You have successfully bought 4 xp!");
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getPlayer().giveExp(4);
					}
					else {
						e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.RED + "You don't have enough emerald!");
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					}
				}
			}	
			}

		}
	}
}
