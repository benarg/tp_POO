package evenements;

public class Dates {

    private Date cour;
    private Dates next;

    public Dates() {
	this.cour = null;
	this.next = null;
    }


    public Dates(Date d) {
	this.cour = d;
	this.next = null;
    }

    /**
     * Cette méthode s'appelle sur la tete d'une liste chainee
     * de Dates et renvoie l'objet Date correspondant à l'entier 
     * date voulu
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
     * Cette méthode s'appelle sur la tete d'une liste chainee
     * de Dates et insere l'evenement à la Dates correspondante
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
