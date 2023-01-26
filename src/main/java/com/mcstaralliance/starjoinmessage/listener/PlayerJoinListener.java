package com.mcstaralliance.starjoinmessage.listener;

import com.mcstaralliance.starjoinmessage.StarJoinMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(player.isOp())
            return;
        FileConfiguration config = StarJoinMessage.getInstance().getConfig();
        List<String> permissions = config.getStringList("settings.permissions");
        List<String> messages = config.getStringList("settings.messages");
        List<String> processedMessages = new ArrayList<>();
        processedMessages = processMultiline(messages, processedMessages);
        processSend(permissions, processedMessages, player);
    }

    public void processSend(List<String> permissions, List<String> processedMessages, Player player){
        for (String permission : permissions) {
            if (player.hasPermission(permission)) {
                for (String processedMessage : processedMessages) {
                    String messageToSend = processedMessage.replaceAll("%player%", player.getName());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messageToSend));
                }
                return;
            }
        }
    }

    public boolean isMultiline(List<String> messages){
        for (String message : messages) {
            return message.contains("\n");
        }
        return false;
    }

    public List<String> processMultiline(List<String> messages, List<String> processed) {
        for (int i = 0; i < messages.size(); i++) {
            if (isMultiline(messages)) {
                Collections.addAll(processed, messages.get(i).split("\n"));
            }
        }
        return processed;
    }
}



