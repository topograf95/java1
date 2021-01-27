package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class GettersAndSetters {
    public static void check(String clazz) throws ClassNotFoundException {
        Class<?> clazn = Class.forName(clazz);
        Method[] methods = clazn.getMethods();
        ArrayList<String> namesGetSet = new ArrayList<>();
        // Получаем список имён методов класса(только Геттеров и Сеттеров)
        for (Method method : methods) {
            // Проверка на модификатор static
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (method.getName().startsWith("get") || method.getName().startsWith("set")) {
                namesGetSet.add(method.getName());
            }
        }
        Field[] fields = clazn.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                String fieldName = toUpperCaseFirstSymbol(field.getName());
                // Проверяем для каждого поля есть ли Геттер в списке имен методов,
                // если нет - печатаем сигнатуру Геттера
                String metodName = "get"+ fieldName;
                if (!searchGetSet(metodName, namesGetSet)) {
                    System.out.println("public "+ field.getType().getSimpleName()+" "+ metodName+"()");
                }
                // Аналогично проверяем и печатаем Сеттер
                metodName = "set"+ fieldName;
                if (!searchGetSet(metodName, namesGetSet)) {
                    System.out.println("public void "+ metodName
                            +"("+ field.getType().getSimpleName()+" " + field.getName()+")");
                }
            }
        }
    }

    private static boolean searchGetSet(String str, List<String> listNames) {
        for (String name : listNames) {
            if (str.equals(name)) return true;
        }
        return false;
    }

    // Метод переводит первую букву строки в верхний регистр
    private static String toUpperCaseFirstSymbol(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        return String.valueOf(charArray);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        check("ru.progwards.java2.lessons.reflection.Persan");
    }
}
