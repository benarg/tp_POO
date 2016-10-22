/**
  * Evnement affichant un message dans la console a une date donnee
  */

package evenements;

public class EvenementMessage extends Evenement {
	private String message;

	public EvenementMessage(int d, String mess) {
		super(d);
		this.message = mess;
	}

	public void execute() {
		System.out.println(this.getDate() + this.message);
	}
}