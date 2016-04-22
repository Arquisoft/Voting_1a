package main.java.es.uniovi.asw.model;

public class Voto {
	private String opcion;
	private Integer codColegio;
	private String preferredColor;
	private Integer numVotos;
	
	public String getOpcion() {
		return opcion;
	}
	public Voto setOpcion(String opcion) {
		this.opcion = opcion;
		return this;
	}
	public Integer getCodColegio() {
		return codColegio;
	}
	public Voto setCodColegio(Integer codColegio) {
		this.codColegio = codColegio;
		return this;
	}
	public String getPreferredColor() {
		return preferredColor;
	}
	public Voto setPreferredColor(String preferredColor) {
		this.preferredColor = preferredColor;
		return this;
	}
	
	public int getNumVotos() {
		return numVotos;
	}
	public Voto setNumVotos(int numVotos) {
		this.numVotos = numVotos;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codColegio == null) ? 0 : codColegio.hashCode());
		result = prime * result + ((opcion == null) ? 0 : opcion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		return other.getOpcion().equals(this.getOpcion()) && other.getCodColegio().equals(this.getCodColegio());
	}
	
	@Override
	public String toString() {
		return "Voto [opci�n=" + opcion + ", codColegio=" + codColegio + "]";
	}
}
