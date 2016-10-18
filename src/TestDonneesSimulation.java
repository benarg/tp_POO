import io.*;
import robots.*;
import outilsBase.*;

public class TestDonneesSimulation {
	public static void main(String[] args) {
		Carte map = new Carte(3, 4, 5);
		Incendie[] incendies= {new Incendie(map.getCase(1,2), 500), new Incendie(map.getCase(2,3), 230), new Incendie(map.getCase(0,2), 40)};
		Robot[] robots = {new Drone(map, map.getCase(2,2)), new RobotAPattes(map, map.getCase(2,1)), new RobotAChenilles(map, map.getCase(1,2))};

		DonneesSimulation d = new DonneesSimulation(map, incendies, robots);

		System.out.println(d.getCarte());

		for (Incendie i : incendies) {
			System.out.println(i);
		}

		for (Robot r : robots) {
			System.out.println(r);
		}
	}
}