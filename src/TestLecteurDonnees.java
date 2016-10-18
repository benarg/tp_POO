
import io.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


import outilsBase.*;
import robots.*;

public class TestLecteurDonnees {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        
        try {
            LecteurDonnees.lire(args[0]);
            System.out.println();
            DonneesSimulation d = LecteurDonnees.creeDonnees(args[0]);

            System.out.println(d.getCarte());

            for (int i=0; i < d.getNbIncendies(); i++) {
                System.out.println(d.getIncendie(i));
            }

            for (int j=0; j < d.getNbRobots(); j++) {
                System.out.println(d.getRobot(j));
            }
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

        
    }

}

