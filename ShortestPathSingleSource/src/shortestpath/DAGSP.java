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

public class DAGSP {
    int stackTop = 0;
    int time;
    ArrayList<BackEdge> backEdges = new ArrayList<>();
    //    LinkedList<Vertex> topologicalSort = new LinkedList<>();
    ArrayList<Vertex> vertexStack = new ArrayList<>();

    public void dagSP(Graph thisGraph, int weight, Vertex source){
        SingleSource singSource = new SingleSource();

        singSource.initSingleSource(thisGraph,source);
        for(int i = 0 ; i < vertexStack.size(); i++){
            for(int j = 0; j < vertexStack.get(i).neighbors.size(); j++){
                singSource.relax(vertexStack.get(i), vertexStack.get(i).neighbors.get(j).vertex2, vertexStack.get(i).neighbors.get(j).weight);
            }
        }
    }





    public void search(Graph thisGraph){
        for(int i = 0; i < thisGraph.vertices.size(); i++){
            thisGraph.vertices.get(i).color = "white";
            //graph.get(i).parent = Integer.MAX_VALUE;
        }
        time = 0;
        for(int i = 0; i < thisGraph.vertices.size(); i++){
            if(thisGraph.vertices.get(i).color.equals("white")){
                visit(thisGraph, thisGraph.vertices.get(i));
            }
        }
    }

    public void visit(Graph graph, Vertex thisVertex){
        time = time+ 1;
        thisVertex.discover = time;
        thisVertex.color = "grey";
        if(backEdges.isEmpty()){push(thisVertex);}
        for(int i = 0; i < thisVertex.neighbors.size(); i++){
            if(thisVertex.neighbors.get(i).vertex2.color.equals("white")){
                thisVertex.neighbors.get(i).vertex2.parent = thisVertex;
                visit(graph, thisVertex.neighbors.get(i).vertex2);
            }
            if(thisVertex.neighbors.get(i).vertex2.color.equals("grey")){
                BackEdge temp = new BackEdge();
                temp.vertexParent = thisVertex;
                temp.vertexToDiscover = thisVertex.neighbors.get(i).vertex2;
                backEdges.add(temp);
            }
        }
        thisVertex.color = "black";
        time = time + 1;
        thisVertex.finish = time;
        //pop();
    }

    public void topSort(){
        System.out.println("Here is the topological sort of the graph");
        for(int i = 0; i < vertexStack.size(); i++){
            System.out.print(vertexStack.get(i).id + " " );
        }
        System.out.println();
    }

    public boolean checkCycle(){
        if(backEdges.isEmpty()){
            System.out.println("This is an acyclic graph and will be topologically sorted");
            topSort();
            return false;
        }
            System.out.println("This graph contains one or more cycles");
//            System.out.println("The back edges of this graph are :");
//            for(int i = 0; i < backEdges.size(); i ++){
//                System.out.println(backEdges.get(i).vertexParent + " to " + backEdges.get(i).vertexToDiscover);
//            }
        return true;
    }
    public void pop(){
        if(stackEmpty() == true){
            System.err.println("Stack underflow");
        }
        stackTop = stackTop - 1;
    }
    public boolean stackEmpty(){
        if(stackTop == 0){
            return true;
        }
        return false;
    }
    public void push(Vertex toAdd){
        vertexStack.add(toAdd);
        stackTop = stackTop + 1;
    }
}
