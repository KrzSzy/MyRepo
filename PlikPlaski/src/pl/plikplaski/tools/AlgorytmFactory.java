package pl.plikplaski.tools;

class AlgorytmFactory {

	private static final String SORT = "SORT";
	private static final String REVERSE = "REVERSE";
	private static final String REPLACE = "REPLACE";
	
	public Algorytm getAlgorytmFactory(String nazwaAlgortytmu) {
		
		if (nazwaAlgortytmu.toUpperCase().equals(SORT)) {
			return new Sort();
		} else if (nazwaAlgortytmu.toUpperCase().equals(REVERSE)) {
			return new Reverse();
		} else if (nazwaAlgortytmu.toUpperCase().equals(REPLACE)) {
			return new Replace();
		} else {
			return null;
		}
	}
	
}
