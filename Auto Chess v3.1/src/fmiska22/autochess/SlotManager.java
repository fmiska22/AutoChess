package fmiska22.autochess;

import org.bukkit.Location;

public class SlotManager {

	private String unitname="null";
	private int ID=-1;
	private int lvl;
	private Location loc;
	private Location buttonloc;
	private boolean color;
	public void setUnitname(String unitname) {
		this.unitname=unitname;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setLvl(int lvl) {
		this.lvl=lvl;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLocation(Location loc) {
		this.loc=loc;
	}
	public Location getLocation() {
		return loc;
	}
	public void setButtonLocation(Location buttonloc) {
		this.buttonloc=buttonloc;
	}
	public Location getButtonLocation() {
		return buttonloc;
	}
	public void setColor(boolean color) {
		this.color=color;
	}
	public boolean getColor() {
		return color;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	public int getID() {
		return ID;
	}
}
