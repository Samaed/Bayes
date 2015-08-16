/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.agents;

import java.util.List;
import sma.tp3.Bayes.Action;
import sma.tp3.Bayes.Bayes;
import sma.tp3.Bayes.Hypothesis;

/**
 *
 * @author Adrien
 */
public class Observateur extends Agent {

    public Observateur(Bayes bayes) {
        super(bayes);
    }
    
    public void updateActions(List<Action> actions){
        this.actions = actions;
    }
    
    public Hypothesis mostProbableHypothesis(){
        return bayes.calculateMostProbableHypothesis(actions);
    }
    
    public Action mostProbableNextAction(){
        return bayes.calculateMostProbableNextAction(actions);
    }
    
}
