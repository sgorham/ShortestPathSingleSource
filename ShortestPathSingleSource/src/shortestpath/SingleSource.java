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
public class SingleSource {

    public void relax(Vertex u, Vertex v, int weight){
        if(v.key > u.key + weight){
            v.key = u.key + weight;
            v.parent = u;
        }
    }

    public void initSingleSource(Graph thisGraph , Vertex source){
        for(int i = 0; i < thisGraph.vertices.size(); i++){
            thisGraph.vertices.get(i).key = Integer.MAX_VALUE;
            thisGraph.vertices.get(i).parent = thisGraph.vertices.get(i);
        }
        source.key = 0;
    }
}
