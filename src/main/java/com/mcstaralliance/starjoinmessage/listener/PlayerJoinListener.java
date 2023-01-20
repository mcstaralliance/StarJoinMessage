package com.mcstaralliance.starjoinmessage.listener;

import com.mcstaralliance.starjoinmessage.StarJoinMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        FileConfiguration config = StarJoinMessage.getInstance().getConfig();
        Player player = e.getPlayer();
        List<String> permissions = config.getStringList("settings.permissions");
        List<String> messages = config.getStringList("settings.messages");


        for(int i = 0;i < permissions.size();i++){
            if(player.hasPermission(permissions.get(i))){
                String messageToSend = messages.get(i).replaceAll("%player%", e.getPlayer().getName());
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',messageToSend));
                return;
            }
        }

    }
}
