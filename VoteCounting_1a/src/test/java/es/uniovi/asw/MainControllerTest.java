package test.java.es.uniovi.asw;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import main.java.es.uniovi.asw.Application;
import main.java.es.uniovi.asw.bussines.CountingSystem;
import main.java.es.uniovi.asw.bussines.DirectCountType;
import main.java.es.uniovi.asw.bussines.StandardStatisticType;
import main.java.es.uniovi.asw.bussines.StatisticsSystem;
import main.java.es.uniovi.asw.persistence.JdbcPersistenceSupplier;
import util.IDictionary;
import util.KeyValuePair;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	private int votosOviedo = 0;
	private int votosGijon = 0;
	private int votosMadrid = 0;
	private int votosBarcelona = 0;
	private int votosSevilla = 0;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testLanding() throws Exception {
		ResultActions ra = mvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Voting")));
		assertNotNull(ra);
	}
	
	//Test que comprueba que se devuelven correctamente los votos de la BBDD
	@Test
	public void testNumeroVotos(){
		CountingSystem sc = new CountingSystem(new DirectCountType(), new JdbcPersistenceSupplier());
		IDictionary<String, Integer> votos = sc.count();
		int votosSi = votos.stream().filter(kvp -> kvp.key.equals("SI")).findFirst().get().value ;
		int votosNo = votos.stream().filter(kvp -> kvp.key.equals("NO")).findFirst().get().value;
		int votosBlanco = votos.stream().filter(kvp -> kvp.key.equals("BLANCO")).findFirst().get().value;
		assertTrue(votosSi == 7);
		assertTrue(votosNo == 7);
		assertTrue(votosBlanco == 6);
		assertTrue(votosSi + votosNo + votosBlanco == 20);
	}
	
	//Test que comprueba que se obtiene correctamente el porcentaje de participación de la BBDD
	@Test
	public void porcentajeParticipacion(){
		StatisticsSystem ss = new StatisticsSystem(new StandardStatisticType(), new JdbcPersistenceSupplier());
		assertTrue(ss.getParticipacion() == 100);
	}
	
	//Test que comprueba que el numero de votos por ciudad es correcto
	@Test
	public void votacionPorCiudad(){
		
		StatisticsSystem ss = new StatisticsSystem(new StandardStatisticType(), new JdbcPersistenceSupplier());
		
		List<IDictionary<KeyValuePair<String, String>, Integer>> cosas = ss.getEstadisticas();
		
		for (int i=0; i<cosas.size(); i++){
			IDictionary<KeyValuePair<String, String>, Integer> lista = cosas.get(i);
			lista.forEach(kvp ->{
				if(kvp.key.key.equals("Oviedo")){
					votosOviedo += kvp.value;	
				}
				else if(kvp.key.key.equals("Gijón")){
					votosGijon += kvp.value;	
				}
				else if(kvp.key.key.equals("Madrid")){
					votosMadrid += kvp.value;	
				}
				else if(kvp.key.key.equals("Sevilla")){
					votosSevilla += kvp.value;	
				}
				else if(kvp.key.key.equals("Barcelona")){
					votosBarcelona += kvp.value;	
				}
			});
				
			
			}
		assertTrue(votosOviedo == 4 );
		assertTrue(votosGijon == 4 );
		assertTrue(votosSevilla == 4 );
		assertTrue(votosMadrid == 4 );
		assertTrue(votosBarcelona == 4 );
		}	

	}
	
		
