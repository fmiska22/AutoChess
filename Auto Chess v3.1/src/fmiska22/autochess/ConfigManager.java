package fmiska22.autochess;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private main plugin = main.getPlugin(main.class);

	public FileConfiguration Units;
	public File Unitsfile;

	public void Units() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		Unitsfile = new File(plugin.getDataFolder(), "Units.yml");

		if (!Unitsfile.exists()) {
			try {
				Unitsfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender();
			}
		}

		Units = YamlConfiguration.loadConfiguration(Unitsfile);
	}

	public FileConfiguration getUnits() {
		return Units;
	}

	public void saveUnits() {
		try {
			Units.save(Unitsfile);
		} catch (IOException e) {
		}
	}
	public FileConfiguration Arenas;
	public File Arenasfile;

	public void Arenas() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		Arenasfile = new File(plugin.getDataFolder(), "Arenas.yml");

		if (!Arenasfile.exists()) {
			try {
				Arenasfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender();
			}
		}

		Arenas = YamlConfiguration.loadConfiguration(Arenasfile);
	}

	public FileConfiguration getArenas() {
		return Arenas;
	}

	public void saveArenas() {
		try {
			Arenas.save(Arenasfile);
		} catch (IOException e) {
		}
	}

	
}