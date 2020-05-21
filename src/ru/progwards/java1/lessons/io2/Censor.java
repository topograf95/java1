package ru.progwards.java1.lessons.io2;

import java.io.RandomAccessFile;

public class Censor {

    public static class CensorException extends Exception {
        String fileName = "";
        String getMessage = "";

        public CensorException(String getMessage, String fileName) {
            this.getMessage = getMessage;
            this.fileName = fileName;
        }
        @Override
        public String toString() {
            return fileName +" : "+ getMessage;
        }
    }
    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try (RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw")) {
            long pos = 0;
            String strIn;
            while ((strIn = raf.readLine()) != null) {
                strIn = new String(strIn.getBytes("ISO-8859-1"), "UTF-8");
                for (int i = 0, n = obscene.length; i < n; ++i) {
                    if (!strIn.contains(obscene[i])) continue;
                    StringBuilder tmpBuil = new StringBuilder();
                    int len = obscene[i].length();
                    while (len-- != 0) {
                        tmpBuil.append('*');
                    }
                    strIn = strIn.replaceAll(obscene[i], tmpBuil.toString());
                }
                String chek = raf.readLine();
                if (chek != null) strIn += '\n';
                raf.seek(pos);
                raf.write(strIn.getBytes());
                pos = raf.getFilePointer();
            }
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
    }

    public static void main(String[] args) {
        try {
            String s = "fileTest2.txt";
            String[] obscene = {"Java", "Oracle", "Sun", "Microsystems"};
            censorFile(s, obscene);
        } catch (CensorException e) {
            System.out.println(e.toString());
        }
    }
}
