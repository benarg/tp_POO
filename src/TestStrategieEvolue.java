import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


import io.*;

public class TestStrategieEvolue {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        
        try {
            DonneesSimulation d = LecteurDonnees.creeDonnees(args[0]);

            Simulateur simul = new Simulateur(d, 2);

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

        
    }

}

