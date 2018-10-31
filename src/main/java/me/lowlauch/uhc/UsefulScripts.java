package me.lowlauch.uhc;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Random;

import static me.lowlauch.uhc.Commands.world;

public class UsefulScripts
{
    public static World createWorld(final String name)
    {
        world = Bukkit.getWorld(name);
        return world == null ? Bukkit.createWorld(new WorldCreator(name)) : world;
    }
    
	public static boolean deleteWorld(File path)
	{
	      if(path.exists()) {
	          File files[] = path.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  deleteWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	      return(path.delete());
	}

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static void clearArmor(Player p)
    {
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
    }

    public static double borderToPlayer(Player p, int d) {
        WorldBorder w = p.getWorld().getWorldBorder();

        Location c = w.getCenter();
        Location l = p.getLocation();
        c.setY(0);
        l.setY(0);

        double s = w.getSize();

        switch (d) {
            case 0:
                c.add(l.getX(), 0, s / 2.0);
                return l.distance(c);
            case 1:
                c.add(s / 2.0, 0, l.getZ());
                return l.distance(c);
            case 2:
                c.add(l.getX(), 0, s / -2.0);
                return l.distance(c);
            case 3:
                c.add(s / -2.0, 0, l.getZ());
                return l.distance(c);
            default:
                return -1;
        }
    }
}
