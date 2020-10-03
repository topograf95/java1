package ru.progwards.java2.lessons.basetypes;

class StringKey implements HashValue{
    private String key;
    final long UINT_MAX = 4294967295L;

    StringKey(String key) {
        this.key = key;
    }
    @Override
    public int getHash() {
        return (int) RSHash(this.key);
    }

    private long RSHash (String str) {
        long b = 378551;
        long a = 63689;
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = unsignedInt(hash * a + str.charAt(i));
            a = unsignedInt(a * b);
        }
        return hash;
    }

    private long unsignedInt(long num) {
        return num % UINT_MAX;
    }
}
