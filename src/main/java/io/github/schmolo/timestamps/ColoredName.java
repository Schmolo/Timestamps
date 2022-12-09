package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;

import java.util.HashMap;

// A Base Class only used for Polymorphism and helper functions
public class ColoredName {
    String name;

    public static HashMap<String, String> customHexCodes;
    static {
        customHexCodes = new HashMap<>();
        customHexCodes.put("black", "#000000");
        customHexCodes.put("dark_blue", "#0000aa");
        customHexCodes.put("dark_green", "#00aa00");
        customHexCodes.put("dark_aqua", "#00aaaa");
        customHexCodes.put("dark_red", "#aa0000");
        customHexCodes.put("dark_purple", "#aa00aa");
        customHexCodes.put("gold", "#ffaa00");
        customHexCodes.put("gray", "#aaaaaa");
        customHexCodes.put("dark_gray", "#555555");
        customHexCodes.put("blue", "#5555ff");
        customHexCodes.put("green", "#55ff55");
        customHexCodes.put("aqua", "#55ffff");
        customHexCodes.put("red", "#ff5555");
        customHexCodes.put("light_purple", "#ff55ff");
        customHexCodes.put("yellow", "#ffff55");
        customHexCodes.put("white", "ffffff");
    }


    public ColoredName(String name) {
        this.name = name;

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
            float p = (float) i / name.length();
            int R = (int) (to.getRed() * p + from.getRed() * (1 - p));
            int B = (int) (to.getBlue() * p + from.getBlue() * (1 - p));
            int G = (int) (to.getGreen() * p + from.getGreen() * (1 - p));

            Color c = Color.fromRGB(R, G, B);
            gradientName += ChatColor.of(toHexString(c)) + Character.toString(name.toCharArray()[i]);
        }

        return gradientName;
    }
}
