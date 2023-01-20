package com.mcstaralliance.starjoinmessage;

import com.mcstaralliance.starjoinmessage.command.StarCommand;
import com.mcstaralliance.starjoinmessage.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarJoinMessage extends JavaPlugin {

    private static StarJoinMessage instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginCommand("slm").setExecutor(new StarCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static StarJoinMessage getInstance(){
        return instance;
    }
}
