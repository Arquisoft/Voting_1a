package hello;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInfoTest {
	
	@Test
	public void testUserInfo() throws Exception {
		UserInfo user = new UserInfo("user", "12563498K", "user@mail.com", 45, "user1");
		user.setContraseña("cambio");
		assertTrue(user.getContraseña().equals("cambio"));
		user.setNIF("33");
		assertFalse(user.getNIF().equals("33"));
		assertTrue(user.getNIF().equals("12563498K"));
		user.setEmail("@hola.com");
		assertFalse(user.getEmail().equals("@hola.com"));
		user.setEmail("hola.com@");
		assertFalse(user.getEmail().equals("hola.com@"));
		assertTrue(user.getEmail().equals("user@mail.com"));
		user.setEmail("cambio@mail.com");
		assertTrue(user.getEmail().equals("cambio@mail.com"));
	}

}
