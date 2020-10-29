package fmiska22.autochess;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;




public class Events implements Listener{

	
	main plugin = main.getPlugin(main.class);
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		plugin.PlayerManager.put(e.getPlayer().getUniqueId(), new PlayerManager(e.getPlayer().getUniqueId(), false, 0, false));
		
	}
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		if(plugin.PlayerManager.containsKey(e.getEntity().getUniqueId())) {
			if(plugin.PlayerManager.get(e.getEntity().getUniqueId()).ifIngame()){
				plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(e.getEntity().getPlayer().getUniqueId()).getArenaid())).setStage(-1);
				plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(e.getEntity().getUniqueId()).getArenaid())).setLooser(e.getEntity().getUniqueId());
				
			}
		}
		
		
	}
	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e) {
		if(plugin.PlayerManager.containsKey(e.getPlayer().getUniqueId())) {
			if(plugin.PlayerManager.get(e.getPlayer().getUniqueId()).ifIngame()) {
				plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(e.getPlayer().getUniqueId()).getArenaid())).setStage(-1);
				e.getPlayer().getInventory().clear();
				plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(e.getPlayer().getUniqueId()).getArenaid())).setLooser(e.getPlayer().getUniqueId());
			}
			if(plugin.PlayerManager.get(e.getPlayer().getUniqueId()).ifInlobby()) {
				plugin.PlayerManager.get(e.getPlayer().getUniqueId()).setInlobby(false);
			}
		}
		
		
	}
}
