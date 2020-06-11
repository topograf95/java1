package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UsageFrequency {
    String fileName;

    public void processFile(String fileName) {
        this.fileName = fileName;
    }

    public Map<Character, Integer> getLetters() {
        Map<Character, Integer> charIntMap = new HashMap<>();
        try (FileReader reader = new FileReader(fileName)) {
            int symbol = reader.read();
            while (symbol != -1) {
                if (Character.isAlphabetic(symbol) || Character.isDigit(symbol)) {
                    Integer value = charIntMap.putIfAbsent((char)symbol, 1);
                    if (value != null) charIntMap.put((char)symbol, ++value);
                }
                symbol = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charIntMap;
    }

    public Map<String, Integer> getWords() {
        Map<String, Integer> strIntMap = new HashMap<>();
        try (FileReader reader = new FileReader(fileName)) {
            try (Scanner scanner = new Scanner(reader).useDelimiter("[^A-Za-z0-9]")) {
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    if (!"".equals(word)) {
                        Integer value = strIntMap.putIfAbsent(word, 1);
                        if (value != null) strIntMap.put(word, ++value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strIntMap;
    }

    public static void main(String[] args) {
        UsageFrequency uf = new UsageFrequency();
        uf.processFile("fileTest2.txt");
        System.out.println(uf.getWords());
    }
}
