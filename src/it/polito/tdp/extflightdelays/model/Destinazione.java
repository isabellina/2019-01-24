package it.polito.tdp.extflightdelays.model;

public class Destinazione {

	String stato;
	int peso;
	
	
	public Destinazione(String stato, int peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}
	public String getStato() {
		return stato;
	}
	public int getPeso() {
		return peso;
	}
	
	
	
}
