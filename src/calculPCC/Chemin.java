/**
  * Chemin, definie, par une liste chainee, un chemin entre 2 cases
  * @author 
  * 2016
  */

package calculPCC;

import outilsBase.Case;
import java.util.*;

public class Chemin {
	LinkedList<Case> liste = new LinkedList<Case>();
	int duree = 0;

	/**
      * Accesseur (get)
      * @return la duree totale du chemin
      */
	public int getDuree() {
		return this.duree;
	}

	/**
	  * Methode ajoute une case au chemin
	  * @param c Case a ajouter
	  * @param temps duree necessaire pour aller de la case precedente a c
	  */
	public void addCase(Case c, int temps) {
		this.liste.add(c);
		this.duree += temps;
	}

	/**
	  * Methode donnant un iterateur permettant de parcourir la liste
	  * @return l'iterateur sur la tete de la liste chainee
	  */
	public Iterator<Case> getIterator() {
		return this.liste.listIterator(0);
	}

	/**
	  * Methode permettant de donne une shallow copy du chemin
	  * @return Shallow copy de ce chemin
	  */
	public Chemin getCopyChemin() {
		Chemin copyChemin = new Chemin();
		copyChemin.liste = new LinkedList<Case>(this.liste);
		copyChemin.duree = this.duree;
		return copyChemin;
	}

}