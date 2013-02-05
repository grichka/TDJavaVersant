import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * 
 */
public class DBOperations {
	private Properties p;
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	
	public DBOperations() throws IOException {
		this.p = loadProperties();
		this.pmf = JDOHelper.getPersistenceManagerFactory(p);
		this.pm = pmf.getPersistenceManager();
	}
	public void close() {
		this.pm.close();
		this.pmf.close();
	}
	
	public void begin() {
		pm.currentTransaction().begin();
	}
	
	/**
	 * Fait persister un objet en base.
	 * @param b
	 */
	public void persist(Object o) {
		pm.currentTransaction().begin();
        pm.makePersistent(o);
        pm.currentTransaction().commit();
	}
	/**
	 * Renvoie le nombre total de billets
	 * @return
	 */
	public int getNbBillets() {
		Query q = pm.newQuery(Billet.class);
		pm.currentTransaction().begin();
		
        pm.currentTransaction().commit();
		return 0;
	}
	/**
	 * Renvoie la liste de tous les billets
	 * @return
	 */
	public List<Billet> listerBillets() {
		List<Billet> l = new ArrayList<Billet>();
		
		return l;
	}
	/**
	 * Renvoie le billet correspondant au code passé en paramètre ou null.
	 * @param code
	 * @return
	 */
	public Billet getBillet(String code) {
		Billet b = null;
		
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
	/**
	 * Charge le fichier versant.properties, afin d'initialiser le persistence manager.
	 * @return
	 * @throws IOException
	 */
	private static Properties loadProperties() throws IOException {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = DBOperations.class.getResourceAsStream("/versant.properties");
            if (in == null) {
                throw new IOException("versant.properties not on classpath");
            }
            p.load(in);
        } finally {
            if (in != null) in.close();
        }
        return p;
    }
}