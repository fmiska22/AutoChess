package fmiska22.autochess;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ShopManager {
	private Location blockloc;
	private Location buttonloc;
	private Location spawnloc;
	private int UnitID;
	private int UnitPrice;
	public void setBlock(Location blockloc) {
		this.blockloc=blockloc;
		this.spawnloc=blockloc;
	}
	public void setButton(Location buttonloc) {
		this.buttonloc=buttonloc;
	}
	public Location getButton() {
		return buttonloc;
	}
	public void setID(int ID) {
		this.UnitID=ID;
	}
	public int getID() {
		return UnitID;
	}
	public void reloadShop() {
		blockloc.getBlock().setType(Material.AIR);
		UnitID=-1;
		new BukkitRunnable() {

            @Override
            public void run() {
    			blockloc.getBlock().setType(Material.QUARTZ_BLOCK);
    			ConfigManager ConfigManager;
    			ConfigManager = new ConfigManager();
    			ConfigManager.Units();
    			int keys = 0;
    			for(@SuppressWarnings("unused") String key : ConfigManager.getUnits().getConfigurationSection("Units").getKeys(false)) {
    				keys++;
    			}
    			Random rand = new Random();
    			if(keys!=0) {
    				keys--;
    			}
    			UnitID = rand.nextInt(keys);
    			UnitPrice = ConfigManager.getUnits().getInt("Units."+UnitID+".Price");
    			SpawnMethod.spawnShop(UnitID, spawnloc);
            }
        }.runTaskLater(main.getPlugin(main.class), 3*20);
			
		
		
		
	}
	public void buyIt(Player p) {
		ItemStack emerald = new ItemStack(Material.EMERALD, UnitPrice);
		if(p.getInventory().containsAtLeast(emerald, UnitPrice)) {
			ConfigManager ConfigManager;
			ConfigManager = new ConfigManager();
			ConfigManager.Units();
			ItemStack item = new ItemStack(Material.SLIME_BALL,1);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("§9"+ConfigManager.getUnits().getString("Units."+UnitID+".Name"));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("§8lvl.:§61");
			im.setLore(lore);
			item.setItemMeta(im);
			p.getInventory().addItem(item);
			UnitID=-1;
			blockloc.getBlock().setType(Material.AIR);
			p.getInventory().removeItem(emerald);
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" +im.getDisplayName()+ ChatColor.DARK_GRAY + " bought!");
			p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1, 1);
		}
		else {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + ChatColor.DARK_GRAY + "You don't have enough emeralds!");
			p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
		}
			
	}
	
}
