package io;

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
import evenements.*;
import strategies.*;

/**
 * Affiche la simulation
 */
public class Simulateur implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;

    private DonneesSimulation donneesSimu;

    /** Date courante de la simulation*/
    private int dateCour = 0;
    /** Derniere date de la simulation*/
    private int derniereDate = 0;

    private HashDates dates = new HashDates();

    private Strategie strat;

    

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
        this.strat = new StrategieNull(d, this);
        draw();
    }

    public Simulateur(DonneesSimulation d, int numStrategie) {
        this(d);
        switch(numStrategie) {
            case 0:
                this.strat = new StrategieTest(d, this);
                break;
            case 1:
                this.strat = new StrategieElem(d, this);
                break;
            case 2:
                this.strat = new StrategieEvolue(d, this);
                break;
            default:
                break;
        }
        this.strat.step();
        draw();
    }

    @Override
    public void next() {
        System.out.println("DerniereDate: " + this.derniereDate);
        System.out.println("DateCour: " + this.dateCour + "\n");

        if (this.simulationTerminee()) {
            System.out.println("La simulation est terminee");
            System.exit(0);
        }
        this.incrementeDate();
        Date d = dates.getDate(this.dateCour);
        if (d != null)
            d.execute();

        this.strat.step();

        this.draw();
    }

    @Override
    public void restart() {
        this.dateCour = 0;
    }

    public void ajouteEvenement(Evenement e) {
        dates.addEvenement(e);
        this.derniereDate = Math.max(this.derniereDate, e.getDate());
    }

    public int getDateCour() {
        return this.dateCour;
    }

    private void incrementeDate() {
        this.dateCour++;
    }

    private boolean simulationTerminee() {
        return this.dateCour == this.derniereDate;
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
            int colonne = incendie.getPosition().getColonne();
            int ligne =incendie.getPosition().getLigne();
            int x = colonne*taille;
            int y = ligne*taille;
            if (incendie.getIntensite() > 0)
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
