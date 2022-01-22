package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String text1 = scanner.nextLine();
    public static String text2 = scanner.nextLine();

    public static void main(String[] args) {

        System.out.println("Text 1 Features");
        System.out.println("Average word length: " + getAverageWordLengthOfText(text1));
        System.out.println("Type-Token Ratio: " + getTypeTokenRatioOfText(text1));
        System.out.println("Hapax Legomena Ratio: " + getHapaxLegomenaRatioOfText(text1));
        System.out.println("Average number of words in a sentence: " + getAverageNumberOfWordsInASentenceForText(text1));

        System.out.println(" ");

        System.out.println("Text 2 Features");
        System.out.println("Average word length: " + getAverageWordLengthOfText(text2));
        System.out.println("Type-Token Ratio: " + getTypeTokenRatioOfText(text2));
        System.out.println("Hapax Legomena Ratio: " + getHapaxLegomenaRatioOfText(text2));
        System.out.println("Average number of words in a sentence: " + getAverageNumberOfWordsInASentenceForText(text2));

        System.out.println(" ");

        getSimilarity();

    }

    private static String getAverageWordLengthOfText(String text) {
        int allWords = 0;
        double sumOfAllWords = 0;
        String[] words = getStrippedText1(text);
        for (String word : words) {
            double wordLength = word.length();
            sumOfAllWords += wordLength;
            allWords++;
        }

        double averageWordLength = 0;
        if (allWords > 0) {
            averageWordLength = sumOfAllWords / allWords;
        }

        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(averageWordLength);
    }

    public static String getTypeTokenRatioOfText(String text) {

        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(getNumberOfUniqueWords(text) / getNumberOfAllWords(text));

    }

    public static String getHapaxLegomenaRatioOfText(String text) {

        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(getNumberOfNonRecurringWords(text) / getNumberOfAllWords(text));

    }

    public static String getAverageNumberOfWordsInASentenceForText(String text) {

        String[] words = text.split("");
        int allSentences = 1;


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
        return df.format(getNumberOfAllWords(text) / allSentences);

    }

    public static float getNumberOfNonRecurringWords(String text) {

        String[] words = getStrippedText1(text);
        int numberOfNonRecurringWords = 0;
        int counter = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    counter += 1;
                    words[j] = "0";
                }

            }

            if (words[i] != "0") {
                if (counter == 1) {
                    numberOfNonRecurringWords++;
                }

                counter = 1;

            }

        }

        return numberOfNonRecurringWords;
    }

    public static float getNumberOfUniqueWords(String text) {

        String[] words = getPunctuationStrippedText(text).split(" ");
        List aList = new ArrayList();

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equalsIgnoreCase(words[j])) {
                    words[j] = "";
                }

            }

            if (words[i].equals("")) {
                break;
            }

            aList.add(words[i]);

        }

        return aList.size();
    }

    public static int getNumberOfAllWords(String text) {

        int allWords = 0;
        String[] words = getStrippedText1(text);

        for (String word : words) {

            allWords++;
        }

        return allWords;
    }

    public static String getPunctuationStrippedText(String text) {

        return text.replaceAll("\\p{Punct}", "");
    }

    public static String[] getStrippedText1(String text) {

        String strippedText1 = getPunctuationStrippedText(text);
        return strippedText1.split(" ");
    }

    public static void getSimilarity() {

        float feature1Weight = 11;
        float feature2Weight = 33;
        float feature3Weight = 50;
        double feature4Weight = 0.4;

        float feature1Text1 = Float.parseFloat(getAverageWordLengthOfText(text1));
        float feature2Text1 = Float.parseFloat(getTypeTokenRatioOfText(text1));
        float feature3Text1 = Float.parseFloat(getHapaxLegomenaRatioOfText(text1));
        float feature4Text1 = Float.parseFloat(getAverageNumberOfWordsInASentenceForText(text1));

        float feature1Text2 = Float.parseFloat(getAverageWordLengthOfText(text2));
        float feature2Text2 = Float.parseFloat(getTypeTokenRatioOfText(text2));
        float feature3Text2 = Float.parseFloat(getHapaxLegomenaRatioOfText(text2));
        float feature4Text2 = Float.parseFloat(getAverageNumberOfWordsInASentenceForText(text2));

        DecimalFormat df = new DecimalFormat("#.###");
        String similarity = df.format(abs(feature1Text1 - feature1Text2) * feature1Weight + abs(feature2Text1 - feature2Text2) * feature2Weight + abs(feature3Text1 - feature3Text2) * feature3Weight + abs(feature4Text1 - feature4Text2) * feature4Weight);
        System.out.println("Similarity: " + similarity);

    }

}