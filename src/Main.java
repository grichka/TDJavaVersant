import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class Main {

	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "database.db4o");
		System.out.println("Chargement des gares");
		Gare proto = new Gare();
		proto.setNom(null);
		proto.setLatLon(0.0, 0.0);
		ObjectSet<Gare> result = db.queryByExample(proto);
		System.out.println(result.size());
		
		for (Gare gare : result) {
			System.out.println(gare);
		}
		
		db.close();
	}

}
