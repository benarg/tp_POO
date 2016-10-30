/**
  * Matrice d'adjacence d'un robot, representant le graphe de ses deplacements possibles sur la carte qui lui est associee.
  * Les sommets du graphe sont les differentes cases de la carte.
  * Les arcs sont les deplacements possibles du robot entre deux cases, pondéré par le temps qu'il met pour rejoindre cette case.
  */

package calculPCC;

import robots.Robot;
import outilsBase.*;
import java.util.*;

public class MatriceAdj {
	private int[][] mat;
	private Carte carte;

	private int calcTemps(Case c0, Case c1, Robot r, int taille) {
		double res = Math.ceil((2*taille*3600/1000)/(r.getVitesse(c0.getNature()) + r.getVitesse(c1.getNature())));
		return (int) res;
	}

	private int numeroSommet(Case sommet, Carte c) {
		return (sommet.getLigne()*c.getNbColonnes() + sommet.getColonne());
	}

	private Case caseDuSommet(int sommet, Carte c) {
		return c.getCase(sommet/c.getNbColonnes(), sommet%(c.getNbLignes() - 1));
	}		

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

	public MatriceAdj(Robot r, Carte c) {
		this.carte = carte;
		int n =  c.getNbLignes()*c.getNbColonnes();
		this.mat = new int[n][n];
		LinkedList<Integer> arcs;
		for (int k = 0; k < n; k++) {
			arcs = voisins(k, c);
			Iterator<Integer> it = arcs.iterator();
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
				
			

