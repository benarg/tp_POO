package simu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import gui.Text;

import io.*;
import robots.*;
import outilsBase.*;

/**
 * Affiche la simulation
 */
public class Simulateur implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;



    private DonneesSimulation donneesSimu;


    

    /**
     * Crée un Invader et le dessine.
     * @param gui l'interface graphique associée, dans laquelle se fera le
     * dessin et qui enverra les messages via les méthodes héritées de
     * Simulable.
     * @param color la couleur de l'invader
     */
    public Simulateur(DonneesSimulation d) {
        int width = d.getCarte().getNbColonnes()*d.getCarte().getTailleCases();
        int height = d.getCarte().getNbLignes()*d.getCarte().getTailleCases();
        this.gui = new GUISimulator(width, height, Color.BLACK);
        this.gui.setSimulable(this);				// association a la gui!
        this.donneesSimu = d;

        draw();
    }

    @Override
    public void next() {
        
    }

    @Override
    public void restart() {
        
    }


    /**
     * Dessine le carte
     */
    private void draw() {
        gui.reset();	// clear the window

        int taille = this.donneesSimu.getCarte().getTailleCases();

        /* On parcours les cases pour les afficher*/
        for (int i = 0; i < this.donneesSimu.getCarte().getNbColonnes(); i++) {
            for (int j = 0; j < this.donneesSimu.getCarte().getNbLignes(); j++) {
                int x = i*taille;
                int y = j*taille;
                String image = "img/" + this.donneesSimu.getCarte().getCase(j,i).getNature().toString() + ".png";
                gui.addGraphicalElement(new ImageElement(x,y,image, taille, taille, this.gui));
            }
        }

        /* On parcours les incendies pour les afficher*/
        for (int i = 0 ; i < this.donneesSimu.getNbIncendies(); i++) {
            Incendie incendie = this.donneesSimu.getIncendie(i);
            int x = incendie.getPosition().getColonne()*taille;
            int y = incendie.getPosition().getLigne()*taille;
            gui.addGraphicalElement(new ImageElement(x,y,"img/INCENDIE.png", taille, taille, this.gui));
        }

        /* On parcours les robots pour les afficher*/
        for (int i = 0 ; i < this.donneesSimu.getNbRobots(); i++) {
            Robot robot = this.donneesSimu.getRobot(i);
            int x = robot.getPosition().getColonne()*taille;
            int y = robot.getPosition().getLigne()*taille;
            gui.addGraphicalElement(new ImageElement(x,y,"img/" + robot.getType() + ".png", taille, taille, this.gui));
        }
    }
}
