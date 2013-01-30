public class Billet {
	// Could be null for an anynomous passenger
	private Passager passager;

	// Could not be null for an random trip
	private Trajet trajet;

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
}
