package main.java.es.uniovi.asw.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.es.uniovi.asw.bussines.CountingSystem;
import main.java.es.uniovi.asw.bussines.DirectCountType;
import main.java.es.uniovi.asw.bussines.StandardStatisticType;
import main.java.es.uniovi.asw.bussines.StatisticsSystem;
import main.java.es.uniovi.asw.persistence.JdbcPersistenceSupplier;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnIniciarRecuento = new JButton("Iniciar recuento");
		btnIniciarRecuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CountingSystem(new DirectCountType(), new JdbcPersistenceSupplier()).count();
				StatisticsSystem ss = new StatisticsSystem(new StandardStatisticType(), new JdbcPersistenceSupplier());
				ss.getEstadisticas();
				ss.getParticipacion();
				
			}
		});
		contentPane.add(btnIniciarRecuento, BorderLayout.CENTER);
	}

}
