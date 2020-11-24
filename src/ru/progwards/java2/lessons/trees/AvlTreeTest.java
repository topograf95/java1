package ru.progwards.java2.lessons.trees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AvlTreeTest {
    public static void main(String[] args) throws TreeException {
        final int VOLUME = 500_000;
        AvlTree<String, Integer> tree = new AvlTree<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        HashSet<String> set = new HashSet<>(VOLUME);
        long start, stop, timeMap = 0, timeTree = 0;
        String skey = "";

        for(int i = 0; i < VOLUME; i++) {
            int key = ThreadLocalRandom.current().nextInt();
            skey += key;
            set.add(skey);
            start = System.currentTimeMillis();
            map.put(skey, i);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.put(skey, i);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
            skey = "";
        }
        System.out.println("TreeMap put random key - "+ timeMap);
        System.out.println("AvlTree put random key - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String key : set) {
            start = System.currentTimeMillis();
            map.get(key);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.find(key);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap get random key -  "+ timeMap);
        System.out.println("AvlTree find random key - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String key : set) {
            start = System.currentTimeMillis();
            map.remove(key);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.delete(key);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap remove random key - "+ timeMap);
        System.out.println("AvlTree delete random key - "+ timeTree);
        System.out.println("----------------------------------" +"\n");

        TreeSet<String> treeSet = new TreeSet<>(set);
        set.clear();
        timeMap = 0;
        timeTree = 0;
        int value = 0;
        for (String ky : treeSet) {
            value++;
            start = System.currentTimeMillis();
            map.put(ky, value);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.put(ky, value);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap put sorted keys - "+ timeMap);
        System.out.println("AvlTree put sorted keys - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String ky : treeSet) {
            start = System.currentTimeMillis();
            map.get(ky);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.find(ky);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap get sorted keys -  "+ timeMap);
        System.out.println("AvlTree find sorted keys - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String ky : treeSet) {
            start = System.currentTimeMillis();
            map.remove(ky);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.delete(ky);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap remove sorted keys - "+ timeMap);
        System.out.println("AvlTree delete sorted keys - "+ timeTree);
        System.out.println("----------------------------------" +"\n");
        treeSet.clear();

        List<String> list = new ArrayList<>();
        Path p = Paths.get(
                "C:/Users/User/IdeaProjects/Prodvinutiy/src/ru/progwards/java2/lessons/trees/wikiTrainTokens.txt");
        try {
            list = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str : list) {
            if (!str.isBlank()) {
                skey = str.strip();
                String[] arr = skey.split("[\\s,.=\"():!?-]+");
                HashSet<String> tmpSet = new HashSet<>(Arrays.asList(arr));
                set.addAll(tmpSet);
            }
        }
        list.clear();

        timeMap = 0;
        timeTree = 0;
        for (String key : set) {
            value++;
            start = System.currentTimeMillis();
            map.put(key, value);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.put(key, value);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap put token key - "+ timeMap);
        System.out.println("AvlTree put token key - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String key : set) {
            start = System.currentTimeMillis();
            map.get(key);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.find(key);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap get token key -  "+ timeMap);
        System.out.println("AvlTree find token key - "+ timeTree +"\n");

        timeMap = 0;
        timeTree = 0;
        for (String key : set) {
            start = System.currentTimeMillis();
            map.remove(key);
            stop = System.currentTimeMillis();
            timeMap += stop - start;
            start = System.currentTimeMillis();
            tree.delete(key);
            stop = System.currentTimeMillis();
            timeTree += stop - start;
        }
        System.out.println("TreeMap remove token key - "+ timeMap);
        System.out.println("AvlTree delete token key - "+ timeTree);
    }
}
