package main.java.es.uniovi.asw.bussines;

import java.util.List;

import main.java.es.uniovi.asw.persistence.IPersistenceSupplier;
import util.IDictionary;
import util.KeyValuePair;

/**
 * Estad�sticas est�ndar
 */
public class StandardStatisticType implements IStatisticType {

	@Override
	public List<IDictionary<KeyValuePair<String, String>, Integer>> conjure(Object usefulData) {
		IPersistenceSupplier ips = (IPersistenceSupplier) usefulData;
		return ips.readStatistics();
	}

	@Override
	public int getIndiceParticipacion(Object usefulData) {
		IPersistenceSupplier ips = (IPersistenceSupplier) usefulData;
		return ips.readParticipation();
	}

}
