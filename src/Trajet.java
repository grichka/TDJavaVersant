import java.util.Date;

public class Trajet {
	private String code;
	private Gare depart;
	private Gare arrivee;
	private int nbPlaces;
	private int nbPlacesReservees;
	private Date dateDepart;
	private Date dateArrivee;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Gare getDepart() {
		return depart;
	}

	public void setDepart(Gare depart) {
		this.depart = depart;
	}

	public Gare getArrivee() {
		return arrivee;
	}

	public void setArrivee(Gare arrivee) {
		this.arrivee = arrivee;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public int getNbPlacesReservees() {
		return nbPlacesReservees;
	}

	public void setNbPlacesReservees(int nbPlacesReservees) {
		this.nbPlacesReservees = nbPlacesReservees;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public long duree() {
		return dateArrivee.getTime() - dateDepart.getTime();
	}

	public double distance() {
		return depart.distanceTo(arrivee);
	}

	/**
	 * Calcul le prix du billet en fonction de la distance de trajet, le nombre
	 * de places réservées, et
	 * 
	 * @return Le prix du trajet (oui monsieur)
	 */
	public double prixActuel() {
		return distance() * 0.00042
				* (0.7 + (nbPlacesReservees / (nbPlaces * 4.2)));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (depart != null) {
			builder.append(depart);
			builder.append(" — ");
		}
		if (arrivee != null) {
			builder.append(arrivee);
			builder.append("\n");
		}
		
		builder.append("\t");
		builder.append(nbPlacesReservees);
		builder.append(" / ");
		builder.append(nbPlaces);
		builder.append(" — ");
		
		if (dateDepart != null) {
			builder.append(dateDepart);
			builder.append(" — ");
		}
		if (dateArrivee != null) {
			builder.append(dateArrivee);
		}
		return builder.toString();
	}
}
