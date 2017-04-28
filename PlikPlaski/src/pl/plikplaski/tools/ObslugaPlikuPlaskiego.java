package pl.plikplaski.tools;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObslugaPlikuPlaskiego {

	private static final Logger LOGGER = Logger.getLogger(ObslugaPlikuPlaskiego.class.getName());
	
	private static final String DOMYSLNA_NAZWA_PLIKU = "output.txt";
	private static final String PUSTY = "";
	
	BufferedReader bufferReader = null;
	
	public void przetworzPlikPlaskiIZapiszDoPliku (File plikPlaski) {
		String przetworzonaTrescPliku = odczytajIPrzetworzPlikPlaski(plikPlaski);
		zapiszDoPliku(przetworzonaTrescPliku.toString());
	}
	
	public void przetworzPlikPlaskiIZapiszDoPliku (File plikPlaski, String plikNaWyjsciuPath) {
		String przetworzonaTrescPliku = odczytajIPrzetworzPlikPlaski(plikPlaski);
		zapiszDoPliku(przetworzonaTrescPliku.toString(), plikNaWyjsciuPath);
	}
	
	public void przetworzPlikPlaskiIZapiszDoPliku (String plikPlaskiPath) {
		String przetworzonaTrescPliku = odczytajIPrzetworzPlikPlaski(plikPlaskiPath);
		zapiszDoPliku(przetworzonaTrescPliku.toString());
	}
	
	public void przetworzPlikPlaskiIZapiszDoPliku (String plikPlaskiPath, String plikNaWyjsciuPath) {
		String przetworzonaTrescPliku = odczytajIPrzetworzPlikPlaski(plikPlaskiPath);
		zapiszDoPliku(przetworzonaTrescPliku.toString(), plikNaWyjsciuPath);
	}
	
	public String odczytajIPrzetworzPlikPlaski (String plikPlaskiPath) {
		File plikPlaski = new File(plikPlaskiPath);
		
		return odczytajIPrzetworzPlikPlaski(plikPlaski);
	}
	
	public String odczytajIPrzetworzPlikPlaski (File plikPlaski) {
		
		int ktoryWiersz = 1;
		String nazwaAlgorytmu = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			String biezacyWiersz;
			bufferReader = new BufferedReader(new FileReader(plikPlaski));
			
			while ((biezacyWiersz = bufferReader.readLine()) != null) {
				
				if (ktoryWiersz%2 == 1) {
					nazwaAlgorytmu = biezacyWiersz.trim();
				} else {
					String bufor = przetworzWierszPlikuPlaskiego(nazwaAlgorytmu, biezacyWiersz);
					stringBuilder.append(bufor + System.getProperty("line.separator"));
				}
				
				ktoryWiersz++;
			}
						
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		} finally {
			try {
				bufferReader.close();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		
		return stringBuilder.toString();
	}
	
	private void zapiszDoPliku (String trescPliku, String plikNaWyjsciuPath) {
		BufferedWriter bufferWriter = null;
		
		try {
			bufferWriter = new BufferedWriter(new FileWriter(plikNaWyjsciuPath));
			bufferWriter.write(trescPliku);
			LOGGER.info("Zapisano plik: "+ plikNaWyjsciuPath);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				bufferWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		
	}
	
	private void zapiszDoPliku (String trescPliku) {
		Frame jFrame = new Frame();
	    FileDialog dialog = new FileDialog(jFrame, "Zapisz jako", FileDialog.SAVE);
	    BufferedWriter bufferWriter = null;
	    	    
	    dialog.setFile(DOMYSLNA_NAZWA_PLIKU);
	    dialog.show();
	    String nazwaPliku = dialog.getFile();
	    
	    if (nazwaPliku != null) {
	    	File plik = new File(dialog.getDirectory(), dialog.getFile());
	    	try {
				bufferWriter = new BufferedWriter(new FileWriter(plik));
				bufferWriter.write(trescPliku);
				LOGGER.info("Zapisano plik: "+ dialog.getDirectory() + nazwaPliku);
				
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				
			} finally {
				try {
					bufferWriter.close();
					
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, e.toString(), e);
					
				}
			}
	    	
	    }
	}	
		
	private String przetworzWierszPlikuPlaskiego (String nazwaAlgorytmu, String wiersz) {
		AlgorytmFactory algorytmFactory = new AlgorytmFactory();
		Algorytm algorytm = algorytmFactory.getAlgorytmFactory(nazwaAlgorytmu);
		
		if (algorytm != null) {
			
			return algorytm.getWynikAlgorytmuJakoString(wiersz);
		} else {
			LOGGER.warning("Nie znaleziono algorytmu o nazwie " + nazwaAlgorytmu);
			
			return PUSTY;
		}
	}
	
}
