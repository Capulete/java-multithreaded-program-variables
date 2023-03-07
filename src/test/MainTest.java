package test;

import main.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void palindrome() {
        List<String> text = Arrays.asList("abbaa", "Acca", "cabab", "a", "ccca", "ababa");
        List<Boolean> expecteds = Arrays.asList(false, true, false, true, false, true);
        List<Boolean> result = new ArrayList<>();

        text.forEach(s -> result.add(Main.palindrome(s)));

        assertEquals(expecteds, result);
    }

    @org.junit.jupiter.api.Test
    void sameChar() {
        List<String> text = Arrays.asList("aaa", "bbb", "ccc", "a", "ab", "b");
        List<Boolean> expecteds = Arrays.asList(true, true, true, true, false, true);
        List<Boolean> result = new ArrayList<>();

        text.forEach(s -> result.add(Main.sameChar(s)));

        assertEquals(expecteds, result);
    }

    @org.junit.jupiter.api.Test
    void ascendingOrder() {
        List<String> text = Arrays.asList("aaacca", "bbba", "abc", "a", "ab", "b", "aaaa");
        List<Boolean> expecteds = Arrays.asList(false, false, true, false, true, false, false);
        List<Boolean> result = new ArrayList<>();

        text.forEach(s -> result.add(Main.ascendingOrder(s)));

        assertEquals(expecteds, result);
    }
}