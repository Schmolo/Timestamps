package io.github.schmolo.timestamps;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.util.*;
import java.util.List;

public class PlayerNameHelper {
    // This is a helper for the usage of colored names
    // The Idea is that you only need to write code that looks like this:

    // playernamehelper.getName("e62b0898-4189-4ae4-9cf5-c9f9fcc004bc")

    // and that just spits out the correct name for that Person


    // This is for the Singleton Pattern
    // so that only one list of Player Names can exist at one time
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



    // Gets the names from the Config and assigns them
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
            writer = new PrintWriter(new File("plugins\\timestamps\\bruh.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        yam.dump(bruh, writer);

























        // Creates a Temp PlayerNameHelper
        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        // Reads the playernames.yml
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("plugins\\timestamps\\playernames.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Reads the Yaml into a List<PlayerNameConfig>
        Yaml yaml = new Yaml(new Constructor(List.class));
        List<LinkedHashMap<String, Object>> data = yaml.load(inputStream);

        // Adds the data into the playerNameHelper class
        playerNameHelper.add(data);

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
            String uuid = p.uuid;
            if(p.color != null) {
                addName(uuid, new SingleName(p.name, p.color));
            } else if(p.gradient != null) {
                addName(uuid, new GradientName(p.name, p.gradient));
            } else if (p.segments != null) {
                addName(uuid, new SegmentedName(p.name, p.segments));
            }
        }
    }

    private HashMap<String, ColoredName> playernames = new HashMap<>();

    public void addName(String player, ColoredName name) {
        playernames.put(player, name);
    }

    public String getName(String uuid) {
        return playernames.get(uuid).getName();
    }
}

class ColoredName {
    String name;

    HashMap<String, String> customHexCodes;



    public ColoredName(String name) {
        this.name = name;
        this.customHexCodes = new HashMap<>();
        customHexCodes.put("black","#000000");
        customHexCodes.put("dark_blue","#0000aa");
        customHexCodes.put("dark_green","#00aa00");
        customHexCodes.put("dark_aqua","#00aaaa");
        customHexCodes.put("dark_red","#aa0000");
        customHexCodes.put("dark_purple","#aa00aa");
        customHexCodes.put("gold","#ffaa00");
        customHexCodes.put("gray","#aaaaaa");
        customHexCodes.put("dark_gray","#555555");
        customHexCodes.put("blue","#5555ff");
        customHexCodes.put("green","#55ff55");
        customHexCodes.put("aqua","#55ffff");
        customHexCodes.put("red","#ff5555");
        customHexCodes.put("light_purple","#ff55ff");
        customHexCodes.put("yellow","#ffff55");
        customHexCodes.put("white","ffffff");

    }

    public String getName() {
        return this.name;
    }

    // Some Helper Functions
    public Color fromHexString(String hex) {
        int rgb = Integer.parseInt(hex.substring(1), 16);

        return Color.fromRGB(rgb);
    }

    public String toHexString(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    public String applyGradientToName(String name, Color from, Color to) {
        String gradientName = "";

        for (int i = 0; i < name.length(); i++) {
            float p = (float)i/name.length();
            int R = (int) (to.getRed()   * p + from.getRed()   * (1 - p));
            int B = (int) (to.getBlue()  * p + from.getBlue()  * (1 - p));
            int G = (int) (to.getGreen() * p + from.getGreen() * (1 - p));

            Color c = Color.fromRGB(R, G, B);
            gradientName += ChatColor.of(toHexString(c)) + Character.toString(name.toCharArray()[i]);
        }

        return gradientName;
    }
}


class SegmentedName extends ColoredName {

    public SegmentedName(String name, List<Segment> segments) {
        super(name);
        StringBuilder sum = new StringBuilder();
        for (Segment s : segments) {
            sum.append(ChatColor.of(s.color)).append(s.name);
        }

        this.name = sum.toString();
    }
}

class GradientName extends ColoredName {

    public GradientName(String name, Gradient gradient) {
        super(name);
        Color from = null;
        Color to = null;

        // TODO: Add the ability to type in normal color names

        if(gradient.from.contains("#")) {
            from = fromHexString(gradient.from);
        } else {
            from = fromHexString(customHexCodes.get(gradient.from));
        }

        if(gradient.to.contains("#")) {
            to = fromHexString(gradient.to);
        } else {
            to = fromHexString(customHexCodes.get(gradient.to));
        }

        this.name = applyGradientToName(name, from, to);

    }
}

class SingleName extends ColoredName {

    public SingleName(String name, String color) {
        super(name);
        this.name = ChatColor.of(color) + this.name;
    }
}