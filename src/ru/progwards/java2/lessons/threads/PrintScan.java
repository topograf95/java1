package ru.progwards.java2.lessons.threads;

class PrintScan {

    static synchronized void print(String name, int pages) {
        System.out.println("print <"+ name +"> page "+ pages);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static synchronized void scan(String name, int pages) {
        System.out.println("scan <"+ name +"> page "+ pages);
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int QUANTITY_OF_DOCUMENTS = 10;

        for (int i = 1; i <= QUANTITY_OF_DOCUMENTS; ++i) {
            String name = String.valueOf(i);
            (new Thread( () -> print(name,1))).start();
        }

        for (int i = 1; i <= QUANTITY_OF_DOCUMENTS; ++i) {
            String name = String.valueOf(i);
            (new Thread (new Runnable() {
                @Override
                public void run() {
                    scan(name,1);
                }
            })).start();
        }
    }
}
