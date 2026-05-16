package model;
import enums.PaintColor;

public class Ceiling {
    private int height;
    private PaintColor color;

    public Ceiling(int height, PaintColor color) {
        this.height = height;
        this.color = color;
    }

    public void create() {
        System.out.println("Ceiling has been built.");
    }

    public int getHeight() { return height; }
    public PaintColor getColor() { return color; }
}
