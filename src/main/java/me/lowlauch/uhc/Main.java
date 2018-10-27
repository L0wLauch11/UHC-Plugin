package me.lowlauch.uhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin
{

    private static Main instance;
    public static Main getInstance() { return instance; }

    @Override
    public void onEnable()
    {
        instance = this;
        
        //Init config
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        
        //Initializing commands
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("startuhc").setExecutor(new Commands(this));
        getCommand("heal").setExecutor(new Commands(this));
        getCommand("bc").setExecutor(new Commands(this));
        getCommand("wannastart").setExecutor(new Commands(this));
        getCommand("enduhc").setExecutor(new Commands(this));
        getCommand("teleport").setExecutor(new Commands(this));
        getCommand("secret").setExecutor(new Commands(this));
        getCommand("autostartuhc").setExecutor(new Commands(this));
        getCommand("toggleautostart").setExecutor(new Commands(this));
        getCommand("remake").setExecutor(new Commands(this));
        getCommand("team").setExecutor(new Commands(this));
        getCommand("teams").setExecutor(new Commands(this));
        getLogger().info("Enabled!");

        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            p.setExp(0f);
            
            if(p.hasMetadata("Team 1"))
            {
            	p.removeMetadata("Team 1", this);
            }
            
            if(p.hasMetadata("Team 2"))
            {
            	p.removeMetadata("Team 2", this);
            }
            
            if(p.hasMetadata("Team 3"))
            {
            	p.removeMetadata("Team 3", this);
            }
            
            if(p.hasMetadata("Team 4"))
            {
            	p.removeMetadata("Team 4", this);
            }
            
            if(p.hasMetadata("hasTeam"))
            {
            	p.removeMetadata("hasTeam", this);
            }
        }

        Bukkit.getServer().getWorld("world").setPVP(false);

        new BukkitRunnable() {
            public void run() {
                OnUpdate.run();
            }
        }.runTaskTimer(this, 0, 20);

        //Custom Recipes

        CustomRecipes.initRecipes();
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Disabled!");
    }
}