import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.util.*;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;
import calculPCC.*;

public class TestChemin {

	private static int calcTemps(Case c0, Case c1, Robot r, int taille) {
		double res = Math.ceil((2*taille*3600/1000)/(r.getVitesse(c0.getNature()) + r.getVitesse(c1.getNature())));
		return (int) res;
	}

    public static void main(String[] args) {
        
        try {
            DonneesSimulation d = LecteurDonnees.creeDonnees("cartes/carteSujet.map");

            Simulateur simul = new Simulateur(d);

            Robot drone = d.getRobot(0);

            simul.ajouteEvenement(new EvtRemplirReservoir(1,drone));

            Carte c = d.getCarte();

            Chemin chem = new Chemin();

            int taille = c.getTailleCases();
            int temps;
            temps = calcTemps(c.getCase(3,3), c.getCase(4,3), drone, taille);
            System.out.println("temps= " + temps);
            chem.addCase(c.getCase(4,3), calcTemps(c.getCase(3,3), c.getCase(4,3), drone, taille));
            chem.addCase(c.getCase(5,3), calcTemps(c.getCase(4,3), c.getCase(5,3), drone, taille));
            chem.addCase(c.getCase(6,3), calcTemps(c.getCase(5,3), c.getCase(6,3), drone, taille));
            chem.addCase(c.getCase(6,2), calcTemps(c.getCase(6,3), c.getCase(6,2), drone, taille));
            chem.addCase(c.getCase(6,1), calcTemps(c.getCase(6,2), c.getCase(6,1), drone, taille));
            chem.addCase(c.getCase(6,0), calcTemps(c.getCase(6,1), c.getCase(6,0), drone, taille));

            Iterator<Case> ite = chem.getIterator();

            Case c_avant = c.getCase(3,3);
            Case c_apres;
            int i=0;
            while (ite.hasNext()) {
            	c_apres = ite.next();
            	i+= calcTemps(c_avant, c_apres, drone, taille);
            	simul.ajouteEvenement(new EvtRobotSetPos(i,drone, c_apres));
            	c_avant = c_apres;
            }

            simul.ajouteEvenement(new EvtInterventionRobot(chem.getDuree() + 1, drone, d.getIncendie(2)));




        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());  
        }

        
    }

}
