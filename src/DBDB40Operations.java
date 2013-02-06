import java.util.List;

import com.db4o.ObjectSet;


public class DBDB40Operations extends DB4ODatabase implements DBOperations {

	@Override
	public Billet getBillet(String code) {
		Billet proto = new Billet();
		proto.setCode(code);
		ObjectSet<Billet> result = db.queryByExample(proto);
		return result.listIterator().next();
	}

	@Override
	public Gare getGare(String code) {
		Gare proto = new Gare();
		proto.setCode(code);
		proto.setLatLon(0.0, 0.0);
		ObjectSet<Gare> result = db.queryByExample(proto);
		return result.listIterator().next();
	}

	@Override
	public Trajet getTrajet(String code) {
		Trajet proto = new Trajet();
		proto.setCode(code);
		ObjectSet<Trajet> result = db.queryByExample(proto);
		return result.listIterator().next();
	}

	@Override
	public Passager getPassager(String numSecu) {
		Passager proto = new Passager();
		proto.setNumSecu(numSecu);
		ObjectSet<Passager> result = db.queryByExample(proto);
		return result.listIterator().next();
	}

	@Override
	public Passager getPassagerByNom(String nom) {
		Passager proto = new Passager();
		proto.setNom(nom);
		ObjectSet<Passager> result = db.queryByExample(proto);
		return result.listIterator().next();
	}

	@Override
	public List<Billet> getBillets() {
		Billet proto = new Billet();
		return db.queryByExample(proto);
	}

	@Override
	public List<Gare> getGares() {
		Gare proto = new Gare();
		proto.setCode(null);
		proto.setLatLon(0.0, 0.0);
		return db.queryByExample(proto);
	}

	@Override
	public List<Trajet> getTrajets() {
		Trajet proto = new Trajet();
		return db.queryByExample(proto);
	}

	@Override
	public List<Passager> getPassagers() {
		Passager proto = new Passager();
		return db.queryByExample(proto);
	}

	@Override
	public void removeBillet(String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBillet(Billet billet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeGare(String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeGare(Gare gare) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTrajet(String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTrajet(Trajet trajet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePassager(String numSecu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePassager(Passager passager) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNbBillets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Trajet> getTrajetsFromGare(Gare gare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trajet> getTrajetsToGare(Gare gare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gare> getDestinations(Gare gare, Double distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trajet> getTrajets(Passager passager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trajet> getTrajets(Passager passager1, Passager passager2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passager> searchPassager(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gare> searchGare(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Double> getPrixBillets() {
		// TODO Auto-generated method stub
		return null;
	}

}
