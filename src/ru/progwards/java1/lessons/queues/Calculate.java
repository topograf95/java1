package ru.progwards.java1.lessons.queues;

public class Calculate {
    public static double calculation1() {
        StackCalc sc = new StackCalc();
        //  2.2*(3+12.1);
        sc.push(12.1);
        sc.push(3);
        sc.add();
        sc.push(2.2);
        sc.mul();
        return sc.pop();
    }
    public static double calculation2() {
        StackCalc sc = new StackCalc();
        //  (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2));
        sc.push(13.001);
        sc.push(9.2);
        sc.sub();
        sc.push(2);
        sc.mul();
        sc.push(87);
        sc.add();
        sc.push(19);
        sc.push(3.33);
        sc.sub();
        sc.mul();
        sc.push(24);
        sc.push(737.22);
        sc.add();
        sc.push(55.6);
        sc.push(12.1);
        sc.sub();
        sc.div();
        sc.add();
        return sc.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculation2());
    }
}
