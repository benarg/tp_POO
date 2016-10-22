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
	public Date(int t, Evenements events) {
		this.t = t;
		this.evenements = new Evenements(null, null);
		while(events.getSuiv() != null) {             // acceseur getSuiv à definir dans Evenements.java ?
			if(events.getDate() == t) 
				this.evenements.addEvenement(events.cour);
			events = events.getSuiv();
		}			
	}


	/**
	  * Execute tous les evenemets de la liste
	  */		
	public void execute() {
		this.evenements.executeAll();
	}
}	
