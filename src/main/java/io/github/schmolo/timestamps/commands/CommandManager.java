package io.github.schmolo.timestamps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(){
        subCommands.add(new ChatColorSub());
        subCommands.add(new PrefixSub());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        getSubCommands().get(i).perform(player, args);
                    }
                }
            } else if (args.length == 0) {
                player.sendMessage("--------------------------------------");
                player.sendMessage("/ts commands:");
                for (int i = 0; i < getSubCommands().size(); i++) {
                    player.sendMessage(getSubCommands().get(i).getUsage() + " - " + getSubCommands().get(i).getDescription());
                }
                player.sendMessage("--------------------------------------");
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            ArrayList<String> subCommandsArgs = new ArrayList<>();

            for (int i = 0; i < getSubCommands().size(); i++) {
                subCommandsArgs.add(getSubCommands().get(i).getName());
            }
            return  subCommandsArgs;

        } else if (args.length >= 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                    return getSubCommands().get(i).getArgs((Player) sender, args);
                }
            }
        }
        return null;
    }
}
