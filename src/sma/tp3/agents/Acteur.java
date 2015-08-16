/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sma.tp3.Bayes.Action;
import sma.tp3.Bayes.Bayes;
import sma.tp3.Bayes.Hypothesis;

/**
 *
 * @author Adrien
 */
public class Acteur extends Agent {
    private List<Observateur> observateurs;
    private boolean random;
    private Hypothesis hypothesis;

    public void setHypothesis(Hypothesis hypothesis) {
        this.hypothesis = hypothesis;
    }

    public Acteur(Bayes bayes) {
        super(bayes);
        this.observateurs = new ArrayList<>();
        this.random = true;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
    
    public void addObsertvateur(Observateur obs){
        this.observateurs.add(obs);
    }
    
    private void notifyObservateurs(){
        for(Observateur observateur : this.observateurs){
            observateur.updateActions(actions);
        }
    }
    
    public Action nextAction(){
        if(actions.size() >= 5){
            return null;
        }
        if(random){
            List<Action> possibleActions = new ArrayList<>();
            for(Action action : bayes.getActions()){
                if(!this.actions.contains(action) || action.getRepeat()){
                    possibleActions.add(action);
                }
            }
            int size = possibleActions.size();
            if(size == 0){
                return null;
            }
            Random r = new Random();
            Action action = possibleActions.get(r.nextInt(size));
            addAction(action);
            notifyObservateurs();
            return action;
        }
        for(Action action : hypothesis.getActions()){
            if(!this.actions.contains(action) || action.getRepeat()){
                addAction(action);
                notifyObservateurs();
                return action;
            }
        }
        return null;
    }
    
}
