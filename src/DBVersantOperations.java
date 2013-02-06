import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.Query;

/**
 * Opérations possibles sur la base de données Versant.
 */
public class DBVersantOperations extends VersantDatabase implements DBOperations {
	public DBVersantOperations() {
		super();
		open();
	}
	
	@Override
	public Billet getBillet(String code) {
		Query q = pm.newQuery(Billet.class);
		q.setUnique(true);
		q.declareParameters("String c");
		q.setFilter("code == c");
		return (Billet) q.execute(code);
	}

	@Override
	public Gare getGare(String code) {
		Query q = pm.newQuery(Gare.class);
		q.setUnique(true);
		q.declareParameters("String c");
		q.setFilter("code == c");
		return (Gare) q.execute(code);
	}

	@Override
	public Trajet getTrajet(String code) {
		Query q = pm.newQuery(Trajet.class);
		q.setUnique(true);
		q.declareParameters("String c");
		q.setFilter("code == c");
		return (Trajet) q.execute(code);
	}

	@Override
	public Passager getPassager(String numSecu) {
		Query q = pm.newQuery(Passager.class);
		q.setUnique(true);
		q.declareParameters("String s");
		q.setFilter("numSecu == s");
		return (Passager) q.execute(numSecu);
	}

	@Override
	public Passager getPassagerByNom(String nom) {
		Query q = pm.newQuery(Passager.class);
		q.setUnique(true);
		q.declareParameters("String s");
		q.setFilter("nom == s");
		return (Passager) q.execute(nom);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passager> searchPassager(String nom) {
		List<Passager> l = new ArrayList<Passager>();
		Query q = pm.newQuery(Passager.class);
		//q.setFilter("nom.matches(nomp)");
		q.setFilter("nom.matches(\".*" + nom + ".*\")");
		Collection<Passager> c = (Collection<Passager>) q.execute();
		if(c != null) {
			l = new ArrayList<Passager>(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gare> searchGare(String nom) {
		List<Gare> l = new ArrayList<Gare>();
		Query q = pm.newQuery(Gare.class);
		//q.setFilter("nom.matches(nomp)");
		q.setFilter("nom.matches(\".*" + nom + ".*\")");
		Collection<Gare> c = (Collection<Gare>) q.execute();
		if(c != null) {
			l = new ArrayList<Gare>(c);
		}
		return l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Billet> getBillets() {
		List<Billet> l = new ArrayList<Billet>();
		Query q = pm.newQuery(Billet.class);
		Collection<Billet> c = (Collection<Billet>) q.execute();
		if(c != null) {
			l = new ArrayList<Billet>(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gare> getGares() {
		List<Gare> l = new ArrayList<Gare>();
		Query q = pm.newQuery(Gare.class);
		Collection<Gare> c = (Collection<Gare>) q.execute();
		if(c != null) {
			l = new ArrayList<Gare>(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajets() {
		List<Trajet> l = new ArrayList<Trajet>();
		Query q = pm.newQuery(Trajet.class);
		Collection<Trajet> c = (Collection<Trajet>) q.execute();
		if(c != null) {
			l = new ArrayList<Trajet>(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passager> getPassagers() {
		List<Passager> l = new ArrayList<Passager>();
		Query q = pm.newQuery(Passager.class);
		Collection<Passager> c = (Collection<Passager>) q.execute();
		if(c != null) {
			l = new ArrayList<Passager>(c);
		}
		return l;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getNbBillets() {
		Query q = pm.newQuery(Billet.class);
		q.declareParameters("String versantOptions");
		Collection c = (Collection) q.execute("countStarOnSize=true");
		return c == null ? 0 : c.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajetsFromGare(Gare gare) {
		List<Trajet> l = new ArrayList<Trajet>();
		Query q = pm.newQuery(Trajet.class);
		q.declareParameters("Gare gare");
		q.setFilter("depart == gare");
		Collection<Trajet> c = (Collection<Trajet>) q.execute(gare);
		if(c != null) {
			l = new ArrayList<Trajet>(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajetsToGare(Gare gare) {
		List<Trajet> l = new ArrayList<Trajet>();
		Query q = pm.newQuery(Trajet.class);
		q.declareParameters("Gare gare");
		q.setFilter("arrivee == gare");
		Collection<Trajet> c = (Collection<Trajet>) q.execute(gare);
		if(c != null) {
			l = new ArrayList<Trajet>(c);
		}
		return l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getPrixBillets() {
		List<Double> l = new ArrayList<Double>();
		Query q = pm.newQuery(Billet.class);
		q.setResult("prix");
		Collection<Double> c = (Collection<Double>) q.execute();
		if(c != null) {
			l = new ArrayList<Double>(c);
		}
		return l;
	}
	
	@Override
	public List<Gare> getDestinations(Gare gare, Double distance) {
		List<Gare> gares = new ArrayList<Gare>();
		for(Trajet t : getTrajetsFromGare(gare)) {
			Gare g = t.getArrivee();
			if(gare.distanceTo(g) <= distance) {
				gares.add(t.getArrivee());
			}
		}
		return gares;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajets(Passager passager) {
		List<Trajet> l = new ArrayList<Trajet>();
		Query q = pm.newQuery(Billet.class);
		q.declareParameters("Passager passager");
		q.setFilter("passager == passager");
		Collection<Billet> c = (Collection<Billet>) q.execute(passager);
		for(Billet b : c) {
			l.add(b.getTrajet());
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajets(Passager passager1, Passager passager2) {
		List<Trajet> l = new ArrayList<Trajet>();
		Query q = pm.newQuery(Billet.class);
		q.declareParameters("Passager passager1, Passager passager2");
		q.setFilter("passager == passager1 || passager == passager2");
		Collection<Billet> c = (Collection<Billet>) q.execute(passager1, passager2);
		for(Billet b : c) {
			l.add(b.getTrajet());
		}
		return l;
	}
}