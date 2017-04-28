package pl.automatykomorkowe.okno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.automatykomorkowe.AutomatKomorkowy;
import pl.automatykomorkowe.BriansBrain;
import pl.automatykomorkowe.GraWZycie;
import pl.automatykomorkowe.Kwadraty;

public class OknoAutomatowKomorkowych extends JFrame {
	
	private static final int BOK_KOMORKI = 5;
	private static final int PRZESUNIECIE_KOMORKI = 6;
	private static final int ROZMIAR_TABLICY = 102;
	private static final Color NIEBIESKI = Color.BLUE;
	private static final Color ZOLTY = Color.YELLOW;
	private static final Color SZARY = Color.LIGHT_GRAY;
	
	private JPanel contentPane;
	private JPanel panel;
	private Graphics graphics;
	private int komorka[][] = new int [ROZMIAR_TABLICY][ROZMIAR_TABLICY];
	private WatekAlgorytmowKomorkowych watek;
	private AutomatKomorkowy automatKomorkowy;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OknoAutomatowKomorkowych frame = new OknoAutomatowKomorkowych();
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
	public OknoAutomatowKomorkowych() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 763);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAutomatKomrkowy = new JMenu("Automat kom\u00F3rkowy");
		menuBar.add(mnAutomatKomrkowy);
		
		JMenuItem mntmGraWycie = new JMenuItem("Gra w \u017Cycie");
		mntmGraWycie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				automatKomorkowy = new GraWZycie();
				uruchomAlgorytm(automatKomorkowy.getLiczbaStanow(), automatKomorkowy.czyOsmiuSasiadow());
			}
		});
		
		JMenuItem mntmKwadraty = new JMenuItem("Kwadraty");
		mntmKwadraty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				automatKomorkowy = new Kwadraty();
				uruchomAlgorytm(automatKomorkowy.getLiczbaStanow(), automatKomorkowy.czyOsmiuSasiadow());
			}
		});
		
		JMenuItem mntmBriansBrain = new JMenuItem("Brian's Brain");
		mntmBriansBrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				automatKomorkowy = new BriansBrain();
				uruchomAlgorytm(automatKomorkowy.getLiczbaStanow(), automatKomorkowy.czyOsmiuSasiadow());
			}
		});
		
		mnAutomatKomrkowy.add(mntmGraWycie);
		mnAutomatKomrkowy.add(mntmBriansBrain);
		mnAutomatKomrkowy.add(mntmKwadraty);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(151, 610));
		
	}
	
	private void uruchomAlgorytm(int liczbaStanow, boolean czyOsmiuSasiadow) {
		if (graphics == null) graphics = panel.getGraphics();
		if (liczbaStanow == 2) utworzLosowaPlanszeDwustanowa();
		else utworzLosowaPlanszeTrojstanowa();
		if (watek != null) watek.stop();
		watek = new WatekAlgorytmowKomorkowych(czyOsmiuSasiadow);
		watek.start();
	}
		
	private void utworzLosowaPlanszeDwustanowa() {
		Random generator = new Random();
		int wygenerowanaWartosc = 0;
		int stan = 0;
		
		for (int j=0; j<ROZMIAR_TABLICY; j++) {
			for (int i=0; i<ROZMIAR_TABLICY; i++) {
				wygenerowanaWartosc = generator.nextInt(10);
				if (wygenerowanaWartosc > 5) {
					stan = 0;
				} else {
					stan = 1;
				}
				komorka[i][j] = stan;
			}
		}
	}
	
	private void utworzLosowaPlanszeTrojstanowa() {
		Random generator = new Random();
		int wygenerowanaWartosc = 0;
		int stan = 0;
		
		for (int j=0; j<ROZMIAR_TABLICY; j++) {
			for (int i=0; i<ROZMIAR_TABLICY; i++) {
				wygenerowanaWartosc = generator.nextInt(10);
				if (wygenerowanaWartosc < 3) {
					stan = 0;
				} else if (wygenerowanaWartosc < 6) {
					stan = 1;
				} else {
					stan = 2;
				}
				komorka[i][j] = stan;
			}
		}
	}
		
	private void rysuj(boolean czyOsmiuSasiadow) {
		int stan;
		int[] stanyKomorekSasiadow = new int[8];
		
		for (int j=1; j < ROZMIAR_TABLICY-1; j++) {
			for (int i=1; i < ROZMIAR_TABLICY-1; i++) {
				stan = komorka[i][j];
				stanyKomorekSasiadow[0] = komorka[i-1][j];
				stanyKomorekSasiadow[1] = komorka[i+1][j];
				stanyKomorekSasiadow[2] = komorka[i][j+1];
				stanyKomorekSasiadow[3] = komorka[i][j-1];
				if (czyOsmiuSasiadow) {
					stanyKomorekSasiadow[4] = komorka[i-1][j-1];
					stanyKomorekSasiadow[5] = komorka[i+1][j+1];
					stanyKomorekSasiadow[6] = komorka[i-1][j+1];
					stanyKomorekSasiadow[7] = komorka[i+1][j-1];
				}
				
				komorka[i][j] = automatKomorkowy.getKolejnyStan(stan, stanyKomorekSasiadow);
				ustawKolorKomorki(komorka[i][j]);
				graphics.fillRect(1+PRZESUNIECIE_KOMORKI*i, 1+PRZESUNIECIE_KOMORKI*j, BOK_KOMORKI, BOK_KOMORKI);
			}
		}
	}
	
	private void ustawKolorKomorki(int stanKomorki) {
		if (stanKomorki == 0) graphics.setColor(NIEBIESKI);
		else if(stanKomorki == 1) graphics.setColor(SZARY);
		else graphics.setColor(ZOLTY);
	}
	
	private class WatekAlgorytmowKomorkowych extends Thread {
		private static final int SLEEP = 90;
		private boolean czyOsmiuSasiadow;
		
		private WatekAlgorytmowKomorkowych() {
			this.czyOsmiuSasiadow = false;
		}
		
		private WatekAlgorytmowKomorkowych(boolean czyOsmiuSasiadow) {
			this.czyOsmiuSasiadow = czyOsmiuSasiadow;
		}
		
		public void run() {
			while (true) {
				try {
					rysuj(czyOsmiuSasiadow);
					Thread.sleep(SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
