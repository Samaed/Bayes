/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Corentin
 */
public class Node {
    
    private List<Edge> _entryEdges;
    private List<Edge> _outterEdges;
    private float _weight;
    private int _id;
    private String _description;
    
    public Node(int id) {
        this(id, 0);
    }
    
    public Node(int id, float weight) {
        this(id, weight, ""+id);
    }
    
    public Node(int id, float weight, String description) {
        _id = id;
        _weight = weight;
        _description = description;
        _entryEdges = new ArrayList<>();
        _outterEdges = new ArrayList<>();
    }
    
    public Node(Node node) {
        this(node._id, node._weight, node._description);
        _entryEdges = new ArrayList<>(node._entryEdges);
        _outterEdges = new ArrayList<>(node._outterEdges);
    }
    
    public void addEdge(Edge edge) {
        if (edge.getDestinationNode() == this)
            _entryEdges.add(edge);
        else
            _outterEdges.add(edge);
    }
    
    public List<Edge> getEdges() {
        List<Edge> allEdges = new ArrayList<>(_entryEdges);
        allEdges.addAll(_outterEdges);
        return allEdges;
    }
    
    public List<Edge> getEntryEdges() {
        return _entryEdges;
    }
    
    public List<Edge> getOutterEdges() {
        return _outterEdges;
    }
    
    public float getWeight() {
        return _weight;
    }
    
    public int getId() {
        return _id;
    }

    @Override
    public String toString() {
        return _description;
    }

    public void setWeight(float _weight) {
        this._weight = _weight;
    }
}
