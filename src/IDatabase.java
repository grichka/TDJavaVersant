
public interface IDatabase {

	public void open();
	public void persist(Object o);
	public void begin();
	public void commit();
	public void rollback();
	public void close();
}
