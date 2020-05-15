package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileReader reader = new FileReader(inFileName);
            try {
                FileWriter fileWriter = new FileWriter(outFileName, true);
                try {
                    Scanner scanner = new Scanner(reader);
                    while (scanner.hasNextLine()) {
                        String strOut = "";
                        String strIn = scanner.nextLine();
                        for (int i = 0, n = strIn.length(); i < n; ++i) {
                            strOut += code[(int) strIn.charAt(i)];
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
            try {
                FileWriter logFile = new FileWriter(logName, true);
                try {
                    logFile.write(e.getMessage() + "\n");
                } finally {
                    logFile.close();
                }
            } catch (IOException elog) {
                System.out.println(elog.getMessage());
            }
        }
    }
//    public static void main(String[] args) {
//        String s = "";
//        System.out.println(s.length());
//    }
}
