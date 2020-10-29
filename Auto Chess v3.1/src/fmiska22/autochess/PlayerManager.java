package fmiska22.autochess;

import java.util.UUID;

import org.bukkit.event.Listener;

public class PlayerManager implements Listener {

	private UUID uuid;
	private boolean ingame;
	private boolean inlobby;
	private int arenaid=-1;
	
	public PlayerManager(UUID uuid, boolean ingame, int arenaid, boolean inlobby){
		this.setUuid(uuid);
		this.setIngame(ingame);
		this.setArenaid(arenaid);
		this.setInlobby(inlobby);
		
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public boolean ifIngame() {
		return ingame;
	}
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}
	public int getArenaid() {
		return arenaid;
	}
	public void setArenaid(int arenaid) {
		this.arenaid = arenaid;
	}
	public boolean ifInlobby() {
		return inlobby;
	}
	public void setInlobby(boolean inlobby) {
		this.inlobby = inlobby;
	}
}
