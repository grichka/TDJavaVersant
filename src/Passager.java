import java.util.Date;

public class Passager {
	// Identifiant
	private String numSecu;

	private String nom;
	private String prenom;
	private String adresse;
	private String mensurations;
	private Date dateNaissance;
	private String religion;
	private String orientationSexuelle;
	private int qi;
	private String metier;
	private double salaireAnnuelBrut;
	private boolean serviceMilitaire;
	private int nbPartenaires;
	private int nbEnfants;
	private double poids;
	private double taille;
	private String sex;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumSecu() {
		return numSecu;
	}

	public void setNumSecu(String numSecu) {
		this.numSecu = numSecu;
	}

	public String getMensurations() {
		return mensurations;
	}

	public void setMensurations(String mensurations) {
		this.mensurations = mensurations;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getOrientationSexuelle() {
		return orientationSexuelle;
	}

	public void setOrientationSexuelle(String orientationSexuelle) {
		this.orientationSexuelle = orientationSexuelle;
	}

	public int getQi() {
		return qi;
	}

	public void setQi(int qi) {
		this.qi = qi;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	public double getSalaireAnnuelBrut() {
		return salaireAnnuelBrut;
	}

	public void setSalaireAnnuelBrut(double salaireAnnuelBrut) {
		this.salaireAnnuelBrut = salaireAnnuelBrut;
	}

	public boolean isServiceMilitaire() {
		return serviceMilitaire;
	}

	public void setServiceMilitaire(boolean serviceMilitaire) {
		this.serviceMilitaire = serviceMilitaire;
	}

	public int getNbPartenaires() {
		return nbPartenaires;
	}

	public void setNbPartenaires(int nbPartenaires) {
		this.nbPartenaires = nbPartenaires;
	}

	public int getNbEnfants() {
		return nbEnfants;
	}

	public void setNbEnfants(int nbEnfants) {
		this.nbEnfants = nbEnfants;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		if (numSecu != null) {
			builder.append(numSecu);
			builder.append(" — ");
		}
		if (nom != null) {
			builder.append(nom);
			builder.append(" ");
		}
		if (prenom != null) {
			builder.append(prenom);
		}
		return builder.toString();
	}
}
