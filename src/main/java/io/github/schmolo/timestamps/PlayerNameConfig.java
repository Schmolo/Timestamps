package io.github.schmolo.timestamps;

import java.util.List;

public class PlayerNameConfig {

    public String uuid;
    public String name;
    public String color;
    public Gradient gradient;
    public List<Segment> segments;

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


    public ColoredName getColoredName() {

        if(this.color != null) {
            return new SingleName(this.name, this.color);
        } else if(this.gradient != null) {
            return new GradientName(this.name, this.gradient);
        } else if (this.segments != null) {
            return new SegmentedName(this.name, this.segments);
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
                '}';
    }
}

