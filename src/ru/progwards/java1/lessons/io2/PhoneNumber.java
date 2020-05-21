package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) throws Exception {
        StringBuilder tmpStr = new StringBuilder();
        for (char c : phone.toCharArray()) {
            if (Character.isDigit(c)) tmpStr.append(c);
        }
        if (tmpStr.length() == 11) tmpStr.delete(0, 1);
        if (tmpStr.length() != 10) throw new Exception("Неверный формат: "+ tmpStr);
        StringBuilder tmpStrRes = new StringBuilder();
        tmpStrRes.append("+7(").append(tmpStr.substring(0, 3)).append(")").
                append(tmpStr.substring(3, 6)).append("-").append(tmpStr.substring(6));
        return tmpStrRes.toString();
    }

    public static void main(String[] args) throws Exception {
        String s = "- 8 999 111 22 33";
        System.out.println(format(s));
    }
}
