package io.github.schmolo.timestamps;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getServer;

public class PlayerNameHelper {
    // This is a helper for the usage of colored names
    // The Idea is that you only need to write code that looks like this:

    // playernamehelper.getName("e62b0898-4189-4ae4-9cf5-c9f9fcc004bc")

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

        // TODO: Move this code to /nick once nick is done
        // Dumping Options for saving the ColoredName into a yaml
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // Custom Representer for all the output formatting
        CustomRepresenter customRepresenter = new CustomRepresenter();

        // This is to remove the stupid !! in the .yml file
        customRepresenter.addClassTag(PlayerNameConfig.class, Tag.MAP);
        Yaml yam = new Yaml(customRepresenter, options);

        // DEBUG: Temp List of Players with ColoredNames for Testing
        List<PlayerNameConfig> bruh = new ArrayList<>();

        // DEBUG: Example of one Player
        PlayerNameConfig p1 = new PlayerNameConfig();
        p1.setUUID("fdfb5640-0e8e-462e-8b06-69102f43e7f3");
        p1.setName("PolyLogic");
        Segment s1 = new Segment("Pol", "#d4006e");
        Segment s2 = new Segment("yLo", "#9b4c94");
        Segment s3 = new Segment("gic", "#0931a5");
        List<Segment> segments = new ArrayList<>();
        segments.add(s1);
        segments.add(s2);
        segments.add(s3);
        p1.setSegments(segments);

        // DEBUG: Example of another Player
        PlayerNameConfig p2 = new PlayerNameConfig();
        p2.setUUID("e62b0898-4189-4ae4-9cf5-c9f9fcc004bc");
        p2.setName("oloadrian");
        Gradient g = new Gradient("#00ff00", "#ff8000");
        p2.setGradient(g);

        bruh.add(p1);
        bruh.add(p2);

        // This is for saving the yaml file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("plugins/timestamps/test.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        yam.dump(bruh, writer);

























        // Creates a Temp PlayerNameHelper
        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        // Reads the playernames.yml
        InputStream inputStream = null;

        try {
            Files.createDirectories(Paths.get("plugins/timestamps"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File yourFile = new File("plugins/timestamps/playernames.yml");
        try {
            yourFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            inputStream = new FileInputStream(new File("plugins/timestamps/playernames.yml"));

        } catch (FileNotFoundException e) {

        }

        // Check if the File is Empty
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader("plugins/timestamps/playernames.yml"));
        } catch (FileNotFoundException e) { }

        String nice = buf.lines().collect(Collectors.joining());


        if(nice != "") {

            getServer().getConsoleSender().sendMessage(nice);
            getServer().getConsoleSender().sendMessage("Trying to load yaml");
            // Reads the Yaml into a List<PlayerNameConfig>
            Yaml yaml = new Yaml(new Constructor(List.class));
            List<LinkedHashMap<String, Object>> data = yaml.load(inputStream);

            getServer().getConsoleSender().sendMessage("Success");
            // Adds the data into the playerNameHelper class
            playerNameHelper.add(data);
        }

        return playerNameHelper;
    }

    // Converts the List of PlayerNameConfig into the Hashmap
    public void add(List<LinkedHashMap<String, Object>> nameConfigs) {

        List<PlayerNameConfig> newNameConfigs = new ArrayList<>();
        ObjectMapper mapped = new ObjectMapper();

        for (LinkedHashMap<String, Object> yeah : nameConfigs) {
            PlayerNameConfig p = mapped.convertValue(yeah, PlayerNameConfig.class);
            newNameConfigs.add(p);
        }

        for (PlayerNameConfig p : newNameConfigs) {
            getServer().getConsoleSender().sendMessage("PlayerNameConfig is: " + p.toString());

            String uuid = p.uuid;

            addName(uuid, p.getColoredName());

        }
    }

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


