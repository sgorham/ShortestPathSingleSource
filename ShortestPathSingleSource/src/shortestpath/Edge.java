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
public class Edge {
    Vertex vertex1;
    Vertex vertex2;
    int weight;
    public void Edge(){
        weight = 0;
    }
    public void Edge(Vertex vertex1, Vertex vertex2, int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }
}
