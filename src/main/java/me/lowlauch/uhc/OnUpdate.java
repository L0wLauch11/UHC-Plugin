package me.lowlauch.uhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lowlauch.uhc.Commands.*;
import static me.lowlauch.uhc.UsefulScripts.*;

public class OnUpdate
{
    static double borderDistanceXP;
    static double borderDistanceXN;
    static double borderDistanceZP;
    static double borderDistanceZN;
    public static void run()
    {
        if(playing)
        {
            for(Player p : Bukkit.getServer().getOnlinePlayers())
            {
                borderDistanceXP = borderToPlayer(p, 0);
                borderDistanceXN = borderToPlayer(p, 1);
                borderDistanceZP = borderToPlayer(p, 2);
                borderDistanceZN = borderToPlayer(p, 3);

                if (borderDistanceXP <= 10 || borderDistanceXN <= 10 || borderDistanceZP <= 10 || borderDistanceZN <= 10)
                {
                    if (borderDistanceXP < borderDistanceXN && borderDistanceXP < borderDistanceZP && borderDistanceXP < borderDistanceZN) {
                        p.sendMessage(prefix + "Du bist nurnoch §4§l§kU§r §4§l" + (int) borderDistanceXP + " Blöcke §kU§r §r§fvon der §4§lBORDER§f§r entfernt!");
                    } else if (borderDistanceXN < borderDistanceXP && borderDistanceXN < borderDistanceZP && borderDistanceXN < borderDistanceZN) {
                        p.sendMessage(prefix + "Du bist nurnoch §4§l§kU§r §4§l" + (int) borderDistanceXN + " Blöcke §kU§r §r§fvon der §4§lBORDER§f§r entfernt!");
                    } else if (borderDistanceZP < borderDistanceXN && (int) borderDistanceZP < borderDistanceXP && borderDistanceZP < borderDistanceZN) {
                        p.sendMessage(prefix + "Du bist nurnoch §4§l§kUWU§r §4§l" + (int) borderDistanceZP + " Blöcke §kU§r §r§fvon der §4§lBORDER§f§r entfernt!");
                    } else if (borderDistanceZN < borderDistanceXN && borderDistanceZN < borderDistanceZP && borderDistanceZN < borderDistanceXP) {
                        p.sendMessage(prefix + "Du bist nurnoch §4§l§kU§r §4§l" + (int) borderDistanceZN + " Blöcke §kU§r §r§fvon der §4§lBORDER§f§r entfernt!");
                    }
                }
            }

            if(playing && protectionTime > 0)
            {
                if(protectionTime == Main.getInstance().getConfig().getInt("protection.time"))
                {
                    Bukkit.getServer().broadcastMessage(prefix + "Die §e§lSchutzzeit§r§f dauert noch §4§l" + protectionTime / 60 + "§f§r Minuten!");
                }

                if(protectionTime == (int) Main.getInstance().getConfig().getInt("protection.time") / 2)
                {
                    Bukkit.getServer().broadcastMessage(prefix + "Die §e§lSchutzzeit§r§f dauert nurnoch §4§l" + protectionTime / 60 / 2 + "§f§r Minuten!");
                }

                if(protectionTime == 60)
                {
                    Bukkit.getServer().broadcastMessage(prefix + "Die §e§lSchutzzeit§r§f dauert nurnoch §4§l1§f§r Minute, beeil dich!");
                }

                if(protectionTime <= 10)
                {
                    Bukkit.getServer().broadcastMessage(prefix + "Die Schutzzeit dauert nurnoch §4§l" + protectionTime + "§r§f Sekunden, SCHNELL!");
                }
            }
            protectionTime--;
            if(protectionTime <= 0 && !pvp)
            {
                Bukkit.getServer().broadcastMessage(prefix + "Die §e§lSchutzzeit§r§f ist §4§laus§f§r!");
                Bukkit.getServer().getWorld(uhcWorld).setPVP(true);
                pvp = true;
            }

            if(protectionTime <= Main.getInstance().getConfig().getInt("protection.border") && protectionTime >= Main.getInstance().getConfig().getInt("protection.border") - 5)
            {
                Bukkit.getServer().broadcastMessage(prefix + "DIE §4§lBORDER§f§r WIRD VERKLEINERT!");
            }

            if(protectionTime <= -600 && Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().getSize() >= 50)
            {
                Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().setSize(Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().getSize() - 1);
                Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().setWarningDistance(15);
                Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().setDamageBuffer(5);
                if(Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().getSize() <= 75 && Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().getSize() >= 50)
                {
                    Bukkit.getServer().broadcastMessage(prefix + "DIE §4§lBORDER§f§r IST NURNOCH " + ( (int) Bukkit.getServer().getWorld(uhcWorld).getWorldBorder().getSize() ) + " BLÖCKE GROß!");
                }
            }
        }

        if(autoStart)
        {
            autoStartSeconds--;
            if(autoStartSeconds <= 10)
            {
            	 Bukkit.getServer().broadcastMessage(prefix + "Ihr müsst nur noch §5§l" + autoStartSeconds + "§r§fs warten");
            }
            for(Player p : Bukkit.getServer().getOnlinePlayers())
            {
            	p.setLevel(autoStartSeconds);
            }
            
            if (autoStartSeconds <= 0 && readyPlayers >= startAmountPlayers)
            {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "startuhc");
                autoStart = false;
            } else if(autoStartSeconds <= 0 && readyPlayers < startAmountPlayers)
            {
                autoStartSeconds = 30;
                Bukkit.getServer().broadcastMessage(prefix + "Nicht genügend §2§lSpieler§r§f. §9Timer§f wird wieder auf §430§fs gesetzt.");
            }
        }
    }
}
