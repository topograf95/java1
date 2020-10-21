package ru.progwards.java2.lessons.basetypes;

class IntKey<K> implements HashValue {
    private K key;

    IntKey(K key) {
        this.key = key;
    }
    @Override
    public int getHash() {
        return key.hashCode();
    }
}
