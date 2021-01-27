package ru.progwards.java2.lessons.annotation;

import ru.progwards.java2.lessons.trees.AvlTreeTest;
import ru.progwards.java2.lessons.trees.TreeException;

import java.util.function.Consumer;

/**
 * Класс описывает структуру сбалансированного двоичного дерева поиска
 * @author Антон
 * @version 1.0
 * @param <K> - "ключ" любой тип для которого определен интерфейс Comparable;
 * @param <V> - "значение" любой тип;
 * @see AvlTreeTest
 */

public class AvlTree1<K extends Comparable<K>, V> {
    /**
     * Корневой узел дерева
     */
    private TreeLeaf<K, V> root;

    /**
     * Класс описавает узел дерева
     * @param <K> - "ключ" любой тип для которого определен интерфейс Comparable;
     * @param <V> - "значение" любой тип;
     */

    class TreeLeaf<K extends Comparable<K>, V> {
        /**
         * "Ключ" уникальный (не повторяется)
         */
        K key;
        /**
         * "Значение" прикреплено к Ключу
         */
        V value;
        /**
         * Высота поддерева с корнем в данном узле
         */
        byte height = 1;
        /**
         * Ссылка на левый узел
         */
        TreeLeaf<K, V> left;
        /**
         * Ссылка на правый узел
         */
        TreeLeaf<K, V> right;

        /**
         * Конструктор с параметрами
         * @param key - ключ
         * @param value - значение
         */
        private TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Обертка("защита") для нулевых указателей
         * @param a - ссылка на узел
         * @return высота поддерева
         */
        private byte height(TreeLeaf<K, V> a) {
            return a == null ? 0 : a.height;
        }

        /**
         * Вычисляет разницу высот левого и правого поддеревьев
         * @param a - узел дерева
         * @return разность высот
         */
        private int bFactor(TreeLeaf<K, V> a) {
            return height(a.right) - height(a.left);
        }

        /**
         * Восстанавливает корректное значение поля height заданного узла
         * @param a - ссылка на узел
         */
        private void fixHeight(TreeLeaf<K, V> a) {
            a.height = (byte)(Math.max(height(a.left), height(a.right))+1);
        }

        /**
         * Простой правый поворот вокруг p
         * @param p - узел
         * @return новый корень(узел)
         */
        private TreeLeaf<K, V> rotateRight(TreeLeaf<K, V> p) {  // правый поворот вокруг p;
            TreeLeaf<K, V> q = p.left;
            p.left = q.right;
            q.right = p;
            fixHeight(p);
            fixHeight(q);
            return q;
        }

        /**
         * Простой левый поворот вокруг q
         * @param q - узел
         * @return новый корень(узел)
         */
        private TreeLeaf<K, V> rotateLeft(TreeLeaf<K, V> q) {  // левый поворот вокруг q;
            TreeLeaf<K, V> p = q.right;
            q.right = p.left;
            p.left = q;
            fixHeight(q);
            fixHeight(p);
            return p;
        }

        /**
         * Проверяет условие разбалансирования и выполняет "большой" или простой повороты
         * @param a - узел
         * @return новый корень(узел)
         */
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

        /**
         * Обход дерева вниз рекурсивно и вставка ключа и значения в правое или левое поддерево
         * @param a - корень дерева
         * @param key - ключ
         * @param val - значение
         * @return balance(a) новый корень(узел) после балансировки
         */
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

        /**
         * Поиск узла с минимальным ключом в дереве a
         * @param a - узел
         * @return узел с минимальным ключом из левой ветки
         */
        private TreeLeaf<K, V> findMin(TreeLeaf<K, V> a) {  // поиск узла с минимальным ключом в дереве a;
            return a.left != null ? findMin(a.left) : a;
        }

        /**
         * удаление узла с минимальным ключом из дерева a
         * @param a - узел
         * @return balance(a) новый корень(узел) после балансировки
         */
        private TreeLeaf<K, V> removeMin(TreeLeaf<K, V> a) {  // удаление узла с минимальным ключом из дерева a;
            if (a.left == null)
                return a.right;
            a.left = removeMin(a.left);
            return balance(a);
        }

        /**
         * Удаление ключа key из дерева p
         * @param p - узел
         * @param key - ключ
         * @return balance новый корень(узел) после балансировки
         */
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

        /**
         * Прямой обход дерева
         * @param consumer функциональный интерфейс
         */
        public void process(Consumer<TreeLeaf<K,V>> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }

    /**
     * Ищет ключ
     * @param key - ключ
     * @return значение по ключу
     */
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

    /**
     * Добавляет пару ключ значение, если такой ключ есть заменяет значение
     * @param key - ключ
     * @param value - значение
     * @throws TreeException если значение равно нулл
     */
    public void put( K key, V value) throws TreeException {
        if (value == null) throw new TreeException("Invalid Value = null");
        if (root == null)
            root = new TreeLeaf<>(key, value);
        else
            root = root.put(root, key, value);
    }

    /**
     * Удаляет пару по ключу
     * @param key - ключ
     * @throws TreeException если ключ не найден
     * @see TreeLeaf#remove(TreeLeaf, Comparable) 
     */
    public void delete(K key) throws TreeException {
 //       V value = find(key);
        if (root == null) throw new TreeException("Key not exist");
        root = root.remove(root, key);
    }

    /**
     * Заменяет старый ключ на новый
     * @param oldKey - старый ключ
     * @param newKey - новый ключ
     * @throws TreeException если ключ не найден
     */
    public void change(K oldKey, K newKey) throws TreeException {
        V value = find(oldKey);
        if (value == null) throw new TreeException("Key not exist");
        delete(oldKey);
        put(newKey, value);
    }

    /**
     * Запускает процесс обхода дерева
     * @param consumer функциональный интерфейс
     * @see TreeLeaf#process(Consumer) 
     */
    public void process(Consumer<TreeLeaf<K,V>> consumer) {
        if (root != null)
            root.process(consumer);
    }

    /**
     * Возращает ссылку на корневой узел дерева
     * @return корневой узел
     */
    public TreeLeaf<K, V> getRoot() {
        return this.root;
    }

    /**
     * Рекурсивно выводит на консоль свойства узлов(ключ, значение, высоту)
     * @param a - узел
     */
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
