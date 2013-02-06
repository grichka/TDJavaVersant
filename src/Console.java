import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class Console {

	private DBOperations db;
	private Scanner scanner;

	// Sessions var
	private Billet billet;
	private Gare gare;
	private Passager passager;
	private Trajet trajet;

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
					System.out.print("Billet " + billet.getCode());
				else if (passager != null)
					System.out.print("Passager " + passager.getNumSecu());
				else if (trajet != null)
					System.out.print("Trajet " + trajet.getCode());
				else if (gare != null)
					System.out.print(gare.getNom());
				System.out.print("> ");
			}
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

	@Aide("Sélectionne un billet")
	public void billet() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		billet = db.getBillet(code);
		gare = null;
		trajet = null;
		passager = null;

		if (billet == null)
			System.err.println("Aucun billet ne correspond au numéro");
	}

	@Aide("Liste des billets")
	public void billets() {
		List<Billet> billets;
		// Liste des billets d'un passager
		if (passager != null) {
			billets = db.getBilletsOfPassager(passager);
			// Ou du trajet
		} else if (trajet != null) {
			billets = db.getBilletsOfTrajet(trajet);
			// Ou de tout les billets
		} else {
			billets = db.getBillets();
		}

		for (Billet billet : billets) {
			System.out.println(billet);
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

	@Aide("Liste des départs")
	public void departs() {
		for (Trajet trajet : db.getTrajetsFromGare(gare)) {
			System.out.print(trajet.getCode());
			System.out.print(" — ");
			System.out.println(trajet);
			System.out.printf("\t%.2f €\n", trajet.prixActuel());
		}
	}

	@Aide("Liste des destinations")
	public void destinations() {
		if (gare != null) {
			System.out.println("Distance maximale (en km) ?");
			double distance = Double.parseDouble(scanner.nextLine().trim());

			for (Gare g : db.getDestinations(gare, distance * 1000.0)) {
				System.out.print(g);
				System.out.printf(" — %.0f km\n", g.distanceTo(gare) / 1000.0);
			}
		} else {
			System.out
					.println("Vous devez sélectionner une gare pour utiliser cette fonctionnalité");
		}
	}

	@Aide("Ferme l'application")
	public void exit() {
		db.commit();
		db.close();
		System.exit(0);
	}

	@Aide("Sélectionne une gare")
	public void gare() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		gare = db.getGare(code);
		billet = null;
		trajet = null;
		passager = null;

		if (gare == null)
			System.err.println("Aucune gare ne correspond au numéro");
	}

	@Aide("Liste des gares")
	public void gares() {
		for (Gare gare : db.getGares()) {
			System.out.print(gare.getCode());
			System.out.print(" — ");
			System.out.println(gare);
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

			// Les méthodes enrichies avec JDO ne doivent pas être affichées
			if (name.indexOf("jdo") == -1) {

				Aide aide = m.getAnnotation(Aide.class);

				if (aide != null)
					System.out.println(m.getName() + " — " + aide.value());

			}
		}
	}

	@Aide("Affiche les informations sur le billet courant")
	public void ibillet() {
		if (billet != null)
			System.out.println(billet);
		else
			System.out.println("Aucun billet n'est sélectionné");
	}

	@Aide("Affichage du nombre de billets")
	public void nbbillets() {
		System.out.println(db.getNbBillets());
	}

	@Aide("Liste les prix des billets")
	public void prixbillets() {
		for (Double prix : db.getPrixBillets()) {
			System.out.printf("%.2f €\n", prix);
		}
	}

	@Aide("Réinitialise le contexte actuel")
	public void reset() {
		gare = null;
		trajet = null;
		passager = null;
		billet = null;
	}

	@Aide("Sélectionne un trajet")
	public void trajet() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		trajet = db.getTrajet(code);
		gare = null;
		passager = null;
		billet = null;

		if (trajet == null)
			System.err.println("Aucune trajet ne correspond au numéro");
	}

	@Aide("Nouveau billet")
	public void nbillet() {
		billet = new Billet();
		billet.setCode(Integer.toString(billet.hashCode(), 36).toUpperCase());
		cbillet();
	}

	@Aide("Change le billet courant")
	public void cbillet() {
		if (billet == null) {
			nbillet();
		}

		System.out.println("Code trajet ?");
		String codeTrajet = scanner.nextLine().trim().toUpperCase();

		Trajet t = db.getTrajet(codeTrajet);

		if (t == null) {
			System.out
					.println("Aucun trajet ne correspond au code que vous avez donné");
			cbillet();
		} else {
			billet.setTrajet(db.getTrajet(codeTrajet));
			System.out.println("Numéro passager (anonyme possible) ?");
			String numPassager = scanner.nextLine().trim().toUpperCase();
			billet.setPassager(db.getPassager(numPassager));

			billet.setPrix(t.prixActuel());
			db.persist(billet);

			ibillet();
		}
	}

	@Aide("Supprime le billet courant")
	public void dbillet() {
		if (billet != null) {
			db.delete(billet);
		} else {
			System.out.println("Vous devez sélectionner un billet à supprimer");
		}
	}

	@Aide("Sélectionne un passager")
	public void passager() {
		System.out.println("Code ?");
		String code = scanner.nextLine().trim().toUpperCase();

		passager = db.getPassager(code);
		gare = null;
		trajet = null;
		billet = null;

		if (passager == null)
			System.err.println("Aucune passager ne correspond au numéro");
	}

	@Aide("Rechercher des passagers")
	public void cpassagers() {
		System.out.println("Recherche: ");
		String recherche = scanner.nextLine().trim();

		for (Passager passager : db.searchPassager(recherche)) {
			System.out.println(passager);
		}
	}

	@Aide("Liste de passagers")
	public void passagers() {
		for (Passager passager : db.getPassagersOfTrajet(trajet)) {
			System.out.println(passager);
		}
	}

	@Aide("Trouver les trajets réalisés par deux personnes")
	public void ctrajets() {
		System.out.println("Code première personne ?");
		String codeA = scanner.nextLine().trim().toUpperCase();
		System.out.println("Code seconde personne ?");
		String codeB = scanner.nextLine().trim().toUpperCase();

		List<Trajet> trajets = db.getTrajets(db.getPassager(codeA),
				db.getPassager(codeB));

		if (trajets.size() > 0) {

			for (Trajet trajet : trajets) {
				System.out.println(trajet);
			}
		} else
			System.out
					.println("Ces deux personnes n'ont pas effectué de trajet en commun");
	}
}
