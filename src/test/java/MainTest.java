package org.example;


import org.example.model.*;
import org.example.model.enums.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    private Lamp lamp;
    private Bed bed;
    private Wardrobe wardrobe;
    private Carpet carpet;
    private Ceiling ceiling;
    private Wall wall1, wall2, wall3, wall4;
    private Bedroom bedroom;

    @BeforeEach
    void setUp() {
        // Nesneleri doğru parametre tipleriyle başlatıyoruz
        lamp = new Lamp(LampType.NORMAL, true, 80);
        bed = new Bed("Double", 4, 1, 2, 2);
        wardrobe = new Wardrobe(2, 4, 40.0);
        carpet = new Carpet(3, 5, PaintColor.RED);
        ceiling = new Ceiling(3, PaintColor.RED);
        wall1 = new Wall("North");
        wall2 = new Wall("South");
        wall3 = new Wall("East");
        wall4 = new Wall("West");

        // Bedroom Composition kurulumunu gerçekleştiriyoruz
        bedroom = new Bedroom("Master Bedroom", wall1, wall2, wall3, wall4,
                ceiling, bed, lamp, wardrobe, carpet);
    }

    @DisplayName("Lamp sınıf değişkenleri private mı?")
    @Test
    public void testLampAccessModifiers() throws NoSuchFieldException {
        Field styleField = lamp.getClass().getDeclaredField("style");
        Field batteryField = lamp.getClass().getDeclaredField("battery");
        Field globalRatingField = lamp.getClass().getDeclaredField("globRating");

        // 2 değeri Java Reflection API'de 'private' modifier'ı doğrular
        assertEquals(2, styleField.getModifiers(), "Style field must be private");
        assertEquals(2, batteryField.getModifiers(), "Battery field must be private");
        assertEquals(2, globalRatingField.getModifiers(), "GlobRating field must be private");
    }

    @DisplayName("Lamp turnOn metodu doğru mesajı basıyor mu?")
    @Test
    public void testLampTurnOnMethod() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lamp.turnOn();

        System.setOut(originalOut); // Konsol çıktısını sisteme geri iade ediyoruz
        assertThat(out.toString(), containsString("Lamp is being turned on."));
    }

    @DisplayName("Bed make metodu doğru mesajı basıyor mu?")
    @Test
    public void testBedMakeMethod() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        bed.make();

        System.setOut(originalOut);
        assertThat(out.toString(), containsString("The bed is being made."));
    }

    @DisplayName("Wardrobe add metodu doğru mesajı basıyor mu?")
    @Test
    public void testWardrobeAddMethod() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        wardrobe.add();

        System.setOut(originalOut);
        assertThat(out.toString(), containsString("Wardrobe added into Bedroom."));
    }

    @DisplayName("Carpet lying metodu doğru mesajı basıyor mu?")
    @Test
    public void testCarpetLyingMethod() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        carpet.lying();

        System.setOut(originalOut);
        assertThat(out.toString(), containsString("Carpet is lying on Bedroom floor."));
    }

    @DisplayName("Bedroom tüm composition bileşenlerini barındırıyor mu?")
    @Test
    public void testBedroomComposition() {
        assertNotNull(bedroom.getLamp(), "Lamp cannot be null");
        assertNotNull(bedroom.getBed(), "Bed cannot be null");
        assertNotNull(bedroom.getWardrobe(), "Wardrobe cannot be null");
        assertNotNull(bedroom.getCarpet(), "Carpet cannot be null");
        assertNotNull(bedroom.getCeiling(), "Ceiling cannot be null");
        assertNotNull(bedroom.getWall1(), "Wall1 cannot be null");
        assertEquals("Master Bedroom", bedroom.getName());
    }

    @DisplayName("Ceiling ve Wall create metotları doğru çalışıyor mu?")
    @Test
    public void testStructureMethods() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ceiling.create();
        wall1.create();

        System.setOut(originalOut);
        String result = out.toString();
        assertThat(result, containsString("Ceiling has been built."));
        assertThat(result, containsString("Wall has been built."));
    }
}