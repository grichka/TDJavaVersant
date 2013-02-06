import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;


public class DB4ODatabase implements IDatabase {

	protected ObjectContainer db;
	
	@Override
	public void open() {
		db = Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "database.db4o");
	}

	@Override
	public void persist(Object o) {
		db.store(o);
	}

	@Override
	public void commit() {
		db.commit();
	}

	@Override
	public void rollback() {
		db.rollback();
	}

	@Override
	public void close() {
		db.close();
	}

	@Override
	public void begin() {
		//rien
	}

	@Override
	public void delete(Object o) {
		db.delete(o);
	}
}
