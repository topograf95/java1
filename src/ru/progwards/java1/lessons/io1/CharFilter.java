package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws Exception {
        try {
            FileReader reader = new FileReader(inFileName);
            try {
                FileWriter fileWriter = new FileWriter(outFileName, true);
                try {
                    Scanner scanner = new Scanner(reader);
                    while (scanner.hasNextLine()) {
                        String strOut = "";
                        String strIn = scanner.nextLine();
                        int pos = 0;
                        for (int i = 0, n = strIn.length(); i < n; ++i) {
                            char c = strIn.charAt(i);
                            for (int j = 0, m = filter.length(); j < m; ++j) {
                                if (c == filter.charAt(j)) {
                                    strOut += strIn.substring(pos, i);
                                    pos = i + 1;
                                    break;
                                }
                            }
                        }
                        fileWriter.write(strOut);
                    }
                } finally {
                    fileWriter.close();
                }
            } finally {
                reader.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static void main(String[] args) {
        String inS = "fileTest.txt";
        String outS = "fileTest2.txt";
        String obscene = " -,.()";
        try {
            filterFile(inS, outS, obscene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
