package main.java.es.uniovi.asw.bussines;

import java.util.List;

import util.IDictionary;
import util.KeyValuePair;

/**
 * Tipo de recuento utilizado en las elecciones al
 * parlamento espa�ol.
 */
public class SpanishParliamentCountType implements ICountType {

	@Override
	public IDictionary<String, Integer> count(List<KeyValuePair<String, Integer>> source) {
		return null;
	}
	
	@Override
	public String findLikelyColour(String opcion) {
		String str = opcion.toUpperCase();
		
		if(str.contains("PP") || str.contains("POPULAR"))
			return "blue";
		if(str.contains("PS") || str.contains("SOCIALISTA"))
			return "red";
		if((str.contains("CIU") && str.length() > 3) || str.contains("C'S"))
			return "orange";
		if(str.contains("PODEM") || str.contains("AHORA") || str.contains("MAREA"))
			return "purple";
		if(str.contains("UNIDA") || str.contains("IU"))
			return "green";
		return "grey";
	}
}
