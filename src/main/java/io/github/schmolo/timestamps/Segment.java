package io.github.schmolo.timestamps;

public class Segment {
    public String name;
    public String color;

    public Segment(String name, String color) {
        this.name = name;
        this.color = color;
    }



    public Segment() {

    }

    @Override
    public String toString() {
        return "Segment{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
