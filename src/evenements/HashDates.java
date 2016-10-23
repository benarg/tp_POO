package evenements;

public class HashDates {

    private Dates[] hashDates;
    
    
    public HashDates() {
	this.hashDates = new Dates[100];
    }
    
    public void addEvenement(Evenement evt) {
	
	Dates d = this.getIndexDate(evt.getDate());
	
	if (d == null) {
	    this.hashDates[evt.getDate() % 100]
		= new Dates(new Date(evt.getDate(), evt));			       		 	 
    } else {
	d.addEvenement(evt);
    }
	
    }

    public Date getDate(int d) {

	if (this.getIndexDate(d) == null) {
	    return null;
	} else {
	    return this.getIndexDate(d).getDate(d);
	}
	
    }
    
    public Dates getIndexDate(int d) {
	return this.hashDates[d % 100];
    }
}
