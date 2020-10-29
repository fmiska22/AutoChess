package fmiska22.autochess.converters;

import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityTypes;

public class UnitType2EntityTypes {

	public static EntityTypes<? extends EntityCreature> Convert(String UnitType) {
		switch(UnitType){
			case "ZOMBIE":
				return EntityTypes.ZOMBIE;
			case "HUSK":
				return EntityTypes.HUSK;
			case "SKELETON":
				return EntityTypes.SKELETON;
			case "STRAY":
				return EntityTypes.STRAY;
			case "WITHER_SKELETON":
				return EntityTypes.WITHER_SKELETON;
			case "ZOMBIFIED_PIGLIN":
				return EntityTypes.ZOMBIFIED_PIGLIN;
			case "ZOMBIE_VILLAGER":
				return EntityTypes.ZOMBIE_VILLAGER;
			default:
				return EntityTypes.ZOMBIE;
		}
		
	}
}
