package main.java.es.uniovi.asw.bussines;

import java.util.List;

import util.IDictionary;
import util.KeyValuePair;

public interface IStatisticType {
	/**
	 * Consigue las estad�sticas de la votaci�n actual
	 * @param usefulData Datos �tiles para crear las estad�sticas, o null
	 * @return Estructura de datos con estad�sticas
	 */
	public List<IDictionary<KeyValuePair<String, String>, Integer>> conjure(Object usefulData);
	/**
	 * Devuelve el �ndice actual de participaci�n
	 * @param usefulData Datos �tiles para crear las estad�sticas, o null
	 */
	public int getIndiceParticipacion(Object usefulData);
}
