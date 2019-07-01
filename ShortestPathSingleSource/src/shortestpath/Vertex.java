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

public class Vertex {
    int id;
    int key;
    Vertex parent;
    String color;
    int discover;
    int finish;
    ArrayList<Edge> neighbors = new ArrayList<>();
}
