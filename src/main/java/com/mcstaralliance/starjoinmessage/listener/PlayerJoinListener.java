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
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(player.isOp()) return;
        FileConfiguration config = StarJoinMessage.getInstance().getConfig();
        List<String> permissions = config.getStringList("settings.permissions");
        List<String> messages = config.getStringList("settings.messages");
        processSend(permissions, messages, player);
    }

    public void processSend(List<String> permissions, List<String> messages, Player player){
        for (int i = 0;i < permissions.size();i++) {
            if (player.hasPermission(permissions.get(i))) {
                if(isMultiline(messages.get(i))){
                    String[] processedMessages = messages.get(i).split("\n");
                    for (String processedMessage : processedMessages) {
                        String messageToSend = processedMessage.replaceAll("%player%", player.getName());
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messageToSend));
                    }
                }else{
                    String processedMessages = messages.get(i);
                    String messageToSend = processedMessages.replaceAll("%player%", player.getName());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messageToSend));
                }
                return;
            }
        }
    }

    public boolean isMultiline(String message){
        return message.contains("\n");
    }

}



