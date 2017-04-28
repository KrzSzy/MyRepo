package pl.plikplaski.test;

import java.io.File;

import pl.plikplaski.tools.ObslugaPlikuPlaskiego;

public class Main {

	private static final String INPUT_FILE = "C:\\test\\input.txt";
	private static final String OUTPUT_FILE = "C:\\test\\output.txt";
	private static final String OUTPUT_FILE_2 = "C:\\test\\output2.txt";
	
	public static void main(String[] args) {
		File plik = new File(INPUT_FILE);
		ObslugaPlikuPlaskiego opp = new ObslugaPlikuPlaskiego();
		
		opp.przetworzPlikPlaskiIZapiszDoPliku(INPUT_FILE, OUTPUT_FILE);
		opp.przetworzPlikPlaskiIZapiszDoPliku(plik, OUTPUT_FILE_2);
		opp.przetworzPlikPlaskiIZapiszDoPliku(INPUT_FILE);
		opp.przetworzPlikPlaskiIZapiszDoPliku(plik);
		String output = opp.odczytajIPrzetworzPlikPlaski(INPUT_FILE);
		String output2 = opp.odczytajIPrzetworzPlikPlaski(INPUT_FILE);
		
		System.out.println(output);
		System.out.println("-----");
		System.out.println(output2);
	}

}
