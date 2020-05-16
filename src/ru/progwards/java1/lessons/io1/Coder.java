package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileReader reader = new FileReader(inFileName);
            try {
                FileWriter fileWriter = new FileWriter(outFileName, true);
                try {
                    int symbol = reader.read();
                    while (symbol != -1) {
                        char c = code[symbol];
                        fileWriter.write(c);
                        symbol = reader.read();
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
    public static void main(String[] args) {
        String inS = "fileTest.txt";
        String outS = "fileTest2.txt";
        String logS = "fileTest2.txt";
        char[] ar = new char[5];
        codeFile(inS, outS, ar, logS);
    }
}
