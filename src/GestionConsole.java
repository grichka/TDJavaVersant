import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class GestionConsole {
	public static void main(String[] args) throws IOException {
		System.out.println("Bienvenue dans l'interface de gestion de réservation de billets de train");
		
		// load versant.properties project file as properties to connect
		Properties p = loadProperties();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(p);
		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.currentTransaction().begin();
		
		Gare gare = new Gare();
		gare.setNom("testGare");
		gare.setLat(0.0);
		gare.setLon(0.0);
		
		pm.makePersistent(gare);
		
		pm.currentTransaction().commit();
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
            in = GestionConsole.class.getResourceAsStream("/versant.properties");
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
