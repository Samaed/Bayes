/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.Bayes;

import java.util.ArrayList;
import java.util.List;
import sma.tp3.graph.Graph;

/**
 *
 * @author Corentin
 */
public class Bayes {
    
    private int NEXT_ID = 0;
    
    private Graph _model;
    private List<Action> _actions;
    private List<Hypothesis> _hypothesis;
    
    public Bayes() {
        _model = new Graph();
        _actions = new ArrayList<>();
        _hypothesis = new ArrayList<>();
    }
    
    public Hypothesis addHypothesis(String description, float weight) {
        Hypothesis hypothesis = new Hypothesis(_model.addNode(NEXT_ID++, weight, description));
        _hypothesis.add(hypothesis);
        return hypothesis;
    }
    
    public List<Hypothesis> getHypotheses() {
        return _hypothesis;
    }
    
    public Action addAction(String description) {
        return addAction(description, false);
    }
    
    public Action addAction(String description, boolean repeat) {
        Action action = new Action(_model.addNode(NEXT_ID++, 0, description), repeat);
        _actions.add(action);
        return action;
    }
    
    public List<Action> getActions() {
        return _actions;
    }
    
    public void addProbability(Hypothesis hypothesis, Action action, float probability) {
        _model.addEdge(hypothesis.getNode(), action.getNode(), probability);
        hypothesis.addAction(action);
    }
    
    public float calculateProbability(Hypothesis hypothesis, List<Action> actions) {
        float upperProduct = 1;
        float downSum = 0;
        
        for (Action a : actions) {
            upperProduct *= a.getProbability(hypothesis);
        }
        upperProduct *= hypothesis.getProbability();
        
        float tempProduct;
        for (Hypothesis hypo : getHypotheses()) {
            tempProduct = 1;
            for (Action a : actions) {
                tempProduct *= a.getProbability(hypo);
            }
            tempProduct *= hypo.getProbability();
            
            downSum += tempProduct;
        }
        float probability = 0;
        if(downSum != 0){
            probability = upperProduct / downSum;
        }
        hypothesis.setIntermediateProbability(probability);
        return probability;
    }
    
    public Hypothesis calculateMostProbableHypothesis(List<Action> actions) {
        float max = 0;
        Hypothesis current = null;
        
        float proba;
        for (Hypothesis hypothesis : getHypotheses()) {
            proba = calculateProbability(hypothesis, actions);
            if (proba > max) {
                max = proba;
                current = hypothesis;
            }
        }
        
        return current;  
    }
    
    public Action calculateMostProbableNextAction(List<Action> actions) {
        Hypothesis probable = calculateMostProbableHypothesis(actions);
        if(probable == null){
            return null;
        }
        List<Action> probableActions = probable.getActions();
        
        Action mostProbable = null;
        float max = 0;
        
        float proba;
        for (Action action : probableActions) {
            proba = action.getProbability(probable);
            if (proba > max && !(actions.contains(action) && !action.getRepeat())) {
                max = proba;
                mostProbable = action;
            }
        }
        
        return mostProbable;
    }
}
