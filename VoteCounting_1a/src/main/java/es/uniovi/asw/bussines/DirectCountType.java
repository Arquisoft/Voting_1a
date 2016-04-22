package main.java.es.uniovi.asw.bussines;

import java.util.List;

import util.Dictionary;
import util.IDictionary;
import util.KeyValuePair;

/**
 * Tipo de recuento utilizado en referendums y
 * otras elecciones de correspondencia directa
 * entre votos y resultado
 */
public class DirectCountType implements ICountType {

	@Override
	public IDictionary<String, Integer> count(List<KeyValuePair<String, Integer>> source) {
		
		return new Dictionary<String, Integer>().putAll(source);
	}
	
	@Override
	public String findLikelyColour(String opcion) {
		String str = opcion.toUpperCase();
		
		if(str.contains("SI"))
			return "green";
		if(str.contains("NO"))
			return "red";
		if(str.contains("BLANCO"))
			return "grey";
		return "grey";
	}

}
