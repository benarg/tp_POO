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

/**
 * La classe Dijkstra permet d'effectuer des calculs de plus court chemin
 * a partir d'une matrice d'adjacence associee a un couple (robot, carte)
 * 
 * L'attribut de la classe est un chemin allant de la case source a la 
 * case destination entree en parametres
 */

package calculPCC;

import java.util.ArrayList;
import java.lang.Math;
import outilsBase.*;

public class Dijkstra {

    private int startNode;

    // distance contient la distance du noeud start a tout les autres noeuds
    private int[] distance;

    // pred[] contient le predecesseur de chaque noeud
    private int[] pred;

    private Carte carte;


    /**
     * cette methode permet de traduire le sommet au sein d'une carte (Case)
     * en un sommet au sein de la matrice d'adjacence (int)
     * @return numeroSommet (int) du sommet (Case) 
     */
    private int numeroSommet(Case sommet, Carte c) {
	return (sommet.getLigne()*c.getNbColonnes() + sommet.getColonne());
    }

    /**
     * cette methode permet de traduire le sommet au sein d'une matrice 
     * d'adjacence (int) en un sommet au sein de la matrice d'adjacence (Case)
     * @return caseDuSommet (Case) du sommet (int) 
     */
    private Case caseDuSommet(int sommet, Carte c) {
	return c.getCase(sommet/c.getNbColonnes(), sommet%(c.getNbColonnes()));
    }

    /**
     * Construit l'objet Dijkstra a partir d'une Case start, d'une Case dest,
     * d'une Carte carte et d'une matrice d'adjacence
     */
    public Dijkstra(int adj[][], Case start, Carte c) {

    carte = c;

	/* 
	   On traduit les Case start et dest en (int) startNode et
	   (int) destNode afin d'effectuer les calculs a partir de 
	   la matrice d'adjacence
	*/
	startNode = numeroSommet(start, carte);
	
	
	int n = adj.length;
	
	int[][] cost = new int[n][n];

	// distance contient la distance du noeud start a tout les autres noeuds
	distance = new int[n];

	// pred[] contient le predecesseur de chaque noeud
	pred = new int[n];

	// visited[] indique si oui ou non un noeud a deja ete visite
	int[] visited = new int[n];

	// count donne le nombre de neouds deja visites
	int count;
	
	int minDistance;
	
	int nextNode = 0;

	/*
	  On initialise la matrice de couts : cost
	 */
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (adj[i][j] == 0) {
		    cost[i][j] = 99999999;
		} else {
		    cost[i][j] = adj[i][j];
		}
	    }
	}	

	/*
	  On initialise la matrice pred[], distance[] et visited[]
	 */
	for (int i = 0; i < n; i++) {
	    distance[i] = cost[startNode][i];
	    pred[i] = startNode;
	    visited[i] = 0;
	}

	distance[startNode] = 0;
	visited[startNode] = 1;
	count = 1;


	// Tant que certains noeuds n'ont pas encore ete visites ...
	while (count < n-1) {

	    minDistance =  99999999;
	    
	    // nextNode donne le noeud a distance minimal de start
	    for (int i = 0; i < n; i++) {
		
		if ((distance[i] < minDistance) && (visited[i] == 0)) {
		    minDistance = distance[i];
		    nextNode = i;
		}
	    }
	    
	    // on verifie si un meilleur parcours existe avec nextNode
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

	
    }

    /**
     * Accesseur (get)
     * @return le Chemin chemin creer par l'algorithme de dijkstra
     */
    public Chemin getPCC(Case dest) {

        int destNode = numeroSommet(dest, carte);

        /*
          - distDestNode est la distance entre start et dest
          - pathToDest contiendra le plus court chemin de start a dest 
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
           On inverse pathToDest afin d'obtenir un path de start => dest
           plutot que dest => start
        */  
        int middle = (int) Math.floor(pathToDest.size()/2);
        int size = pathToDest.size();
        
        for (int i = 0; i < middle; i++) {
            int tmp = pathToDest.get(i);
            pathToDest.set(i, pathToDest.get(size - i - 1));
            pathToDest.set(size - i - 1, tmp);
        }
        
        /*
          - On cree un Chemin chemin en transformant les (int) noeuds en 
          Case case
          - On affecte a chemin.duree la distance de la source a la dest
        */
        Chemin chemin = new Chemin();
        chemin.duree = distDestNode;

        if (chemin.duree <  99999999) {
            
            for (int i = 1; i < pathToDest.size(); i++) {
            Case sommet = caseDuSommet(pathToDest.get(i), carte);
            chemin.addCase(sommet, 0);
            }
            
        } else {

            chemin.duree = Integer.MAX_VALUE;

        }

    	return chemin;

    }

    
}
