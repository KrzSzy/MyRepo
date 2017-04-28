package pl.automatykomorkowe;

public interface AutomatKomorkowy {
	public int getKolejnyStan(int stan, int[] tablicaKomorekSasiadow);
	public int getLiczbaStanow();
	public boolean czyOsmiuSasiadow();
}
