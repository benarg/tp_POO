/**
  * Rassemble une instance de Evenements pour une date.
  * Contient une methode qui execute tous les evenements.
  */

package evenements;

public class Date {
	private Evenements evenements;
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
	
	/**
	  * Ajoute un evenement a liste chainee des evenements associes a la date t
	  */
	public void addEvenement(Evenement event) {
		if (event.getDate() == t)
			this.evenements.addEvenement(event);
		else
			throw new IllegalArgumentException("Date non compatible !");
	}
	/**
          * Accesseur (get)
          * @return la date des evenements
          */
	public int getDate() {
		return this.t;
	}

	/**
	  * Execute tous les evenemets de la liste
	  */		
	public void execute() {
		this.evenements.executeAll();
	}
}	
