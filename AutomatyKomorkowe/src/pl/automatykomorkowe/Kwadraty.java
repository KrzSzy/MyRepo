package pl.automatykomorkowe;

public class Kwadraty implements AutomatKomorkowy {

	@Override
	public int getKolejnyStan(int stan, int[] tablicaKomorekSasiadow) {
		
		int liczbaZywychSasiadow = 0;
		
		for (int stanKomorki : tablicaKomorekSasiadow) {
			liczbaZywychSasiadow += stanKomorki;
		}
				
		if (liczbaZywychSasiadow < 1) return 2;
        	else if (liczbaZywychSasiadow < 3) return 1;
        	else if (liczbaZywychSasiadow < 5) return 0;
        	else return 2;
	}
	
	@Override
	public int getLiczbaStanow() {
		return 3;
	}
	
	@Override
	public boolean czyOsmiuSasiadow() {
		return false;
	}

}
