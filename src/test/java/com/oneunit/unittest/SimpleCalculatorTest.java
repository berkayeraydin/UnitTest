package com.oneunit.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    void twoPlusTwoShouldEqualsFour(){
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        // assertEquals : işlemn sonucu excepted alanında ki değer mi kontrol.
        assertEquals(4,simpleCalculator.add(2,2));
        // assertTrue : işlem sonucunu == deki değer ediyorsa true döner
        assertTrue(simpleCalculator.add(2,2) == 4);

        //assertNotEquals();
        //assertFalse();
        //assertNull();
        //assertNotNull();
    }
    @Test
    void threePlusSevenShouldEqualsTen(){
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        // assertEquals : işlemn sonucu excepted alanında ki değer mi kontrol.
        assertEquals(10,simpleCalculator.add(3,7));
        // assertTrue : işlem sonucunu == deki değer ediyorsa true döner
        assertTrue(simpleCalculator.add(3,7) == 10);
    }

}