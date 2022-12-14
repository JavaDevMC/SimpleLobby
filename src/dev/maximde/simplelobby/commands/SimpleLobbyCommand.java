package dev.maximde.simplelobby.commands;


import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.maximde.simplelobby.SimpleLobby;
import dev.maximde.simplelobby.utils.Settings;
import dev.maximde.simplelobby.utils.Config;
import dev.maximde.simplelobby.utils.Locations;
import dev.maximde.simplelobby.utils.Utils;

public class SimpleLobbyCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			/*If player has permsissions*/if (p.hasPermission(SimpleLobby.name.toLowerCase() + ".admin") || p.hasPermission(SimpleLobby.prefixWithoutColor.toLowerCase() + ".cmd")) {
			if(args.length == 0) {
            sendCMDListAdmin(p);
			} else if (args.length == 1) {
				switch(args[0])
				{
				case "setspawn":
					Settings.Spawn = p.getLocation();
					Settings.lobbyWorldName = p.getLocation().getWorld().getName();
					Locations.setLocation("Spawn", p.getLocation());
					Locations.saveLocations();
					Utils.reloadConfig();
					p.sendMessage("§aLobby spawnpoint set!");	
					break;
					
				case "reload":
					Utils.reloadConfigCMD(p);
					break;
					
				case "spawn":
					teleportToSpawn(p);
					break;
					
				case "hub":
					teleportToSpawn(p);
					break;
					
				case "lobby":
					teleportToSpawn(p);
					break;
					
				case "l":
					teleportToSpawn(p);
					break;
				
				default:
		            sendCMDListAdmin(p);
		            break;
				}
				
			} else {
	            sendCMDListAdmin(p);
			}
			
			
			} else {
				p.sendMessage(Settings.prefix+"§cNo permission!");
			}
		} else {
			if (args.length == 1) {
			switch(args[0])
			{
			case "reload":
				Utils.reloadConfigConsole();
			break;
			
			default:
				org.bukkit.Bukkit.getServer().getConsoleSender().sendMessage(Settings.prefix + "§cCommand not found ore you cant use it because you are not a player!");
			break;
			}
			}
		}
		return false;
	}
	
	
	/**
	 * Send player commands to player
	 * @param player
	 */
    void sendCMDList(Player player) {
    	player.sendMessage(SimpleLobby.prefix + "§f----Commands----");
		player.sendMessage(SimpleLobby.prefix + "§6/" + SimpleLobby.nameK.toLowerCase() + " spawn");
		player.sendMessage(SimpleLobby.prefix + "§f----------------");
    }
    
    /**
     * Send all commands (includes permission required commands) to player
     * @param player
     */
    void sendCMDListAdmin(Player player) {
    	player.sendMessage(SimpleLobby.prefix + "§f----Commands----");
		player.sendMessage(SimpleLobby.prefix + "§6/" + SimpleLobby.nameK.toLowerCase() + " spawn");
		player.sendMessage(SimpleLobby.prefix + "§6/" + SimpleLobby.nameK.toLowerCase() + " reload");
		player.sendMessage(SimpleLobby.prefix + "§6/" + SimpleLobby.nameK.toLowerCase() + " setspawn");
		player.sendMessage(SimpleLobby.prefix + "§f----------------");
    }
    
    /**
     * @param bool (Boolean)
     * @return Boolean as a better locking string (String)
     */
    String booleanToString(boolean bool) {
    	if(bool) {
    		return " = §aEnabled ✔";
    	} else {
    		return " = §cDisabled ✘";
    	}
    }
    
    /**
     * Teleport player to lobby spawn location
     * @param player
     */
    public void teleportToSpawn(Player player) {
    	if(Settings.Spawn == null) {
    		if (player.hasPermission(SimpleLobby.prefixWithoutColor.toLowerCase() + ".admin") || player.hasPermission(SimpleLobby.prefixWithoutColor.toLowerCase() + ".cmd")) {
        		player.sendMessage("§cType /sl setspawn to set the spawn point!");

    		 } else {
        		player.sendMessage("§cNo spawn is set!");
    	     }
           return;
    	} 
    	
    	player.teleport(Settings.Spawn);
		player.sendMessage(Settings.teleportToSpawnMessage);
    }
    
    /**
     * Save settings to config
     */
    public static void saveSettingsInConfig() {
    	//TODO save settings
    }
    
    /**
     * Set settings to the default values
     * and save them in the config
     */
    public static void resetSettings() {
    	//TODO set settings to the default values
    	saveSettingsInConfig();
    }
    
	/**
	 * MaximDe 2022.
	 * 
	 * LINKS:
	 * https://github.com/JavaDevMC
	 * https://www.spigotmc.org/members/maximde.1620695/
	 */
}
