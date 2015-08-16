/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.tp3.views;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Adrien
 */
public class ProbabilityLine {
    private Color color = Color.BLACK;
    private float probability;
    private HypothesisPanel start;
    private ActionPanel end;

    public ProbabilityLine(float probability, HypothesisPanel start, ActionPanel end) {
        this.probability = probability;
        this.start = start;
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public HypothesisPanel getStart() {
        return start;
    }

    public void setStart(HypothesisPanel start) {
        this.start = start;
    }

    public ActionPanel getEnd() {
        return end;
    }

    public void setEnd(ActionPanel end) {
        this.end = end;
    }
    
    
}
