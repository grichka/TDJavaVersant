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
	private Gare gare;
	
	// TODO
	public List<Gare> gares;
	public List<Billet> billets;
	public List<Trajet> trajets;

	public void start() {
		scanner = new Scanner(System.in);

		System.out.println("Tapez help pour obtenir de l'aide.");

		System.out.print("> ");
		while (scanner.hasNext()) {

			String methodName = scanner.nextLine().trim();

			try {
				Method m = getClass().getDeclaredMethod(methodName);

				// TODO transaction begin
				m.invoke(this);
				// TODO transaction end

			} catch (NoSuchMethodException e) {
				System.err.println("La commande " + methodName
						+ " est inconnue. Tapez help pour obtenir de l'aide.");
			} catch (SecurityException e) {
				System.err.println("Vous ne pouvez pas appelez cette méthode");
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				System.err
						.println("Une erreur est survenue lors de l'appel de la méthode");
				e.printStackTrace();
			}

			if (gare != null)
				System.out.print(gare.getNom());
			System.out.print("> ");
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
		// TODO close
//		tx.commit();
//		pm.close();
		System.exit(0);
	}

	@Aide("Liste des gares")
	public void gares() {
		for (int i = 0; i < gares.size(); ++i) {
			System.out.print(i);
			System.out.print(" — ");
			System.out.println(gares.get(i));
		}
	}
	
	@Aide("Rechercher des gares")
	public void cgares()
	{
		System.out.println("Recherche: ");
		String recherche = scanner.nextLine().trim();
		
		/* L'algorithme n'est pas très optimisé parce que la recherche
		 * est réalisée en O(n).
		 * Mais étant donné qu'il n'y a que 3000 gares, ce n'est pas très
		 * grave.
		 */
		for (int i = 0; i < gares.size(); ++i) {
			String gs = gares.get(i).toString();
			if (gs.toLowerCase().indexOf(recherche.toLowerCase()) != -1) {
				System.out.print(i);
				System.out.print(" — ");
				System.out.println(gares.get(i));				
			}
		}
	}
	
	@Aide("Sélectionne une gare")
	public void gare()
	{
		System.out.println("Numéro ?");
		int num = Integer.parseInt(scanner.nextLine().trim());
		
		if (num >= 0 && num < gares.size())
			gare = gares.get(num);
		else
			System.err.println("Aucune gare ne correspond au numéro");
	}
	
	
	@Aide("Liste des billets")
	public void billets() {
		

	}
	
	@Aide("Liste des départs à partir d'une gare")
	public void departs()
	{
		// TODO changer ça
		ArrayList<Trajet> trajets = new ArrayList<>();
		
		for (Trajet t : this.trajets)
			if (t.getDepart().equals(gare))
				System.out.println(t);
	}

}
