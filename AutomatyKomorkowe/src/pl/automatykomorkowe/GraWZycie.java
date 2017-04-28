package pl.automatykomorkowe;

public class GraWZycie implements AutomatKomorkowy {

	@Override
	public int getKolejnyStan (int stan, int[] tablicaKomorekSasiadow) {
		
		int liczbaZywychSasiadow = 0;
		
		for (int stanKomorki : tablicaKomorekSasiadow) {
			liczbaZywychSasiadow += stanKomorki;
		}
		
		switch(stan) {
			case(0): return getKolejnyStanDlaMartwejKomorki(liczbaZywychSasiadow);
			case(1): return getKolejnyStanDlaZywejKomorki(liczbaZywychSasiadow);
			default: return 0;
		}
		
	}
	
	@Override
	public int getLiczbaStanow() {
		return 2;
	}
	
	@Override
	public boolean czyOsmiuSasiadow() {
		return false;
	}
	
	private int getKolejnyStanDlaMartwejKomorki (int liczbaZywychSasiadow) {
		
		if (liczbaZywychSasiadow == 3) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private int getKolejnyStanDlaZywejKomorki (int liczbaZywychSasiadow) {
		
		if (liczbaZywychSasiadow == 2 || liczbaZywychSasiadow == 3) {
			return 1;
		} else {
			return 0;
		}		
	}
	
}
