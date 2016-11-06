import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;
import calculPCC.*;

public class TestCreerEvtsPCC {
	public static void main(String[] args) {

		try {
       			DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

           		Simulateur simul = new Simulateur(d);

			Robot drone = d.getRobot(0);
		        Robot roues = d.getRobot(1);
           		Robot pattes = d.getRobot(2);
			Carte c = d.getCarte();
			Case dest = c.getCase(0,0);

			drone.creerEvtsPCC(simul, dest);
			roues.creerEvtsPCC(simul, c.getCase(1,5));
			pattes.creerEvtsPCC(simul, c.getCase(1,3));

       		} catch (FileNotFoundException e) {
            		System.out.println("fichier " + args[0] + " inconnu ou illisible");
        	} catch (DataFormatException e) {
            		System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        	}

        
    	}

}

