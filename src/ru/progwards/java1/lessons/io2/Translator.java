package ru.progwards.java1.lessons.io2;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    Translator(String[] inLang, String[] outLang) {
        int n = inLang.length;
        this.inLang = new String[n];
        this.outLang = new String[n];
        for (int i = 0; i < n; ++i) {
            this.inLang[i] = inLang[i];
            this.outLang[i] = outLang[i];
        }
    }
    public String translate(String sentence) {
        String[] arraySentence = sentence.split(" ");
        StringBuilder result = new StringBuilder(64);
        int j = 0;
        for (String strIn : arraySentence) {
            StringBuilder tmpBuil = new StringBuilder();
            for (char c : strIn.toCharArray()) {
                if (Character.isAlphabetic(c)) tmpBuil.append (Character.toLowerCase(c));
            }
            String tmpStr = tmpBuil.toString();
            for (int i = 0, n = inLang.length; i < n; ++i) {
                if (tmpStr.compareTo(inLang[i]) == 0) tmpStr = outLang[i];
            }
            if (Character.isUpperCase(strIn.charAt(0))) {
                tmpStr = tmpStr.replace(tmpStr.charAt(0), Character.toUpperCase(tmpStr.charAt(0)));
            }
            if (strIn.length() > tmpBuil.length()) {
                tmpStr += strIn.charAt(strIn.length()-1);
            }
            if (j == arraySentence.length - 1) result.append(tmpStr);
            else result.append(tmpStr).append(" ");
            ++j;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] outLang = {"hello", "world"};
        String[] inLang = {"привет", "мир"};
        Translator tran = new Translator(inLang, outLang);
        System.out.println(tran.translate("Привет, - Мир!"));
    }
}
