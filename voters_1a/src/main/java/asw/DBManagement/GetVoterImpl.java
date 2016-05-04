package asw.DBManagement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import asw.model.UserInfo;

public class GetVoterImpl implements GetVoter{

	@Override
	public UserInfo getVP(String email, String password) {
		System.out.println("Iniciando búsqueda de usuario...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("voters");
		EntityManager em = emf.createEntityManager();
		List<UserInfo> aux;
		aux = em.createNamedQuery("UserInfo.login", UserInfo.class)
				.setParameter(1, email).setParameter(2, password).getResultList();
		
		return aux.isEmpty() ? null: aux.get(0);
	}

}
