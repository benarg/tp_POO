import evenements.*;

public class TestEvenements {
	public static void main(String[] args) {
		Evenement e = new EvenementMessage(1, " 0eme evenement insere execute");
		Evenements events = new Evenements(e, null);
		for (int i=1; i<10; i++) {
			events.addEvenement(new EvenementMessage(1, " " + i + "eme evenement insere execute"));
		}

		events.executeAll();
	}
}