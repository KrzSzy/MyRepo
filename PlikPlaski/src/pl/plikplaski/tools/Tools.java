package pl.plikplaski.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class Tools {

	private static final Logger LOGGER = Logger.getLogger(Tools.class.getName());
	
	private static final String SPACJA = " ";
	private static final String PUSTO = "";
	
	public List<Integer> getListaNaPodstawieStringa (String wiersz) throws NumberFormatException {
		
		List<Integer> lista = new ArrayList<Integer>();
		
		try {
			String[] listaStringow = wiersz.split(SPACJA);
			
			for (String s : listaStringow) {
				lista.add(Integer.valueOf(s));
			}
			
		} catch (NumberFormatException nfe) {
			LOGGER.log(Level.SEVERE, nfe.toString(), nfe);
			
		}
		
		return lista;
	}
	
	public String getStringNaPodstawieListy (List<Integer> lista) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i : lista) {
			stringBuilder.append(Integer.valueOf(i).toString() + SPACJA);
		}
		
		if (lista.size() == 0) stringBuilder.append(PUSTO);
		
		return stringBuilder.toString();
	}
}
