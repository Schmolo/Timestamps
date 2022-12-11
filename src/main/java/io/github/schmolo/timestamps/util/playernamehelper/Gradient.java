package io.github.schmolo.timestamps.util.playernamehelper;

public class Gradient {
    public String from;
    public String to;

    public Gradient(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public Gradient() {}

    @Override
    public String toString() {
        return "Gradient{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
