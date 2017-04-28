package pl.automatykomorkowe;

public class BriansBrain implements AutomatKomorkowy {

	@Override
	public int getKolejnyStan(int stan, int[] tablicaKomorekSasiadow) {
		int licznikPlonacychSasiadow = 0;
		
		for (int stanKomorki : tablicaKomorekSasiadow) {
			if (stanKomorki == 1) licznikPlonacychSasiadow++;
		}
		
		switch(stan) {
			case(0): return getKolejnyStanDlaOczekujacejKomorki(licznikPlonacychSasiadow);
			case(1): return getKolejnyStanDlaPlonacejKomorki();
			case(2): return getKolejnyStanDlaPrzetwarzajacejKomorki();
			default: return 0;
		}
	}
	
	@Override
	public int getLiczbaStanow() {
		return 3;
	}
	
	@Override
	public boolean czyOsmiuSasiadow() {
		return true;
	}
	
	private int getKolejnyStanDlaOczekujacejKomorki(int liczbaPlonacychSasiadow) {
		if (liczbaPlonacychSasiadow == 2) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private int getKolejnyStanDlaPlonacejKomorki() {
		return 2;
	}
	
	private int getKolejnyStanDlaPrzetwarzajacejKomorki() {
		return 0;
	}

}
