package pl.plikplaski.tools;

import java.util.Collections;
import java.util.List;

class Reverse implements Algorytm {

	@Override
	public String getWynikAlgorytmuJakoString(String wiersz) {
		Tools tools = new Tools();
		List<Integer> lista = tools.getListaNaPodstawieStringa(wiersz);
		Collections.reverse(lista);
		
		return tools.getStringNaPodstawieListy(lista);
	}

}
