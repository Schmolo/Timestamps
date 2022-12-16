package io.github.schmolo.timestamps.util.playernamehelper;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;

import java.util.HashMap;

// A Base Class only used for Polymorphism and helper functions
public class ColoredName {
    String name;
    Affix prefix;
    Affix suffix;

    public static HashMap<String, String> customHexCodes;
    static {
        customHexCodes = new HashMap<>();
        customHexCodes.put("red", "#ff0000");
        customHexCodes.put("vermilion", "#ff4000");
        customHexCodes.put("orange", "#ff8000");
        customHexCodes.put("amber", "#ffc000");
        customHexCodes.put("yellow", "#ffff00");
        customHexCodes.put("lime", "#c0ff00");
        customHexCodes.put("charteuse", "#80ff00");
        customHexCodes.put("harlequin", "#40ff00");
        customHexCodes.put("green", "#00ff00");
        customHexCodes.put("erin", "#00ff40");
        customHexCodes.put("springgreen", "#00ff80");
        customHexCodes.put("aquamarine", "#00ffc0");
        customHexCodes.put("cyan", "#00ffff");
        customHexCodes.put("capri", "#00c0ff");
        customHexCodes.put("azure", "#0080ff");
        customHexCodes.put("cerulean", "#0040ff");
        customHexCodes.put("blue", "#0000ff");
        customHexCodes.put("indigo", "#4000ff");
        customHexCodes.put("violet", "#8000ff");
        customHexCodes.put("purple", "#c000ff");
        customHexCodes.put("magenta", "#ff00ff");
        customHexCodes.put("cerise", "#ff00c0");
        customHexCodes.put("rose", "#ff0080");
        customHexCodes.put("crimson", "#ff0040");

        customHexCodes.put("white", "#ffffff");
        customHexCodes.put("light_gray", "#c0c0c0");
        customHexCodes.put("gray", "#808080");
        customHexCodes.put("dark_gray", "#404040");
        customHexCodes.put("black", "#000000");


    }


    public ColoredName(String name) {
        this.name = name;

    }

    public ColoredName(String name, Affix prefix, Affix suffix) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
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

    public Affix getPrefix() {
        return this.prefix;
    }

    public Affix getSuffix() {
        return this.suffix;
    }

    public void setPrefix(String prefixname, String prefixcolor) {
        this.prefix = new Affix(prefixname, prefixcolor);
    }

    public void setSuffix(String suffixname, String suffixcolor) {
        this.suffix = new Affix(suffixname, suffixcolor);
    }

    @Override
    public String toString() {
        return "ColoredName{" +
                "name='" + name + '\'' +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                '}';
    }

    public void removePrefix() {
        this.prefix = null;
    }

    public void removeSuffix() {
        this.suffix = null;
    }
}
