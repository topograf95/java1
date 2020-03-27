package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a +"+"+ b +"i";
    }
    public ComplexNum add(ComplexNum num) {
        return new ComplexNum(a + num.a, b + num.b);
    }
    public ComplexNum sub(ComplexNum num) {
        return new ComplexNum(a - num.a, b - num.b);
    }
    public ComplexNum mul(ComplexNum num) {
        int Re = a * num.a - b * num.b;
        int Im = b * num.a + a * num.b;
        return new ComplexNum(Re, Im);
    }
    public ComplexNum div(ComplexNum num) {
        int Re = (a*num.a + b*num.b) / (num.a*num.a + num.b*num.b);
        int Im = (b*num.a - a*num.b) / (num.a*num.a + num.b*num.b);
        return new ComplexNum(Re, Im);
    }

    public static void main(String[] args) {
        ComplexNum a_b = new ComplexNum(3, 5);
        ComplexNum d_c = new ComplexNum(2, 3);
        System.out.println(a_b.add(d_c));
        System.out.println(a_b.sub(d_c));
        System.out.println(a_b.mul(d_c));
        System.out.println(a_b.div(d_c));
    }
}
