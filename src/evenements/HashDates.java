/**
 * La classe HashDates a pour attribut un array d'objet Dates
 * Pour plus de visibilite : Consulter notre rapport PDF
 */

package evenements;

public class HashDates {

    private Dates[] hashDates;
    
    /**
     * Construit un objet HashDates de longueur 100 Dates
     */
    public HashDates() {
	this.hashDates = new Dates[100];
    }

    /**
     * Ajoute a l'objet HashDates l'evenement evt a l'objet Date
     * correspondant
     */
    public void addEvenement(Evenement evt) {
	
	Dates d = this.getIndexDate(evt.getDate());
	
	if (d == null) {
	    this.hashDates[evt.getDate() % 100]
		= new Dates(new Date(evt.getDate(), evt));			       		 	 
	} else {
	    d.addEvenement(evt);
	}
	
    }

    /**
     * Accesseur (get)
     * @return l'objet Date correspondant a la date (int) d
     */
    public Date getDate(int d) {

	if (this.getIndexDate(d) == null) {
	    return null;
	} else {
	    return this.getIndexDate(d).getDate(d);
	}
	
    }

    /**
     * accesseur (get)
     * @return l'objet Dates qui est la tete de liste chainee d'objets Date
     * et dans laquelle se trouve l'objet Date associe a la date (int) d
     *
     * JE VOUS CONSEILLE VRAIMENT DE CONSULTER LE RAPPORT 
     * SI VOUS NE COMPRENEZ PAS
     */
    public Dates getIndexDate(int d) {
	return this.hashDates[d % 100];
    }
}
