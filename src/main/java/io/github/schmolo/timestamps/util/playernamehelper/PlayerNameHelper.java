package io.github.schmolo.timestamps.util.playernamehelper;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.schmolo.timestamps.util.TimsLib.PrintDebug;


public class PlayerNameHelper {
    // This is a helper for the usage of colored names
    // The Idea is that you only need to write code that looks like this:

    // playernamehelper.getName("069a79f4-44e9-4726-a5be-fca90e38aaf5")

    // and that just spits out the correct name for that Person



    // This is for the Singleton Pattern
    // so that only one list of Player Names can exist at one time

    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile PlayerNameHelper instance;
    public static PlayerNameHelper getInstance() {
        // The approach taken here is called double-checked locking (DCL). It
        // exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        //
        // It may seem that having the `result` variable here is completely
        // pointless. There is, however, a very important caveat when
        // implementing double-checked locking in Java, which is solved by
        // introducing this local variable.
        //
        // You can read more info DCL issues in Java here:
        // https://refactoring.guru/java-dcl-issue
        PlayerNameHelper result = instance;
        if (result != null) {
            return result;
        }
        synchronized(PlayerNameHelper.class) {
            if (instance == null) {
                instance = new PlayerNameHelper();
            }
            return instance;
        }
    }

    // Private for the Singleton Pattern
    private PlayerNameHelper() {

    }

    // Get the names from the Config and assign them
    public static PlayerNameHelper getNamesfromConfig() {
        // Gets a PlayerNameHelper
        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        String workingDir = Bukkit.getServer().getPluginManager().getPlugin("Timestamps").getDataFolder() + "/";

        // Reads the playernames.yml
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(workingDir + "playernames.yml"));
        } catch (FileNotFoundException e) { }

        // Check if the File is Empty
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader(workingDir + "playernames.yml"));
        } catch (FileNotFoundException e) { }

        String nice = buf.lines().collect(Collectors.joining());

        // If the file is not empty
        if(nice != "") {

            PrintDebug("Trying to load playernames.yml");
            // Reads the Yaml into a List<PlayerNameConfig>
            Yaml yaml = new Yaml(new Constructor(List.class));
            List<LinkedHashMap<String, Object>> data = yaml.load(inputStream);

            PrintDebug("Success");
            // Adds the data into the playerNameHelper class
            playerNameHelper.add(data);
        }

        return playerNameHelper;
    }

    // update or set names
    public void updateNames() {

        String workingDir = Bukkit.getServer().getPluginManager().getPlugin("Timestamps").getDataFolder() + "/";

        // Dumping Options for saving the PlayerNameConfig into a yaml
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // Custom Representer for all the output formatting
        CustomRepresenter customRepresenter = new CustomRepresenter();

        // This is to remove the stupid !! in the .yml file
        customRepresenter.addClassTag(PlayerNameConfig.class, Tag.MAP);
        Yaml yaml = new Yaml(customRepresenter, options);

        // The list of PlayerNameConfig that will be written back into the file
        List<PlayerNameConfig> bruh = new ArrayList<>();

        // Building of the List from the ColoredNames Hashmap
        for (String uuid : this.playernames.keySet()) {
            // The ColoredName of the user
            ColoredName currname = this.playernames.get(uuid);

            PlayerNameConfig temp = new PlayerNameConfig();

            temp.setUUID(uuid);
            temp.setName(ChatColor.stripColor(currname.getName()));
            temp.bruhColoredName(currname);

            bruh.add(temp);
        }

        // This is for saving the yaml file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(workingDir + "playernames.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        yaml.dump(bruh, writer);

    }

    // Creates the List of PlayerNameConfig from the Hashmap
    public void add(List<LinkedHashMap<String, Object>> nameConfigs) {

        List<PlayerNameConfig> newNameConfigs = new ArrayList<>();
        ObjectMapper mapped = new ObjectMapper();

        for (LinkedHashMap<String, Object> yeah : nameConfigs) {
            PlayerNameConfig p = mapped.convertValue(yeah, PlayerNameConfig.class);
            newNameConfigs.add(p);
        }

        for (PlayerNameConfig p : newNameConfigs) {
            String uuid = p.uuid;

            addName(uuid, p.getColoredName());
        }
    }

    // Key: PlayerUUID  Value: ColoredName of the Player
    private HashMap<String, ColoredName> playernames = new HashMap<>();

    public void addName(String player, ColoredName name) {
        if (name != null) {
            playernames.put(player, name);
        }
    }

    public String getName(String uuid) {
        if(playernames.isEmpty()) return null;
        return playernames.get(uuid).getName();
    }
}


