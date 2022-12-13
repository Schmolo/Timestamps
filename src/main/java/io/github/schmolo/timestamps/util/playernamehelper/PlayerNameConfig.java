package io.github.schmolo.timestamps.util.playernamehelper;

import java.util.List;

import static io.github.schmolo.timestamps.util.TimsLib.PrintDebug;

public class PlayerNameConfig {

    public String uuid;
    public String name;
    public String color;
    public Gradient gradient;
    public List<Segment> segments;
    public Affix prefix;
    public Affix suffix;

    public PlayerNameConfig() {

    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrefix(Affix prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(Affix suffix) {
        this.suffix = suffix;
    }

    public void bruhColoredName(ColoredName bruh) {
        if (bruh.getClass() == SingleName.class) {
            SingleName yeah = (SingleName) bruh;
            setColor(yeah.color);

        } else if (bruh.getClass() == GradientName.class) {
            GradientName yeah = (GradientName) bruh;
            setGradient(yeah.gradient);

        } else if (bruh.getClass() == SegmentedName.class) {
            SegmentedName yeah = (SegmentedName) bruh;
            setSegments(yeah.segments);

        }
    }


    public ColoredName getColoredName(Affix prefix, Affix suffix) {
        PrintDebug("In the getColoredName method");

        // TODO: HOLY SHIT DRY THE FUCK OUT OF THESE CONSTRUCTORS PLEASE FOR THE LOVE OF GOD
        if(this.color != null) {
            SingleName temp = new SingleName(this.name, this.color, prefix, suffix);
            PrintDebug("The SingleName is " + temp);
            return temp;
        } else if(this.gradient != null) {
            GradientName temp = new GradientName(this.name, this.gradient, prefix, suffix);
            PrintDebug("The GradientName is " + temp);
            return temp;
        } else if (this.segments != null) {
            SegmentedName temp = new SegmentedName(this.name, this.segments, prefix, suffix);
            PrintDebug("The SegmentedName is " + temp);
            return temp;
        }

        return null;
    }

    @Override
    public String toString() {
        return "PlayerNameConfig{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", gradient=" + gradient +
                ", segments=" + segments +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                '}';
    }
}

