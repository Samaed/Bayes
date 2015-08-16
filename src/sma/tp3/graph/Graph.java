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
public class Graph {
    
    private List<Node> _roots;
    private List<Node> _nodes;
    
    public Graph() {
        _roots = new ArrayList<>();
        _nodes = new ArrayList<>();
    }
    
    public Node addNode(Node node) throws IllegalArgumentException {
        if (getNode(node.getId()) == null) {
            Node copy = new Node(node);
            _roots.add(copy);
            _nodes.add(copy);
            return copy;
        } else {
            throw new IllegalArgumentException(String.format("ID %d already in use", node.getId()));
        }
    }
    
    public Node addNode(int id) {
        return addNode(id, 0f);
    }
    
    public Node addNode(int id, float weight) {
        return addNode(id, weight, ""+id);
    }
    
    public Node addNode(int id, float weight, String description) {
        Node node = new Node(id, weight, description);
        _roots.add(node);
        _nodes.add(node);
        
        return node;
    }
    
    public Edge addEdge(Node source, Node destination, float weight) {
        if (_roots.contains(destination))
            _roots.remove(destination);
        
        Edge edge = new Edge(source, destination, weight);
        source.addEdge(edge);
        destination.addEdge(edge);
        
        return edge;
    }
    
    public Node getNode(int id) {
        return _nodes.stream().filter(node -> node.getId() == id).findFirst().orElseGet(null);
    }
    
    public List<Node> getRoots() {
        return _roots;
    }
    
    public List<Node> getNodes() {
        return _nodes;
    }
}
