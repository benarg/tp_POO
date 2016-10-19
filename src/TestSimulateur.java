


import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import io.*;
import outilsBase.*;
import robots.*;

public class TestSimulateur {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestSimulation <nomDeFichier>");
            System.exit(1);
        }
        
        try {
            LecteurDonnees.lire(args[0]);
            System.out.println();
            DonneesSimulation d = LecteurDonnees.creeDonnees(args[0]);

            Simulateur simul = new Simulateur(d);

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

        
    }

}

