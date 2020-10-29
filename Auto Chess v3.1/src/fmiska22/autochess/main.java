package fmiska22.autochess;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;







public class main extends JavaPlugin {
	public Recipes Recipes;
	public Events Events;
	public BuyXP BuyXp;
	public ReloadShop ReloadShop;
	public SpawnIn SpawnIn;
	public Buy Buy;
	private ConfigManager ConfigManager;
	public HashMap<String,ArenaManager> ArenaManager = new HashMap<String,ArenaManager>();
	public HashMap<UUID,PlayerManager> PlayerManager = new HashMap<UUID,PlayerManager>();
	public void onEnable() {
		registerCommands();
		loadConfig();
		loadConfigManager();
		LoadArenas();
		Recipes recipes = new Recipes();
		recipes.recipe();
		getServer().getPluginManager().registerEvents(new Recipes(), this);
		getServer().getPluginManager().registerEvents(new Events(), this);
		getServer().getPluginManager().registerEvents(new SpawnIn(), this);
		getServer().getPluginManager().registerEvents(new BuyXP(), this);
		getServer().getPluginManager().registerEvents(new ReloadShop(), this);
		getServer().getPluginManager().registerEvents(new Buy(), this);
	}
	public void onReload() {
		for(int i =0;i<ConfigManager.Arenas.getInt("ArenaCount");i++) {
			main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).resetArena();;
			LoadArenas();
		}
	}
	public void loadConfigManager() {
		ConfigManager = new ConfigManager();
		ConfigManager.Arenas();
		ConfigManager.Units();
		ConfigManager.saveArenas();
		ConfigManager.saveUnits();	
		
	}
	public void loadConfig() {
		getConfig().options().copyDefaults(false);
		saveConfig();
	}
	public void registerCommands() {
		this.getCommand("autochess").setExecutor(new CommandManager(this));
		this.getCommand("autochesssetup").setExecutor(new CommandManager(this));
	}
	public void LoadArenas() {
		for(int i =0;i<ConfigManager.Arenas.getInt("ArenaCount");i++) {
			main.getPlugin(main.class).ArenaManager.put(Integer.toString(i), new ArenaManager(i));
			main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).loadArena();
			
		}
	}
		
}
