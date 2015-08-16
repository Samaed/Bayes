/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.agents;

import java.util.ArrayList;
import java.util.List;
import sma.tp3.Bayes.Action;
import sma.tp3.Bayes.Bayes;

/**
 *
 * @author Adrien
 */
public abstract class Agent {
    protected Bayes bayes;
    
    protected List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }
    
    public void removeAction(Action action){
        actions.remove(action);
    }
    
    public void addAction(Action action){
        actions.add(action);
    }

    public Agent(Bayes bayes) {
        this.bayes = bayes;
        this.actions = new ArrayList<>();
    }
    
}
