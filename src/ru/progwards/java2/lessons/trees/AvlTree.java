package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class AvlTree<K extends Comparable<K>, V> {
    private TreeLeaf<K, V> root;

    class TreeLeaf<K extends Comparable<K>, V> {
        K key;
        V value;
        byte height = 1;
        TreeLeaf<K, V> left;
        TreeLeaf<K, V> right;

        private TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private byte height(TreeLeaf<K, V> a) {
            return a == null ? 0 : a.height;
        }

        private int bFactor(TreeLeaf<K, V> a) {
            return height(a.right) - height(a.left);
        }

        private void fixHeight(TreeLeaf<K, V> a) {
            a.height = (byte)(Math.max(height(a.left), height(a.right))+1);
        }

        private TreeLeaf<K, V> rotateRight(TreeLeaf<K, V> p) {  // правый поворот вокруг p;
            TreeLeaf<K, V> q = p.left;
            p.left = q.right;
            q.right = p;
            fixHeight(p);
            fixHeight(q);
            return q;
        }

        private TreeLeaf<K, V> rotateLeft(TreeLeaf<K, V> q) {  // левый поворот вокруг q;
            TreeLeaf<K, V> p = q.right;
            q.right = p.left;
            p.left = q;
            fixHeight(q);
            fixHeight(p);
            return p;
        }

        private TreeLeaf<K, V> balance(TreeLeaf<K, V> a) {  // балансировка узла a;
            fixHeight(a);
            if (bFactor(a) == 2) {
                if (bFactor(a.right) < 0)
                    a.right = rotateRight(a.right);
                return rotateLeft(a);
            }
            if (bFactor(a) == -2) {
                if (bFactor(a.left) > 0)
                    a.left = rotateLeft(a.left);
                return rotateRight(a);
            }
            return a;
        }

        private TreeLeaf<K, V> put(TreeLeaf<K, V> a, K key, V val) {
            if (a == null) return new TreeLeaf<>(key, val);
            int cmp = key.compareTo(a.key);
            if (cmp < 0) a.left = put(a.left, key, val);
            else if (cmp > 0) a.right = put(a.right, key, val);
            else {
                a.value = val;
                return a;
            }
            return balance(a);
        }

        private TreeLeaf<K, V> findMin(TreeLeaf<K, V> a) {  // поиск узла с минимальным ключом в дереве a;
            return a.left != null ? findMin(a.left) : a;
        }

        private TreeLeaf<K, V> removeMin(TreeLeaf<K, V> a) {  // удаление узла с минимальным ключом из дерева a;
            if (a.left == null)
                return a.right;
            a.left = removeMin(a.left);
            return balance(a);
        }

        private TreeLeaf<K, V> remove(TreeLeaf<K, V> p, K key) { // удаление ключа key из дерева p;
            if (p == null) return null;
            if (key.compareTo(p.key) < 0)
                p.left = remove(p.left, key);
            else if (key.compareTo(p.key) > 0)
                p.right = remove(p.right, key);
            else {
                TreeLeaf<K, V> q = p.left;
                TreeLeaf<K, V> r = p.right;
                if (r == null) return q;
                TreeLeaf<K, V> min = findMin(r);
                min.right = removeMin(r);
                min.left = q;
                return balance(min);
            }
            return balance(p);
        }

        @Override
        public String toString() {
            return "("+ key +", "+ value +", h-"+ height +")";
        }

        public void process(Consumer<TreeLeaf<K,V>> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }

    public V find(K key) {
        TreeLeaf<K, V> a = root;
        while (a != null) {
            int cmp = key.compareTo(a.key);
            if (cmp == 0) return a.value;
            else if (cmp < 0) a = a.left;
            else a = a.right;
        }
        return null;
    }

    public void put( K key, V value) throws TreeException {
        if (value == null) throw new TreeException("Invalid Value = null");
        if (root == null)
            root = new TreeLeaf<>(key, value);
        else
            root = root.put(root, key, value);
    }

    public void delete(K key) throws TreeException {
 //       V value = find(key);
        if (root == null) throw new TreeException("Key not exist");
        root = root.remove(root, key);
    }

    public void change(K oldKey, K newKey) throws TreeException {
        V value = find(oldKey);
        if (value == null) throw new TreeException("Key not exist");
        delete(oldKey);
        put(newKey, value);
    }

    public void process(Consumer<TreeLeaf<K,V>> consumer) {
        if (root != null)
            root.process(consumer);
    }

    public TreeLeaf<K, V> getRoot() {
        return this.root;
    }

    public void show(TreeLeaf<K, V> a) {
        if (a == null) return;
        System.out.println(a);
        show(a.left);
        show(a.right);
    }

//    public void show1() {
//        TreeLeaf<K, V> a = root;
//        System.out.println("root "+ a);
//        while (a != null) {
//            System.out.println("right "+ a.right);
//            a = a.left;
//            System.out.println("left  "+ a);
//        }
//        a = root;
//        System.out.println("root "+ a);
//        while (a != null) {
//            System.out.println("left  "+ a.left);
//            a = a.right;
//            System.out.println("right "+ a);
//        }
//    }
}
