package ru.progwards.java2.lessons.reflection;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.*;

class ClassInspector {
    public static void inspect(String clazz) throws FileNotFoundException, ClassNotFoundException {
        Class<?> claz = Class.forName(clazz);
        String name = claz.getSimpleName() + ".java";
        PrintWriter printWriter = new PrintWriter(name);

        Field[] fields = claz.getDeclaredFields();
        printWriter.println("class "+ claz.getSimpleName()+" {");
        for (var fi : fields) {
            printWriter.println("    "+ Modifier.toString(fi.getModifiers())+" "
                    + fi.getType().getSimpleName() +" "+ fi.getName()+";");
        }
        printWriter.println();

        Constructor<?>[] constructors = claz.getDeclaredConstructors();
        for (var co : constructors) {
            printWriter.print("    "+ Modifier.toString(co.getModifiers())+" "+claz.getSimpleName()+"(");
            Parameter[] param = co.getParameters();
            printParameters(param, printWriter);
        }
        printWriter.println();

        Method[] methods = claz.getDeclaredMethods();
        for (var met : methods) {
            printWriter.print("    "+ Modifier.toString(met.getModifiers())+" "
                    + met.getReturnType().getSimpleName()+" "+ met.getName()+"(");
            Parameter[] param = met.getParameters();
            printParameters(param, printWriter);
        }
        printWriter.println('}');
        printWriter.close();
    }

    private static void printParameters(Parameter[] param, PrintWriter printWriter) {
        int j = 0;
        for (var p : param) {
            ++j;
            if (j == param.length) {
                printWriter.print(p.getType().getSimpleName()+" "+ p.getName());
            } else {
                printWriter.print(p.getType().getSimpleName()+" "+ p.getName()+", ");
            }
        }
        printWriter.println(") {}");
    }

    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Employee");
    }
}
