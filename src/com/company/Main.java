package com.company;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String text1 = scanner.nextLine();
    public static String text2 = scanner.nextLine();

    public static void main(String[] args) {
        getTypeTokenRatio();
//        System.out.println(getTypeTokenRatio());
        System.out.println("Average word length:" + getAverageWordLengthOfText1());

    }

    private static float getAverageWordLengthOfText1() {
        int count = 0;
        float sum = 0;
        String[] words = getStrippedText1(text1);
        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }

        float averageWordLength = 0;
        if (count > 0) {
            averageWordLength = sum / count;
        }

        return averageWordLength;

    }

    public static String[] getStrippedText1(String text1) {
        String strippedText1 = text1.replaceAll("\\p{Punct}", "");

        return strippedText1.split(" ");

    }

    public static void getTypeTokenRatio() {
        int allWords = 0;
        String str = getPunctuationStrippedText1(text1);
        String currentWord = null;
        String[] words;
        for (int i = 0; i < str.length(); i++) {
            words = str.split(" ");
            for (String word : words) {
                currentWord = word;
            }

        }

    }
    public static String getPunctuationStrippedText1 (String text1){
        return text1.replaceAll("\\p{Punct}", "");
    }
    
}