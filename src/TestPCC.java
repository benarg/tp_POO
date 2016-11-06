import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import calculPCC.*;
import robots.*;
import outilsBase.*;
import io.*;

import java.util.zip.DataFormatException;


public class TestPCC {

    private static void afficher(int[][] mat) {
	System.out.println("\n Affichage matrice....");
	for (int i = 0; i < mat.length; i++) {
	    System.out.println("");
	    for (int j = 0; j < mat[0].length; j++) {
		System.out.print(mat[i][j]+" ");
	    }
	}
	System.out.println("");
    }


    
    public static void main(String[] args) {

	// On commence par créer une carte
	Carte map = new Carte(3, 2, 100);

	// On ajoute deux robots drones à cette carte
	Robot drone1 = new Drone(map, map.getCase(0, 0));
	Robot drone2 = new Drone(map, map.getCase(0, 0), 150);

	// On crée la matrice d'adjacence associée à cette carte
	MatriceAdj adj = new MatriceAdj(drone2, map);
	int[][] mat = adj.getMatriceAdj();

	// On affiche la matrice d'adjacence
	afficher(mat);

	// Et maintenant pour un second test ...
	try {

	    // On récupère les données d'une carte fournie par le sujet
	    DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

	    // On assigne à r0, r1, r2 les différents robots de cette carte
	    Robot r0 = d.getRobot(0);
	    Robot r1 = d.getRobot(1);
	    Robot r2 = d.getRobot(2);

	    // On affiche la matrice d'adjacence de r2
	    System.out.println("HERE IS SOME INFO ON ADJ MATRIX");

	    System.out.println(r2.getMatriceAdj()[39][28]);    
	    System.out.println(r2.getMatriceAdj()[28][25]);
	    System.out.println(r2.getMatriceAdj()[25][20]);
	    System.out.println(r2.getMatriceAdj()[20][14]);
	    System.out.println(r2.getMatriceAdj()[14][0]);  

	    
	    System.out.println("HERE IS INFORMATION ON THE MAP");
	    System.out.println(d.getCarte());
	    System.out.println("");

	    System.out.println("HERE IS INFORMATION ON THE ROBOT POSITION");
	    System.out.println(r2.getPosition());
	    System.out.println("");

	    System.out.println("HERE IS INFORMATION ON THE DIJKSTRA OBJECT");
	    
	    // On crée un objet dijsktra
	    Dijkstra dij = new Dijkstra(r2.getMatriceAdj(), r2.getPosition(), d.getCarte().getCase(0,0), d.getCarte());
	    
	    
	} catch (FileNotFoundException e) {
	    
	    System.out.println("fichier " + args[0] + " inconnu ou illisible");
	    
	} catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        }

    }
}
