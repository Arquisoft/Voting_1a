package es.uniovi.asw.bussines;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.bussines.implStatistic.StandardStatisticType;
import es.uniovi.asw.externalPersistence.impl.SQLServerBridge;
import es.uniovi.asw.persistence.FakePersistenceSupplier;
import es.uniovi.asw.persistence.ReadData;
import util.IDictionary;
import util.KeyValuePair;

public class StatisticsSystem {
	/* Atributos de la clase */
	private IStatisticType stype;
	private ReadData psupplier;

	/* Clases por defecto para los atributos de la clase */
	private static final Class<? extends IStatisticType> defaultStatisticType = 
			StandardStatisticType.class;
	private static final Class<? extends ReadData> defaultPersistenceSupplier =
			FakePersistenceSupplier.class;

	/**
	 * Inicializa un nuevo CountingSystem con un Count Type y Persistence
	 * Supplier determinados
	 */
	public StatisticsSystem(IStatisticType ctype, ReadData psupplier) {
		this.stype = ctype;
		this.psupplier = psupplier;
	}

	/**
	 * Inicializa un nuevo CountingSystem con un Count Type y
	 * PersistenceSupplier por defecto
	 */
	public StatisticsSystem() {
		// Intentar instanciar el Count Type por defecto
		try {
			this.stype = defaultStatisticType.newInstance();
		// Si falla, crear uno sobre la marcha que no haga nada
		} catch (Throwable t) {
			this.stype = new IStatisticType() {

				@Override
				public List<IDictionary<KeyValuePair<String, String>, Integer>> conjure(
						Object usefulData) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public int getIndiceParticipacion(Object usefulData) {
					// TODO Auto-generated method stub
					return 0;
				}
			};
		}
		// Intentar instanciar el Persistence Supplier por defecto
		try {
			this.psupplier = defaultPersistenceSupplier.newInstance();
		// Si falla, crear uno sobre la marcha que no haga nada
		} catch (Throwable t) {
			this.psupplier = new ReadData() {

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
	public StatisticsSystem getEstadisticas() {
		List<IDictionary<KeyValuePair<String, String>, Integer>> cosas = stype.conjure(this.psupplier);
		System.out.println("Consigo los datos");
		new SQLServerBridge().sendStatistics(cosas);
		System.out.println("Envío los datos");
		return this;
	}
	
	public StatisticsSystem getParticipacion() {
		int indice = psupplier.readParticipation();
		new SQLServerBridge().sendParticipation(indice);
		
		return this;
	}
	
}
