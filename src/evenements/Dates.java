package evenements;

public class Dates {

    private Date cour;
    private Date next;

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

	Date runner = this.cour;
	
	while (runner != null) {
	    if (runner.cour.getDate() == d) {
		return runner;
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
	
	Date = getDate(evt.getDate());
	
	if (Date == null) {
	    Dates toAdd = new Dates();
	    toAdd.cour = new Date(evt.getDate(), evt);
	    toAdd.next = this.cour;
	} else {
	    Date.addEvenement(evt);
	}
    }
}
