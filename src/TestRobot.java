import robots.*;
import outilsBase.*;

public class TestRobot {
	public static void main(String[] args) {
		Carte map = new Carte(3, 4, 5);
		Case pos = map.getCase(2, 3);
		Robot rob = new Robot(map, pos, 5, 5.0, 0.0, 0.0, 10.0, 0.0, 5, 0.0);
		double vitesse;
		for (NatureTerrain nature : NatureTerrain.values()) {
			vitesse = rob.getVitesse(nature);
			System.out.print(vitesse + "\n");
			
		}
		System.out.print(rob.getPosition() + "\n");
		rob.setPosition(map.getCase(0, 0));
		System.out.print(rob.getPosition() + "\n");
		System.out.print(rob.getQuantiteEau() + "\n");
		rob.setQuantiteEau(4);
		System.out.print(rob.getQuantiteEau() + "\n");
		
		System.out.println("");
		rob.deverserEau(1);
		System.out.print(rob.getQuantiteEau() + "\n");
		rob.remplirReservoir();
		Case voisin = map.getCase(0,1);
		voisin.setNature(NatureTerrain.EAU);
		rob.remplirReservoir();
		System.out.print(rob.getQuantiteEau() + "\n");
		rob.remplirReservoir();

		System.out.println("");
		rob.deplacer(Direction.SUD);
		System.out.println(rob.getPosition());
		rob.deplacer(Direction.SUD);
		System.out.println(rob.getPosition());
		rob.deplacer(Direction.EST);
		System.out.println(rob.getPosition());
	
		System.out.println("");
		System.out.println(rob);
		
		
	}
}
	
