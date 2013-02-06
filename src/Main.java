
public class Main {

	public static void main(String[] args) {
		DBOperations db;
		if(args.length > 0 && "db4o".equals(args[0])) {
			db = new DBDB40Operations();
		} else {
			db = new DBVersantOperations();
		}
		
		db.open();
		
		Console c = new Console(db);
		c.start();
		
		db.close();
	}

}
