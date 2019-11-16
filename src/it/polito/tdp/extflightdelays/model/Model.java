package it.polito.tdp.extflightdelays.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO  dao;
	private Graph grafo = new DefaultDirectedWeightedGraph<String,MyEdge>(MyEdge.class);
	
	public  Model() {
		this.dao = new ExtFlightDelaysDAO();
	}

	public List<String> getstati(){
		List<String>  stati =  dao.loadAllStates();
		return stati;
	}
	
	public void creaGrafo() {
		for(String s: dao.loadAllStates()) {
			grafo.addVertex(s);
		}
		
		for(Object i: grafo.vertexSet()) {
			for(Object d: grafo.vertexSet()) {
				String s1 = (String) i;
				String s2 = (String) d;
				List<Integer> a = dao.getAirportsByState(s1);
				List<Integer> b = dao.getAirportsByState(s2);
				int sommaPesi=0;
				int parzialePesi;
				for(Integer m : a) {
					for(Integer n: b) {
						if((parzialePesi = dao.peso(m, n)) != -1) {
							sommaPesi+=parzialePesi;
						}
					}
						
				}	
				MyEdge edge = (MyEdge) grafo.addEdge(s1, s2);
				if(edge != null) {
					edge.setWeight(sommaPesi);
				}
			}
		}
		System.out.println(grafo.toString());
		
	}
	
	public String getVicini(String s) {
		List<String> neighbours = Graphs.neighborListOf(grafo, s);
		String ret = "";
		for(String n: neighbours) {
			MyEdge edge = (MyEdge) grafo.getEdge(s, n);
			ret += n + ": " + edge.getWeight() + "\r\n";
		}
		return ret;
	}
	
	public String getSimulazione(int giorni, int turisti, String partenza) {
		Simulatore s = new Simulatore();
		s.init(turisti, giorni, this.grafo, partenza);
		s.run();
		return s.getTuristi();
	}
	
}
