package fmiska22.autochess;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import fmiska22.autochess.converters.ItemName2Material;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityHuman;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.EnumItemSlot;
import net.minecraft.server.v1_16_R2.GenericAttributes;
import net.minecraft.server.v1_16_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R2.PathfinderGoalLookAtPlayer;

public class UnitManager extends EntityCreature {

	public UnitManager(EntityTypes<? extends EntityCreature> type, Location loc, int lvl,String ItemName) {
		super(type, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setCustomNameVisible(true);
		
		Material im;
		Material ih;
		Material ic;
		Material il;
		Material ib;
		switch(lvl) {
			case 0:
				im = ItemName2Material.toMatIron(ItemName);
				ih = Material.AIR;
				ic = Material.AIR;
				il = Material.AIR;
				ib = Material.AIR;
				break;
			case 1:
				im = ItemName2Material.toMatIron(ItemName);
				ih = Material.IRON_HELMET;
				ic = Material.IRON_CHESTPLATE;
				il = Material.IRON_LEGGINGS;
				ib = Material.IRON_BOOTS;
				break;
			case 2:
				im = ItemName2Material.toMatGold(ItemName);
				ih = Material.GOLDEN_HELMET;
				ic = Material.GOLDEN_CHESTPLATE;
				il = Material.GOLDEN_LEGGINGS;
				ib = Material.GOLDEN_BOOTS;
				break;
			case 3:
				im = ItemName2Material.toMatDiamond(ItemName);
				ih = Material.DIAMOND_HELMET;
				ic = Material.DIAMOND_CHESTPLATE;
				il = Material.DIAMOND_LEGGINGS;
				ib = Material.DIAMOND_BOOTS;
				break;
			default:
				im = Material.IRON_SWORD;
				ih = Material.AIR;
				ic = Material.AIR;
				il = Material.AIR;
				ib = Material.AIR;
		}
		ItemStack i = new ItemStack(im,1);
		ItemStack h = new ItemStack(ih,1);
		ItemStack c = new ItemStack(ic,1);
		ItemStack l = new ItemStack(il,1);
		ItemStack b = new ItemStack(ib,1);
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(i));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(h));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(c));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(l));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(b));
	}
	public void setDmg(int dmg) {
		this.getAttributeMap().a(GenericAttributes.ATTACK_DAMAGE).setValue(dmg);
	}
	public void setHealth(int health) {
		this.getAttributeMap().a(GenericAttributes.MAX_HEALTH).setValue(health);
		this.heal(health);
	}
	public void setKnockback(int knockback) {
	}
	public void setShopUnitName(String ShopUnitName) {
		this.setCustomName(new ChatComponentText(ChatColor.YELLOW+ShopUnitName));
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
		this.getAttributeMap().a(GenericAttributes.MAX_HEALTH).setValue(999999);
		this.heal(99999);
	}
	

	

}
