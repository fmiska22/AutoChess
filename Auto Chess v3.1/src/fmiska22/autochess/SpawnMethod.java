package fmiska22.autochess;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;


import fmiska22.autochess.converters.UnitType2EntityTypes;
import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.WorldServer;

public class SpawnMethod {
	
	private static main plugin = main.getPlugin(main.class);
	
	public static void spawnShop(int ID, Location loc) {
		ConfigManager ConfigManager;
		ConfigManager = new ConfigManager();
		ConfigManager.Units();
		EntityTypes<? extends EntityCreature> UnitTypes = UnitType2EntityTypes.Convert(ConfigManager.getUnits().getString("Units."+ID+".Type"));
		String ItemName = ConfigManager.getUnits().getString("Units."+ID+".ItemName");
		Location spawnloc = new Location(loc.getWorld(), loc.getX()+0.5, loc.getY()+1.0, loc.getZ()+0.5);
		UnitManager UnitManager = new UnitManager(UnitTypes, spawnloc, 0, ItemName);
		UnitManager.setShopUnitName(ConfigManager.getUnits().getString("Units."+ID+".Name"));
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
	 	world.addEntity(UnitManager);
	}
	public static void spawnGreenUnit(int ID, Location loc,int lvl) {
		ConfigManager ConfigManager;
		ConfigManager = new ConfigManager();
		ConfigManager.Units();
		EntityTypes<? extends EntityCreature> UnitTypes = UnitType2EntityTypes.Convert(ConfigManager.getUnits().getString("Units."+ID+".Type"));
		String ItemName = ConfigManager.getUnits().getString("Units."+ID+".ItemName");
		GreenUnit GreenUnit = new GreenUnit(UnitTypes, loc, lvl, ItemName);
		GreenUnit.setUnitName(ConfigManager.getUnits().getString("Units."+ID+".Name"));
		GreenUnit.setDmg(ConfigManager.getUnits().getInt("Units."+ID+".Lvl"+lvl+".Dmg"));
		GreenUnit.setHealth(ConfigManager.getUnits().getInt("Units."+ID+".Lvl"+lvl+".Health"));
	 	WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
	 	world.addEntity(GreenUnit);
	}
	public static void spawnRedUnit(int ID, Location loc,int lvl) {
		ConfigManager ConfigManager;
		ConfigManager = new ConfigManager();
		ConfigManager.Units();
		EntityTypes<? extends EntityCreature> UnitTypes = UnitType2EntityTypes.Convert(ConfigManager.getUnits().getString("Units."+ID+".Type"));
		String ItemName = ConfigManager.getUnits().getString("Units."+ID+".ItemName");
		RedUnit RedUnit = new RedUnit(UnitTypes, loc, lvl, ItemName);
		RedUnit.setUnitName(ConfigManager.getUnits().getString("Units."+ID+".Name"));
		RedUnit.setDmg(ConfigManager.getUnits().getInt("Units."+ID+".Lvl"+lvl+".Dmg"));
		RedUnit.setHealth(ConfigManager.getUnits().getInt("Units."+ID+".Lvl"+lvl+".Health"));
	 	WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
	 	world.addEntity(RedUnit);
		
	}
	public static void spawnOnSlot(int aid, int slotid) {
		if(!main.getPlugin(main.class).ArenaManager.get(Integer.toString(aid)).getSlot(slotid).equals(null)) {
			SlotManager slot = main.getPlugin(main.class).ArenaManager.get(Integer.toString(aid)).getSlot(slotid);
			
			for(Entity ent : plugin.ArenaManager.get(Integer.toString(aid)).getSlot(slotid).getLocation().getWorld().getEntities()) {
				if(ent instanceof Creature){
					
					if(ent.getLocation().getX()>=plugin.ArenaManager.get(Integer.toString(aid)).getSlot(slotid).getLocation().getX()-1&&
							ent.getLocation().getX()<=plugin.ArenaManager.get(Integer.toString(aid)).getSlot(slotid).getLocation().getX()+1&&
							ent.getLocation().getZ()>=plugin.ArenaManager.get(Integer.toString(aid)).getSlot(slotid).getLocation().getZ()-1&&
							ent.getLocation().getZ()<=plugin.ArenaManager.get(Integer.toString(aid)).getSlot(slotid).getLocation().getZ()+1) {
						ent.remove();
					}

					
				}
			}
			ConfigManager ConfigManager;
			ConfigManager = new ConfigManager();
			ConfigManager.Units();
			int ID = slot.getID();
			if(ID>=0) {
				if(slot.getColor()) {
					spawnGreenUnit(ID,slot.getLocation(),slot.getLvl());
				}
				else {
					spawnRedUnit(ID,slot.getLocation(),slot.getLvl());
				}
			}
				
			}
			
		}
	public static void spawnOnArena(int aid) {
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
								ent.remove();
								
							}
						}
					}
					
				}

			}
		}
		for(int i=0;i<26;i++) {
			spawnOnSlot(aid, i);
			
		}
		
	}
	
}
