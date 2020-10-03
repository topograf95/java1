package ru.progwards.java2.lessons.basetypes;

class IntKey implements HashValue {
    private int key;

    IntKey(int key) {
        this.key = key;
    }
    @Override
    public int getHash() {
        return key;
    }
}
