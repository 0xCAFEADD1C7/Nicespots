package org.utils;


import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class TransporationFinder {
	private static final TransporationFinder instance = new TransporationFinder();

	public static TransporationFinder getInstance() {
		return instance;
	}

	private Map<String, Point2D.Double> transportsLoc;

	private TransporationFinder() {
		try {
			init();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Init the TransporationFinder instance at the beginning of the program
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void init() throws NumberFormatException, IOException {
		String filepath = "/data/projets_arrets_idf.csv";
		
		InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(filepath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		
	
		transportsLoc = new HashMap<String, Point2D.Double>();

		String sCurrentLine = reader.readLine();
		while ((sCurrentLine = reader.readLine()) != null) {
			String[] tab = sCurrentLine.split(";");
			String[] pointtab = tab[tab.length-1].split(",");
			double x = Double.parseDouble(pointtab[0]);
			double y = Double.parseDouble(pointtab[1]);
			Point2D.Double pt = new Point2D.Double(x, y);
			transportsLoc.put(tab[1], pt);
		}
		
		
	}

	/**
	 * Return the nearest station for a position
	 * @param x
	 * @param y
	 * @return
	 */
	public String getNearest(Double x, Double y) {
		return getNearest(new Point2D.Double(x, y));
	}
	
	public String getNearest(Point2D.Double loc) {
		Point2D.Double pt = transportsLoc.values().iterator().next();
		String res = transportsLoc.keySet().iterator().next();
		Double minDist = loc.distance(pt);
		
		for(final Entry<String, java.awt.geom.Point2D.Double> entry : transportsLoc.entrySet()) {
		    final String key =  entry.getKey();
		    final Point2D.Double value =  entry.getValue();

		    Double tmp = loc.distance(value);
		    if (minDist > tmp) {
		    	minDist = tmp;
		    	res = key;
		    }
		}
		
		return res;
	}
}