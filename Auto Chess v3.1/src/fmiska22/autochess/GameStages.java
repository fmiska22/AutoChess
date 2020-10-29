package fmiska22.autochess;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;



public class GameStages {
	
	private static main plugin = main.getPlugin(main.class);
	public static void prepStage(int aid) {
		Player Red = plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getRedUUID());
		Player Green = plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getGreenUUID());
		if(plugin.ArenaManager.get(Integer.toString(aid)).getStage()==1) {
			for(int j = 0;j<8;j++) {
				main.getPlugin(main.class).ArenaManager.get(Integer.toString(aid)).getshop(j).reloadShop();
			}
			ConfigManager ConfigManager;
			ConfigManager = new ConfigManager();
			ConfigManager.Arenas();
			Red.getPlayer().getInventory().clear();
			Red.getPlayer().setHealth(20);
			Red.getPlayer().setExp(0);
			Red.getPlayer().setLevel(1);
			Red.teleport(ConfigManager.Arenas.getLocation("Arenas."+aid+".GameSpawn.1"));
			Green.getPlayer().getInventory().clear();
			Green.getPlayer().setHealth(20);
			Green.getPlayer().setExp(0);
			Green.getPlayer().setLevel(1);
			Green.teleport(ConfigManager.Arenas.getLocation("Arenas."+aid+".GameSpawn.0"));
		}
		else {
    		Red.getPlayer().giveExp(4);
    		Green.getPlayer().giveExp(4);
		}
		ItemStack emerald = new ItemStack(Material.EMERALD, 5);
		Red.getPlayer().getInventory().addItem(emerald);
		Green.getPlayer().getInventory().addItem(emerald);
		SpawnMethod.spawnOnArena(aid);
		plugin.ArenaManager.get(Integer.toString(aid)).setCanSpawn(true);
		setBlock(Material.OAK_FENCE, aid); 
			new BukkitRunnable() {
	            @Override
	            public void run() {
	            	if(plugin.ArenaManager.get(Integer.toString(aid)).getStage()!=-1) {
	            		CountDown.startCountDown(Red);
	            		CountDown.startCountDown(Green);
	            		new BukkitRunnable() {
	                        @Override
	                        public void run() {
	                        	fightStage(aid);
	    		            	plugin.ArenaManager.get(Integer.toString(aid)).setStage(plugin.ArenaManager.get(Integer.toBinaryString(aid)).getStage()+1);
	                        	
	                        }
	                    }.runTaskLater(main.getPlugin(main.class), 3*20);
	            		
	            	}
	            	else {
	            		plugin.ArenaManager.get(Integer.toString(aid)).resetArena();
	            	}
	            	
	            }
	        }.runTaskLater(main.getPlugin(main.class), 20*20);
		
		
	}
	public static void fightStage(int aid) {
		
		plugin.ArenaManager.get(Integer.toString(aid)).setCanSpawn(false);
		setBlock(Material.AIR, aid);   
		new BukkitRunnable() {
            @Override
            public void run() {
            	if(plugin.ArenaManager.get(Integer.toString(aid)).getStage()!=-1) {
            		if(checkIfOver(aid)) {
                		deleteUnits(aid);
                	}
                	else {
                		checkAgain(aid);
                	}
            	
            }
            	else {
            		plugin.ArenaManager.get(Integer.toString(aid)).resetArena();
            	}
            	
   
            }
        }.runTaskLater(main.getPlugin(main.class), 3*20);
	}
	public static void checkAgain(int aid) {
		new BukkitRunnable() {
            @Override
            public void run() {
            	if(plugin.ArenaManager.get(Integer.toString(aid)).getStage()!=-1) {
            		if(checkIfOver(aid)) {
                		deleteUnits(aid);
                	}
                	else {
                		checkAgain(aid);
                	}
            }
            	else {
            		plugin.ArenaManager.get(Integer.toString(aid)).resetArena();
            	}
            	
   
            }
        }.runTaskLater(main.getPlugin(main.class), 3*20);
		
	}
	public static void setBlock(Material mat, int aid) {
		ConfigManager ConfigManager;
		ConfigManager = new ConfigManager();
		ConfigManager.Arenas();
		Location loc1 = ConfigManager.getArenas().getLocation("Arenas."+aid+".Fence."+0);
		Location loc2 = ConfigManager.getArenas().getLocation("Arenas."+aid+".Fence."+1);
		for (Double x = loc1.getX(); x <= loc2.getX(); x++) {
			for (Double y = loc1.getY(); y <= loc2.getY(); y++) {
				for (Double z = loc1.getZ(); z <= loc2.getZ(); z++) {
					 Location lll = new Location(loc1.getWorld(), x.intValue() , y.intValue() , z.intValue());
					 loc1.getWorld().getBlockAt(lll).setType(mat);
				}
			}
            
		}
	}
	public static void deleteUnits(int aid) {
		int green=0;
		int red=0;
		for(Entity ent : plugin.ArenaManager.get(Integer.toString(aid)).getSlot(0).getLocation().getWorld().getEntities()) {
			if(ent instanceof Creature) {
				
				Double x = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(7).getLocation().getX();
				Double z = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(7).getLocation().getZ();
				Double x1 = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(23).getLocation().getX();
				Double z1 = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(23).getLocation().getZ();
				Double entx = ent.getLocation().getX();
				Double entz = ent.getLocation().getZ();
				
				if(	x+1.0>entx) {
					if(z+1.0>entz) {
						if(x1-1.0<entx) {
							if(z1-1.0<entz) {
								
								if(ent.getCustomName().contains("§a")) {
									green++;
								}
								else if(ent.getCustomName().contains("§c")){
									red++;
								}
								
								ent.remove();
								
							}
						}
					}
					
				}
				
	
			}
		}
		
		if(green>=red) {
			new BukkitRunnable() {
	            @Override
	            public void run() {        
	            	plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getRedUUID()).damage(1.0);
	   
	            }
	        }.runTaskLater(main.getPlugin(main.class), 10);
			
		}
		if(red>=green) {
			new BukkitRunnable() {
	            @Override
	            public void run() {        
	            	
	            	plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getGreenUUID()).damage(1.0);
	            }
	        }.runTaskLater(main.getPlugin(main.class), 10);
			
		}
		
		if(plugin.ArenaManager.get(Integer.toString(aid)).getStage()!=-1) {
			Player Red = plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getRedUUID());
			Player Green = plugin.getServer().getPlayer(plugin.ArenaManager.get(Integer.toString(aid)).getGreenUUID());
    		Red.getPlayer().giveExp(4);
    		Green.getPlayer().giveExp(4);
    		prepStage(aid);
        	plugin.ArenaManager.get(Integer.toString(aid)).setStage(plugin.ArenaManager.get(Integer.toBinaryString(aid)).getStage()+1);
                	
    	
    	}
	}
	public static boolean checkIfOver(int aid) {
		int green=0;
		int red=0;
		for(Entity ent : plugin.ArenaManager.get(Integer.toString(aid)).getSlot(0).getLocation().getWorld().getEntities()) {
			if(ent instanceof Creature) {
				
				Double x = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(7).getLocation().getX();
				Double z = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(7).getLocation().getZ();
				Double x1 = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(23).getLocation().getX();
				Double z1 = plugin.ArenaManager.get(Integer.toString(aid)).getSlot(23).getLocation().getZ();
				Double entx = ent.getLocation().getX();
				Double entz = ent.getLocation().getZ();
				
				if(	x+1.0>entx) {
					if(z+1.0>entz) {
						if(x1-1.0<entx) {
							if(z1-1.0<entz) {
								
								if(ent.getCustomName().contains("§a")) {
									green++;
								}
								else if(ent.getCustomName().contains("§c")){
									red++;
								}
								
								
								
							}
						}
					}
					
				}
				
				
	
			}
		}
		if(red==0) {
			return true;
		}
		else if(green==0) {
			return true;
		}
		else {
			return false;
		}
	}

}
