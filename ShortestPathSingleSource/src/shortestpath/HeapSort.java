/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

/**
 *Gorham
 * CS340 Project 5
 * Shortest Path Single Source
 * 
 */
import java.util.ArrayList;
import java.util.Collections;

public class HeapSort {
    int heapSize;
    public void heapSort(ArrayList<Vertex> array){
        buildMinHeap(array);
        for(int i = array.size()-1; i > 0; i--) {
            Collections.swap(array, 0, i);
            heapSize = heapSize -1;
            minHeapify(array, 0);
        }
    }
    public void buildMinHeap(ArrayList<Vertex> array){
        heapSize = array.size()-1;
        for(int i = (int)Math.floor(array.size() / 2); i >= 0; i--){
            minHeapify(array, i);
        }
    }

    public void minHeapify(ArrayList<Vertex> array, int index){
        int min;
        int left = left(index);
        int right = right(index);

        if(left <= heapSize && array.get(left).key > array.get(index).key){
            min = left;
        }else {
            min = index;
        }

        if (right <= heapSize && array.get(right).key > array.get(min).key) {
            min = right;
        }

        if(min != index){
            Collections.swap(array, index, min);
            minHeapify(array, min);
        }
    }

    public int left(int index){
        int l = (2 * index) + 1;
        return l;
    }

    public int right(int index){
        int r = (2 * index) + 2;
        return r;
    }

    public Vertex extractMin(ArrayList<Vertex> array){
        Vertex min = null;
        if(array.size() < 1){
            System.out.println("Error underflow");
            return min;
        }
        min = array.get(0);
        Collections.swap(array, 0, heapSize);
        heapDelete(array);
        heapSize = heapSize -1;
        //minHeapify(array, 0);
        heapSort(array);
        return min;
    }

//    public void decreaseKey(ArrayList<Vertex> array, int index, int key){
//        if(key > array.get(index).key){
//            System.out.println("New key is larger than current key");
//        }
//        array.get(index).key = key;
//        while(index > 0 && array.get(index).parent.key > array.get(index).key){
//            array.get(index).adj.get(index).v.key = array.get(index).adj.get(index).v.parent.key;
//            index = array.get(index).parent.id - 1 ;
//        }
//    }

    public void heapInsert(ArrayList<Vertex> array, int key){
        heapSize = heapSize + 1;
        Vertex nu = new Vertex();
        array.add(nu);
        //decreaseKey(array, heapSize, key);

    }

    public void heapDelete(ArrayList<Vertex> array){
        array.remove(heapSize);
    }
}