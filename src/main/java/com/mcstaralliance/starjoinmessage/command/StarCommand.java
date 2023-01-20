package com.mcstaralliance.starjoinmessage.command;

import com.mcstaralliance.starjoinmessage.StarJoinMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StarCommand implements CommandExecutor {
    final String PREFIX = "[StarLoginMessage] ";
    final String PRIMARY_MESSAGE = PREFIX + "Type /slm reload to reload config.";
    final String RELOAD_MESSAGE = PREFIX + "插件已重载";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length != 1){
            sender.sendMessage(PRIMARY_MESSAGE);
            return false;
        }
        if(args[0].equalsIgnoreCase("reload") && (sender.isOp() || !(sender instanceof Player))){
            StarJoinMessage.getInstance().reloadConfig();
            sender.sendMessage(RELOAD_MESSAGE);
            return true;
        }
        return false;
    }
}
