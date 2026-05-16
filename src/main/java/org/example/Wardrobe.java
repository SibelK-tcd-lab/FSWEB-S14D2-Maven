package org.example.model;

public class Wardrobe {
    private int width, height;
    private double weight;

    public Wardrobe(int width, int height, double weight) {
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public void add() {
        System.out.println("Wardrobe added into Bedroom.");
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getWeight() { return weight; }
}