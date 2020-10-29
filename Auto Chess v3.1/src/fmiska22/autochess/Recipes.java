package fmiska22.autochess;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes implements Listener {
	private main plugin = main.getPlugin(main.class);
	public void recipe(){
		ItemStack item = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("§aGreenKnightZombie");
		item.setItemMeta(im);
		@SuppressWarnings("deprecation")
		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(3,Material.SLIME_BALL);
		plugin.getServer().addRecipe(slr);
		}
	@EventHandler
	private void upgradeCrafting(PrepareItemCraftEvent e) {
		int a=1;
		ItemStack i1 = new ItemStack(Material.AIR);
		ItemStack i2 = new ItemStack(Material.AIR);
		ItemStack i3 = new ItemStack(Material.AIR);
		for (ItemStack ingredient : e.getInventory().getMatrix()) {
			try {
				if(ingredient.getType().equals(Material.SLIME_BALL)) {
					if(a==1) {
						i1=ingredient;
						a++;
					}
					else if(a==2) {
						i2=ingredient;
						a++;
					}
					else if(a==3) {
						i3=ingredient;
						a++;
					}
					else {
						a++;
					}
				}
			}catch(Exception ex) {
				
			}
		}
		try {
			if(i1.getItemMeta().equals(i2.getItemMeta())&&i2.getItemMeta().equals(i3.getItemMeta())&&a==4) {
				if(i1.getItemMeta().getLore().contains("§8lvl.:§61")) {
					e.getInventory().setResult(i1);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("§8lvl.:§62");
					ItemMeta im = e.getInventory().getResult().getItemMeta();
					im.setLore(lore);
					e.getInventory().getResult().setItemMeta(im);
					e.getInventory().getResult().setAmount(1);
				}
				else if(i1.getItemMeta().getLore().contains("§8lvl.:§62")) {
					e.getInventory().setResult(i1);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("§8lvl.:§63");
					ItemMeta im = e.getInventory().getResult().getItemMeta();
					im.setLore(lore);
					e.getInventory().getResult().setItemMeta(im);
					e.getInventory().getResult().setAmount(1);
				}
				else if(i1.getItemMeta().getLore().contains("§8lvl.:§63")) {
					e.getInventory().setResult(new ItemStack(Material.AIR));
				}
				else {
					e.getInventory().setResult(new ItemStack(Material.AIR));
				}
				
				
			}
			else {
				e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}catch(Exception ex) {
			e.getInventory().setResult(new ItemStack(Material.AIR));
		}
		
		
		
	}
}

