package fmiska22.autochess;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;



public class CommandManager implements CommandExecutor, Listener {
	
	static main plugin;
	public CommandManager(main main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
	        if (command.getName().equalsIgnoreCase("AutoChess")) {
	            if (sender instanceof Player) {
	               if(args.length==1) {
	            	   if(args[0].equalsIgnoreCase("join")){
	            		   
	            		   if(main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).ifIngame()==false) {
	            			   if(main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).ifInlobby()==false) {
	            				   
	            				   main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).setInlobby(true);
	                			   sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" +ChatColor.DARK_GRAY + "You have joined the queue!");
	            				   for(Player p: Bukkit.getOnlinePlayers()) {
	            					   if(p.getUniqueId()!=((Player) sender).getPlayer().getUniqueId()) {
	            						   if(main.getPlugin(main.class).PlayerManager.get(p.getUniqueId()).ifInlobby()==true) {
	            							   ConfigManager ConfigManager;
	            								ConfigManager = new ConfigManager();
	            								ConfigManager.Arenas();
	            							   for(int i=0;i<ConfigManager.getArenas().getInt("ArenaCount");i++) {
	            								   if(main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).getStage()==-1) {
	            									   main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).setStage(1);
	            									   main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).setGreenUUID(p.getUniqueId());
	            									   main.getPlugin(main.class).ArenaManager.get(Integer.toString(i)).setRedUUID(((Player) sender).getPlayer().getUniqueId());
	            									   main.getPlugin(main.class).PlayerManager.get(p.getUniqueId()).setInlobby(false);
			            							   main.getPlugin(main.class).PlayerManager.get(p.getUniqueId()).setIngame(true);
			            							   main.getPlugin(main.class).PlayerManager.get(p.getUniqueId()).setArenaid(i);
			            							   main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).setInlobby(false);
			            							   main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).setIngame(true);
			            							   main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).setArenaid(i);
			            							   GameStages.prepStage(i);
	            									   return true;
	            								   }
	            								   
	            							   }
	            							   return true;
	            							 
	            							   
	            						   }
	            					   }
	            				   }
	                			   }
	            			   else {
	            				   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	        	   	            		   ChatColor.RED + "You are in queue!");
	            			   }
	            		   }
	            		   else {
	            			   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	    	   	            		   ChatColor.RED + "You are ingame!");
	            		   }
	            	   }
	            	   else  if(args[0].equalsIgnoreCase("leave")){
	            		   if(main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).ifIngame()==false) {
	            			   if(main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).ifInlobby()==true) {
	            				   main.getPlugin(main.class).PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).setInlobby(false);
	            				   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	        	   	            		   ChatColor.DARK_GRAY + "You have successfully left the queue!");
	            			   }
	            		   }
	            		   else {
	            			   if(plugin.PlayerManager.containsKey(((Player) sender).getPlayer().getUniqueId())) {
	            					if(plugin.PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).ifIngame()){
	            					
	            						((Player) sender).getPlayer().getInventory().clear();
	            						plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).getArenaid())).setStage(-1);
	            						plugin.ArenaManager.get(Integer.toString(plugin.PlayerManager.get(((Player) sender).getPlayer().getUniqueId()).getArenaid())).setLooser(((Player) sender).getPlayer().getUniqueId());
	            						((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	     	    	   	            		   ChatColor.DARK_GRAY + "You have successfully left the game!");
	            					}
	            				}
	            			   
	            			   
	            		   }
	            			   
	            	   }
	            	   else {
	            		   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
		   	            		   ChatColor.RED + "Invalid usage! Usage: /AutoChess <join/leave>");
	            	   }
	   	  
	            	   
	               }
	               else {
	            	   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	   	            		   ChatColor.RED + "Invalid usage! Usage: /AutoChess <join/leave>");
	               }
	              

	            }
	            
	       
	        }
	        if(command.getName().equalsIgnoreCase("AutoChessSetup")) {
	        	if(((Player)sender).isOp()) {
	        		if(args.length==3) {
		        		 ConfigManager ConfigManager;
		        		 ConfigManager = new ConfigManager();
		        		 ConfigManager.Arenas();
		            	   if(args[0].equalsIgnoreCase("setspawn")){
		            		   ConfigManager.Arenas.set("Arenas."+ args[1] +".Slots."+args[2], ((Player) sender).getLocation());
		            		   ConfigManager.saveArenas();
		            		   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
		            		   ChatColor.DARK_GRAY + "Spawn set!");
		            	   }
		            	   else if(args[0].equalsIgnoreCase("setbutton")){
		            		  ConfigManager.Arenas.set("Arenas."+ args[1] +".Buttons.SpawnButtons."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
		            		  ConfigManager.saveArenas();
		            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
		   	            		   ChatColor.DARK_GRAY + "Button set!");
		            	   }
		            	   else if(args[0].equalsIgnoreCase("setbuybutton")){
		            		  ConfigManager.Arenas.set("Arenas."+ args[1] +".Buttons.BuyButtons."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
		            		  ConfigManager.saveArenas();
		            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
			   	            		   ChatColor.DARK_GRAY + "Button set!");
		            	   }
		            	   else if(args[0].equalsIgnoreCase("setreloadbutton")){
		            		   ConfigManager.Arenas.set("Arenas."+ args[1] +".Buttons.ReloadButtons."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
		            		  ConfigManager.saveArenas();
		            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
			   	            		   ChatColor.DARK_GRAY + "Button set!");
		            	   }
		            	   else if(args[0].equalsIgnoreCase("setshopblock")){
		            		  ConfigManager.Arenas.set("Arenas."+ args[1] +".Shopblocks."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
		            		  ConfigManager.saveArenas();
		            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
			   	            		   ChatColor.DARK_GRAY + "Block set!");
		            	   }
		            	   else if(args[0].equalsIgnoreCase("setxpbutton")){
			            		  ConfigManager.Arenas.set("Arenas."+ args[1] +".Buttons.XpButtons."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
			            		  ConfigManager.saveArenas();
			            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
				   	            		   ChatColor.DARK_GRAY + "Button set!");
			            	   }
		            	   else if(args[0].equalsIgnoreCase("setfence")){
			            		  ConfigManager.Arenas.set("Arenas."+ args[1] +".Fence."+args[2], ((Player) sender).getTargetBlock(null, 6).getLocation());
			            		  ConfigManager.saveArenas();
			            		  ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
				   	            		   ChatColor.DARK_GRAY + "Fence set!");
			            	   }
		            	   else if(args[0].equalsIgnoreCase("setgamespawn")){
		            		   ConfigManager.Arenas.set("Arenas."+ args[1] +".GameSpawn."+args[2], ((Player) sender).getLocation());
		            		   ConfigManager.saveArenas();
		            		   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
		            		   ChatColor.DARK_GRAY + "Spawn set!");
		            	   }
		            	   else {
		            		   ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
			   	            		   ChatColor.RED + "Invalid objectname!");
		            	   }
		            	   
		               }
	        		else {
	        			 ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
		   	            		   ChatColor.RED + "Invalid usage! Usage: /AutoChessSetup <objectname> <arenaid> <objectid>");
	        		}
	        	}
	        	else {
	        		 ((Player) sender).getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "AutoChess" + ChatColor.GRAY + "]" + 
	   	            		   ChatColor.RED + "You don't have permission to use this command!");
	        	}
	        	 
	        }
	        
	   
	     
	        
	        return true;
	    }
   
	            
	   	  
	
}
