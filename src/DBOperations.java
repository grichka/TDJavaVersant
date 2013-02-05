import java.util.List;


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
	
	public void removeBillet(String code);
	public void removeGare(String code);
	public void removeTrajet(String code);
	public void removePassager(String numSecu);
	
	//requetes plus complexes
	public List<Trajet> getTrajetsFromGare(Gare gare);
	//FIXME getprixbillets ?
	public List<Gare> getDestinations(Gare gare, Double distance);
	public List<Trajet> getTrajets(Passager passager);
	public List<Trajet> getTrajets(Passager passager1, Passager passager2);
}
