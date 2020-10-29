package fmiska22.autochess;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown {

	public static void startCountDown(Player p) {
		p.sendTitle("§93","", 2,20, 2);
		new BukkitRunnable() {
            @Override
            public void run() {
            	p.sendTitle("§92","", 2,20, 2);
            	new BukkitRunnable() {
                    @Override
                    public void run() {
                    	p.sendTitle("§91","", 2,20, 2);
                    	
                    }
                }.runTaskLater(main.getPlugin(main.class), 1*20);
            }
        }.runTaskLater(main.getPlugin(main.class), 1*20);
	}
	
}
