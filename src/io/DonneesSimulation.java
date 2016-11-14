/**
  * DonneesSimulation est la classe principale regroupant toutes 
  * les données du problème : une carte, les incendies et les robots.
  * @author 
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
			return this.incendies[i];
		throw new IllegalArgumentException(
				"l'indice entre est incorrect");
	}

	public int getNbIncendies() {
		return this.incendies.length;
	}

	private Robot[] robots;

	/**
      * Accesseur (get)
      * @param i indice du robot voulu
      * @return le ieme robot
      */
	public Robot getRobot(int i) {
		if (i >= 0 && i < this.robots.length)
			return this.robots[i];
		throw new IllegalArgumentException(
				"l'indice entre est incorrect");
	}

	public int getNbRobots() {
		return this.robots.length;
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