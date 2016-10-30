import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import calculPCC.*;
import robots.*;
import outilsBase.*;
import io.*;

import java.util.zip.DataFormatException;


public class TestMatriceAdj {

	private static void afficher(int[][] mat) {
		System.out.println("\n \n Affichage matrice....\n");
		for (int i = 0; i < mat.length; i++) {
			System.out.println("");
			for (int j = 0; j < mat[0].length; j++) {
				System.out.println(mat[i][j]);
			}
		}
	}		
	public static void main(String[] args) {
		Carte map = new Carte(3, 2, 100);
		Robot drone1 = new Drone(map, map.getCase(0, 0));
		Robot drone2 = new Drone(map, map.getCase(0, 0), 150);
		MatriceAdj adj = new MatriceAdj(drone2, map);
		int[][] mat = adj.getMatriceAdj();
		afficher(mat);

		try {
     	       	DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");
		Robot r0 = d.getRobot(0);
		Robot r1 = d.getRobot(1);
		Robot r2 = d.getRobot(2);
		afficher(r0.getMatriceAdj());
		} catch (FileNotFoundException e) {
           	 System.out.println("fichier " + args[0] + " inconnu ou illisible");
       		 } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        }

	}
}
