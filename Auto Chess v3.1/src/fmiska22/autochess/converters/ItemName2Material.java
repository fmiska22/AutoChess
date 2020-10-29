package fmiska22.autochess.converters;

import org.bukkit.Material;

public class ItemName2Material {
	public static Material toMatIron(String ItemName) {
		switch(ItemName){
		case "SWORD":
			return Material.IRON_SWORD;
		case "AXE":
			return Material.IRON_AXE;
		case "SHOVEL":
			return Material.IRON_SHOVEL;
		default:
			return null;
		}
	}
	public static Material toMatGold(String ItemName) {
		switch(ItemName){
		case "SWORD":
			return Material.GOLDEN_SWORD;
		case "AXE":
			return Material.GOLDEN_AXE;
		case "SHOVEL":
			return Material.GOLDEN_SHOVEL;
		default:
			return null;
		}
	}
	public static Material toMatDiamond(String ItemName) {
		switch(ItemName){
		case "SWORD":
			return Material.DIAMOND_SWORD;
		case "AXE":
			return Material.DIAMOND_AXE;
		case "SHOVEL":
			return Material.DIAMOND_SHOVEL;
		default:
			return null;
		}
	}
}
