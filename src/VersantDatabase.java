import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;


public class VersantDatabase implements IDatabase {

	protected PersistenceManager pm;
	
	@Override
	public void open() {
		/**
		 * Charge le fichier versant.properties, afin d'initialiser le persistence manager.
		 */
		Properties p = new Properties();
        InputStream in = null;
        try {
            in = InitialisationData.class.getResourceAsStream("/versant.properties");
            if (in == null) {
                throw new IOException("versant.properties not on classpath");
            }
            p.load(in);
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(p);
		
		pm = pmf.getPersistenceManager();
		//pm.currentTransaction().begin();
	}

	@Override
	public void persist(Object o) {
		pm.makePersistent(o);
	}

	@Override
	public void begin() {
		pm.currentTransaction().begin();
	}
	
	@Override
	public void commit() {
		pm.currentTransaction().commit();
	}

	@Override
	public void rollback() {
		pm.currentTransaction().rollback();
	}

	@Override
	public void close() {
		pm.close();
	}

	@Override
	public void delete(Object o) {
		pm.deletePersistent(o);
	}
}
