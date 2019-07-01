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
import java.util.*;

public class Dijkstra {

    public void dijkstra(Graph thisGraph, int weight, Vertex source){
        //initialize
        SingleSource singleSourceStuff = new SingleSource();
        singleSourceStuff.initSingleSource(thisGraph, source);
        HeapSort heap = new HeapSort();
        ArrayList<Vertex> list = new ArrayList<>();
        ArrayList<Vertex> queue = new ArrayList<>(thisGraph.vertices);
        heap.heapSize = queue.size()-1;
        while(!queue.isEmpty()){
            Vertex min = heap.extractMin(queue);
            list.add(min);
            for(int i =0; i < min.neighbors.size(); i ++){
                //relax
                singleSourceStuff.relax(min, min.neighbors.get(i).vertex2, min.neighbors.get(i).weight);
            }
        }

    }


}