package main.java.es.uniovi.asw.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import util.IDictionary;
import util.KeyValuePair;

/**
 * Proporciona comunicación con una base de datos Microsoft SQL Server
 * en Azure que será utilizada por la vista web del sistema de
 * publicación según el plan ASWneo
 */
public class SQLServerBridge {
	private static String url = "jdbc:sqlserver://aswneo.database.windows.net:1433;database=ASWneo;user=carlubian@aswneo;password=H3lloKitty;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	
	private SQLServerBridge() {}
	
	static {}
	
	/**
	 * Envía el recuento de votos a SQL Server
	 * @param count Recuento de votos
	 */
	public static void sendVoteCount(IDictionary<String, Integer> count) {
		try {
			Connection con = DriverManager.getConnection(url);
			System.out.println("Ala conectao");
			PreparedStatement st = con.prepareStatement("DELETE FROM Votos");
			st.execute();
			st.close();
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Votos VALUES (?, ?)");
			for (KeyValuePair<String, Integer> kvp : count)
			{
				ps.setString(1, kvp.key);
				ps.setInt(2, kvp.value);
				ps.execute();
			}
			ps.close();
			con.close();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	/**
	 * Envía el índice de participación a SQL Server
	 * @param indice Índice de participación
	 */
	public static void sendParticipation(int indice) {
		try {
			Connection con = DriverManager.getConnection(url);
			PreparedStatement st = con.prepareStatement("DELETE FROM Participacion");
			st.execute();
			st.close();
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Participacion VALUES (?)");
			ps.setInt(1, indice);
			ps.execute();
			
			ps.close();
			con.close();
			
		} catch (Throwable t) {
			
		}
	}
	
	/**
	 * Envía las estadísticas a SQL Server
	 * @param cosas Estadísticas
	 */
	public static void sendStatistics(List<IDictionary<KeyValuePair<String, String>, Integer>> cosas) {
		try {
			Connection con = DriverManager.getConnection(url);
			PreparedStatement st = con.prepareStatement("DELETE FROM Estadisticas");
			st.execute();
			st.close();
			
			PreparedStatement ps = con.prepareStatement("INSeRT INTO Estadisticas VALUES (?, ?, ?)");
			for(IDictionary<KeyValuePair<String, String>, Integer> dic : cosas)
			{
				KeyValuePair<KeyValuePair<String, String>, Integer> kvp = dic.asList().get(0);
				ps.setString(1, kvp.key.value);
				ps.setString(2, kvp.key.key);
				ps.setInt(3, kvp.value);
				ps.execute();
			}
			ps.close();
			con.close();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}

