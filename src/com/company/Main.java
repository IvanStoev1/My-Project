package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String text1 = scanner.nextLine();
    public static String text2 = scanner.nextLine();

    public static void main(String[] args) {

        System.out.println("Average word length: " + getAverageWordLengthOfText1());
        System.out.println("Type-Token Ratio: " + getTypeTokenRatio());
        System.out.println("Hapax Legomena Ratio: " + getHapaxLegomenaRatio());
        System.out.println("Average number of words in a sentence: " + getAverageNumberOfWordsInASentence());

    }

    private static String getAverageWordLengthOfText1() {
        int count = 0;
        double sum = 0;
        String[] words = getStrippedText1(text1);
        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }

        double averageWordLength = 0;
        if (count > 0) {
            averageWordLength = sum / count;
        }

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(averageWordLength);

    }

    public static String getTypeTokenRatio() {

        long result = getNumberOfRepeatingWords() / getNumberOfAllWords();
        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(result);

    }

    public static int getHapaxLegomenaRatio() {

        String[] words = getPunctuationStrippedText1(text1).split(" ");
        long counter;
        int wordsOnlyUsedOnce = 1;

        for (int i = 0; i < words.length; i++) {
            counter = Arrays.stream(words).count();
            if (counter == 1) {
                wordsOnlyUsedOnce += 1;

            }

        }
        return wordsOnlyUsedOnce;
    }

    public static String getAverageNumberOfWordsInASentence() {

        String[] words = text1.split("");
        int allSentences = 0;


        for (String word : words) {
            if (word.equals("?")) {

                allSentences++;

            } else if (word.equals(".")) {

                allSentences++;

            } else if (word.equals("!")) {
                allSentences++;
            }

        }

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(getNumberOfAllWords() / allSentences);

    }

    public static String getPunctuationStrippedText1(String text1) {

        return text1.replaceAll("\\p{Punct}", "");

    }

    public static String[] getStrippedText1(String text1) {
        String strippedText1 = text1.replaceAll("\\p{Punct}", "");

        return strippedText1.split(" ");

    }

    public static int getNumberOfRepeatingWords() {

        String[] words = getPunctuationStrippedText1(text1).split(" ");
        long counter = 0;
        int repeatingWords = 1;

        for (int i = 0; i < words.length; i++) {
            counter = Arrays.stream(words).count();
            if (counter > 1) {
                repeatingWords += 1;

            }

        }

        return repeatingWords;

    }

    public static int getNumberOfAllWords() {

        int allWords = 0;
        String text = getPunctuationStrippedText1(text1);
        String[] words = text.split(" ");

        for (String word : words) {

            allWords++;
        }

        return allWords;

    }

}