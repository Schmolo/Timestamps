package io.github.schmolo.timestamps;

import org.bukkit.Color;

public class GradientName extends ColoredName {

    public GradientName(String name, Gradient gradient) {
        super(name);
        Color from = null;
        Color to = null;

        // TODO: Add the ability to type in normal color names

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
