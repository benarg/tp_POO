/**
  * DonneesSimulation est la classe principale regroupant toutes 
  * les données du problème : une carte, les incendies et les robots.
  * @author lefoulj
  * 2016
  */

package io;

import outilsBase.*;
import robots.*;

public class DonneesSimulation {

	private Carte map;

	/**
      * Accesseur (get)
      * @return la carte associee aux donnees
      */
	public Carte getCarte() {
		return this.map;
	}

	private Incendie[] incendies;
	
	/**
      * Accesseur (get)
      * @param i indice de l'incendie voulu
      * @return le ieme incendie
      */
	public Incendie getIncendie(int i) {
		if (i >= 0 && i < this.incendies.length)
			throw new IllegalArgumentException(
				"l'indice entre est incorrect");
		return this.incendies[i];
	}

	private Robot[] robots;

	/**
      * Accesseur (get)
      * @param i indice du robot voulu
      * @return le ieme robot
      */
	public Robot getRobot(int i) {
		if (i >= 0 && i < this.robots.length)
			throw new IllegalArgumentException(
				"l'indice entre est incorrect");
		return this.robots[i];
	}


	/**
      * Construit la classe avec les donnees entrees en parametres
      */
	public DonneesSimulation(Carte c, Incendie[] i, Robot[] r) {
		this.map = c;
		this.incendies = i;
		this.robots = r;
	}




}