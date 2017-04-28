package pl.plikplaski.tools;

class Replace implements Algorytm {

	@Override
	public String getWynikAlgorytmuJakoString(String wiersz) {
		
		if (wiersz.length() == 0) {
			return Integer.valueOf(0).toString();
			
		} else {
			
			String wzorzec = wiersz.substring(0, 1);
			String[] tablicaStringow = wiersz.split("");
			int licznik = 1;
			
			for (int i = 1; i < tablicaStringow.length; i++) {
				if (tablicaStringow[i].equals(wzorzec)) {
					licznik++;
				}
			}
			
			return Integer.valueOf(licznik).toString();
		}
	}

}
