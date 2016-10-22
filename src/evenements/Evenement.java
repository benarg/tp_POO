/** 
  * La classe Evenement est abstraite, 
  * elle devra être héritée par des sous-classes qui représenteront 
  * des évènements réels avec leurs propres proriétés
  * @author lefoulj
  * 2016
  */

package evenements;

public abstract class Evenement {
	private int date;

	/**
      * Accesseur (get)
      * @return la date de l'evenement
      */
	public int getDate() {
		return this.date;
	}

	/**
      * Construit un Evenement a la date d
      */
	public Evenement(int d) {
		this.date = d;
	}

	/**
      * Execution propre a une sous-classe d'evenement
      */
	public abstract void execute();

}