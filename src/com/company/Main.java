package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String text1 = scanner.nextLine();
    public static String text2 = scanner.nextLine();

    public static void main(String[] args) {
        System.out.println("Average word length:" + getAverageWordLengthOfText1());

    }

    private static double getAverageWordLengthOfText1() {
        int count = 0;
        double sum = 0;
        String[] words = getPunctuationStrippedText1(text1);
        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }

        double average = 0;
        if (count > 0) {
            average = sum / count;
        }

        return average;

    }

    public static String[] getPunctuationStrippedText1(String text1) {
        String[] strippedInput = text1.split("\\s+");
        return strippedInput;

    }

}


