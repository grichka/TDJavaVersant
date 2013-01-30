
public class Ville {
	private String nom;
	
	private double lat;
	private double lon;

	public Ville() {
		this.nom = null;
		
		// Heart island
		this.lat = 43.978487;
		this.lon = 15.383574;
	}
	
	public Ville(String nom) {
		this();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public void setLatLon(double lat, double lon) {
		setLat(lat);
		setLon(lon);
	}
	
	/**
	 * Distance entre deux points de coordonées géographiques.
	 * 
	 * Il s'agit de formules adaptées à partir du logiciel libre JOSM, un
	 * éditeur de cartes OpenStreetMap en Java.
	 * 
	 * La méthode utilisée est celle de Haversine.
	 * 
	 * @return Distance en mètres.
	 */
	public double distanceTo(Ville otherVille) {
		double R = 6378137;
		double sinHalfLat = Math.sin(Math.toRadians(otherVille.lat - this.lat) / 2);
		double sinHalfLon = Math.sin(Math.toRadians(otherVille.lon - this.lon) / 2);
		double d = 2
				* R
				* Math.asin(Math.sqrt(sinHalfLat * sinHalfLat
						+ Math.cos(Math.toRadians(this.lat))
						* Math.cos(Math.toRadians(this.lon)) * sinHalfLon
						* sinHalfLon));

		// For points opposite to each other on the sphere,
		// rounding errors could make the argument of asin greater than 1
		// (This should almost never happen.)
		if (java.lang.Double.isNaN(d)) {
			System.err.println("Error: NaN in distanceTo");
			d = Math.PI * R;
		}
		return d;
	}
}
