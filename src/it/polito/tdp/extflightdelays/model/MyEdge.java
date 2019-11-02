package it.polito.tdp.extflightdelays.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class MyEdge extends DefaultWeightedEdge {
	private int weight;
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
