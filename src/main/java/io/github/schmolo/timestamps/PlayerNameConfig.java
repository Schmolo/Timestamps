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


}

