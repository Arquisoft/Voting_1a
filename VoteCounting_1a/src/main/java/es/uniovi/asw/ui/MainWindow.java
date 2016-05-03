package es.uniovi.asw.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.bussines.CountingSystem;
import es.uniovi.asw.bussines.StatisticsSystem;
import es.uniovi.asw.bussines.implCountType.DirectCountType;
import es.uniovi.asw.bussines.implStatistic.StandardStatisticType;
import es.uniovi.asw.persistence.impl.ReadDataP;
import threading.Task;

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
				new ScheduledThreadPoolExecutor(2).scheduleWithFixedDelay(new Runnable() {

					@Override
					public void run() {
						new Task(() -> {
							new CountingSystem(new DirectCountType(), new ReadDataP()).count();
							new StatisticsSystem(new StandardStatisticType(), new ReadDataP())
									.getEstadisticas().getParticipacion();
						}).start();
					}
				}, 0, 60, TimeUnit.SECONDS);

			}
		});
		contentPane.add(btnIniciarRecuento, BorderLayout.CENTER);
	}

}
