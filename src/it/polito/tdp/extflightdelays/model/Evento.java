package it.polito.tdp.extflightdelays.model;

public class Evento implements Comparable<Evento> {

	private int NTuristi;
	private int giorni;
	private String partenza;
	
	
	
	
	
	
	
	
	public int getNTuristi() {
		return NTuristi;
	}








	public int getGiorni() {
		return giorni;
	}



  public String getPartenza() {
	  return partenza;
  }




	public Evento(int nTuristi, int giorni,String p) {
		super();
		this.NTuristi = nTuristi;
		this.giorni = giorni;
		this.partenza = p;
	}








	@Override
	public int compareTo(Evento altro) {
		// TODO Auto-generated method stub
		return this.giorni-altro.giorni;
	}
	
	
	
}
