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

        int F1Number = 11;
        int F2Number = 33;
        int F3Number = 50;
        double F4Number = 0.4;

        float F1T1 = Float.parseFloat(getAverageWordLengthOfText1());
        float F2T1 = Float.parseFloat(getTypeTokenRatioOfText1());
        float F3T1 = Float.parseFloat(getHapaxLegomenaRatioOfText1());
        float F4T1 = Float.parseFloat(getAverageNumberOfWordsInASentenceForText1());

        float F1T2 = Float.parseFloat(getAverageWordLengthOfText2());
        float F2T2 = Float.parseFloat(getTypeTokenRatioOfText2());
        float F3T2 = Float.parseFloat(getHapaxLegomenaRatioOfText2());
        float F4T2 = Float.parseFloat(getAverageNumberOfWordsInASentenceForText2());


        System.out.println("Text 1 Features");
        System.out.println("Average word length: " + getAverageWordLengthOfText1());
        System.out.println("Type-Token Ratio: " + getTypeTokenRatioOfText1());
        System.out.println("Hapax Legomena Ratio: " + getHapaxLegomenaRatioOfText1());
        System.out.println("Average number of words in a sentence: " + getAverageNumberOfWordsInASentenceForText1());

        System.out.println(" ");

        System.out.println("Text 2 Features");
        System.out.println("Average word length: " + getAverageWordLengthOfText2());
        System.out.println("Type-Token Ratio: " + getTypeTokenRatioOfText2());
        System.out.println("Hapax Legomena Ratio: " + getHapaxLegomenaRatioOfText2());
        System.out.println("Average number of words in a sentence: " + getAverageNumberOfWordsInASentenceForText2());

        System.out.println(" ");

        DecimalFormat df = new DecimalFormat("#.###");
        String simalirity = df.format(abs(F1T1 - F1T2) * F1Number + abs(F2T1 - F2T2) * F2Number + abs(F3T1 - F3T2) * F3Number + abs(F4T1 - F4T2) * F4Number);
        System.out.println("Similarity: " + simalirity);


    }

    //Text 1
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

    public static String getTypeTokenRatioOfText1() {

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(getNumberOfUniqueWords() / getNumberOfAllWords());


    }

    public static String getHapaxLegomenaRatioOfText1() {

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(getNumberOfNonRecurringWords() / getNumberOfAllWords());

    }


    public static String getAverageNumberOfWordsInASentenceForText1() {

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

    public static float getNumberOfNonRecurringWords() {
        String[] words = getStrippedText1(text1);
        int numberOfNonRecurringWords = 0;
        int counter = 1;

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

    public static float getNumberOfUniqueWords() {

        String[] words = getPunctuationStrippedText1(text1).split(" ");
        List aList = new ArrayList();
        int count;

        for (int i = 0; i < words.length; i++) {
            count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equalsIgnoreCase(words[j])) {

                    count++;

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

    public static int getNumberOfAllWords() {

        int allWords = 0;
        String text = getPunctuationStrippedText1(text1);
        String[] words = text.split(" ");

        for (String word : words) {

            allWords++;
        }

        return allWords;

    }

    public static String getPunctuationStrippedText1(String text1) {

        return text1.replaceAll("\\p{Punct}", "");

    }

    public static String[] getStrippedText1(String text1) {
        String strippedText1 = text1.replaceAll("\\p{Punct}", "");

        return strippedText1.split(" ");

    }

    //Text 2
    private static String getAverageWordLengthOfText2() {
        int count = 0;
        double sum = 0;
        String[] words = getStrippedText1(text2);
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

    public static String getTypeTokenRatioOfText2() {

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(getNumberOfUniqueWordsForText2() / getNumberOfAllWordsForText2());


    }

    public static String getHapaxLegomenaRatioOfText2() {

        DecimalFormat df = new DecimalFormat("#.###");

        return df.format(getNumberOfNonRecurringWordsForText2() / getNumberOfAllWordsForText2());

    }


    public static String getAverageNumberOfWordsInASentenceForText2() {

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

        return df.format(getNumberOfAllWordsForText2() / allSentences);

    }

    public static float getNumberOfNonRecurringWordsForText2() {
        String[] words = getStrippedText2(text2);
        int counter = 1;
        int numberOfNonRecurringWords = 0;

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

    public static String getPunctuationStrippedText2(String text2) {

        return text2.replaceAll("\\p{Punct}", "");

    }

    public static String[] getStrippedText2(String text2) {
        String strippedText1 = text2.replaceAll("\\p{Punct}", "");

        return strippedText1.split(" ");

    }

    public static float getNumberOfUniqueWordsForText2() {

        String[] words = getPunctuationStrippedText2(text2).split(" ");
        List aList = new ArrayList();
        int count;

        for (int i = 0; i < words.length; i++) {
            count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equalsIgnoreCase(words[j])) {

                    count++;

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

    public static int getNumberOfAllWordsForText2() {

        int allWords = 0;
        String text = getPunctuationStrippedText2(text2);
        String[] words = text.split(" ");

        for (String word : words) {

            allWords++;
        }

        return allWords;

    }

}