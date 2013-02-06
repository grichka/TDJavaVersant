import java.util.List;

/**
 * Oprations possibles en base pour notre application.
 */
public interface DBOperations {
	public Billet getBillet(String code);
	public Gare getGare(String code);
	public Trajet getTrajet(String code);
	public Passager getPassager(String numSecu);
	public Passager getPassagerByNom(String nom);
	
	public List<Billet> getBillets();
	public List<Gare> getGares();
	public List<Trajet> getTrajets();
	public List<Passager> getPassagers();
	
	public List<Passager> searchPassager(String nom);
	public List<Gare> searchGare(String nom);
	
	public void removeBillet(String code);
	public void removeBillet(Billet billet);
	public void removeGare(String code);
	public void removeGare(Gare gare);
	public void removeTrajet(String code);
	public void removeTrajet(Trajet trajet);
	public void removePassager(String numSecu);
	public void removePassager(Passager passager);
	
	//requetes plus complexes
	public int getNbBillets();
	public List<Trajet> getTrajetsFromGare(Gare gare);
	public List<Trajet> getTrajetsToGare(Gare gare);
	public List<Double> getPrixBillets();
	public List<Gare> getDestinations(Gare gare, Double distance);
	public List<Trajet> getTrajets(Passager passager);
	public List<Trajet> getTrajets(Passager passager1, Passager passager2);
}
