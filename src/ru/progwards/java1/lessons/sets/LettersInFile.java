package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.util.HashSet;
import java.util.TreeSet;

public class LettersInFile {
    public static String process(String fileName) throws Exception {
        HashSet<Character> setChar = new HashSet<>();
        try (FileReader reader = new FileReader(fileName)) {
            int symbol = reader.read();
            while (symbol != -1) {
                if (Character.isAlphabetic(symbol)) setChar.add((char)symbol);
                symbol = reader.read();
            }
        }
        TreeSet<Character> treeSet = new TreeSet<>(setChar);
        StringBuilder result = new StringBuilder(treeSet.size());
        for (Character c : treeSet) {
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        String inS = "fileTest.txt";
        System.out.println(process(inS));
    }
}
