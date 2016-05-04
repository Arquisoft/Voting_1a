package es.uniovi.asw;

import java.awt.EventQueue;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.uniovi.asw.ui.MainWindow;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
	  EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
}