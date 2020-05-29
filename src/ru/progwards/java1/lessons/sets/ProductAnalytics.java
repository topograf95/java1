package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = new ArrayList<>(shops);
        this.products = new ArrayList<>(products);
    }
    public Set<Product> existInAll() {
        Set<Product> result = new HashSet(products);
        for (Shop ls : shops) {
            result.retainAll(ls.getProducts());
        }
        return result;
    }
    public Set<Product> existAtListInOne() {
        Set<Product> allProdShops = new HashSet();
        for (Shop ls : shops) {
            allProdShops.addAll(ls.getProducts());
        }
        return allProdShops;
    }
    public Set<Product> notExistInShops() {
        Set<Product> result = new HashSet(products);
        result.removeAll(existAtListInOne());
        return result;
    }
    public Set<Product> existOnlyInOne() {
        Set<Product> result = new HashSet<>();
        Set<Product> tmpResult = new HashSet<>(existAtListInOne());
        tmpResult.removeAll(existInAll());
        for (Product prod : tmpResult) {
            int count = 0;
            for (Shop shop : shops) {
                Set<Product> prodSet = new HashSet<>(shop.getProducts());
                if (prodSet.contains(prod)) ++count;
            }
            if (count == 1) result.add(prod);
        }
        return result;
    }

    public static void main(String[] args) {
        Product a = new Product("1");
        Product b = new Product("2");
        Product c = new Product("3");
        Product d = new Product("4");
        Product e = new Product("5");
        Product f = new Product("6");
        Product g = new Product("7");
        Product h = new Product("8");
        Product k = new Product("9");
        Product j = new Product("10");
        Product m = new Product("11");
        Product n = new Product("12");
        Product p = new Product("13");
        List<Product> l1 = new ArrayList<>();
        Collections.addAll(l1, a, b, c, d, k, h);
        List<Product> l2 = new ArrayList<>();
        Collections.addAll(l2, k, h, g, f, e, d);
        List<Product> l3 = new ArrayList<>();
        Collections.addAll(l3, k, h, g, j, m);
        Shop sh1 = new Shop(l1);
        Shop sh2 = new Shop(l2);
        Shop sh3 = new Shop(l3);
        List<Product> big = new ArrayList<>();
        Collections.addAll(big, a, b, c, d, k, h, g, f, e, j, m, n, p);
        List<Shop> list = new ArrayList<>();
        Collections.addAll(list, sh1, sh2, sh3);
        ProductAnalytics pan = new ProductAnalytics(big, list);
        System.out.println(pan.existOnlyInOne().toString());
    }
}
