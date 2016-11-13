/**
 * La classe Dates contient une Date cour ainsi qu'un objet Dates next
 * Cette classe permet d'organiser les differents temps d'execution
 * au sein de notre simulation.
 * Pour plus de visibilite : Consulter notre rapport PDF
 */



package evenements;

public class Dates {

    private Date cour;
    private Dates next;

    public Dates() {
	this.cour = null;
	this.next = null;
    }

    /**
       Construit un objet Dates contenant une date d et
       un objet Dates next initialises a NULL
     */
    public Dates(Date d) {
	this.cour = d;
	this.next = null;
    }

    /**
     * Cette methode s'appelle sur la tete d'une liste chainee
     * de Dates et renvoie l'objet Date correspondant à l'entier 
     * date voulu
     *@return la Date associee a la date (int) d
     **/
    public Date getDate(int d) {

	Dates runner = this;
	
	while (runner != null) {
	    if (runner.cour.getDate() == d) {
		return runner.cour;
	    } else {
		runner = runner.next;
	    }
	}
	return null;
    }

    /**
     * Cette methode s'appelle sur la tete d'une liste chainee
     * de Dates et insere l'evenement evt à l'objet Dates correspondant
     **/
    public void addEvenement(Evenement evt) {
	
	Date d = getDate(evt.getDate());
	
	if (d == null) {
	    
	    Dates tmp = this.next;
	    this.next = new Dates(this.cour);
	    this.next.cour = this.cour;
	    this.next.next = tmp;
	    this.cour = new Date(evt.getDate(), evt);
	    
	} else {
	    d.addEvenement(evt);
	}
    }
}
