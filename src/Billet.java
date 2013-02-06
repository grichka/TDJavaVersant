import java.util.Formatter;

public class Billet {
	private String code;

	// Could be null for an anynomous passenger
	private Passager passager;

	// Could not be null for an random trip
	private Trajet trajet;

	// Prix de vente du billet
	// qui peut différer du prix du trajet qui n'est pas fixe
	private double prix;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Billet ");
		if (code != null) {
			builder.append(code);
			builder.append(", ");
		}
		if (passager != null) {
			builder.append(passager);
			builder.append(", ");
		};
		Formatter f = new Formatter(builder);
		f.format("%.2f €\n\t", prix);
		
		if (trajet != null) {
			builder.append(trajet);
		}
		return builder.toString();
	}
}
