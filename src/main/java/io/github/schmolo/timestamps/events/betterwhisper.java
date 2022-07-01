package io.github.schmolo.timestamps.events;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class betterwhisper implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("msg")) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatDateTime = now.format(formatter);

            String time = ChatColor.WHITE + "[" + formatDateTime + "] ";

            TextComponent messagesender = new TextComponent(time);
            messagesender.setColor(ChatColor.WHITE);

            TextComponent messagetarget = new TextComponent(time);
            messagetarget.setColor(ChatColor.WHITE);

            Player target = Bukkit.getServer().getPlayer(args[0]);

            String msg = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            String whispersmsg = null;

            if (!(sender instanceof Player)) {

                String targetmessage = "[SERVER] whispers: " + msg;
                target.sendMessage(time + ChatColor.of("#FF8000") + targetmessage );
                sender.sendMessage(time + ChatColor.of("#FF8000") + "To [" +  ChatColor.of("#FF8000") + target.getName() + ChatColor.of("#FF8000") + "]: " + ChatColor.of("#FF8000") + msg);

            } else {

                TextComponent sendercomp = new TextComponent("[" + sender.getName() + "]");
                sendercomp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/w " + sender.getName() + " "));
                sendercomp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to Answer!").color(ChatColor.of("#FF8000")).italic(true).create()));

                whispersmsg = " whispers: " + msg;

                sendercomp.addExtra(whispersmsg);
                sendercomp.setColor(ChatColor.of("#FF8000"));

                messagetarget.addExtra(sendercomp);
            }

            TextComponent targetcomp = new TextComponent("To [" + target.getName() + "]");
            targetcomp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/w " + target.getName() + " "));
            targetcomp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("Click here to Answer!").color(ChatColor.of("#FF8000")).italic(true).create()));

            String tomsg = ": " + msg;

            targetcomp.addExtra(tomsg);
            targetcomp.setColor(ChatColor.of("#FF8000"));

            messagesender.addExtra(targetcomp);

            if (sender instanceof Player) {
                sender.spigot().sendMessage(messagesender);
                target.spigot().sendMessage(messagetarget);
            }

            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (args.length == 1) {

                List<String> playerNames = new ArrayList<>();

                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];

                Bukkit.getServer().getOnlinePlayers().toArray(players);

                for (int i = 0; i < players.length; i++) {

                    playerNames.add(players[i].getName());

                }

                List<String> result = new ArrayList<>();

                for (String a : playerNames)
                {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    {
                        result.add(a);
                    }
                }

                return result;

            } else if (args.length >= 2) {

                List<String> arguments = new ArrayList<>();

                return arguments;
            }
            return null;
        }
        return null;
    }
}
