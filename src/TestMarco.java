
public class TestMarco {

	public static void main(String[] args) {
		WeatherApiClient mt = new WeatherApiClient();
		MapApiClient mp = new MapApiClient();
		double lat = 48.8464111;
		double lon = 2.3548468;
		try {
			String weather = mt.getWeather(lat, lon, 1);
			String address = mp.getAddress(lat, lon);
			System.out.println("Here at: "+address+", it's " + weather);
			
			System.out.println( "Nearest station : " +
					TransporationFinder.getInstance().getNearest(lat, lon)
			);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
