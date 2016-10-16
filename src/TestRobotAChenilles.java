public class TestRobotAChenilles {
	public static void main(String[] args) {
		Carte map = new Carte(3, 4, 5);
		Case C0 = map.getCase(1,1);
		Case C1 = map.getCase(1,2);
		C1.setNature(NatureTerrain.EAU);
		System.out.println(map);
		RobotAChenilles RAC = new RobotAChenilles(map, C0);
		System.out.println("La position du robot est: " + RAC.getPosition());
		System.out.println("Le reservoir du robot est " + RAC.getReservoir()+ "L");
		System.out.println("La quantite d'eau du robot est de " + RAC.getQuantiteEau() + "L");
		RAC.remplirReservoir();
		System.out.println("Apres remplissage, la quantite d'eau du robot est de " + RAC.getQuantiteEau() + "L");
		RAC.deverserEau(500);
		System.out.println("Apres deversement, la quantite d'eau du robot est de " + RAC.getQuantiteEau() + "L");
		try {RAC.deverserEau(2000);}
		catch (IllegalArgumentException e) {
			System.out.println("L'operation " 
				+ "RAC.deverserEau(2000) "
				+ "a bien leve une exception");
		}
		System.out.println("Le temps de remplissage du robot est de " + RAC.getTempsRemplissage() + "min");
		System.out.println("L'extinction du robot est de " + RAC.getExtinction() + "L/s");
		
		try {RAC.deplacer(Direction.EST);}
		catch (IllegalArgumentException e) {
			System.out.println("L'operation " 
				+ "RAC.deplacer(Direction.EST) "
				+ "a bien leve une exception");
		}
		try {RAC.setPosition(C1);}
		catch (IllegalArgumentException e) {
			System.out.println("L'operation " 
				+ "RAC.setPosition(C1) "
				+ "a bien leve une exception");
		}

		RAC.deplacer(Direction.SUD);
		System.out.println("Apres deplacement vers le SUD , la position du robot est: " + RAC.getPosition());

	}
}