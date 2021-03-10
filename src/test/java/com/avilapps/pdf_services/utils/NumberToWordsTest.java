package com.avilapps.pdf_services.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberToWordsTest {
    @Test
    public void convertNumber1() {
        String result = NumberToWords.convert(1);
        assertEquals("uno", result);
    }

    @Test
    public void convertNumber25() {
        String result = NumberToWords.convert(25);
        assertEquals("veinticinco", result);
    }

    @Test
    public void convertNumber68() {
        String result = NumberToWords.convert(68);
        assertEquals("sesenta y ocho", result);
    }

    @Test
    public void convertNumber100() {
        String result = NumberToWords.convert(100);
        assertEquals("cien", result);
    }

    @Test
    public void convertNumber139() {
        String result = NumberToWords.convert(139);
        assertEquals("ciento treinta y nueve", result);
    }

    @Test
    public void convertNumber999() {
        String result = NumberToWords.convert(999);
        assertEquals("novecientos noventa y nueve", result);
    }

    @Test
    public void convertNumber1000() {
        String result = NumberToWords.convert(1000);
        assertEquals("mil", result);
    }

    @Test
    public void convertNumber1346() {
        String result = NumberToWords.convert(1346);
        assertEquals("mil trescientos cuarenta y seis", result);
    }

    @Test
    public void convertNumber2192() {
        String result = NumberToWords.convert(2192);
        assertEquals("dos mil ciento noventa y dos", result);
    }

    @Test
    public void convertNumber21683() {
        String result = NumberToWords.convert(21683);
        assertEquals("veintiun mil seiscientos ochenta y tres", result);
    }

    @Test
    public void convertNumber40000() {
        String result = NumberToWords.convert(40000);
        assertEquals("cuarenta mil", result);
    }

    @Test
    public void convertNumber999999() {
        String result = NumberToWords.convert(999999);
        assertEquals("novecientos noventa y nueve mil novecientos noventa y nueve", result);
    }
}
