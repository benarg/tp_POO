/**
  * Rassemble une instance de Evenements pour une date.
  * Contient une methode qui execute tous les evenements.
  */

package evenements;

public class Date {
	private Evenement evenements;
	private int t;


	/**
	  * Construit une liste d'evenements associes a la date t
	  */
	public Date(int t, Evenement event) {
		this.t = t;
		if (event.getDate() == t)
			this.evenements = new Evenements(event, null);
		else
			throw new IllegalArgumentException("Date non compatible !");
	}
	

	public void addEvenement(Evenement event) {
		if (event.getDate() == t)
			this.evenements = event.addEvenement(event);
		else
			throw new IllegalArgumentException("Date non compatible !");
	}



	/**
	  * Execute tous les evenemets de la liste
	  */		
	public void execute() {
		this.evenements.executeAll();
	}
}	
