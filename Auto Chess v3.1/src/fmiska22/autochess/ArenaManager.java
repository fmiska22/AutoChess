package fmiska22.autochess;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArenaManager{

	private int aid;
	private UUID RedUUID;
	private UUID GreenUUID;
	private UUID looser=null;
	private int redcount;
	private int greencount;
	private int stage=-1;
	private boolean canSpawn=true;
	private Location reroll[] = new Location[3];
	private SlotManager slot[] = new SlotManager[27];
	private ShopManager shop[] = new ShopManager[9];
	
	public boolean canSpawn() {
		return canSpawn;
	}
	public void setCanSpawn(boolean canSpawn) {
		this.canSpawn=canSpawn;
	}
	public ArenaManager(int aid) {
		this.aid=aid;
	}
	public void setSlot(int slotid,SlotManager slot) {
		this.slot[slotid] = slot;
	}
	public SlotManager getSlot(int slotid) {
		return slot[slotid];
	}
	public void setshop(int shopid,ShopManager shop) {
		this.shop[shopid] = shop;
	}
	public ShopManager getshop(int shopid) {
		return shop[shopid];
	}
	public void setAid(int aid) {
		this.aid=aid;
	}
	public int getAid() {
		return aid;
	}
	public void setRedcount(int redcount) {
		this.redcount=redcount;
	}
	public int getRedcount() {
		return redcount;
	}
	public void addRedcount() {
		redcount++;
	}
	public void removeRedcount() {
		redcount--;
	}
	public void setGreencount(int greencount) {
		this.greencount=greencount;
	}
	public int getGreencount() {
		return greencount;
	}
	public void addGreencount() {
		greencount++;
	}
	public void removeGreencount() {
		greencount--;
	}
	public void setRedUUID(UUID RedUUID) {
		this.RedUUID=RedUUID;
	}
	public UUID getRedUUID() {
		return RedUUID;
	}
	public void setLooser(UUID looser) {
		this.looser=looser;
	}
	public UUID getLooser() {
		return looser;
	}
	public void setGreenUUID(UUID GreenUUID) {
		this.GreenUUID=GreenUUID;
	}
	public UUID getGreenUUID() {
		return GreenUUID;
	}
	public void setStage(int stage) {
		this.stage=stage;
	}
	public int getStage() {
		return stage;
	}
	public void setReroll(Location reroll,int buttid) {
		this.reroll[buttid]=reroll;
	}
	public Location getReroll(int buttid) {
		return reroll[buttid];
	}
	public void resetArena() {
		this.setRedcount(0);
		this.setGreencount(0);
		main.getPlugin(main.class).PlayerManager.get(RedUUID).setArenaid(-1);
		main.getPlugin(main.class).PlayerManager.get(GreenUUID).setArenaid(-1);
		main.getPlugin(main.class).PlayerManager.get(RedUUID).setIngame(false);;
		main.getPlugin(main.class).PlayerManager.get(GreenUUID).setIngame(false);;
		
		if(looser!=null) {
			if(looser==RedUUID) {
				Player p = main.getPlugin(main.class).getServer().getPlayer(GreenUUID);
				p.getInventory().clear();
				p.getPlayer().setHealth(20);
				p.getPlayer().setExp(0);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	            		   ChatColor.GREEN + "You have won!");
				p.teleport(p.getWorld().getSpawnLocation());
				looser=null;
				RedUUID=null;
				GreenUUID=null;
			}
			else if(looser==GreenUUID) {
				Player p = main.getPlugin(main.class).getServer().getPlayer(RedUUID);
				p.getInventory().clear();
				p.getPlayer().setHealth(20);
				p.getPlayer().setExp(0);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	            		   ChatColor.GREEN + "You have won!");
				p.teleport(p.getWorld().getSpawnLocation());
				looser=null;
				RedUUID=null;
				GreenUUID=null;
			}
			else {
				looser=null;
				RedUUID=null;
				GreenUUID=null;
			}
		}
		stage = -1;
		for(int i = 0;i<26;i++) {
			slot[i]=null;
		}
		for(int i = 0;i<8;i++) {
			shop[i]=null;
		}
		loadArena();
		//main.getPlugin(main.class).getServer().broadcastMessage("resetedarena");
		
	}
	public void loadArena() {
		for(int i=0;i<2;i++) {
			ConfigManager ConfigManager;
			ConfigManager = new ConfigManager();
			ConfigManager.Arenas();
			Location loc = ConfigManager.Arenas.getLocation("Arenas."+aid+".Buttons.ReloadButtons."+i);
			this.reroll[i] = loc;
		}
			for(int i=0;i<26;i++) {
			SlotManager slot = new SlotManager();
			ConfigManager ConfigManager;
			ConfigManager = new ConfigManager();
			ConfigManager.Arenas();
			slot.setLocation(ConfigManager.Arenas.getLocation("Arenas."+aid+".Slots."+i));
			if(13>i) {
				slot.setColor(true);
			}
			this.slot[i]=slot;
			
			//main.getPlugin(main.class).getServer().broadcastMessage(Integer.toString(slot.getLocation().getBlockX())+"   "+Integer.toString(slot.getLocation().getBlockZ()));
		}
			for(int i = 0;i<8;i++) {
				ShopManager shop = new ShopManager();
				ConfigManager ConfigManager;
				ConfigManager = new ConfigManager();
				ConfigManager.Arenas();
				shop.setBlock(ConfigManager.Arenas.getLocation("Arenas."+aid+".Shopblocks."+i));
				shop.setButton(ConfigManager.Arenas.getLocation("Arenas."+aid+".Buttons.BuyButtons."+i));
				this.shop[i]=shop;
			}
			
		
		
		
	}
	
		
	
}
	