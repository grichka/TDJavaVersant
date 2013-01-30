
public class Trajet {
	private Ville depart;
	private Ville arrivee;
	private int nbPlaces;
	private int nbPlacesReservees;
	
	
	public Ville getDepart() {
		return depart;
	}
	public void setDepart(Ville depart) {
		this.depart = depart;
	}
	public Ville getArrivee() {
		return arrivee;
	}
	public void setArrivee(Ville arrivee) {
		this.arrivee = arrivee;
	}
	
	public double distance() {
		return depart.distanceTo(arrivee);
	}
	
	/**
	 * Calcul le prix du billet en fonction de la distance de trajet,
	 * le nombre de places réservées, et 
	 * 
	 * @return Le prix du trajet (oui monsieur)
	 */
	public double prixActuel() {
		return distance() * 0.42 * (0.7 + (nbPlacesReservees / (nbPlaces * 4.2)));
	}
}
