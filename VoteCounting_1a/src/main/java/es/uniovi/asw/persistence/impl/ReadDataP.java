package es.uniovi.asw.persistence.impl;

import java.sql.*;
import java.util.ArrayList;

import util.Dictionary;
import util.IDictionary;

import java.util.List;

import es.uniovi.asw.persistence.ReadData;
import util.KeyValuePair;

public class ReadDataP implements ReadData {
	private Connection conexion;

	public ReadDataP() {
		try {
			conexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "SA", "");
		} catch (SQLException e) {
			System.err.println("No ha sido posible establecer la conexión con la base de datos.");
		}
	}

	@Override
	public List<KeyValuePair<String, Integer>> readResults() {
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select o.nombre, sum(numero) from tvotes v , topciones o where v.opcion = o.nombre group by o.nombre;");
			List<KeyValuePair<String, Integer>> lista = new ArrayList<>();

			while (rs.next()) {
				String opcion = rs.getString(1);
				int numVotos = rs.getInt(2);
		
				lista.add(new KeyValuePair<>(opcion, numVotos));
			}

			return lista;
		} catch (Throwable t) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<IDictionary<KeyValuePair<String, String>, Integer>> readStatistics() {
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select o.nombre , l.ciudad , v.numero from "
					+ "TOPCIONES o , TVOTES v , TPLACES l where o.nombre = v.opcion and v.lugar = l.id;");
			List<IDictionary<KeyValuePair<String, String>, Integer>> estadisticas = new ArrayList<>();
			while (rs.next()) {
				String opcion = rs.getString(1);
				String ciudad = rs.getString(2);
				int votos = rs.getInt(3);

				KeyValuePair<String, String> a = new KeyValuePair<String, String>(ciudad, opcion);
				IDictionary<KeyValuePair<String, String>, Integer> diccionario = new Dictionary<>();
				diccionario.put(a, votos);
				estadisticas.add(diccionario);

			}

			return estadisticas;
		} catch (Throwable t) {
			return new ArrayList<>();
		}
	}

	public int readParticipation() {
		try {
			Statement st = conexion.createStatement();
			ResultSet rs1 = st.executeQuery("select sum(numVotos) from tvotos;");
			ResultSet rs2 = st.executeQuery("select count(*) from tusers;");
			rs1.next();
			rs2.next();
			int votantes = rs1.getInt(1);
			int censados = rs2.getInt(1);
			int porcentaje = (votantes * 100) / censados;
			return porcentaje;
		} catch (Throwable t) {
			//t.printStackTrace();
			return 0;
		}
	}

}
