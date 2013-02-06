import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Console {

	private Scanner scanner;
	
	// Sessions var
	private Gare gare;
	private Trajet trajet;
	private Billet billet;
	private Passager passager;

	private DBOperations db;

	public Console(DBOperations db) {
		this.db = db;
	}

	public void start() {
		scanner = new Scanner(System.in);

		System.out.println("Tapez help pour obtenir de l'aide.");

		System.out.print("> ");
		while (scanner.hasNext()) {

			String methodName = scanner.nextLine().trim();

			if (methodName.length() > 0) {

				try {
					Method m = getClass().getDeclaredMethod(methodName);

					db.begin();
					m.invoke(this);
					db.commit();

				} catch (NoSuchMethodException e) {
					System.err
							.println("La commande "
									+ methodName
									+ " est inconnue. Tapez help pour obtenir de l'aide.");
				} catch (SecurityException e) {
					System.err
							.println("Vous ne pouvez pas appelez cette méthode");
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.err
							.println("Une erreur est survenue lors de l'appel de la méthode");
					e.printStackTrace();
					db.rollback();
				}
				if (billet != null)
					System.out.println("Billet "+billet.getCode());
				else if (passager != null)
					System.out.println("Passager "+billet.getCode());
				else if (trajet != null)
					System.out.print("Trajet " + trajet.getCode());
				else if (gare != null)
					System.out.print(gare.getNom());
				System.out.print("> ");
			}
		}

	}

	@Aide("Affiche l'aide")
	public void help() {
		System.out
				.println("Saisissez des commandes pour utiliser l'application.");
		System.out.println("Commandes disponibles: ");
		Method[] methods = getClass().getDeclaredMethods();
		for (Method m : methods) {
			String name = m.getName();

			if (name.indexOf("jdo") == -1) {

				Aide aide = m.getAnnotation(Aide.class);

				if (aide != null)
					System.out.println(m.getName() + " — " + aide.value());

			}
		}
	}

	@Aide("Ferme l'application")
	public void exit() {
		db.close();
		System.exit(0);
	}
	
	@Aide("Réinitialise le contexte actuel")
	public void reset() {
		gare = null;
		trajet = null;
	}

	@Aide("Liste des gares")
	public void gares() {
		for (Gare gare : db.getGares()) {
			System.out.print(gare.getCode());
			System.out.print(" — ");
			System.out.println(gare);
		}
	}

	@Aide("Rechercher des gares")
	public void cgares() {
		System.out.println("Recherche: ");
		String recherche = scanner.nextLine().trim();

		for (Gare gare : db.searchGare(recherche)) {
			System.out.print(gare.getCode());
			System.out.print(" — ");
			System.out.println(gare);
		}
	}
	
	@Aide("Liste de passagers")
	public void passagers() {
		for (Passager passager : db.getPassagersOfTrajet(trajet)) {
			System.out.println(passager);
		}
	}
	
	@Aide("Rechercher des passagers")
	public void cpassagers() {
		System.out.println("Recherche: ");
		String recherche = scanner.nextLine().trim();
		
		for (Passager passager : db.searchPassager(recherche)) {
			System.out.println(passager);
		}
	}

	@Aide("Sélectionne une gare")
	public void gare() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		gare = db.getGare(code);

		if (gare == null)
			System.err.println("Aucune gare ne correspond au numéro");
	}
	
	@Aide("Déselectionne la gare")
	public void fgare() {
		gare = null;
	}

	@Aide("Liste des billets")
	public void billets() {
		for (Billet billet : db.getBilletsOfTrajet(trajet)) {
			System.out.println(billet);
		}
	}

	@Aide("Affichage du nombre de billets")
	public void nbbillets() {
		System.out.println(db.getNbBillets());
	}
	
	@Aide("Sélectionne un trajet")
	public void trajet() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		trajet = db.getTrajet(code);

		if (trajet == null)
			System.err.println("Aucune trajet ne correspond au numéro");
	}
	
	@Aide("Déselectionne un trajet")
	public void ftrajet() {
		trajet = null;
	}
	
	@Aide("Liste des destinations")
	public void destinations() {
		if (gare != null) {
			System.out.println("Distance maximale (en km) ?");
			double distance = Double.parseDouble(scanner.nextLine().trim());
			
			for (Gare g : db.getDestinations(gare, distance*1000.0)) {
				System.out.print(g);
				System.out.printf(" — %.0f km\n", g.distanceTo(gare)/1000.0);
			}
		} else {
			System.out.println("Vous devez sélectionner une gare pour utiliser cette fonctionnalité");
		}
	}
	
	@Aide("Liste des départs")
	public void departs() {
		for (Trajet trajet : db.getTrajetsFromGare(gare)) {
			System.out.print(trajet.getCode());
			System.out.print(" — ");
			System.out.println(trajet);
			System.out.printf("\t%.2f €\n", trajet.prixActuel());
		}
	}

	@Aide("Liste des arrivées")
	public void arrivees() {
		for (Trajet trajet : db.getTrajetsToGare(gare)) {
			System.out.print(trajet.getCode());
			System.out.print(" — ");
			System.out.println(trajet);

			System.out.printf("\t%.2f €\n", trajet.prixActuel());
		}
	}
	
	@Aide("Liste les prix des billets")
	public void prixbillets() {
		for (Double prix : db.getPrixBillets()) {
			System.out.printf("%.2f €\n", prix);
		}
	}

}
