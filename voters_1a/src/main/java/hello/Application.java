package hello;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
//    	EntityManagerFactory emf = Persistence
//				.createEntityManagerFactory("voters");
//
//		emf.close();
//
//		System.out.println("--> Si no hay excepciones todo va bien");
//		System.out.println("\n\t (O no hay ninguna clase mapeada)");
	
    }
}