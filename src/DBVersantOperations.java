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
		q.setFilter("nom.toLowerCase().matches(\".*" + nom.toLowerCase() + ".*\")");
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
		q.setFilter("nom.toLowerCase().matches(\".*" + nom.toLowerCase() + ".*\")");
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
		if(gare == null) {
			return getTrajets();
		} else {
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajetsToGare(Gare gare) {
		if(gare == null) {
			return getTrajets();
		} else {
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
		if(passager == null) {
			return getTrajets();
		} else {
			List<Trajet> l = new ArrayList<Trajet>();
			Query q = pm.newQuery(Billet.class);
			q.declareParameters("Passager passager");
			q.setFilter("passager == passager");
			q.setResult("trajet");
			Collection<Trajet> c = (Collection<Trajet>) q.execute(passager);
			if(c != null) {
				l = new ArrayList<Trajet>(c);
			}
			return l;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trajet> getTrajets(Passager passager1, Passager passager2) {
		List<Trajet> l1 = new ArrayList<Trajet>();
		Query q = pm.newQuery(Billet.class);
		q.declareParameters("Passager passager1");
		q.setFilter("passager == passager1");
		q.setResult("trajet");
		Collection<Trajet> c1 = (Collection<Trajet>) q.execute(passager1);
		if(c1 != null) {
			l1 = new ArrayList<Trajet>(c1);
		}
		
		List<Trajet> l2 = new ArrayList<Trajet>();
		Query q2 = pm.newQuery(Billet.class);
		q2.declareParameters("Passager passager2");
		q2.setFilter("passager == passager2");
		q2.setResult("trajet");
		Collection<Trajet> c2 = (Collection<Trajet>) q2.execute(passager2);
		if(c2 != null) {
			l2 = new ArrayList<Trajet>(c2);
		}
		
		l1.retainAll(l2);
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passager> getPassagersOfTrajet(Trajet trajet) {
		if(trajet == null) {
			return getPassagers();
		} else {
			List<Passager> l = new ArrayList<Passager>();
			Query q = pm.newQuery(Billet.class);
			q.declareParameters("Trajet t");
			q.setFilter("trajet == t");
			q.setResult("passager");
			Collection<Passager> c = (Collection<Passager>) q.execute(trajet);
			if(c != null) {
				l = new ArrayList<Passager>(c);
			}
			return l;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Billet> getBilletsOfTrajet(Trajet trajet) {
		if(trajet == null) {
			return getBillets();
		} else {
			List<Billet> l = new ArrayList<Billet>();
			Query q = pm.newQuery(Billet.class);
			q.declareParameters("Trajet t");
			q.setFilter("trajet == t");
			Collection<Billet> c = (Collection<Billet>) q.execute(trajet);
			if(c != null) {
				l = new ArrayList<Billet>(c);
			}
			return l;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Billet> getBilletsOfPassager(Passager passager) {
		if(passager == null) {
			return getBillets();
		} else {
			List<Billet> l = new ArrayList<Billet>();
			Query q = pm.newQuery(Billet.class);
			q.declareParameters("Passager p");
			q.setFilter("passager == p");
			Collection<Billet> c = (Collection<Billet>) q.execute(passager);
			if(c != null) {
				l = new ArrayList<Billet>(c);
			}
			return l;
		}
	}
}