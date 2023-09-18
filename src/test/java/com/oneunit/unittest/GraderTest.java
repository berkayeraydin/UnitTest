package com.oneunit.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraderTest {

    @Test
    void fiftyNineShouldReturnF() {
        Grader grader = new Grader();
        assertEquals('F',grader.determineLetterGrade(59));
    }

    @Test
    void nintyNineShouldReturnB() {
        Grader grader = new Grader();
        assertEquals('A',grader.determineLetterGrade(90));
    }

    @Test
    void sixtyNineShouldReturnB() {
        Grader grader = new Grader();
        assertEquals('D',grader.determineLetterGrade(69));
    }

    @Test
    void seventyNineShouldReturnB() {
        Grader grader = new Grader();
        assertEquals('C',grader.determineLetterGrade(79));
    }

    @Test
    void eightyNineShouldReturnB() {
        Grader grader = new Grader();
        assertEquals('B',grader.determineLetterGrade(89));
    }

    @Test
    void zeroShouldReturnF() {
        Grader grader = new Grader();
        assertEquals('F',grader.determineLetterGrade(0));
    }

    @Test
    void negativeOneShouldReturnIllegalArgumentsException() {
        Grader grader = new Grader();
        assertThrows(IllegalArgumentException.class, () -> {grader.determineLetterGrade(-1);});
    }
}