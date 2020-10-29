package fmiska22.autochess;

import org.bukkit.Location;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R2.PathfinderGoalNearestAttackableTarget;

public class GreenUnit extends UnitManager{

	private String unitName;
	private int lvl;
	public GreenUnit(EntityTypes<? extends EntityCreature> type, Location loc, int lvl, String ItemName) {
		
		super(type, loc, lvl, ItemName);
		this.setCustomName(new ChatComponentText(ChatColor.GREEN+unitName + ChatColor.DARK_GRAY 
				+" lvl.: " + ChatColor.GOLD+ Integer.toString(lvl)));
		this.lvl=lvl;
			
	}
	@Override
	public void initPathfinder() {
		this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.goalSelector.a(1, new PathfinderGoalNearestAttackableTarget<RedUnit>(this,RedUnit.class,false));
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
		this.setCustomName(new ChatComponentText(ChatColor.GREEN+unitName + ChatColor.DARK_GRAY 
				+" lvl.: " + ChatColor.GOLD+ Integer.toString(lvl)));
	}

}
