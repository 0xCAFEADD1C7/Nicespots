
public class TestMarco {

	public static void main(String[] args) {
		MeteoApiClient mt = new MeteoApiClient();
		MapApiClient mp = new MapApiClient();
		double lat = 48.84;
		double lon = 2.35;
		try {
			String weather = mt.getMeteo(lat, lon, 1);
			String address = mp.getAddress(lat, lon);
			System.out.println("Here at: "+address+", it's " + weather);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
