package main.java.es.uniovi.asw.bussines;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.es.uniovi.asw.persistence.FakePersistenceSupplier;
import main.java.es.uniovi.asw.persistence.IPersistenceSupplier;
import main.java.es.uniovi.asw.persistence.SQLServerBridge;
import util.Dictionary;
import util.IDictionary;
import util.KeyValuePair;

public class CountingSystem {
	/* Atributos de la clase */
	private ICountType ctype;
	private IPersistenceSupplier psupplier;

	/* Clases por defecto para los atributos de la clase */
	private static final Class<? extends ICountType> defaultCountType = 
			DirectCountType.class;
	private static final Class<? extends IPersistenceSupplier> defaultPersistenceSupplier =
			FakePersistenceSupplier.class;

	/**
	 * Inicializa un nuevo CountingSystem con un Count Type y Persistence
	 * Supplier determinados
	 */
	public CountingSystem(ICountType ctype, IPersistenceSupplier psupplier) {
		this.ctype = ctype;
		this.psupplier = psupplier;
	}

	/**
	 * Inicializa un nuevo CountingSystem con un Count Type y
	 * PersistenceSupplier por defecto
	 */
	public CountingSystem() {
		// Intentar instanciar el Count Type por defecto
		try {
			this.ctype = defaultCountType.newInstance();
		// Si falla, crear uno sobre la marcha que no haga nada
		} catch (Throwable t) {
			this.ctype = new ICountType() {

				@Override
				public IDictionary<String, Integer> count(List<KeyValuePair<String, Integer>> source) {
					return new Dictionary<>();
				}

				@Override
				public String findLikelyColour(String opcion) {
					return "gray";
				}
			};
		}
		// Intentar instanciar el Persistence Supplier por defecto
		try {
			this.psupplier = defaultPersistenceSupplier.newInstance();
		// Si falla, crear uno sobre la marcha que no haga nada
		} catch (Throwable t) {
			this.psupplier = new IPersistenceSupplier() {

				@Override
				public List<IDictionary<KeyValuePair<String, String>, Integer>> readStatistics() {
					return null; // De todas formas, no se usa aqu�
				}

				@Override
				public List<KeyValuePair<String, Integer>> readResults() {
					return new ArrayList<>();
				}

				@Override
				public int readParticipation() {
					return 100;
				}
			};
		}
	}

	/**
	 * Recupera, cuenta y devuelve los votos actuales
	 * @throws SQLException 
	 */
	public IDictionary<String, Integer> count() {
		List<KeyValuePair<String, Integer>> votos = psupplier.readResults();
		
		IDictionary<String, Integer> result = ctype.count(votos);
		SQLServerBridge.sendVoteCount(result);
		
		return result;
	}
	
}
