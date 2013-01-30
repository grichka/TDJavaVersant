public class Billet {
	// Could be null for an anynomous passenger
	private Passager passager;

	// Could not be null for an random trip
	private Trajet trajet;
	
	// Prix de vente du billet
	// qui peut différer du prix du trajet qui n'est pas fixe
	private double prix;

	public Passager getPassager() {
		return passager;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}

	public Trajet getTrajet() {
		return trajet;
	}

	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}
