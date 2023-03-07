package main;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger length3 = new AtomicInteger();
    public static AtomicInteger length4 = new AtomicInteger();
    public static AtomicInteger length5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread1 = new Thread(() -> {
            for (String text : texts) {
                if (palindrome(text)) {
                    if (text.length() == 3) length3.getAndIncrement();
                    if (text.length() == 4) length4.getAndIncrement();
                    if (text.length() == 5) length5.getAndIncrement();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (String text : texts) {
                if (sameChar(text)) {
                    if (text.length() == 3) length3.getAndIncrement();
                    if (text.length() == 4) length4.getAndIncrement();
                    if (text.length() == 5) length5.getAndIncrement();
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            for (String text : texts) {
                if (ascendingOrder(text)) {
                    if (text.length() == 3) length3.getAndIncrement();
                    if (text.length() == 4) length4.getAndIncrement();
                    if (text.length() == 5) length5.getAndIncrement();
                }
            }
        });
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Красивых слов с длиной 3: " + length3 + " шт.");
        System.out.println("Красивых слов с длиной 4: " + length4 + " шт.");
        System.out.println("Красивых слов с длиной 5: " + length5 + " шт.");
    }

    public static boolean palindrome(String text) {
        return text.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(text.replaceAll("\\W", ""))
                        .reverse().toString());
    }

    public static boolean sameChar(String text) {
        return text.chars().allMatch(value -> text.charAt(0) == value);
    }

    public static boolean ascendingOrder(String text) {
        char[] words = text.toCharArray();
        boolean check = false;
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i] > words[i + 1]) return false;
            if (words[i] == words[i + 1] || words[i] < words[i + 1]) {
                if (words[i] < words[i + 1]) {
                    check = true;
                }
            }
        }
        return check;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}