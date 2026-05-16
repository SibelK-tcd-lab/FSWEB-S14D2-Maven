package org.example.model; // model klasörünün içinde olduğu için paket adı böyle olmalı
import org.example.model.enums.*;
import org.example.model.enums.PaintColor; // PaintColor enum'ını import ediyoruz

public class Carpet {
    // Instance variables (Hepsi private olmalı)
    private int width;
    private int height;
    private PaintColor color;

    // 3 değişkeni de alan tek bir constructor
    public Carpet(int width, int height, PaintColor color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Görev tanımındaki lying metodu
    public void lying() {
        System.out.println("Carpet is lying on Bedroom floor.");
    }

    // Getter metotları
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PaintColor getColor() {
        return color;
    }
}