/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.Bayes;

import java.util.ArrayList;
import java.util.List;
import sma.tp3.graph.Node;

/**
 *
 * @author Corentin
 */
public class Hypothesis {
    
    private Node _node;
    private List<Action> _actions;
    private float _intermediateProbability;

    public float getIntermediateProbability() {
        return _intermediateProbability;
    }

    public void setIntermediateProbability(float _intermediateProbability) {
        this._intermediateProbability = _intermediateProbability;
    }
    
    Hypothesis(Node node) {
        _node = node;
        _actions = new ArrayList<>();
        _intermediateProbability = getProbability();
    }
    
    public Node getNode() {
        return _node;
    }
    
    Action addAction(Action action) {
        _actions.add(action);
        return action;
    }
    
    public List<Action> getActions() {
        return _actions;
    }
    
    public float getProbability() {
        return _node.getWeight();
    }
    
    public void setProbability(float probability) {
        _node.setWeight(probability);
    }

    @Override
    public String toString() {
        return _node.toString();
    }
    
    public String getSmallDesc(){
        return _node.toString().replaceAll(".*\\(|\\).*", "");
    }
}
