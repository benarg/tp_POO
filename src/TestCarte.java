public class TestCarte {
	public static void main(String[] args) {
		Carte map = new Carte(3, 4, 5);
		System.out.println(map);
		Case C0 = map.getCase(0,0);
		System.out.println(C0);
		for (Direction dir : Direction.values()) {
			System.out.print("a-t-elle un voisin " + dir + "? ");
			System.out.println(map.voisinExiste(C0, dir));
		}

		try {map.getVoisin(C0, Direction.NORD);}
		catch (IllegalArgumentException e) {
			System.out.println("L'operation " 
				+ "map.getVoisin(C0, Direction.NORD) "
				+ "a bien leve une exception");
		}

		System.out.println("");
		C0 = map.getCase(1,2);
		System.out.println("Les voisins de " + C0);
		for (Direction dir : Direction.values()) {
			System.out.print(dir + " : ");
			Case C1 = map.getVoisin(C0, dir);
			System.out.println(C1);
		}

	}
}