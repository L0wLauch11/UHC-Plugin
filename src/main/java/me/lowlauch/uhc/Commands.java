package me.lowlauch.uhc;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

import static me.lowlauch.uhc.UsefulScripts.*;

public class Commands implements CommandExecutor
{
    protected static World world;
    protected static boolean pvp = false;
    protected static boolean playing = false;
    protected static Location loc;
    protected static int alivePlayers = 0;
    protected static int readyPlayers = 0;
    protected static boolean autoStart = false;
    protected static int autoStartSeconds = 1;
    protected static int startAmountPlayers = 0;
    protected static String autoStartWorld;
    protected static int playersWannaRemake = 0;
    protected static int playersMustRemake = 2;
    protected static int protectionTime = 1;
    protected static int maxTeamMembers = Main.getInstance().getConfig().getInt("teams.maxsize");
    protected static int team1Members = 0;
    protected static int team2Members = 0;
    protected static int team3Members = 0;
    protected static int team4Members = 0;
    protected static String uhcWorld;
    protected static boolean teamsEnabled = Main.getInstance().getConfig().getBoolean("teams.enabled");
    protected static String prefix = "§9[UHC]§f ";
    protected static World lobbyWorld = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("world.lobby"));
    protected static Location lobbyLocation = new Location(lobbyWorld , Main.getInstance().getConfig().getInt("world.x"), Main.getInstance().getConfig().getInt("world.y"), Main.getInstance().getConfig().getInt("world.z"));

    public Commands(Main instance) {

    }


    public boolean onCommand(CommandSender commandSender, Command command, String commandInput, String[] args)
    {
    	//TODO: Commands to rework: toggleautostart
    	if(commandInput.equalsIgnoreCase("uhc"))
    	{
    		if(args.length >= 1)
    		{
    			if(args[0].equalsIgnoreCase("ready"))
    			{
    				Player p = (Player) commandSender;
    	            if(p.getExp() == 0f)
    	            {
    	                p.setMetadata("isReady", new FixedMetadataValue(Main.getInstance(), "yos!"));
    	                p.setExp(1f);
    	            } else if(p.getExp() == 1f)
    	            {
    	                p.removeMetadata("isReady", Main.getInstance());
    	                p.setExp(0f);
    	            }

    	            if(p.getExp() == 1f)
    	            {
    	                readyPlayers++;
    	                Bukkit.getServer().broadcastMessage("§4§l" + ((Player)commandSender).getDisplayName() + " §r§fist bereit! (" + readyPlayers + "/" + Bukkit.getServer().getOnlinePlayers().size() + ")");
    	            } else if(p.getExp() == 0f)
    	            {
    	                readyPlayers--;
    	                Bukkit.getServer().broadcastMessage("§4§l" + ((Player)commandSender).getDisplayName() + " §r§fist nichtmehr bereit! (" + readyPlayers + "/" + Bukkit.getServer().getOnlinePlayers().size() + ")");
    	            }
    			}
    			
    			if(args[0].equalsIgnoreCase("autostart"))
    			{
    				autoStart = true;
                    if(args.length == 0)
                    {
                        startAmountPlayers = 4;
                        autoStartSeconds = 60;
                    } else if(args.length == 1)
                    {
                        startAmountPlayers = Integer.parseInt(args[1]);
                        autoStartSeconds = 60;
                    } else if(args.length == 2)
                    {
                        startAmountPlayers = Integer.parseInt(args[1]);
                        autoStartSeconds = Integer.parseInt(args[2]);
                    }
                    uhcWorld = getSaltString();
                    commandSender.sendMessage(prefix + "Du willst dass das §aSpiel§f in " + args[2] + "s startet.");
    			}
    			
    			if(args[0].equalsIgnoreCase("toggleautostart"))
    			{
    				autoStart = !autoStart;
                    if(autoStart)
                    {
                        Bukkit.getServer().broadcastMessage(prefix + "Der §1Countdown§f wurde §a§lgestartet§r§f!");
                    }
                    if(!autoStart)
                    {
                        Bukkit.getServer().broadcastMessage(prefix + "Der §1Countdown§f wurde §4§lgestoppt§r§f!");
                    }
    			}
    			
    			if(args[0].equalsIgnoreCase("stop"))
    			{
    				playing = false;
                    for(Player p : Bukkit.getServer().getOnlinePlayers())
                    {
                        lobbyLocation = new Location(Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("world.lobby")), Main.getInstance().getConfig().getInt("world.x"), Main.getInstance().getConfig().getInt("world.y"), Main.getInstance().getConfig().getInt("world.z"));
                        p.teleport(lobbyLocation);
                        p.setGameMode(GameMode.ADVENTURE);
                        p.setExp(0f);
                        clearArmor(p);
                        readyPlayers = 0;
                        p.sendMessage(prefix + "Du wurdest zur §2§lLobby§r§f §nteleportiert§r.");
                    }
                    Bukkit.getServer().broadcastMessage(prefix + "Das Spiel wurde frühzeitig §4beendet§r!");
    			}
    			
    			if(args[0].equalsIgnoreCase("start") && commandSender.isOp())
    			{
    				if(!playing)
                    {
                        Bukkit.getServer().broadcastMessage(prefix + "Das §aSpiel§f §e§l§n§ostartet!§r§f Es wird kurz §c§llaggen§r§f!");
                        uhcWorld = getSaltString();
                        createWorld(uhcWorld);
                        loc = new Location(Bukkit.getWorld(uhcWorld), 0, 55, 0);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "parkour peod");
                        for(Player p : Main.getInstance().getServer().getOnlinePlayers())
                        {
                            p.setExp(0f);
                            p.getInventory().clear();
                            for (PotionEffect effect : p.getActivePotionEffects())
                            {
                                p.removePotionEffect(effect.getType());
                            }
                            readyPlayers = 0;
                            
                            if(Main.getInstance().getConfig().getBoolean("starter.items"))
                            {
                            	p.getInventory().addItem(new ItemStack(Material.getMaterial(Main.getInstance().getConfig().getString("starter.item1"))));
                                p.getInventory().addItem(new ItemStack(Material.getMaterial(Main.getInstance().getConfig().getString("starter.item2"))));
                            }
                            
                            p.removeMetadata("isReady", Main.getInstance());
                            p.setHealth(20f);
                            p.setFoodLevel(20);
                            p.setExp(0);
                            p.setLevel(0);
                            p.setGameMode(GameMode.SURVIVAL);
                            p.teleport(loc);
                            Bukkit.getServer().getWorld(uhcWorld).setPVP(pvp);
                        }

                        protectionTime = Main.getInstance().getConfig().getInt("protection.time");

                        alivePlayers = Bukkit.getServer().getOnlinePlayers().size();
                        World world = Bukkit.getServer().getWorld(uhcWorld);
                        world.getWorldBorder().setCenter(0, 0);
                        world.getWorldBorder().setSize(150*alivePlayers);
                        Bukkit.getServer().broadcastMessage(prefix + "Die §4§lBORDER§f§r ist §6" + (int) world.getWorldBorder().getSize() + " §f§rBlöcke im §nDurchmesser§r groÃ?");

                        Bukkit.getServer().broadcastMessage(prefix + "Das §aSpiel§f hat soeben §l§agestartet§r§f.");
                        playing = true;
                    } else {
                        commandSender.sendMessage(prefix + "Du hast bereits ein §aSpiel§f gestartet!");
                    }
    			}
    			
    			if(args[0].equalsIgnoreCase("bc") && commandSender.isOp())
    			{
    				StringBuilder message = new StringBuilder(args[0]);
                    for (int arg = 1; arg < args.length; arg++)
                    {
                        message.append(" ").append(args[arg]);
                    }
                    Bukkit.getServer().broadcastMessage(prefix + "" + message);
    			}
    			
    			if(args[0].equalsIgnoreCase("team") || args[0].equalsIgnoreCase("teams"))
    			{
    				if(args.length >= 2)
    	            {    
    	                if(args[1].equalsIgnoreCase("toggle") && commandSender.isOp())
    	                {
    	                    teamsEnabled = !teamsEnabled;
    	                    if(teamsEnabled)
    	                    {
    	                        commandSender.sendMessage(prefix + "Du hast §e§lTeams§r§f §aaktiviert§f!");
    	                    } else
    	                    {
    	                    	for(Player p : Bukkit.getServer().getOnlinePlayers())
    	                    	{
    	                    		if(p.hasMetadata("hasTeam"))
    	                            {
    	                                if(p.hasMetadata("Team 1"))
    	                                {
    	                                    p.removeMetadata("Team 1", Main.getInstance());
    	                                } else if(p.hasMetadata("Team 2"))
    	                                {
    	                                    p.removeMetadata("Team 2", Main.getInstance());
    	                                } else if(p.hasMetadata("Team 3"))
    	                                {
    	                                    p.removeMetadata("Team 3", Main.getInstance());
    	                                } else if(p.hasMetadata("Team 4"))
    	                                {
    	                                    p.removeMetadata("Team 4", Main.getInstance());
    	                                }
    	                            }
    	                    		p.removeMetadata("hasTeam", Main.getInstance());
    	                    	}
    	                        commandSender.sendMessage(prefix + "Du hast §e§lTeams§r§f §cdeaktivert§f!");
    	                    }
    	                }
    	                
    	                if(args[1].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("none"))
    	                {
    	                    Player p = (Player) commandSender;  //  p.setMetadata("wannaremake", new FixedMetadataValue(Main.getInstance(), "yes!"));
    	                    
    	                    if(p.hasMetadata("hasTeam"))
    	                    {
    	                        if(p.hasMetadata("Team 1"))
    	                        {
    	                            p.removeMetadata("Team 1", Main.getInstance());
    	                        } else if(p.hasMetadata("Team 2"))
    	                        {
    	                            p.removeMetadata("Team 2", Main.getInstance());
    	                        } else if(p.hasMetadata("Team 3"))
    	                        {
    	                            p.removeMetadata("Team 3", Main.getInstance());
    	                        } else if(p.hasMetadata("Team 4"))
    	                        {
    	                            p.removeMetadata("Team 4", Main.getInstance());
    	                        }
    	                        p.removeMetadata("hasTeam", Main.getInstance());
    	                    }
    	                    
    	                    p.sendMessage(prefix + "Du hast dein §e§lTeam§r§f zurückgesetzt!");
    	                }
    	                
    	                if(args[1].equalsIgnoreCase("join"))
    	                {
    	                    if(teamsEnabled)
    	                    {
    	                        if(args[2].equalsIgnoreCase("1") && team1Members < maxTeamMembers)
    	                        {
    	                            Player p = (Player) commandSender;  //  p.setMetadata("wannaremake", new FixedMetadataValue(Main.getInstance(), "yes!"));
    	                            
    	                            if(!p.hasMetadata("hasTeam"))
    	                            {
    	                                p.setMetadata("hasTeam", new FixedMetadataValue(Main.getInstance(), "teams"));
    	                            }
    	                            
    	                            if(!p.hasMetadata("Team 1"))
    	                            {
    	                                p.setMetadata("Team 1", new FixedMetadataValue(Main.getInstance(), "teams"));
    	                                team1Members++;
    	                                p.sendMessage(prefix + "Du bist§e§l Team 1§r§f gejoint!");
    	                            } else
    	                            {
    	                                team1Members--;
    	                                p.removeMetadata("Team 1", Main.getInstance());
    	                                p.sendMessage(prefix + "Du hast§e§l Team 1 §r§fverlassen!");
    	                            }
    	                            
    	                            if(p.hasMetadata("Team 2"))
    	                            {
    	                                team2Members--;
    	                                p.removeMetadata("Team 2", Main.getInstance());
    	                            }
    	                            
    	                            if(p.hasMetadata("Team 3"))
    	                            {
    	                                team3Members--;
    	                                p.removeMetadata("Team 3", Main.getInstance());
    	                            }
    	                            
    	                            if(p.hasMetadata("Team 4"))
    	                            {
    	                                team4Members--;
    	                                p.removeMetadata("Team 4", Main.getInstance());
    	                            }
    	                    } else if(args[2].equalsIgnoreCase("1"))
    	                    {
    	                        commandSender.sendMessage(prefix + "Dieses §e§lTeam§r§f ist voll!");
    	                    }
    	                        
    	               
    		               if(args[2].equalsIgnoreCase("2") && team2Members < maxTeamMembers)
    		               {
    		                   Player p = (Player) commandSender;
    		                   
    		                   if(!p.hasMetadata("hasTeam"))
    		                   {
    		                       p.setMetadata("hasTeam", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                   }
    		                   
    		                   if(!p.hasMetadata("Team 2"))
    		                   {
    		                       p.setMetadata("Team 2", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                       team2Members++;
    		                       p.sendMessage(prefix + "Du bist§e§l Team 2§r§f gejoint!");
    		                   } else
    	                       {
    	                           team2Members--;
    	                           p.removeMetadata("Team 2", Main.getInstance());
    	                           p.sendMessage(prefix + "Du hast§e§l Team 2 §r§fverlassen!");
    	                       }
    		                   
    		                   if(p.hasMetadata("Team 1"))
    		                   {
    		                       team1Members--;
    		                       p.removeMetadata("Team 1", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 3"))
    		                   {
    		                       team3Members--;
    		                       p.removeMetadata("Team 3", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 4"))
    		                   {
    		                       team4Members--;
    		                       p.removeMetadata("Team 4", Main.getInstance());
    		                   }
    		               } else if(args[2].equalsIgnoreCase("2"))
    		               {
    		                   commandSender.sendMessage(prefix + "Dieses §e§lTeam§r§f ist voll!");
    		               }
    		               
    		               
    		               if(args[2].equalsIgnoreCase("3") && team3Members < maxTeamMembers)
    		               {
    		                   Player p = (Player) commandSender;
    		                   
    		                   if(!p.hasMetadata("hasTeam"))
    		                   {
    		                       p.setMetadata("hasTeam", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                   }
    		                   
    		                   if(!p.hasMetadata("Team 3"))
    		                   {
    		                       p.setMetadata("Team 3", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                       team3Members++;
    		                       p.sendMessage(prefix + "Du bist§e§l Team 3§r§f gejoint!");
    		                   } else
    	                       {
    	                           team3Members--;
    	                           p.removeMetadata("Team 3", Main.getInstance());
    	                           p.sendMessage(prefix + "Du hast§e§l Team 3 §r§fverlassen!");
    	                       }
    		                   
    		                   if(p.hasMetadata("Team 1"))
    		                   {
    		                       team1Members--;
    		                       p.removeMetadata("Team 1", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 2"))
    		                   {
    		                       team2Members--;
    		                       p.removeMetadata("Team 2", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 4"))
    		                   {
    		                       team4Members--;
    		                       p.removeMetadata("Team 4", Main.getInstance());
    		                   }
    		               } else if(args[2].equalsIgnoreCase("3"))
    		               {
    		                   commandSender.sendMessage(prefix + "Dieses §e§lTeam§r§f ist voll!");
    		               }
    		               
    		               
    		               if(args[2].equalsIgnoreCase("4") && team4Members < maxTeamMembers)
    		               {
    		                   Player p = (Player) commandSender;
    		                   
    		                   if(!p.hasMetadata("hasTeam"))
    		                   {
    		                       p.setMetadata("hasTeam", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                   }
    		                   
    		                   if(!p.hasMetadata("Team 4"))
    		                   {
    		                       p.setMetadata("Team 4", new FixedMetadataValue(Main.getInstance(), "teams"));
    		                       team4Members++;
    		                       p.sendMessage(prefix + "Du bist§e§l Team 4§r§f gejoint!");
    		                   } else
    	                       {
    	                           team4Members--;
    	                           p.removeMetadata("Team 4", Main.getInstance());
    	                           p.sendMessage(prefix + "Du hast§e§l Team 4 §r§fverlassen!");
    	                       }
    		                   
    		                   if(p.hasMetadata("Team 1"))
    		                   {
    		                       team1Members--;
    		                       p.removeMetadata("Team 1", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 2"))
    		                   {
    		                       team2Members--;
    		                       p.removeMetadata("Team 2", Main.getInstance());
    		                   }
    		                   
    		                   if(p.hasMetadata("Team 3"))
    		                   {
    		                       team3Members--;
    		                       p.removeMetadata("Team 3", Main.getInstance());
    		                   }
    		               } else if(args[2].equalsIgnoreCase("4"))
    		               {
    		                   commandSender.sendMessage(prefix + "Dieses §e§lTeam§r§f ist voll!");
    		               }
    		               
    			           } else
    			           {
    			        	   commandSender.sendMessage(prefix + "§e§lTeams §r§fsind nicht aktiviert!");
    			           } 
    		           }
    			    } else
    			    {
    			    	commandSender.sendMessage(prefix + "Falsche Argumente!");
    			    }
    			}
    			
    		} else
    		{
    			commandSender.sendMessage(prefix + "Falsche Argumente!");
    		}
    	}

        if(commandInput.equalsIgnoreCase("remake"))
        {
            if(playing)
            {
                if(Bukkit.getServer().getOnlinePlayers().size() == 1)
                {
                    playersMustRemake = 1;
                }

                if(Bukkit.getServer().getOnlinePlayers().size() == 2 || Bukkit.getServer().getOnlinePlayers().size() == 3)
                {
                    playersMustRemake = 2;
                }

                if(Bukkit.getServer().getOnlinePlayers().size() == 4 || Bukkit.getServer().getOnlinePlayers().size() == 5)
                {
                    playersMustRemake = 3;
                }

                if(Bukkit.getServer().getOnlinePlayers().size() >= 6)
                {
                    playersMustRemake = 5;
                }
                Player p = (Player) commandSender;
                if(!p.hasMetadata("wannaremake"))
                {
                    p.setMetadata("wannaremake", new FixedMetadataValue(Main.getInstance(), "yes!"));
                    playersWannaRemake++;
                    Bukkit.getServer().broadcastMessage("§1§l" + p.getDisplayName() + "§r§f will das eine neue Welt generiert wird (" + playersWannaRemake + "/" + playersMustRemake + ") /remake");
                } else {
                    p.sendMessage(prefix + "Du willst schon ein remake machen!");
                }
            }

            if(playersWannaRemake >= playersMustRemake)
            {
                for(Player pl : Bukkit.getServer().getOnlinePlayers())
                {
                    pl.removeMetadata("wannaremake", Main.getInstance());
                }
                playing = false;
                Bukkit.getServer().broadcastMessage(prefix +  "Es wird eine neue Welt generiert.");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "startuhc");
                playersWannaRemake = 0;
            }
        }

        if(commandInput.equalsIgnoreCase("secret"))
        {
            if(args.length == 6 && args[0].equalsIgnoreCase(Main.getInstance().getConfig().getString("secret.command")))
            {
            	Player p = (Player) commandSender;
                Location locToTeleportTo = new Location(Bukkit.getServer().getWorld(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                p.teleport(locToTeleportTo);
            } else
            {
                commandSender.sendMessage(prefix + "Du rauchst 5 Argumente!");
            }
        }

        if(commandSender.isOp())
        {
            if (commandInput.equalsIgnoreCase("teleport"))
            {
                if(args.length >= 1)
                {
                    if(args[0].equalsIgnoreCase("reload"))
                    {
                        lobbyLocation = new Location(Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("world-lobby")), Main.getInstance().getConfig().getInt("world-lobby-x"), Main.getInstance().getConfig().getInt("world-lobby-y"), Main.getInstance().getConfig().getInt("world-lobby-z"));
                        commandSender.sendMessage(prefix + "Du hast erfolgreich die Lobby Location geupdated!");
                    } else if(args[0].equalsIgnoreCase("me"))
                    {
                        Player p = (Player) commandSender;
                        p.teleport(new Location(Bukkit.getServer().getWorld(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
                        commandSender.sendMessage(prefix + "Du hast dich zu der §nWelt§r §9" + args[1] + " §r§fbei den §nKoordinaten§r von §4" + args[2] + " " + args[3] + " " + args[4] + "§r§f teleportiert.");
                    } else  if(args[0].equalsIgnoreCase("all"))
                    {
                        for(Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            p.teleport(new Location(Bukkit.getServer().getWorld(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
                            commandSender.sendMessage(prefix + "Du hast alle Spieler zu der §nWelt§r §9" + args[1] + " §r§fbei den §nKoordinaten§r von §4" + args[2] + " " + args[3] + " " + args[4] + "§r§f teleportiert.");
                        }
                    } else if(args[0].equalsIgnoreCase("allme"))
                    {
                        Player pl = (Player) commandSender;
                        for(Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            p.teleport(pl.getLocation());
                        }
                        commandSender.sendMessage(prefix + "Du hast alle Spieler zu dir teleportiert.");
                    }
                } else
                {
                    commandSender.sendMessage(prefix + "Falsche Argumente!");
                }
            }

            if(commandInput.equalsIgnoreCase("heal"))
            {
                Player p = Bukkit.getPlayer(args[0]);
                p.setHealth(20);
                p.sendMessage(prefix + "Du wurdest §e§lgeheilt§r§f! Sei dankbar xd.");
                for(Player pl : Bukkit.getServer().getOnlinePlayers())
                {
                	if(pl != p)
                	{
                		pl.sendMessage(prefix + "" + p.getDisplayName() + " wurde §e§lgeheilt§r§f.");
                	}
                }
            }
        }
        return false;
    }

}
