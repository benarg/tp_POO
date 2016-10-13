/**
  * Carte : 
  * contient une matrice de Case, et la taille du cote des cases.
  * Cette classe fournit notamment des methodes pour accéder 
  * a une case en fonction de ses coordonnees, 
  * ou pour trouver le voisin d’une case dans une direction donnee
  * @author lefoulj
  * 2016
  */

public class Carte {

	/** Taille des Cases*/
	private int tailleCases;

	/**
      * Accesseur (get)
      * @return la taille des cases
      */
	public int getTailleCases() {
		return this.tailleCases;
	}

	/** Matrice de Case*/
	private Case[][] Cases;

	/**
      * Accesseur (get), releve une exception si les coordonnees entrees 
      * sont incorrectes
      * @return la taille des cases
      */
	public Case getCase(int lig, int col) {
		if (lig < 0 || lig >= Cases.length || col < 0 || col >= Cases[0].length)
			throw new IllegalArgumentException("Les coordonnees entrees sont "
												+ "incorrectes");
		else
			return Cases[lig][col];
	}

	/**
      * Accesseur (get)
      * @return le nombre de lignes de la carte
      */
	public int getNbLignes() {
		return Cases.length;
	}

	/**
      * Accesseur (get)
      * @return le nombre de colonnes de la carte
      */
	public int getNbColonnes() {
		if (Cases.length == 0)
			return 0;
		else
			return Cases[0].length;
	}

	/**
      * Construit une carte de matrice (n x m) 
      * dont les cases sont de taille size
      */
	public Carte(int n, int m, int size) {
		this.tailleCases = size;
		this.Cases = new Case[n][m];
		/* On initialise les cases*/
		for (int lig = 0; lig < n; lig++) {
			for (int col = 0; col < m; col++) {
				Cases[lig][col] = new Case(lig, col, NatureTerrain.TERRAIN_LIBRE);
			}
		}
	}


	/** 
	  * Determine si une case a une case voisine dans une direction donnee
	  * @return si la case src a un voisin dans la direction dir
	  */
	public boolean voisinExiste(Case src, Direction dir) {
		switch (dir) {
			case NORD :
				return (src.getLigne() > 0); 
			case SUD :
				return (src.getLigne() < (this.getNbLignes() - 1));
			case EST :
				return (src.getColonne() < (this.getNbColonnes() - 1));
			case OUEST :
				return (src.getColonne() > 0);
			default :
				return false;
		}
	}

	/** 
	  * Retourne la case voisine de src dans la direction dir si elle existe
	  * ou leve une exception sinon
	  * @return la case voisine de src dans la direction dir
	  */
	public Case getVoisin(Case src, Direction dir) {
		if (!voisinExiste(src, dir))
			throw new IllegalArgumentException(
				src + "n'a pas de voisin dans la direction " + dir);
		int l = src.getLigne();
		int c = src.getColonne();
		switch (dir) {
			case NORD :
				return this.getCase(l - 1, c); 
			case SUD :
				return this.getCase(l + 1, c); 
			case EST :
				return this.getCase(l, c + 1); 
			case OUEST :
				return this.getCase(l, c - 1); 
			default :
				throw new IllegalArgumentException(
					"la direction n'est pas connue");
		}
	}


	/**
	  * Donne une representation de la matrice des cartes avec comme valeur
	  * le type de terrain
	  */
	@Override
	public String toString() {
		String map = "Les cases ont des cotes de taille ";
		map +=  + this.getTailleCases() + "\n";
		for (int ligne = 0; ligne < this.getNbLignes(); ligne ++) {
			for (int colonne = 0; colonne < this.getNbColonnes(); colonne ++) {
				map += this.getCase(ligne,colonne).getNature().ordinal();				
			}
			map += "\n";
		}
		map += "Legende:\n";
		for (NatureTerrain nat : NatureTerrain.values()) {
			map += nat.ordinal() + " : " + nat + "\n";
		}
		return map;
	}

}