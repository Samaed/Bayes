/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.graph;

/**
 *
 * @author Corentin
 */
public class Edge {
    
    private Node _source, _destination;
    private float _weight;
    
    public Edge(Node sourceNode, Node destinationNode, float weight) {
        _source = sourceNode;
        _destination = destinationNode;
        _weight = weight;
    }
    
    public float getWeight() {
        return _weight;
    }
    
    public Node getSourceNode() {
        return _source;
    }
    
    public Node getDestinationNode() {
        return _destination;
    }

    @Override
    public String toString() {
        return String.format("%s to %s, weight %f", _source, _destination, _weight);
    }
}