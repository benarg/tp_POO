/**
  * Matrice d'adjacence d'un robot, representant le graphe de ses deplacements possibles sur la carte qui lui est associee.
  * Les sommets du graphe sont les differentes cases de la carte.
  * Les arcs sont les deplacements possibles du robot entre deux cases, pondere par le temps qu'il met pour rejoindre cette case.
  */

package calculPCC;

import robots.Robot;
import outilsBase.*;
import java.util.*;

public class MatriceAdj {
	private int[][] mat;
	private Carte carte;

	/**
          * Calcule le temps que met le robot pour rejoindre la case 'c1' à partir de la case 'c0'
	  */
	private int calcTemps(Case c0, Case c1, Robot r, int taille) {
		if ((r.getVitesse(c0.getNature()) == 0) || (r.getVitesse(c1.getNature()) == 0))
			return 0;
		else {
			double res = Math.ceil((2*taille*3600/1000)/(r.getVitesse(c0.getNature()) + r.getVitesse(c1.getNature())));
			return (int) res;
		}
	}


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
          * Stocke dans une liste chainee les cases adjacentes case correspondant ) l'entier 'sommet'
          */
	private LinkedList<Integer> voisins(int sommet, Carte c) {
		LinkedList<Integer> listeVoisins = new LinkedList<Integer>();
		Case s = caseDuSommet(sommet, c);
		for (Direction dir : Direction.values()) {
			if (c.voisinExiste(s, dir)) {
				int voisin = numeroSommet(c.getVoisin(s, dir), c);
				listeVoisins.add(voisin);
			}		
		}
		return listeVoisins;
	}		

	/**
          * Construit la matrice d'adjacence du robot
          */
	public MatriceAdj(Robot r, Carte c) {
		this.carte = carte;
		int n =  c.getNbLignes()*c.getNbColonnes();

		// on cree un tableau n*n de zeros
		this.mat = new int[n][n];
		LinkedList<Integer> arcs;
	
		// on parcours toutes les lignes de la matrice et pour chaque ligne on fait...
		for (int k = 0; k < n; k++) {
			
			// on regarde les arcs qui partent du sommet 'k' et on stock les numeros des sommets vers lesquels ces arcs arrivent dans une liste
			arcs = voisins(k, c);
			Iterator<Integer> it = arcs.iterator();
		
			// on parcours cette liste, on calcule les poids associés a chaque arc et on met a jour la matrice d'adjacence
			while(it.hasNext()) {
				int s = it.next();
				this.mat[k][s] = calcTemps(caseDuSommet(k, c), caseDuSommet(s, c), r, c.getTailleCases());
			}
		}
	}

	public int[][] getMatriceAdj() {
		return this.mat;
	}
}
				
			

