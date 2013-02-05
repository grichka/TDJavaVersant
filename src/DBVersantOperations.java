import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * 
 */
public class DBVersantOperations extends VersantDatabase {
	public DBVersantOperations() {
		super();
		open();
	}
	/**
	 * Renvoie le nombre total de billets
	 * @return
	 */
	public int getNbBillets() {
		Query q = pm.newQuery(Billet.class);
		Collection c = (Collection) q.execute();
		return c == null ? 0 : c.size();
	}
	
	/**
	 * Renvoie la liste de tous les billets
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Billet> getBillets() {
		List<Billet> l = new ArrayList<Billet>();
		Query q = pm.newQuery(Billet.class);
		Collection c = (Collection) q.execute();
		if(c != null) {
			l = new ArrayList<Billet>(c);
		}
		return l;
	}
	
	/**
	 * Renvoie le billet correspondant au code passé en paramètre ou null.
	 * @param code
	 * @return
	 */
	public Billet getBillet(String code) {
		Billet b = null;
		Query q = pm.newQuery(Billet.class);
		q.setUnique(true);
		return b;
	}
	
	/**
	 * Met à jour un billet en base.
	 * @param code
	 * @param trajet
	 * @param p
	 */
	public void updateBillet(String code, Trajet trajet, Passager passager) {
		Billet b = getBillet(code);
		b.setTrajet(trajet);
		b.setPassager(passager);
	}
}