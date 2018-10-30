package me.lowlauch.uhc;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ThreadLocalRandom;

import static me.lowlauch.uhc.Commands.*;
import static me.lowlauch.uhc.UsefulScripts.*;

public class EventListener implements Listener
{
    public void newInventory(Player p)
    {
        Inventory inv = Main.getInstance().getServer().createInventory(null, 27, "§aSelector");
        
        ItemStack snowballGame = new ItemStack(Material.SNOW_BALL);
        ItemMeta snowballGameMeta = snowballGame.getItemMeta();
        snowballGameMeta.setDisplayName("§9Snowball");

        ItemStack lobby = new ItemStack(Material.BEACON);
        ItemMeta lobbyMeta = snowballGame.getItemMeta();
        lobbyMeta.setDisplayName("§4Lobby");

        ItemStack crafting = new ItemStack(Material.WORKBENCH);
        ItemMeta craftingMeta = crafting.getItemMeta();
        craftingMeta.setDisplayName("§7Crafting Rezepte");

        ItemStack team1Wool = null;
        ItemStack team2Wool = null;
        ItemStack team3Wool = null;
        ItemStack team4Wool = null;
        
        ItemMeta team1WoolMeta = null;
        ItemMeta team2WoolMeta = null;
        ItemMeta team3WoolMeta = null;
        ItemMeta team4WoolMeta = null;
        
        if(p.hasMetadata("hasTeam"))
        {
        	if(p.hasMetadata("Team 1"))
            {
            	team1Wool = new ItemStack(Material.WOOL, 1, (short) 1);
            	team2Wool = new ItemStack(Material.WOOL, 1);
            	team3Wool = new ItemStack(Material.WOOL, 1);
            	team4Wool = new ItemStack(Material.WOOL, 1);
            	
            	team1WoolMeta = team1Wool.getItemMeta();
                team2WoolMeta = team2Wool.getItemMeta();
                team3WoolMeta = team3Wool.getItemMeta();
                team4WoolMeta = team4Wool.getItemMeta();
            	
            	team1WoolMeta.setDisplayName("§eTeam 1");
                team2WoolMeta.setDisplayName("§fTeam 2");
                team3WoolMeta.setDisplayName("§fTeam 3");
                team4WoolMeta.setDisplayName("§fTeam 4");
            }
            
            if(p.hasMetadata("Team 2"))
            {
            	team1Wool = new ItemStack(Material.WOOL, 1);
            	team2Wool = new ItemStack(Material.WOOL, 1, (short) 1);
            	team3Wool = new ItemStack(Material.WOOL, 1);
            	team4Wool = new ItemStack(Material.WOOL, 1);
            	
            	team1WoolMeta = team1Wool.getItemMeta();
                team2WoolMeta = team2Wool.getItemMeta();
                team3WoolMeta = team3Wool.getItemMeta();
                team4WoolMeta = team4Wool.getItemMeta();
            	
            	team1WoolMeta.setDisplayName("§fTeam 1");
                team2WoolMeta.setDisplayName("§eTeam 2");
                team3WoolMeta.setDisplayName("§fTeam 3");
                team4WoolMeta.setDisplayName("§fTeam 4");
            }
            
            if(p.hasMetadata("Team 3"))
            {
            	team1Wool = new ItemStack(Material.WOOL, 1);
            	team2Wool = new ItemStack(Material.WOOL, 1);
            	team3Wool = new ItemStack(Material.WOOL, 1, (short) 1);
            	team4Wool = new ItemStack(Material.WOOL, 1);
            	
            	team1WoolMeta = team1Wool.getItemMeta();
                team2WoolMeta = team2Wool.getItemMeta();
                team3WoolMeta = team3Wool.getItemMeta();
                team4WoolMeta = team4Wool.getItemMeta();
            	
            	team1WoolMeta.setDisplayName("§fTeam 1");
                team2WoolMeta.setDisplayName("§fTeam 2");
                team3WoolMeta.setDisplayName("§eTeam 3");
                team4WoolMeta.setDisplayName("§fTeam 4");
            }
            
            if(p.hasMetadata("Team 4"))
            {
            	team1Wool = new ItemStack(Material.WOOL, 1);
            	team2Wool = new ItemStack(Material.WOOL, 1);
            	team3Wool = new ItemStack(Material.WOOL, 1);
            	team4Wool = new ItemStack(Material.WOOL, 1, (short) 1);
            	
            	team1WoolMeta = team1Wool.getItemMeta();
                team2WoolMeta = team2Wool.getItemMeta();
                team3WoolMeta = team3Wool.getItemMeta();
                team4WoolMeta = team4Wool.getItemMeta();
            	
            	team1WoolMeta.setDisplayName("§fTeam 1");
                team2WoolMeta.setDisplayName("§fTeam 2");
                team3WoolMeta.setDisplayName("§fTeam 3");
                team4WoolMeta.setDisplayName("§eTeam 4");
            }
        } else
        {
        	team1Wool = new ItemStack(Material.WOOL, 1);
        	team2Wool = new ItemStack(Material.WOOL, 1);
        	team3Wool = new ItemStack(Material.WOOL, 1);
        	team4Wool = new ItemStack(Material.WOOL, 1);
        	
        	team1WoolMeta = team1Wool.getItemMeta();
            team2WoolMeta = team2Wool.getItemMeta();
            team3WoolMeta = team3Wool.getItemMeta();
            team4WoolMeta = team4Wool.getItemMeta();
        	
        	team1WoolMeta.setDisplayName("§fTeam 1");
            team2WoolMeta.setDisplayName("§fTeam 2");
            team3WoolMeta.setDisplayName("§fTeam 3");
            team4WoolMeta.setDisplayName("§fTeam 4");
        }
        
        team1Wool.setItemMeta(team1WoolMeta);
        team2Wool.setItemMeta(team2WoolMeta);
        team3Wool.setItemMeta(team3WoolMeta);
        team4Wool.setItemMeta(team4WoolMeta);
        
        lobby.setItemMeta(lobbyMeta);
        snowballGame.setItemMeta(snowballGameMeta);
        crafting.setItemMeta(craftingMeta);

        inv.setItem(11, lobby);
        inv.setItem(13, snowballGame);
        inv.setItem(15, crafting);
        
        inv.setItem(0, team1Wool);
        inv.setItem(8, team2Wool);
        inv.setItem(18, team3Wool);
        inv.setItem(26, team4Wool);

        p.openInventory(inv);
 }
 
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if(!playing)
        {
            if(event.getEntity() instanceof Player)
            {
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerDamageOtherPlayer(EntityDamageByEntityEvent event)
    {
        if(playing)
        {
            if(event.getEntity() instanceof Player && teamsEnabled)
            {
                Player p = (Player) event.getDamager();
                if(p.hasMetadata("Team 1") && event.getDamager().hasMetadata("Team 1") || p.hasMetadata("Team 2") && event.getDamager().hasMetadata("Team 2") || p.hasMetadata("Team 3") && event.getDamager().hasMetadata("Team 3") || p.hasMetadata("Team 4") && event.getDamager().hasMetadata("Team 4"))
                {
                    p.sendMessage(prefix + "Du kannst deine Team-Member nicht angreifen!");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event)
    {
        if(!playing && event.getPlayer().getWorld() == Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("world.lobby")))
        {
            event.getPlayer().setFoodLevel(20);
            event.getPlayer().setSaturation(20f);
            if(uhcWorld != null && event.getPlayer().getWorld() != null)
            {
                if(event.getPlayer().getWorld() == Bukkit.getServer().getWorld(uhcWorld) && lobbyLocation != null)
                {
                    event.getPlayer().teleport(lobbyLocation);
                }
            }

            ItemStack readyDiamond = new ItemStack(Material.DIAMOND, 1);
            ItemMeta readyDiamondMeta = readyDiamond.getItemMeta();
            readyDiamondMeta.setDisplayName("§9§lReady");
            readyDiamond.setItemMeta(readyDiamondMeta);

            if(!event.getPlayer().getInventory().contains(readyDiamond))
            {
                if(event.getPlayer().getGameMode() != GameMode.CREATIVE && event.getPlayer().getGameMode() != GameMode.SPECTATOR)
                {
                    event.getPlayer().getInventory().setItem(4, readyDiamond);
                    readyDiamond.setItemMeta(readyDiamondMeta);
                }
            }

            ItemStack lobbyCompass = new ItemStack(Material.COMPASS, 1);
            ItemMeta lobbyCompassMeta = lobbyCompass.getItemMeta();
            lobbyCompassMeta.setDisplayName("§aSelector");
            lobbyCompass.setItemMeta(lobbyCompassMeta);

            if(!event.getPlayer().getInventory().contains(lobbyCompass))
            {
                if(event.getPlayer().getGameMode() != GameMode.CREATIVE && event.getPlayer().getGameMode() != GameMode.SPECTATOR)
                {
                    event.getPlayer().getInventory().setItem(0, lobbyCompass);
                    lobbyCompass.setItemMeta(lobbyCompassMeta);
                }
            } 
            
            Player p = event.getPlayer();
            if(p.hasMetadata("isReady"))
            {
                p.setExp(1f);
            } else
            {
                p.setExp(0f);
            }
        }
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        ((Player) event.getEntity()).setExp(0);
        ((Player) event.getEntity()).setLevel(0);
        
        if(((Player) event.getEntity()).hasMetadata("Team 1"))
        {
        	team1Members--;
        	((Player) event.getEntity()).removeMetadata("Team 1", Main.getInstance());
        }
        
        if(((Player) event.getEntity()).hasMetadata("Team 2"))
        {
        	team2Members--;
        	((Player) event.getEntity()).removeMetadata("Team 2", Main.getInstance());
        }
        
        if(((Player) event.getEntity()).hasMetadata("Team 3"))
        {
        	team3Members--;
        	((Player) event.getEntity()).removeMetadata("Team 3", Main.getInstance());
        }
        
        if(((Player) event.getEntity()).hasMetadata("Team 4"))
        {
        	team4Members--;
        	((Player) event.getEntity()).removeMetadata("Team 4", Main.getInstance());
        }
        
        if(playing)
        {
        	alivePlayers--;
        	
            if(event.getEntity().getKiller() instanceof Player)
            {
                event.setDeathMessage(prefix + "§e§l" + ( (Player) event.getEntity() ).getDisplayName() + " §r§fwurde von §6§l" + event.getEntity().getKiller().getDisplayName() + " §r§fgetötet.");
            } else
            {
                event.setDeathMessage(prefix + "§e§l" + ( (Player) event.getEntity() ).getDisplayName() + "§f§r ist §4gestorben§r§f.");
            }
            
            if(alivePlayers <= 1 && !teamsEnabled)
            {
                if(event.getEntity().getKiller() != null)
                {
                    Bukkit.getServer().broadcastMessage(prefix + "§e§l§n§o" + event.getEntity().getKiller().getDisplayName() + "§r§f hat gewonnen!");
                } else
                {
                    Bukkit.getServer().broadcastMessage(prefix + "§e§l§n§oEine mysteriöse Person§r§f hat gewonnen!");
                }
                for(Player p : Bukkit.getServer().getOnlinePlayers())
                {
                    if(lobbyLocation != null)
                    {
                        p.teleport(lobbyLocation);
                    }
                    p.getInventory().clear();
                    clearArmor(p);
                    p.setGameMode(GameMode.ADVENTURE);
                    p.setFoodLevel(20);
                    p.setHealth(20f);
                }
                playing = false;
            }
            
            if(teamsEnabled)
            {
            	if(team1Members >= 1 && team2Members <= 0 && team3Members <= 0 && team4Members <= 0)
            	{
            		Bukkit.getServer().broadcastMessage(prefix + "§e§lTeam 1§r§f hat gewonnen!");
            	} else if(team1Members <= 0 && team2Members >= 1 && team3Members <= 0 && team4Members <= 0)
            	{
            		Bukkit.getServer().broadcastMessage(prefix + "§e§lTeam 2§r§f hat gewonnen!");
            	} else if(team1Members <= 0 && team2Members <= 0 && team3Members >= 1 && team4Members <= 0)
            	{
            		Bukkit.getServer().broadcastMessage(prefix + "§e§lTeam 3§r§f hat gewonnen!");
            	} else if(team1Members <= 0 && team2Members <= 0 && team3Members <= 0 && team4Members >= 1)
            	{
            		Bukkit.getServer().broadcastMessage(prefix + "§e§lTeam 4§r§f hat gewonnen!");
            	}
            	
            	for(Player p : Bukkit.getServer().getOnlinePlayers())
                {
                    if(lobbyLocation != null)
                    {
                        p.teleport(lobbyLocation);
                    }
                    p.getInventory().clear();
                    clearArmor(p);
                    p.setGameMode(GameMode.ADVENTURE);
                    p.setFoodLevel(20);
                    p.setHealth(20f);
                }
                playing = false;
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event)
    {
        if(!playing && event.getPlayer().getGameMode() == GameMode.ADVENTURE || !playing && event.getPlayer().getGameMode() == GameMode.SURVIVAL)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage(prefix + event.getPlayer().getDisplayName() + " hat das Spiel §a§lbetreten§r§f. Somit sind §e§l" + Bukkit.getServer().getOnlinePlayers().size() + "§f§r Spieler online!");
        if(playing)
        {
        	event.getPlayer().setGameMode(GameMode.SURVIVAL);
            if(event.getPlayer().getWorld() == Bukkit.getServer().getWorld(uhcWorld))
            {
                alivePlayers++;
            }
        }
        
        if(!playing && event.getPlayer().getWorld() != Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("world.lobby")))
        {
        	event.getPlayer().teleport(lobbyLocation);
        }
        
        if(!playing)
        {
        	event.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        event.setQuitMessage(prefix + event.getPlayer().getDisplayName() + " hat das Spiel §4§lverlassen§r§f. Somit sind §e§l" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "§f§r online!");
        if(playing)
        {
            if(event.getPlayer().getWorld() == Bukkit.getServer().getWorld(uhcWorld))
            {
                alivePlayers--;
            }
        } else
        {
        	if(event.getPlayer().hasMetadata("isReady"))
        	{
        		readyPlayers--;
        	}
        }
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event)
    {
        if (event.getBlock().getType() == Material.BARRIER && event.getPlayer().getGameMode() == GameMode.SURVIVAL && playing)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.PHYSICAL && !playing && event.getClickedBlock().getType() != Material.STONE_PLATE)
        {
            event.setCancelled(true);
        }

        if(event.getMaterial() == Material.DIAMOND && !playing)
        {
            Bukkit.getServer().dispatchCommand(event.getPlayer(), "wannastart");
        }
        
        if(event.getMaterial() == Material.COMPASS)
        {
        	newInventory(event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player p = (Player) event.getWhoClicked();
        ClickType clickType = event.getClick();
        Inventory thisInv = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if(thisInv.getName().equals("§aSelector"))
        {
            if(clickedItem == null || !clickedItem.hasItemMeta())
            {
                return;
            }

            if(clickedItem.getItemMeta().getDisplayName().equals("§9Snowball"))
            {
                Bukkit.dispatchCommand(p, "snowball lobby");
                Bukkit.dispatchCommand(p, "snowball kit");
            }

            if(clickedItem.getItemMeta().getDisplayName().equals("§4Lobby"))
            {
                p.teleport(lobbyLocation);
                p.getInventory().clear();
            }

            if(clickedItem.getItemMeta().getDisplayName().equals("§7Crafting Rezepte"))
            {
                Bukkit.dispatchCommand(p, "secret " + Main.getInstance().getConfig().getString("secret.command") + " lobbyWorld -303 32 198");
            }
            
            if(clickedItem.getItemMeta().getDisplayName().contains("Team 1"))
            {
                Bukkit.dispatchCommand(p, "team join 1");
                p.closeInventory();
            }
            
            if(clickedItem.getItemMeta().getDisplayName().contains("Team 2"))
            {
                Bukkit.dispatchCommand(p, "team join 2");
                p.closeInventory();
            }
            
            if(clickedItem.getItemMeta().getDisplayName().contains("Team 3"))
            {
                Bukkit.dispatchCommand(p, "team join 3");
                p.closeInventory();
            }
            
            if(clickedItem.getItemMeta().getDisplayName().contains("Team 4"))
            {
                Bukkit.dispatchCommand(p, "team join 4");
                p.closeInventory();
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event)
    {
        if(!playing && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL) && event.getPlayer().getWorld().equals(lobbyWorld)) { event.setCancelled(true); }
        if(event.getBlock().getType() == Material.IRON_ORE)
        {
            int diamondChance = ThreadLocalRandom.current().nextInt(1, 50 + 1);
            if(diamondChance > 1)
            {
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
            } else
            {
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
            }
        }

        if(event.getBlock().getType() == Material.SAND)
        {
            if(event.getPlayer().getItemInHand().getType() == Material.STONE_SPADE || event.getPlayer().getItemInHand().getType() == Material.DIAMOND_SPADE || event.getPlayer().getItemInHand().getType() == Material.IRON_SPADE || event.getPlayer().getItemInHand().getType() == Material.WOOD_SPADE || event.getPlayer().getItemInHand().getType() == Material.GOLD_SPADE)
            {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GLASS));
                event.getBlock().setType(Material.AIR);
            } else
            {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SAND));
                event.getBlock().setType(Material.AIR);
            }
        }

        if(event.getBlock().getType() == Material.GOLD_ORE)
        {
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
        }

        if(event.getBlock().getType() == Material.LEAVES && event.getPlayer().getItemInHand() != new ItemStack(Material.SHEARS) || event.getBlock().getType() == Material.LEAVES_2 && event.getPlayer().getItemInHand() != new ItemStack(Material.SHEARS))
        {
            int appleChance = ThreadLocalRandom.current().nextInt(1, 25 + 1);
            int goldenAppleChance = ThreadLocalRandom.current().nextInt(1, 75 + 1);
            int sugarCaneChance = ThreadLocalRandom.current().nextInt(1, 25 + 1);
            
            if(goldenAppleChance <= 1)
            {
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE));
                appleChance = 420;
            }
            
            if(sugarCaneChance <= 1)
            {
            	event.getBlock().setType(Material.AIR);
            	event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SUGAR_CANE));
            }

            if(appleChance <= 1)
            {
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE));
                goldenAppleChance = 1337;
            }
        }
    }

    @EventHandler
    public void onPlayerRegen(EntityRegainHealthEvent event)
    {
        if(playing)
        {
            if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            {
                event.setCancelled(true);
            }
        }
    }
}