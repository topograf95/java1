package ru.progwards.java2.lessons.gc;

import java.util.ArrayDeque;
import java.util.TreeMap;

class Heap1 {
 //   byte[] bytes;
    ArrayDeque<Block> dequeFreeBlock;
    TreeMap<Integer, Integer> mapOccupiedCell;
    int maxHeapSize;
    int freeCell = 0;

    Heap1(int maxHeapSize) {
 //       bytes = new byte[maxHeapSize];
        this.maxHeapSize = maxHeapSize;
        dequeFreeBlock = new ArrayDeque<>();
        mapOccupiedCell = new TreeMap<>();
    }

    static class Block {
        public int ptr;
        public int size;

        Block(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }

        @Override
        public String toString() {
            return "{"+ ptr +" "+ size +"}";
        }
    }

    public int malloc(int size) {
        if (freeCell+size <= maxHeapSize) {
            mapOccupiedCell.put(freeCell, size);
     //       bytes[freeCell] = 1;
            freeCell += size;
            return freeCell - size;
        }
        if (dequeFreeBlock.isEmpty()) throw new OutOfMemoryException("Недостаточно памяти!");
        int res = -1;
        for (Block b : dequeFreeBlock) {
            if (b.size >= size) {
                res = b.ptr;
                int sizeB = b.size;
                dequeFreeBlock.remove(b);
                if (sizeB > size) {
                    dequeFreeBlock.offerLast(new Block(res + size, sizeB - size));
                }
                break;
            }
        }
        if (res < 0) {
            compact();
            return malloc(size);
        }
        mapOccupiedCell.put(res, size);
     //   bytes[res] = 1;
        return res;
    }

    public void free(int ptr) {
        if (!mapOccupiedCell.containsKey(ptr)) throw new InvalidPointerException("Неверный указатель!");
     //   bytes[ptr] = 0;
        Integer ptrNext = mapOccupiedCell.higherKey(ptr);
        int size = mapOccupiedCell.remove(ptr);
        int ptr2 = ptr + size;
        if (ptrNext != null) {
            if (ptrNext > ptr2) {
                removePointerFromQueue(ptr2);
                size += (ptrNext - ptr2);
            }
        } else {
            if (freeCell > ptr2) {
                removePointerFromQueue(ptr2);
                size += freeCell - ptr2;
            }
        }
        ptrNext = mapOccupiedCell.lowerKey(ptr);
        if (ptrNext != null) {
            ptr2 = ptrNext + mapOccupiedCell.get(ptrNext);
            if (ptr2 < ptr) {
                removePointerFromQueue(ptr2);
                size += (ptr - ptr2);
                ptr = ptr2;
            }
        } else {
            if (ptr != 0) {
                removePointerFromQueue(0);
                size += ptr;
                ptr = 0;
            }
        }
        dequeFreeBlock.offerLast(new Block(ptr, size));
    }

    public void compact() {
        dequeFreeBlock.clear();
        freeCell = 0;
        int newPtr = 0, oldPtr, size;
        for (var pair : mapOccupiedCell.entrySet()) {
            oldPtr = pair.getKey();
            size = pair.getValue();
            freeCell += size;
            dequeFreeBlock.offerLast(new Block(newPtr, size));
            if (newPtr == oldPtr) {
                newPtr += size;
            } else {
             //   bytes[oldPtr] = 0;
             //   while (newPtr < freeCell)  bytes[newPtr++] = bytes[oldPtr++];
             //   bytes[newPtr - size] = 1;
            }
        }
        mapOccupiedCell.clear();
        dequeFreeBlock.forEach( b -> mapOccupiedCell.put(b.ptr, b.size));
     //   new HeapTest().setNewDeque(dequeFreeBlock);
        dequeFreeBlock.clear();
    }

    private void removePointerFromQueue(int ptr) {
        for (Block b : dequeFreeBlock) {
            if (b.ptr == ptr) {
                dequeFreeBlock.remove(b);
                break;
            }
        }
    }

//    int sizeOccupied() {
//        return mapOccupiedCell.size();
//    }
//
//    int sizeFree() {
//        return dequeFreeBlock.size();
//    }

//    public static void main(String[] args) {
//        Heap1 heap = new Heap1(15);
//        System.out.println(heap.malloc(3));
//        System.out.println(heap.malloc(2));
//        System.out.println(heap.malloc(4));
//        System.out.println(heap.malloc(1));
//        System.out.println(heap.malloc(5));
//        heap.free(0);
//        heap.free(5);
//        heap.free(9);
////        System.out.println(heap.malloc(4));
//        System.out.println(heap.malloc(8));
//        System.out.println(Arrays.toString(heap.bytes));
//        System.out.println("Занятые блоки:   "+ heap.mapOccupiedCell.toString());
//        System.out.println("Свободные блоки: "+ heap.dequeFreeBlock.toString());
//    }
}
