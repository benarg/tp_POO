package simulationTests;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;

public class TestCarteSujet0 {

    public static void main(String[] args) {
        
        try {
            DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

            Simulateur simul = new Simulateur(d);

            Robot drone = d.getRobot(0);
            Robot roues = d.getRobot(1);
            Robot pattes = d.getRobot(2);

            simul.ajouteEvenement(new EvtRemplirReservoir(1,drone));
            simul.ajouteEvenement(new EvtDeplacerRobot(2,drone, Direction.SUD));
            simul.ajouteEvenement(new EvtDeplacerRobot(3,drone, Direction.SUD));
            simul.ajouteEvenement(new EvtDeplacerRobot(4,drone, Direction.SUD));
            simul.ajouteEvenement(new EvtDeplacerRobot(5,drone, Direction.OUEST));
            simul.ajouteEvenement(new EvtDeplacerRobot(6,drone, Direction.OUEST));



        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        }

        
    }

}
