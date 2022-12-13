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

    public GradientName(String name, Gradient gradient, Affix prefix, Affix suffix) {
        super(name, prefix, suffix);
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

    @Override
    public String toString() {
        return "GradientName{" +
                "name='" + name + '\'' +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                ", gradient=" + gradient +
                '}';
    }
}
