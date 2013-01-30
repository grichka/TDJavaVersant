import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InitialisationData {

	public static void main(String[] args) throws Exception {
		System.out.println("Initialisation des données");
		
		// Données opendata :
		// http://www.data.gouv.fr/donnees/view/Liste-des-gares-de-voyageurs-du-RFN-avec-coordonn%C3%A9es-30383099
		FileReader fichierGares = new FileReader("gares.csv");
		Scanner sc = new Scanner(fichierGares);
		List<Gare> gares = new ArrayList<>(); 
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] infosGare = line.split(",");
			
			Gare gare = new Gare();
			gare.setNom(infosGare[0] + " - " + infosGare[1]);
			gare.setLat(Double.valueOf(infosGare[2]));
			gare.setLon(Double.valueOf(infosGare[3]));
			gares.add(gare);
		}
		
		System.out.print(gares.size());
		System.out.println(" gares chargées.");
		
	}
}
