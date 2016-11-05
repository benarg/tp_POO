/**
 * Robot : 
 * contient la carte et la case sur laquelle il est positionne, le volume et le temps pour remplir et vider (extinction) son reservoir, sa vitesse en fonction du type de terrain et le volume d'eau qu'il contient.
 * Cette classe fournit notamment des methodes pour acceder aux vitesses du robot en fonction du terrain, a sa position...
 * ou pour effectuer une intervention (deverser une quantite d'eau, remplir son reservoir) ou se deplacer sur une case donnee.
 * @author argensob
 * 2016
 */

package robots;

import outilsBase.*;
import calculPCC.*;

public abstract class Robot {
    protected Carte carte;
    protected Case position;
    protected int reservoir;
    protected double tempsRemplissage;

    /** temps pour vider entierement son reservoir */
    protected double extinction;

    protected double vitesseForet;
    protected double vitesseEau;
    protected double vitesseRocher;
    protected double vitesseLibre;
    protected int quantiteEau;
    protected int[][] matriceAdj;
	
    /** 
     * Construit un objet de type Robot
     */
    public Robot(Carte carte, Case position, int reservoir, double vitesseForet, double vitesseEau, double vitesseRocher, double vitesseLibre, double tempsRemplissage, int quantiteEau, double extinction) {
	this.carte = carte;
	this.position = position;
	this.reservoir = reservoir;
	this.vitesseForet = vitesseForet;
	this.vitesseEau = vitesseEau;
	this.vitesseRocher = vitesseRocher;
	this.vitesseLibre = vitesseLibre;
	this.tempsRemplissage = tempsRemplissage;
	this.quantiteEau = quantiteEau;
	this.extinction = extinction;
	MatriceAdj mat = new MatriceAdj(this, carte);
	this.matriceAdj = mat.getMatriceAdj();
    }

    /**
     * Accesseur (get)
     * @return la position du robot
     */
    public Case getPosition() {
	return this.position;
    }
	
    /**
     * Accesseur (get)
     * @return le volume du reservoir du robot
     */
    public int getReservoir() {
	return this.reservoir;
    }

    /**
     * Accesseur (get)
     * @return le volume d'eau du robot
     */
    public int getQuantiteEau() {
	return this.quantiteEau;
    }
	
    /**
     * Accesseur (get)
     * @return le temmps que met le robot pour remplir son reservoir
     */
    public double getTempsRemplissage() {
	return this.tempsRemplissage;
    }

    /**
     * Accesseur (get)
     * @return le temps que met le robot pour vider la totalite de son reservoir
     */
    public double getExtinction() {
	return this.extinction;
    }

    /**
     * Accesseur (get)
     * @return la matrice d'adjacence associee au robot
     */
    public int[][] getMatriceAdj() {
	return this.matriceAdj;
    }

    /**
     * Accesseur (get) 
     * @return le plus court chemi
     */
    public Chemin getPCC(Case dest) {

	int startNode = this.position.getLigne() * this.carte.getNbColonnes()
	    + this.position.getColonne();
	int destNode = dest.getLigne() * this.carte.getNbColonnes()
	    + dest.getColonne();
	
	int[][] matriceAdj = this.getMatriceAdj();
	Dijkstra dijkstra = new Dijkstra(matriceAdj, startNode, destNode);

	int dureePCC = dijkstra.getDistDestNode();
	ArrayList<Integer> path = dijkstra.getPathToDest();
	Chemin chemin = new Chemin();

	for (int i = 0; i < path.size(); i++) {
	    chemin.addCase(this.carte.getCase(path.get(i)/this.carte.getNbLignes(),
					      path.get(i) % (carte.getNbLignes()-1)));
	}
	chemin.duree = dureePCC;
	
    }
	
