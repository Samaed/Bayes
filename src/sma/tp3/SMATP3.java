/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sma.tp3.Bayes.Action;
import sma.tp3.Bayes.Bayes;
import sma.tp3.Bayes.Hypothesis;
import sma.tp3.agents.Acteur;
import sma.tp3.agents.Observateur;
import sma.tp3.views.MainFrame;

/**
 *
 * @author Corentin
 */
public class SMATP3 {
    public static MainFrame mainFrame;
    public static List<Action> actions;
    public static Bayes b;
    public static Acteur acteur;
    public static Observateur observateur;
    private static boolean example1;
    private static boolean random;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        example1 = false;
        random = true;
        
        example2();
        
        random();
    }
    
    public static void removeAction(Action action){
        mainFrame.setActivated(action, false);
        acteur.removeAction(action);
        observateur.updateActions(acteur.getActions());
        mainFrame.addMessage("Action supprimée : " + action + "\n");
        if(acteur.getActions().contains(action)){
            mainFrame.setActivated(action, true);
        }
        refresh(false);
    }
    
    public static void addAction(Action action, boolean user){
        if(user){
            acteur.addAction(action);
            mainFrame.addMessage("Nouvelle action : " + action + "\n");
            mainFrame.setActionLabel(action);
            observateur.updateActions(acteur.getActions());
        }
        
        mainFrame.setActivated(action, true);
        if(user){
            refresh(false);
        }
    }
    
    public static void refresh(boolean next){
        Action action = null;
        if(next){
            action = acteur.nextAction();
            if(action != null){
                addAction(action, false);
                mainFrame.addMessage("Nouvelle action : " + action + "\n");
                mainFrame.setActionLabel(action);
            }
        }
        actions = acteur.getActions();
        Action mostProbableAction = observateur.mostProbableNextAction();
        Hypothesis mostProbableHypothesis = observateur.mostProbableHypothesis();
        mainFrame.setMostProbableActionLabel(mostProbableAction);
        mainFrame.setMostProbableHypothesisLabel(mostProbableHypothesis);
        if((next && action != null) || !next) {
            mainFrame.addMessage("Hypothèse la plus probable selon l'observateur : " + mostProbableHypothesis);
            mainFrame.addMessage("Action suivante la plus probable selon l'observateur : " + mostProbableAction + "\n");
        }
        mainFrame.setHyposthesisProbabilities(actions);
        mainFrame.setHighlighted(mostProbableAction, mostProbableHypothesis);
        mainFrame.setSizeRefresh();
    }
    
    public static void reset(){
        
        
        mainFrame.reset();
        if(example1){
            example1();
        } else {
            example2();
        }
        actions = new ArrayList<>();
        acteur = new Acteur(b);
        observateur = new Observateur(b);
        acteur.addObsertvateur(observateur);
        mainFrame.addMessage("Reset");
        mainFrame.setCheckBoxes();
        mainFrame.refresh();
        mainFrame.setSizeRefresh();
        mainFrame.emptyTextArea();
        
        acteur.setRandom(random);
        Random r = new Random();
        Hypothesis h = b.getHypotheses().get((r.nextInt(b.getHypotheses().size())));
        acteur.setHypothesis(h);
        mainFrame.addMessage(random ? "L'acteur est aléatoire.\n" : "L'acteur n'est pas aléatoire." + "\nNouvelle hypothèse de départ : " + h + "\n");
        
        refresh(false);
    }
    
    public static void random(){
        random = !random;
        reset();
    }
    
    private static void example1(){
        b = new Bayes();
        
        Hypothesis chasser = b.addHypothesis("Aller chasser (h1)", .35f);
        mainFrame.addHyposthesis(chasser);
        Hypothesis voler = b.addHypothesis("Voler la banque (h2)", .3f);
        mainFrame.addHyposthesis(voler);
        Hypothesis retirer = b.addHypothesis("Retirer de l'argent (h3)", .35f);
        mainFrame.addHyposthesis(retirer);
        
        Action prendre = b.addAction("Prendre un fusil (e1)");
        mainFrame.addAction(prendre);
        Action aller = b.addAction("Aller à la banque (e2)");
        mainFrame.addAction(aller);
        
        
        b.addProbability(chasser, prendre, .5f);
        mainFrame.addProbability(chasser, prendre, .5f, Color.BLACK);
        b.addProbability(voler, prendre, .5f);
        mainFrame.addProbability(voler, prendre, .5f, Color.BLACK);
        b.addProbability(voler, aller, .5f);
        mainFrame.addProbability(voler, aller, .5f, Color.BLUE);
        b.addProbability(retirer, aller, .5f);
        mainFrame.addProbability(retirer, aller, .5f, Color.BLUE);
    }
    
    private static void example2(){
        b = new Bayes();
        
        Hypothesis oeufs = b.addHypothesis("Pâtes aux oeufs (h1)", .1f);
        mainFrame.addHyposthesis(oeufs);
        Hypothesis carbonara = b.addHypothesis("Pâtes carbonara (h2)", .25f);
        mainFrame.addHyposthesis(carbonara);
        Hypothesis alfredo = b.addHypothesis("Pâtes Alfredo (h3)", .15f);
        mainFrame.addHyposthesis(alfredo);
        Hypothesis tomate = b.addHypothesis("Pâtes sauce tomates (h4)", .2f);
        mainFrame.addHyposthesis(tomate);
        Hypothesis fruitsmer = b.addHypothesis("Pâtes aux fruits de mer (h5)", .3f);
        mainFrame.addHyposthesis(fruitsmer);
        
        Action epices = b.addAction("Acheter épices (e1)");
        mainFrame.addAction(epices);
        Action tomates = b.addAction("Acheter tomates (e2)");
        mainFrame.addAction(tomates);
        Action creme = b.addAction("Acheter crème (e3)");
        mainFrame.addAction(creme);
        Action viande = b.addAction("Acheter viande/poisson (e4)");
        mainFrame.addAction(viande);
        Action fromage = b.addAction("Acheter fromage (e5)");
        mainFrame.addAction(fromage);
        
        
        b.addProbability(oeufs, epices, .1f);
        mainFrame.addProbability(oeufs, epices, .1f, Color.BLACK);
        b.addProbability(oeufs, viande, .9f);
        mainFrame.addProbability(oeufs, viande, .9f, Color.BLACK);
        b.addProbability(carbonara, creme, .3f);
        mainFrame.addProbability(carbonara, creme, .3f, Color.BLUE);
        b.addProbability(carbonara, viande, .4f);
        mainFrame.addProbability(carbonara, viande, .4f, Color.BLUE);
        b.addProbability(carbonara, fromage, .3f);
        mainFrame.addProbability(carbonara, fromage, .3f, Color.BLUE);
        b.addProbability(alfredo, creme, .5f);
        mainFrame.addProbability(alfredo, creme, .5f, Color.MAGENTA);
        b.addProbability(alfredo, fromage, .5f);
        mainFrame.addProbability(alfredo, fromage, .5f, Color.MAGENTA);
        b.addProbability(tomate, epices, .7f);
        mainFrame.addProbability(tomate, epices, .7f, Color.RED);
        b.addProbability(tomate, tomates, .3f);
        mainFrame.addProbability(tomate, tomates, .3f, Color.RED);
        b.addProbability(fruitsmer, creme, .2f);
        mainFrame.addProbability(fruitsmer, creme, .2f, Color.LIGHT_GRAY);
        b.addProbability(fruitsmer, viande, .7f);
        mainFrame.addProbability(fruitsmer, viande, .7f, Color.LIGHT_GRAY);
        b.addProbability(fruitsmer, epices, .1f);
        mainFrame.addProbability(fruitsmer, epices, .1f, Color.LIGHT_GRAY);
    }
    
    public static void toggleExample1(){
        example1 = !example1;
        reset();
    }
}
