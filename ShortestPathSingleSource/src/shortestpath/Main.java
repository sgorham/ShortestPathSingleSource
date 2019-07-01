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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        String[] files = {"graphin_Fig1.txt","graphin_Fig2.txt","graphin_Fig4.txt","graphin_Fig5.txt"};
        int choice;
        OUTER_LOOP1:
        do{
            System.out.println("Select which file you would like to use: 0: Figure1  1: Figure 2  2: Figure 4 3: Figure 5 4: Exit");
            Scanner kb = new Scanner(System.in);
            Dijkstra dijk = new Dijkstra();
            DAGSP dag = new DAGSP();
            Bellman bell = new Bellman();
            choice = kb.nextInt();

            if(choice == 4){return;}
            Graph thisGraph = new Graph();
            makeGraph(thisGraph, files[choice]);
            dag.search(thisGraph);

            if (dag.checkCycle() == false) {
                System.out.println("There are no cylces, DAGSP will be used");
                dag.dagSP(thisGraph, 0, thisGraph.vertices.get(0));
                int sum = 0;
                for (int i = 0; i < thisGraph.vertices.size(); i++) {
                    System.out.println(thisGraph.vertices.get(i).id + " " + thisGraph.vertices.get(i).parent.id + " " + thisGraph.vertices.get(i).key);
                    sum += thisGraph.vertices.get(i).key;
                }
                System.out.println("The weight of this graph is " + sum);
                continue OUTER_LOOP1;
            }

            for (int i = 0; i < thisGraph.edges.size(); i++) {
                if (thisGraph.edges.get(i).weight < 0) {
                    System.out.println("This graph contains negative weights, Bellman Ford will be used");
                    bell.bf(thisGraph, thisGraph.vertices.get(0));
                    int sum = 0;
                    for (int j = 0; j < thisGraph.vertices.size(); j++) {
                        System.out.println(thisGraph.vertices.get(j).id + " " + thisGraph.vertices.get(j).parent.id + " " + thisGraph.vertices.get(j).key);
                        sum += thisGraph.vertices.get(j).key;
                    }
                    System.out.println("The final weight of this path is : " + sum);
                   continue OUTER_LOOP1;
                }
            }

            System.out.println("There are cycles and no negative edge weights, Dijkstra will be used");
            dijk.dijkstra(thisGraph, 0, thisGraph.vertices.get(0));
            int sum = 0;
            for (int i = 0; i < thisGraph.vertices.size(); i++) {
                System.out.println(thisGraph.vertices.get(i).id + " " + thisGraph.vertices.get(i).parent.id + " " + thisGraph.vertices.get(i).key);
                sum += thisGraph.vertices.get(i).key;
            }
            System.out.println("The weight of this graph is " + sum);

        }while(choice != 5);

	// write your code here
    }
    public static void makeGraph(Graph thisGraph, String fileName)throws java.io.IOException{
        File input = new File(fileName);
        String line;
        BufferedReader br;
        BufferedReader br2;

        try{
            br = new BufferedReader(new FileReader((input)));
            br2 = new BufferedReader(new FileReader((input)));
        }catch(Exception e){
            return;
        }

        while((line = br.readLine()) != null){
            String[] tempArr = line.split("[\\s\\:]+");
            Vertex temp = new Vertex();
            temp.id = Integer.parseInt(tempArr[0]);
            thisGraph.vertices.add(temp);
        }
        while((line = br2.readLine()) != null){
            String[] tempArr = line.split("[\\s\\:]+");
            for(int i = 1; i < tempArr.length; i += 2){
                //Edge thisEdge = new Edge(thisGraph.vertices.get(Integer.parseInt(tempArr[0])-1),thisGraph.vertices.get(Integer.parseInt(tempArr[i])-1), Integer.parseInt(tempArr[i+1]));
                Edge thisEdge = new Edge();
                thisEdge.vertex1 = thisGraph.vertices.get(Integer.parseInt(tempArr[0])-1);
                thisEdge.vertex2 = thisGraph.vertices.get(Integer.parseInt(tempArr[i])-1);
                thisEdge.weight = Integer.parseInt(tempArr[i+1]);
                thisGraph.vertices.get(Integer.parseInt(tempArr[0])-1).neighbors.add(thisEdge);
                thisGraph.edges.add(thisEdge);
            }
        }
    }

}
