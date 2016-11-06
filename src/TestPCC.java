import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import calculPCC.*;
import robots.*;
import outilsBase.*;
import io.*;
import java.util.*;

import java.util.zip.DataFormatException;


public class TestPCC {

    private static void afficher(int[][] mat) {
	System.out.println("\n ********  Here IS THE ADJACENCY MATRIX ********\n");
	for (int i = 0; i < mat.length; i++) {
	    System.out.println("");
	    for (int j = 0; j < mat[0].length; j++) {
		System.out.print(mat[i][j]+" ");
	    }
	}
	System.out.println("");
    }


    
    public static void main(String[] args) {

	try {

	    // On récupère les données d'une carte fournie par le sujet
	    DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

	    // On assigne à r0, r1, r2 les différents robots de cette carte
	    Robot r0 = d.getRobot(0);
	    Robot r1 = d.getRobot(1);
	    Robot r2 = d.getRobot(2);

	    
	    System.out.println("\n ******** HERE IS INFORMATION ON THE MAP ********");
	    System.out.println(d.getCarte());
	    System.out.println("");

	    System.out.println("\n ******** HERE IS INFORMATION ON THE ROBOT POSITION ********");
	    System.out.println(r2.getPosition());
	    System.out.println("");

	    System.out.println("\n ******** HERE IS THE SHORTEST PATH  TO : (0,0) ********");
	    
	    // On crée un objet dijsktra
	    Dijkstra dij = new Dijkstra(r2.getMatriceAdj(), r2.getPosition(), d.getCarte().getCase(0,0), d.getCarte());

	    Chemin chemin = dij.getPCC();
	    Iterator<Case> ite = chemin.getIterator();
	    Case currentCase;
	    
	    while (ite.hasNext()) {

		currentCase = ite.next();
		System.out.print("(" + currentCase.getLigne() + " , "
				 + currentCase.getColonne() + ") -> ");

	    }

	    System.out.print("THE LENGTH OF THIS PATH IS : " + chemin.getDuree());
	    System.out.println("");
		    
	    
	} catch (FileNotFoundException e) {
	    
	    System.out.println("fichier " + args[0] + " inconnu ou illisible");
	    
	} catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        }

    }
}
