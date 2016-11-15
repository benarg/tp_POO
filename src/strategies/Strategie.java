package strategies;

import io.*;
import outilsBase.*;
import robots.*;
import java.util.ArrayList;
import calculPCC.*;

public abstract class Strategie {

	protected Robot robots[];

	/** Liste des etats des robots
	  * true : occupe
	  * false : libre
	  */
	protected boolean etatsRobots[];

	protected Incendie incendies[];

	/** Liste des etats des incendies
	  * true : occupe
	  * false : libre
	  */
	protected boolean etatsIncendies[];

	/** Liste des cases contenants de l'eau sur la carte*/
	protected Case casesEau[];

	protected Carte carte;
	
	protected Simulateur simu;

	public Strategie(DonneesSimulation d, Simulateur s) {
		this.simu = s;
		this.carte = d.getCarte();

		this.incendies = new Incendie[d.getNbIncendies()];
		this.etatsIncendies = new boolean[d.getNbIncendies()];
		for (int i=0; i < d.getNbIncendies(); i++) {
			this.incendies[i] = d.getIncendie(i);
			this.etatsIncendies[i] = false;
		}

		this.robots = new Robot[d.getNbRobots()];
		this.etatsRobots = new boolean[d.getNbRobots()];
		for (int i=0; i < d.getNbRobots(); i++) {
			this.robots[i] = d.getRobot(i);
			this.etatsRobots[i] = false;
		}

		ArrayList<Case> cases = new ArrayList<Case>();
		for (int i=0; i<this.carte.getNbLignes(); i++) {
			for (int j=0; j<this.carte.getNbColonnes(); j++) {
				Case c = this.carte.getCase(i,j);
				if (c.getNature() == NatureTerrain.EAU)
					cases.add(c);
			}
		}
		this.casesEau = cases.toArray(new Case[0]);
	}

	public abstract void step();

	/**
	  * Marque un robot comme libre
	  */
	public void robotFini(int indiceRobot) {
		etatsRobots[indiceRobot] = false;
	}

	/**
	  * @return l'indice du robot libre le plus proche de l'incendie i
	  */
	public int robotPPIncendie(Incendie i) {
		Case dest = i.getPosition();
		int bestRobot = -1;
		int dureeMin = Integer.MAX_VALUE;
		for (int j=0; j<robots.length; j++) {
			if(!etatsRobots[j]) {
				Robot r = robots[j];
				int duree = r.getDureePCC(dest);
				if(duree < dureeMin) {
					bestRobot = j;
					dureeMin = duree;
				}
			}
		}
		return bestRobot;
	}


	/**
	  * @return l'indice de l'incendie le plus proche du robot i
	  */
	public int incendiePPRobot(Robot r) {
		int bestIncendie = -1;
		int dureeMin = Integer.MAX_VALUE;
		int duree = Integer.MAX_VALUE;                                                           
		for (int j=0; j<incendies.length; j++) {
			if(!etatsIncendies[j]) {
				Incendie feu = incendies[j];
				Case dest = feu.getPosition();
				duree = r.getDureePCC(dest);
				if(duree < dureeMin) {
					bestIncendie = j;
					dureeMin = duree;
				}
			}
		}
		return bestIncendie;
	}

	/**
	  * @return la case a cote de l'eau la plus proche du robot r
	  */
	public Case eauPPRobot(Robot r) {
		Case bestCase = null;
		int dureeMin = Integer.MAX_VALUE;
		Dijkstra dij = new Dijkstra(r.getMatriceAdj(), r.getPosition(), this.carte);
		for (Case c:casesEau) {
			int duree = dureeMin;
			if (r.getVitesse(NatureTerrain.EAU) != 0)
				duree = (dij.getPCC(c)).getDuree();
			else {
	        	int dureeM = Integer.MAX_VALUE;
				NatureTerrain nature;
	        	Chemin pCC = new Chemin();
				Case voisin;
				for (Direction dir : Direction.values()) {
					if (this.carte.voisinExiste(c, dir)) {
				    	voisin = carte.getVoisin(c, dir);
				   		nature = voisin.getNature();
						if ((r.getVitesse(nature) != 0) && ((dij.getPCC(voisin)).getDuree() < duree)) {
							duree = (dij.getPCC(voisin)).getDuree();
						}
					}
				}
			}
			if(duree < dureeMin) {
				bestCase = c;
				dureeMin = duree;
			}
		}
		return bestCase;
	}

}
