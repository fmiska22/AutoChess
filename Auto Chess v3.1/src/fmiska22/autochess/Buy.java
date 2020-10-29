package fmiska22.autochess;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Buy implements Listener {

	@EventHandler
	public void OnButtonPress1(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
				ConfigManager ConfigManager;
				ConfigManager = new ConfigManager();
				ConfigManager.Arenas();
				for(int i=0;i<ConfigManager.getArenas().getInt("ArenaCount");i++) {
					for(int j=0;j<8;j++){
						if(e.getClickedBlock().getLocation().equals(ConfigManager.getArenas().getLocation("Arenas."+i+".Buttons.BuyButtons."+j))) {
							if(main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getshop(j).getID()!=-1) {
								main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getshop(j).buyIt(e.getPlayer());
							}
							else {
								e.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	        	   	            		   ChatColor.DARK_GRAY + "This shop is empty!");
								e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
							}
							
						}
					}
					
					
					
				
				
			}	
			}

		}
	}
}
