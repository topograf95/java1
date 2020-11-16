package ru.progwards.java2.lessons.gc;

import java.util.Arrays;
import java.util.TreeMap;

public class Heap5 {
    byte[] bytes;
    TreeMap<Integer, Integer> mapFreeCell;
    TreeMap<Integer, Integer> mapOccupiedCell;
    int maxHeapSize;
    int freeCell = 0;

    Heap5(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        this.maxHeapSize = maxHeapSize;
        mapFreeCell = new TreeMap<>();
        mapOccupiedCell = new TreeMap<>();
    }

    public int malloc(int size) {
        if (freeCell+size <= maxHeapSize) {
            mapOccupiedCell.put(freeCell, size);
            bytes[freeCell] = 1;
            freeCell += size;
            return freeCell - size;
        }
        if (mapFreeCell.size() == 0) throw new OutOfMemoryException("Недостаточно памяти!");
        int res = -1;
        for (var pair : mapFreeCell.entrySet()) {
            if (pair.getValue() >= size) {
                res = pair.getKey();
                break;
            }
        }
        if (res < 0) {
            compact();
            return malloc(size);
        }
        int sizeB = mapFreeCell.remove(res);
        if (sizeB > size) {
            mapFreeCell.put(res + size, sizeB - size);
        }
        mapOccupiedCell.put(res, size);
        bytes[res] = 1;
        return res;
    }

    public void free(int ptr) {
        Integer size = mapOccupiedCell.remove(ptr);
        if (size == null) throw new InvalidPointerException("Неверный указатель!");
        bytes[ptr] = 0;
        Integer next = mapFreeCell.remove(ptr + size);
        if (next != null) {
            size += next;
        }
        var pair = mapFreeCell.lowerEntry(ptr);
        if (pair != null) {
            next = pair.getKey();
            if (next + pair.getValue() == ptr) {
                size += mapFreeCell.remove(next);
                ptr = next;
            }
        }
        mapFreeCell.put(ptr, size);
    }

    public void compact() {
        mapFreeCell.clear();
        freeCell = 0;
        int newPtr = 0, oldPtr, size;
        for (var pair : mapOccupiedCell.entrySet()) {
            oldPtr = pair.getKey();
            size = pair.getValue();
            freeCell += size;
            mapFreeCell.put(newPtr, size);
            if (newPtr == oldPtr) {
                newPtr += size;
            } else {
                   bytes[oldPtr] = 0;
                   while (newPtr < freeCell)  bytes[newPtr++] = bytes[oldPtr++];
                   bytes[newPtr - size] = 1;
            }
        }
        mapOccupiedCell.clear();
        mapOccupiedCell = new TreeMap<>(mapFreeCell);
        //   new HeapTest().setNewDeque(mapOccupiedCell);
        mapFreeCell.clear();
    }
    public static void main(String[] args) {
        Heap5 heap = new Heap5(15);
        System.out.println(heap.malloc(3));
        System.out.println(heap.malloc(2));
        System.out.println(heap.malloc(3));
        System.out.println(heap.malloc(1));
        System.out.println(heap.malloc(3));
        heap.free(0);
        heap.free(5);
        System.out.println(heap.malloc(3));
        heap.free(12);
        heap.free(9);
        System.out.println(heap.malloc(3));
        System.out.println(heap.malloc(2));
        heap.free(3);
     //   heap.free(5);
     //   System.out.println(heap.malloc(9));
        System.out.println(Arrays.toString(heap.bytes));
        System.out.println("Занятые блоки:   "+ heap.mapOccupiedCell.toString());
        System.out.println("Свободные блоки: "+ heap.mapFreeCell.toString());
    }
}