    /** 
     * Retourne la vitesse de deplacement du robot en fonction de la nature du terrain
     * Si le robot ne peut se deplacer sur un type de terrain sa vitesse est nulle
     * @return la vitesse du robot sur un terrain de nature 'terrain'
     */
    public double getVitesse(NatureTerrain terrain) {
	switch (terrain) {
	case EAU :
	    return this.vitesseEau;
	case FORET :
	    return this.vitesseForet;
	case ROCHE :
	    return this.vitesseRocher;
	case TERRAIN_LIBRE :
	    return this.vitesseLibre;
	case HABITAT :
	    return this.vitesseLibre; //vitesseLibre = vitesseHabitat pour tous les robots
	default :
	    throw new IllegalArgumentException("la direction n'est pas connue");
	}
    }

    /**
     * Mutateur (set)
     * Modifie la Case position du robot
     * La Case 'pos' devient la nouvelle position du robot 
     */
    public void setPosition(Case pos) {
	this.position = pos;
    }

    /**
     * Mutateur (set)
     * Modifie le volume d'eau que contient le robot
     * Releve une exception si le volume entree est incompatible avec le reservoir du robot
     * Sinon l'attribut 'QuantiteEau' prend pour valeur 'volume' 
     */
    public void setQuantiteEau(int volume) {
	if (volume > this.reservoir)
	    throw new IllegalArgumentException("Le volume d'eau depasse la taille du reservoir !");
	if (volume < 0)
	    throw new IllegalArgumentException("Le volume entree est incorrecte !");
	else
	    this.quantiteEau = volume;
    }
	
    /**
     * Methode qui permet au robot de deverser une certaine quantite d'eau sur la case ou il se trouve
     * Releve une excetion si le volume entree est incompatible avec le volume d'eau que contient le robot
     * Sinon l'attribut 'quantiteEau' est mis a jour en fonction du volume deverse
     */
    public void deverserEau(int volume) {
	if (volume > this.quantiteEau)
	    throw new IllegalArgumentException("Vous n'avez pas assez d'eau !");
	if (volume < 0)
	    throw new IllegalArgumentException("Le volume entree est incorrecte !");
	else
	    this.quantiteEau -= volume;
    }

    /**
     * Methode qui permet au robot de remplir completement son rerservoir d'eau, si de sa 'position' il a une case voisine de type eau.
     * L'attribut 'quantiteEau' est alors egal au volume du reservoir;
     */
    public void remplirReservoir() {
	Case voisin;
	NatureTerrain nature;
	boolean eau = false;
	if (this.quantiteEau == this.reservoir)
	    System.out.println("Reservoir d'eau deja rempli !");
	else {
	    for (Direction dir : Direction.values()) {
		if (carte.voisinExiste(this.position, dir)) {
		    voisin = carte.getVoisin(this.position, dir);
		    nature = voisin.getNature();
		    if (nature == NatureTerrain.EAU) {
			this.quantiteEau = this.reservoir;
			eau = true;
		    }
		}
	    }
	    if (eau == true)
		System.out.println("Remplissage en cours...");
	    else
		System.out.println("Remplissage impossible !");
	}
    }
				
    /**
     * Methode qui permet au robot de se deplacer dans une direction 'dir' donnee, en verifiant que cette case est bien accessible.
     * Une exception est levee si la case ne possede pas de voisine dans la direction 'dir' ou si la nature de la case n'est pas compatible.
     * Le cas echeant, l'attribut 'position' du robot est mis a jour.
     */
    public void deplacer(Direction dir) {
	Case dest = this.carte.getVoisin(this.position, dir);
	NatureTerrain nature = dest.getNature();
	double vitesse = this.getVitesse(nature);
	if (vitesse != 0)
	    this.position = dest;
	else {
	    throw new IllegalArgumentException("Terrain non accessible !");
	}
    }

    /**
     * Retourne le type de Robot
     */
    public abstract String getType();

    /**
     * Donne une representation en Sring du robot
     */
    @Override
    public String toString() {
	String rob; 
	rob = "Le robot est positionne sur la case " + this.position + " de la carte. Il possede "  + this.quantiteEau + " litres d'eau dans son reservoir de " + this.reservoir + " litres.";
	return "Robot: \t"
	    + "position = (" + this.position.getLigne() 
	    + "," + this.position.getColonne() + ");\t"
	    + "vitesse = " + this.getVitesse(NatureTerrain.TERRAIN_LIBRE)
	    + "\ttype = ";
		
    }

}
		


