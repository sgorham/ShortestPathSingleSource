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
public class Bellman {
    SingleSource singleSourceStuff = new SingleSource();

    public boolean bf(Graph thisGraph, Vertex source){
        singleSourceStuff.initSingleSource(thisGraph, source);
        for(int i = 0; i < Math.abs(thisGraph.vertices.size() -1); i++){
            for(int j = 0; j < thisGraph.edges.size(); j++){
                singleSourceStuff.relax(thisGraph.edges.get(j).vertex1, thisGraph.edges.get(j).vertex2, thisGraph.edges.get(j).weight);
            }
        }

        for(int i = 0; i < thisGraph.edges.size(); i++){
            if(thisGraph.edges.get(i).vertex1.key > thisGraph.edges.get(i).vertex2.key + thisGraph.edges.get(i).weight){
                return false;
            }
        }
        return true;
    }
}