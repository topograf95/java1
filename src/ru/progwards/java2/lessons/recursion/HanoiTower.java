package ru.progwards.java2.lessons.recursion;

public class HanoiTower {
    int size;
    int pos;
    int[] tower0;
    int[] tower1;
    int[] tower2;

    public HanoiTower(int size, int pos) {
        if (size < 1) {
            System.out.println("Недопустимое значение size = "+ size);
            System.out.println("Введите size от 1 до 20");
            return;
        }
        this.size = size;
        this.pos = pos;
        tower0 = new int[size];
        tower1 = new int[size];
        tower2 = new int[size];
    }

    private void fillingSourceTower(int[] tower) {
        for (int i = 0; i < size; ++i)
            tower[i] = i+1;
    }

    public void move(int from, int to) {
        pos = from;
        if (from == 0) {
            fillingSourceTower(tower0);
            print();
            if (to == 1) moveTower(size, tower0, tower1, tower2);
            else moveTower(size, tower0, tower2, tower1);
        } else if (from == 1) {
            fillingSourceTower(tower1);
            print();
            if (to == 0) moveTower(size, tower1, tower0, tower2);
            else moveTower(size, tower1, tower2, tower0);
        } else {
            fillingSourceTower(tower2);
            print();
            if (to == 0) moveTower(size, tower2, tower0, tower1);
            else moveTower(size, tower2, tower1, tower0);
        }
    }

    private void moveTower(int n, int[] from_rod, int[] to_rod, int[] aux_rod) {
        if (n < 1) {
            System.out.println("Недопустимое значение n = "+ n);
            System.out.println("Введите size от 1 до 20");
            return;
        }
        if (n == 1) {
            movingDisk(1, from_rod, to_rod);
            print();
            return;
        }
        moveTower(n-1, from_rod, aux_rod, to_rod);
        movingDisk(n, from_rod, to_rod);
        print();
        moveTower(n-1, aux_rod, to_rod, from_rod);
    }

    private void movingDisk(int n, int[] from, int[] to) {
        for (int i = 0; i < size; ++i) {
            if (from[i] == n) {
                from[i] = 0;
                for (int j = size - 1; j >= 0; --j) {
                    if (to[j] == 0) {
                        to[j] = n;
                        break;
                    }
                }
                break;
            }
        }
    }

    void print() {
        for (int i = 0; i < size; ++i) {
            if (tower0[i] == 0) System.out.print("  I   ");
            else System.out.printf("%c%03d%s", '<', tower0[i], "> ");
            if (tower1[i] == 0) System.out.print("  I   ");
            else System.out.printf("%c%03d%s", '<', tower1[i], "> ");
            if (tower2[i] == 0) System.out.println("  I  ");
            else System.out.printf("%c%03d%c%n", '<', tower2[i], '>');
        }
        System.out.println("=================");
    }

    public static void main(String[] args) {
        new HanoiTower(10, 0).move(0, 2);
    }
}
