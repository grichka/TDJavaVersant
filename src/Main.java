
public class Main {

	public static void main(String[] args) {
		
		DBOperations db = new DBDB40Operations();
		//DBOperations db = new DBVersantOperations();
		db.open();
		
		Console c = new Console(db);
		c.start();
		
		db.close();
	}

}
