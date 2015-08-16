/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.Bayes;

import sma.tp3.graph.Edge;
import sma.tp3.graph.Node;

/**
 *
 * @author Corentin
public class Action extends No
 */
public class Action {
    
    private Node _node;
    private boolean _repeat;

    Action(Node node) {
        this(node, false);
    }
    
    Action(Node node, boolean repeat) {
        _node = node;
        _repeat = repeat;
    }
    
    public Node getNode() {
        return _node;
    }

    @Override
    public String toString() {
        return _node.toString();
    }
    
    public String getSmallDesc(){
        return _node.toString().replaceAll(".*\\(|\\).*", "");
    }
    
    public boolean setRepeat(boolean repeat) {
        _repeat = repeat;
        return _repeat;
    }
    
    public boolean getRepeat() {
        return _repeat;
    }
    
    public float getProbability(Hypothesis hypothesis) {
        Edge e = null;
        for (Edge edge : _node.getEntryEdges()) {
            if (edge.getSourceNode() == hypothesis.getNode()) {
                e = edge;
                break;
            }
        }
        
        return (e == null) ? 0 : e.getWeight();
    }
}
