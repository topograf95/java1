package lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) throws IOException {
        int i = 0;
        try {
//            FileWriter fileWriter = new FileWriter( "fileTest.txt");
//            fileWriter.write( "Этот файл создан мной.\n");
//            fileWriter.write( "\n");
            FileReader reader = new FileReader(fileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().isEmpty()) ++i;
                }
            } finally {
 //               fileWriter.close();
                reader.close();
            }
        } catch (IOException e) {
            return -1;
        }
        return i;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "fileTest.txt";
        System.out.println(calcEmpty(fileName));
    }
}
