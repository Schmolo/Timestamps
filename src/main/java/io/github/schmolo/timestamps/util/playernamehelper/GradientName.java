package io.github.schmolo.timestamps.util.playernamehelper;

import org.bukkit.Color;

public class GradientName extends ColoredName {

    public Gradient gradient;

    public GradientName(String name, Gradient gradient) {
        super(name);
        this.gradient = gradient;
        Color from;
        Color to;


        if (gradient.from.contains("#")) {
            from = fromHexString(gradient.from);
        } else {
            from = fromHexString(customHexCodes.get(gradient.from));
        }

        if (gradient.to.contains("#")) {
            to = fromHexString(gradient.to);
        } else {
            to = fromHexString(customHexCodes.get(gradient.to));
        }

        this.name = applyGradientToName(name, from, to);

    }
}