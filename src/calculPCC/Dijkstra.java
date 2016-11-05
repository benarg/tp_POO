/*
  The dijkstra class enables you to construct a dikstra object given 
  an adjacency matric, a source node and a destination node.
  Once the object constructed, its attributes distDestNode (integer) 
  and pathToDest(ArrayList<Integer>) will give you the answers to your
  questions : 
  => what is the shortest path from my source node to my destination node ?
  => what is the time it takes to travel on this shortest path from my
     source node to my destination node
*/ 

package calculPCC;

import java.util.ArrayList;
import java.lang.Math;

public class Dijkstra {

    private int distDestNode;
    private ArrayList<Integer> pathToDest;

    public Dijkstra(int adj[][], int startNode, int destNode) {
	
	int n = adj.length;
	
	int[][] cost = new int[n][n];
	int[] distance = new int[n];
	int[] pred = new int[n];
	int[] visited = new int[n];
	int count;
	int minDistance;
	int nextNode = 0;
	
	//pred[] stores the predecessor of each node
	//count gives the number of nodes seen so far
	//create the cost matrix
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (adj[i][j] == 0) {
		    cost[i][j] = 99999999;
		} else {
		    cost[i][j] = adj[i][j];
		}
	    }
	}	
	
	//initialize pred[],distance[] and visited[]
	for (int i = 0; i < n; i++) {
	    distance[i] = cost[startNode][i];
	    pred[i] = startNode;
	    visited[i] = 0;
	}

	distance[startNode] = 0;
	visited[startNode] = 1;
	count = 1;

	while (count < n-1) {

	    minDistance =  99999999;

	    //nextnode gives the node at minimum distance
	    for (int i = 0; i < n; i++) {

		if ((distance[i] < minDistance) && (visited[i] == 0)) {
		    minDistance = distance[i];
		    nextNode = i;
		}
	    }
	    
	    //check if a better path exists through nextnode
	    visited[nextNode] = 1;
	    for (int i = 0; i < n; i++) {
		if (visited[i] == 0) {
		    if (minDistance + cost[nextNode][i] < distance[i]) {
			distance[i] = minDistance + cost[nextNode][i];
			pred[i] = nextNode;
		    }
		}
	    }
	    count++;
	}



	//print the path and distance of each node
	
	/*
	  for (int i = 0; i < n; i++) {
	  if (i != startNode) {
	  System.out.println("Distance of node "
	  + i + " = " + distance[i]);
	  System.out.print("Path" + " = " + i);

	  int j = i;

	  do {
	  j = pred[j];
	  System.out.print("<-" + j);
	  } while (j != startNode);

	  System.out.println("");
	  }
	  }
	*/

	int distDestNode = distance[destNode];
	ArrayList<Integer> pathToDest = new ArrayList<Integer>();

	int j = destNode;

	pathToDest.add(j);

	do {
	    j = pred[j];
	    pathToDest.add(j);
	} while (j != startNode);
	

	/* 
	   We invert the arrayList containing the path to obtain
	   a path from source => dest instead of dest => source
	*/
	
	int middle = (int) Math.floor(pathToDest.size()/2);
	int size = pathToDest.size();
	
	for (int i = 0; i < middle; i++) {
	    int tmp = pathToDest.get(i);
	    pathToDest.set(i, pathToDest.get(size - i - 1));
	    pathToDest.set(size - i - 1, tmp);
	}
	
	this.pathToDest = pathToDest;
	this.distDestNode = distDestNode;

	
    }

    public ArrayList<Integer> getPathToDest() {

	return this.pathToDest;

    }

    public int getDistDestNode() {

	return this.distDestNode;
	
    }

    
}
