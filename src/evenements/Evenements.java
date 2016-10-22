/** 
  * La classe Evenements est une liste simplement chainee d'evenements
  * @author lefoulj
  * 2016
  */

package evenements;

public class Evenements {
	private Evenement cour;
	private Evenements suiv;

	/**
	  * Construit un noeud de la liste
	  */
	public Evenements(Evenement c, Evenements s) {
		this.cour = c;
		this.suiv = s;
	}

	/**
      * Accesseur (get)
      * @return la date des evenements chaines
      */
	public int getDate() {
		return this.cour.getDate();
	}


	/**
	  * Ajoute un element en tete de la liste
	  */
	public void addEvenement(Evenement event) {
		this.suiv = new Evenements(this.cour, this.suiv);
		this.cour = event;
	}

	/**
	  * Execute tous les evenemets de la liste
	  */
	public void executeAll() {
		Evenements courant = this;
		while (courant != null) {
			courant.cour.execute();
			courant = courant.suiv;
		}
	}
}