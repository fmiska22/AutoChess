package fmiska22.autochess;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class ReloadShop implements Listener {
	
	@EventHandler
	public void OnButtonPress1(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
				ConfigManager ConfigManager;
				ConfigManager = new ConfigManager();
				ConfigManager.Arenas();
				for(int i=0;i<ConfigManager.getArenas().getInt("ArenaCount");i++) {
				ItemStack emerald = new ItemStack(Material.EMERALD, 2);
				
					
					if(e.getClickedBlock().getLocation().equals(main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getReroll(0))) {
						if(e.getPlayer().getInventory().containsAtLeast(emerald, 2)) {
							e.getPlayer().getInventory().removeItem(emerald);
							e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "You have refreshed your shop!");
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
							ArenaManager am = main.getPlugin(main.class).ArenaManager.get(Integer.toString(i));
							am.setReroll(null, 0);
							new BukkitRunnable() {
					            @Override
					            public void run() {
					            	am.setReroll(e.getClickedBlock().getLocation(), 0);
					            }
					        }.runTaskLater(main.getPlugin(main.class), 3*20);
						for(int j = 0;j<4;j++) {
							main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getshop(j).reloadShop();
						}
					}
					else {
							e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "You don't have enough emeralds!");
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						}
						}
					
					if(e.getClickedBlock().getLocation().equals(main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getReroll(1))) {
						if(e.getPlayer().getInventory().containsAtLeast(emerald, 2)) {
							e.getPlayer().getInventory().removeItem(emerald);
							e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "You have refreshed your shop!");
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
							ArenaManager am = main.getPlugin(main.class).ArenaManager.get(Integer.toString(i));
							am.setReroll(null, 1);
							new BukkitRunnable() {
					            @Override
					            public void run() {
					            	am.setReroll(e.getClickedBlock().getLocation(), 1);
					            }
					        }.runTaskLater(main.getPlugin(main.class), 3*20);
							for(int j = 4;j<8;j++) {
								main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getshop(j).reloadShop();
							}
						}
						else {
							e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "You don't have enough emeralds!");
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						}
						
					}
					
				
				
			}	
			}

		}
	}
}
