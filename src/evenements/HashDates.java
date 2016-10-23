package evenements;

public class HashDates {

    private Dates[] hashDate;
    
    
    public HashDates() {
	this.hasDates = new Dates[100];
    }
    
    public void addEvenement(Evenement evt) {
	
	Date d = this.getDate(evt.getDate());
	
	if (d == null) {
	    this.hashDates[evt.getDate() % 100].addEvenemt(evt);
	} else {
	    d.addEvenement(evt);
	}
	
    }
    
    public Date getDate(int d) {
	return this.hasDates[d % 100].getDate(d);
    }
}
