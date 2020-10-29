package fmiska22.autochess;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;




public class SpawnIn implements Listener {
	private main plugin = main.getPlugin(main.class);
	@EventHandler
	public void OnButtonPress1(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
				ConfigManager ConfigManager;
				ConfigManager = new ConfigManager();
				ConfigManager.Arenas();
				ConfigManager.Units();
				for(int i=0;i<ConfigManager.Arenas.getInt("ArenaCount");i++) {
					if(plugin.ArenaManager.get(Integer.toString(i)).canSpawn()) {
						for(int j=0;j<26;j++) {
						if(e.getClickedBlock().getX()==ConfigManager.Arenas.getLocation("Arenas."+i+".Buttons.SpawnButtons."+j).getBlockX()) {
							if(e.getClickedBlock().getY()==ConfigManager.Arenas.getLocation("Arenas."+i+".Buttons.SpawnButtons."+j).getBlockY()) {
								if(e.getClickedBlock().getZ()==ConfigManager.Arenas.getLocation("Arenas."+i+".Buttons.SpawnButtons."+j).getBlockZ()) {
									if(e.getPlayer().getInventory().getItemInMainHand().equals(null)||e.getPlayer().getInventory().getItemInMainHand().getType()==Material.AIR) {
										
										if(!plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getUnitname().equals("null")) {
											ItemStack item = new ItemStack(Material.SLIME_BALL,1);
											ItemMeta im = item.getItemMeta();
											im.setDisplayName("§9"+plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getUnitname());
											ArrayList<String> lore = new ArrayList<String>();
											lore.add("§8lvl.:§6"+plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getLvl());
											im.setLore(lore);
											item.setItemMeta(im);
											e.getPlayer().getInventory().addItem(item);
											plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).setUnitname("null");
											plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).setID(-1);
											if(plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getColor()==true) {
												plugin.ArenaManager.get(Integer.toString(i)).removeGreencount();
											}
											else {
												plugin.ArenaManager.get(Integer.toString(i)).removeRedcount();
											}
										for(Entity ent : e.getPlayer().getWorld().getEntities()) {
												if(ent instanceof Creature) {
													Double x = plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getLocation().getX();
													Double z = plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getLocation().getZ();
													Double entx = ent.getLocation().getX();
													Double entz = ent.getLocation().getZ();

													if(	x+1.0>entx) {
														if(x-1.0<entx) {
															if(z+1.0>entz) {
																if(z-1.0<entz) {
																	ent.remove();
																	
																}
															}
														}
														
													}

												}
											}
										}
										
									}
									else if(e.getPlayer().getInventory().getItemInMainHand().getType()==Material.SLIME_BALL&&e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()&&
											e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
										int count;
										if(plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getColor()==true) {
											count= plugin.ArenaManager.get(Integer.toString(i)).getGreencount();
										}
										else {
											count= plugin.ArenaManager.get(Integer.toString(i)).getRedcount();
										}
										int lvl=1;
										if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§8lvl.:§62")) {
											lvl=2;
										}
										else if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§8lvl.:§63")) {
											lvl=3;
										}
										if(e.getPlayer().getLevel()>count) {
											if(plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getUnitname().equals("null")) {
												for(String key : ConfigManager.getUnits().getConfigurationSection("Units").getKeys(false)) {
													if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ConfigManager.getUnits().getString("Units."+key+".Name"))){
														addCount(plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getColor(), i);
														plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).setLvl(lvl);
														plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).setUnitname(ConfigManager.getUnits().getString("Units."+key+".Name"));
														plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).setID(Integer.parseInt(key));
														
														if(plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getColor()) {
															SpawnMethod.spawnGreenUnit(Integer.parseInt(key),plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getLocation(),lvl);
														}
														else {
															SpawnMethod.spawnRedUnit(Integer.parseInt(key),plugin.ArenaManager.get(Integer.toString(i)).getSlot(j).getLocation(),lvl);
														}
														e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
														e.setCancelled(true);
														return;
													}
												}
											}
										}
										else {
											e.getPlayer().sendMessage("§7[§cAutoChess§7]§aYou can't place more units!");
										}
										
											
									
									}
									
								}
							}
						}
							
						
							
					}
					
				
			
						}	
					}
			}
			}
		}

		
	
	public void addCount(boolean color,int aid) {
		if(color==true) {
			plugin.ArenaManager.get(Integer.toString(aid)).addGreencount();
		}
		else {
			plugin.ArenaManager.get(Integer.toString(aid)).addRedcount();
		}
	}
}
