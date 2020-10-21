package ru.progwards.java2.lessons.basetypes;

import java.util.Iterator;
import java.util.NoSuchElementException;

    // Хэш таблица с двойным хэширование, аналог словаря (Map),
    // ключём могут быть одновременно данные разных типов (int, long, double, String);

public class DoubleHashTable<K, V> implements
        Iterable<DoubleHashTable.TableItem>,
        Iterator<DoubleHashTable.TableItem> {

    @Override
    public Iterator<DoubleHashTable.TableItem> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (this.numberElem < this.count_elem) return true;
        this.index_table = 0;
        this.numberElem = 0;
        return false;
    }

    @Override
    public TableItem<K, V> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        do {
            this.index_table++;
        } while (this.table[this.index_table - 1] == null);
        this.numberElem++;
        return this.table[this.index_table - 1];
    }

    @Override
    public void remove() {
        if (this.index_table == 0) throw new IllegalStateException();
        if (this.table[this.index_table - 1] != null) {
            this.table[this.index_table - 1] = null;
            this.count_elem--;
            this.numberElem--;
        } else throw new IllegalStateException();
    }

    class TableItem<K, V> { // Элемент таблицы;
        private K key;      // Ключ;
        private V item;     // Значение;
        private int depth;  // Количество коллизий ("глубина") каждого элемента,
                            // в решении не используется, сохраняется для информации;

        TableItem(K key, V item, int depth) {
            this.key = key;
            this.item = item;
            this.depth = depth;
        }

        K getKey() { return key; }

        V getItem() { return item; }

        @Override
        public String toString() {
            return "Key - "+ key +"   Value - "+ item +"   depth - "+ depth;
        }
    }

    private TableItem<K,V>[] table;    // Основной массив для хранения элементов "TableItem<K, V>";
    private TableItem<K,V>[] tableTmp; // Временный массив для хранения данных при перестроении таблицы;
    private int count_elem = 0;        // Количество элементов "TableItem<K, V>";
    private int collision = 11;        // Максимальное количество коллизий для одного элемента,
                                    // вычисляется по формуле: collision = table.length / 10 + 1;
    private int count_build = 0;       // Количество перестроений таблицы подряд;
    private int index_table = 0;       // Индекс элемента в массиве, используется для Iterator;
    private int numberElem = 0;        // Порядковый номер элемента для Iterator;

    DoubleHashTable() {
        this.table = new TableItem[101];
    } // Размер массива - Простое число;

    private int hashDev(int key) {   // Хэш функция для вычисления индекса в массиве;
        return Math.abs(key % table.length);
    }

    private int hashMul(int key) {   // Хэш функция для повторного вычисления индекса в случае коллизии;
        double d = key * 0.6180339887;
        return (int)(table.length * (d - Math.floor(d)));
    }

    private int getTmpKey(K key) {   // Определение типа (String или любого числового) и вычисление первичного ключа
        HashValue hashValue;         // с помощью интерфейса HashValue;
        if (key.getClass().getName().equals("java.lang.String")) {
            hashValue = new StringKey((String)key);
        } else {
            hashValue = new IntKey(key);
        }
        return hashValue.getHash();
    }

    private int calculateNewSize() {  // Вычисление размера массива (простого числа) при перестроении таблицы;
        int size = table.length * 2 - 1;
        boolean flag;
        do {
            flag = false;
            size += 2;
            for (int i = 3, n = size/2 + 1; i < n; i += 2) {
                if (size % i == 0) {
                    flag = true;
                    break;
                }
            }
        } while (flag);
        return size;
    }

    private void buildNewTable(K key, V value) {   // Перестроение таблицы;
        if (count_build < 2) {
            tableTmp = new TableItem[table.length];
            System.arraycopy(table, 0, tableTmp, 0, tableTmp.length);
        }
 //       System.out.println("Количество перестроений = "+ count_build);
        int size = calculateNewSize();
        table = new TableItem[size];
        collision = table.length / 10 + 1;
        count_elem = 0;
        int k = 0;
        for (int i = 0, n = tableTmp.length; i < n; ++i) {
            if (tableTmp[i] != null) {
                add(tableTmp[i].getKey(), tableTmp[i].getItem());
                ++k;
                if (count_elem > k) break;
     //           System.out.println("элементов - "+ k);
            }
        }
        if (count_elem <= k) add(key, value);
//        System.out.println("Записано элементов - "+ k);
    }

    public void add(K key, V value) {
        int tpmKey = getTmpKey(key);
        int index = hashDev(tpmKey);
        int count_collision = 0;
        while (table[index] != null && count_collision < collision) {
            ++count_collision;     // Перевычесление индекса элемента при возникновении коллизии;
            index = (index + hashMul(tpmKey)) % table.length;
        }
        if (count_collision < collision) {
            table[index] = new TableItem<>(key, value, count_collision);
            ++count_elem;
        } else {
     //       System.out.println(index +" - "+ count_collision +" key = "+ key);
            ++count_build;
            buildNewTable(key, value); // Перестроение таблицы (увеличение вдвое + до простого числа), если кол-во
            count_build = 0;           // коллизий одного элемента больше или равно максимальному (collision);
        }
    }

    public V get(K key) {     // Получение значения по ключу;
        int tpmKey = getTmpKey(key);
        int index = hashDev(tpmKey);
        for (int i = 0; i < collision; ++i) {
            if (table[index] != null && table[index].getKey().equals(key)) {
                return table[index].getItem();
            }
            index = (index + hashMul(tpmKey)) % table.length;
        }
        System.out.println("Ключ [ "+ key +" ] не найден!");
        return null;
    }

    public void remove(K key) {   // Удаление по ключу;
        int tpmKey = getTmpKey(key);
        int index = hashDev(tpmKey);
        for (int i = 0; i < collision; ++i) {
            if (table[index] != null && table[index].getKey().equals(key)) {
                table[index] = null;
                --count_elem;
                break;
            }
            index = (index + hashMul(tpmKey)) % table.length;
        }
    }

    public void change(K key1, K key2) {  // Замена ключа у элемента с key1 на key2;
        V value = get(key1);
        remove(key1);
        add(key2, value);
    }

    public int size() {
        return count_elem;
    }

    void show() {
        int d = 0;
        for (TableItem<K, V> tableItem : table) {
            if (tableItem != null) {
                ++d;
                System.out.println(d + ".   " + tableItem);
            }
        }
        System.out.println("Количество элементов = "+ count_elem);
    }

    public static void main(String[] args) {
        DoubleHashTable tab = new DoubleHashTable();
        for (int i = 0; i < 4; ++i) {
            String s = Integer.toString(i);
            s += "qwert";
            if (i % 2 == 0) {
                tab.add(i, i*2);
            } else tab.add(s, i*2);
        }

        for (int i = 0; i < 4; ++i) {
            String s = Integer.toString(i);
            s += "qwert";
            if (i % 2 == 0) {
                System.out.println(i +" - "+ tab.get(i));
            } else {
                System.out.println(s +" - "+ tab.get(s));
            }
        }
        System.out.println(tab.get("477qwert"));
        tab.remove("477qwert");
        System.out.println(tab.get("477qwert"));
        tab.add("477qwert", "OK");
        System.out.println(tab.get("477qwert"));
        tab.change("477qwert", -1000123456789.55);
        System.out.println(tab.get(-1000123456789.55));
 //       tab.show();
        System.out.println(tab.size());
        tab.forEach(a -> System.out.println(a));
        System.out.println("---------------------------------------");
        for (var t : tab) {
            System.out.println(t);
        }
        System.out.println("---------------------------------------");
        while (tab.hasNext()) {
            System.out.println(tab.next());
        }
        System.out.println("---------------------------------------");
        tab.next();
        tab.remove();
        tab.next();
        tab.remove();
        tab.forEach(System.out::println);
        System.out.println(tab.next().key);  // По итералам можно получить приватные поля класса!!?
        System.out.println(tab.next().item);
        System.out.println(tab.get(-1000123456789.55));
        System.out.println(tab.size());
    }
}
