package main.java.es.uniovi.asw.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Dictionary;
import util.IDictionary;
import util.KeyValuePair;

public class FakePersistenceSupplier implements IPersistenceSupplier {

	@Override
	public List<KeyValuePair<String, Integer>> readResults() {
		List<KeyValuePair<String, Integer>> votos = new ArrayList<>();
		votos.add(new KeyValuePair<>("PP", 5));
		votos.add(new KeyValuePair<>("PSOE", 3));
		votos.add(new KeyValuePair<>("BLANCO", 4));
		return votos;
	}

	@Override
	public List<IDictionary<KeyValuePair<String, String>, Integer>> readStatistics() {
		return Stream.iterate(
				(IDictionary<KeyValuePair<String, String>, Integer>)null, 
				n -> new Dictionary<KeyValuePair<String, String>, Integer>()
				.put(new KeyValuePair<>("Prueba", "PP"), 5)
				.put(new KeyValuePair<>("Prueba", "PSOE"), 3)
				.put(new KeyValuePair<>("Prueba", "BLANCO"), 4))
				.limit(1)
				.collect(Collectors.toList());
	}

	@Override
	public int readParticipation() {
		return 100;
	}

}
