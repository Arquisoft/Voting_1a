package hello;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo user(@RequestBody Peticion peticion) {
        UserInfo result = buscarUsuario(peticion.getEmail(), peticion.getPassword());
        
        if (result != null)
        	return result;
        throw new UsuarioNoEncontrado();
    }

    public UserInfo buscarUsuario(String email, String password) {
    	System.out.println("Iniciando búsqueda de usuario...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("voters");
		EntityManager em = emf.createEntityManager();
		List<UserInfo> aux;
		aux = em.createNamedQuery("UserInfo.login", UserInfo.class)
				.setParameter(1, email).setParameter(2, password).getResultList();
		
		return aux.isEmpty() ? null: aux.get(0);
	}
    
    private UserInfo buscarTodo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("voters");
		EntityManager em = emf.createEntityManager();
		List<UserInfo> aux;
		aux = em.createNamedQuery("UserInfo.topami", UserInfo.class).getResultList();
		for (UserInfo u : aux)
			System.out.println(u.toString());
		return aux.isEmpty() ? null: aux.get(0);
	}

	@RequestMapping("/")
    public String landing() {
    	// Devolver una cadena informativa
        return "User Management Service";
    }
}
