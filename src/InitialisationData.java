import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class InitialisationData {

	public static void main(String[] args) throws Exception {
		System.out.println("Initialisation des données");
		List<Gare> gares = new ArrayList<>();
		List<Trajet> trajets = new ArrayList<>();
		
		// Données opendata :
		// http://www.data.gouv.fr/donnees/view/Liste-des-gares-de-voyageurs-du-RFN-avec-coordonn%C3%A9es-30383099
		FileReader fichierGares = new FileReader("gares.csv");
		Scanner sc = new Scanner(fichierGares);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] infosGare = line.split(",");
			
			Gare gare = new Gare();
			gare.setNom(infosGare[0] + " - " + infosGare[1]);
			gare.setLat(Double.valueOf(infosGare[2]));
			gare.setLon(Double.valueOf(infosGare[3]));
			gares.add(gare);
		}
		
		int nbGares = gares.size();
		System.out.print(nbGares);
		System.out.println(" gares chargées.");
		Random r = new Random("canard".hashCode());
		
		for (int i = 0; i < 15000; ++i) {
			Trajet trajet = new Trajet();
			
			trajet.setDepart(gares.get(r.nextInt(nbGares)));
			trajet.setArrivee(gares.get(r.nextInt(nbGares)));
			
			// Génération aléatoire du nombre de places
			trajet.setNbPlaces(100 + r.nextInt(5)*100 + r.nextInt(5)*10);
			
			// Tout les trains partent maintenant
			trajet.setDateDepart(new Date());
			
			// Date d'arrivée en fonction de la distance
			trajet.setDateArrivee(new Date((new Date().getTime()) + ((long) trajet.distance() * 15)));
			
			trajets.add(trajet);
		}
		
		System.out.println("15000 trajets ajoutés");
		
		for (int i = 0; i < 10; ++i) {
			Trajet t = trajets.get(i);
			System.out.println(t);
			System.out.println(t.prixActuel());
		}
		
	}
}
