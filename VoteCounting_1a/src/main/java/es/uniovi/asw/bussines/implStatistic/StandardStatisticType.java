package es.uniovi.asw.bussines.implStatistic;

import java.util.List;

import es.uniovi.asw.bussines.IStatisticType;
import es.uniovi.asw.persistence.ReadData;
import util.IDictionary;
import util.KeyValuePair;

/**
 * Estad�sticas est�ndar
 */
public class StandardStatisticType implements IStatisticType {

	@Override
	public List<IDictionary<KeyValuePair<String, String>, Integer>> conjure(Object usefulData) {
		ReadData ips = (ReadData) usefulData;
		return ips.readStatistics();
	}

	@Override
	public int getIndiceParticipacion(Object usefulData) {
		ReadData ips = (ReadData) usefulData;
		return ips.readParticipation();
	}

}
