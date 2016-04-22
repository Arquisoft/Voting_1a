package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TUsers")

public class UserInfo {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfo.class);
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String nif;
	private String email;
	private int codigoMesa;
	private String contraseña;

	UserInfo() {

	}

	public UserInfo(String name, String NIF, String email, int codigoMesa,String contraseña) {
		this.setName(name);
		this.setNIF(NIF);
		this.email = email;
		this.setCodigoMesa(codigoMesa);
		this.setContraseña(contraseña);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNIF() {
		return nif;
	}

	public void setNIF(String nIF) {
		if (nifValido(nIF))
			this.nif = nIF;
		else log.error("Formato de NIF incorrecto");
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (correoValido(email))
			this.email = email;
		else log.error("Formato de correo no válido");
	}

	public int getCodigoMesa() {
		return codigoMesa;
	}

	public void setCodigoMesa(int codigoMesa) {
		this.codigoMesa = codigoMesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", NIF=" + nif + ", email=" + email + ", codigoMesa=" + codigoMesa
				+ ", contraseña=" + contraseña + "]";
	}

	public Long getId(){
		return id;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public boolean nifValido(String nif){
		if (nif != null && !nif.isEmpty())
			if (nif.length() == 9 && Character.isLetter(nif.charAt(8)))
				return true;
		return false;
	}
	
	public boolean correoValido(String correo){
		if (correo.contains("@") && !correo.startsWith("@") && !correo.endsWith("@"))
			return true;
		else return false;
	}


    
}