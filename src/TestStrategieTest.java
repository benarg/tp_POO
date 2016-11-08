import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;
import calculPCC.*;

public class TestStrategieTest {
	public static void main(String[] args) {

		try {
       		DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

           	Simulateur simul = new Simulateur(d, 0);

       		} catch (FileNotFoundException e) {
            		System.out.println("fichier " + args[0] + " inconnu ou illisible");
        	} catch (DataFormatException e) {
            		System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        	}

        
    	}

}

