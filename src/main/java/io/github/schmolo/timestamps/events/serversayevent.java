package io.github.schmolo.timestamps.events;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class serversayevent  implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("say")) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatDateTime = now.format(formatter);

            String time = "[" + formatDateTime + "] ";

            String colon = ChatColor.WHITE + ": ";

            String msg = ChatColor.WHITE + String.join(" ", Arrays.copyOfRange(args, 0, args.length));

            String sendername = null;

            if (!(sender instanceof Player)) {

                sendername = ChatColor.RED + "[SERVER]";

            } else if (sender.getName().equals("PolyLogic")) {

                sendername = ChatColor.of("#d4006e") + "Pol" + ChatColor.of("#9b4c94") + "yLo" + ChatColor.of("#0931a5") + "gic";

            } else if (sender.isOp()) {

                sendername = ChatColor.RED + sender.getName();

            } else {

                sendername = ChatColor.WHITE + sender.getName();
            }

            Bukkit.broadcastMessage(time + sendername + colon +  msg);

            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length > 0) {

            List<String> arguments = new ArrayList<>();

            return arguments;
        }

        return null;
    }
}