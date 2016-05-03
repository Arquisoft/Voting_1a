package es.uniovi.asw.externalPersistence;

import java.util.List;

import util.IDictionary;
import util.KeyValuePair;

public interface Bridge {
	
	public  void sendVoteCount(IDictionary<String, Integer> count);
	
	public  void sendParticipation(int indice);
	
	public  void sendStatistics(List<IDictionary<KeyValuePair<String, String>, Integer>> cosas);

}
