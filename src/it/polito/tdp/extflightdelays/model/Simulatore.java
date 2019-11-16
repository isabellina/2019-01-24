package it.polito.tdp.extflightdelays.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;


public class Simulatore {

	private Graph<String, MyEdge> grafo ;
	private PriorityQueue queue;
	
	private int numeroTuristi;
	private int numeroGiorni;
	
	private Map<String,Integer> mappaTuristi ;
	
	public void init(int turisti, int giorni, Graph<String,MyEdge> grafo, String partenza) {
		this.numeroGiorni = giorni;
	    this.numeroTuristi = turisti;
	    this.grafo = grafo;
	    this.mappaTuristi = new TreeMap<String,Integer>();
	  for (String s: grafo.vertexSet()) {
		  mappaTuristi.put(s,0);
		  
	  }
	  
		queue = new PriorityQueue<Evento>();
		this.queue.add(new Evento(numeroGiorni,numeroTuristi,partenza));
	    
	}
	
	public void run() {
		Evento e;
		while((e=(Evento) queue.poll())!=null){
			numeroGiorni= e.getGiorni();
			numeroTuristi = e.getNTuristi();
			String partenza = e.getPartenza();
			if(numeroGiorni > 0) {
				for(int i = 1; i <= numeroTuristi; i++) {
					System.out.println(this.getDestinazione(partenza));
					queue.add(new Evento(1, numeroGiorni-1, this.getDestinazione(partenza)));
				}
			}
			else {
				int prevTuristi = this.mappaTuristi.get(partenza);
				this.mappaTuristi.put(partenza, prevTuristi + 1);
			}	
		}
	}
	
	public String getDestinazione(String p) {
		List<Destinazione> destinazioni = new LinkedList<Destinazione>();
		Destinazione d;
		int pesoTotale=0;
		for(String s : Graphs.neighborListOf(this.grafo, p)) {
			d = new Destinazione(s, (int) grafo.getEdge(p, s).getWeight());
			destinazioni.add(d);
		}
		
		for(Destinazione dest: destinazioni) {
			pesoTotale+=dest.getPeso();
		}
		
		Random r = new Random();
		
		Destinazione dest = destinazioni.get(r.nextInt(destinazioni.size()));
		
		return dest.getStato();
		
/*		while(true) {
			Random r = new Random();
			
			Destinazione dest = destinazioni.get(r.nextInt(destinazioni.size()));
			
			if((dest.getPeso()/pesoTotale) > Math.random()) {
				return dest.getStato();
			}
		}*/
		
	}
	
	public String getTuristi() {
		String result = "";
		for(String s : this.mappaTuristi.keySet()) {
			result += s + " " + this.mappaTuristi.get(s) + "\n";
		}
		return result;
	}
	
	
}
