package hello;

import static org.junit.Assert.*;

import org.junit.Test;

public class BBDDTests {
	
	@Test
	
	public void UsuarioEncontrado() throws Exception{
		MainController m = new MainController();
		UserInfo user = m.buscarUsuario("gjb@mail.com", "gjb");
		assertTrue(user.getCodigoMesa() == 152);
		assertTrue(user.getName().equals("Guillermo Jiménez Barba"));
		assertTrue(user.getNIF().equals("13458796O"));
		
	}
	
	@Test
	public void UsuarioNoEncontrado() throws Exception{
		Peticion p = new Peticion ("aa" , "aa");
		MainController m = new MainController();
		assertTrue(m.buscarUsuario(p.getEmail(), p.getPassword()) == null);
	}

}
