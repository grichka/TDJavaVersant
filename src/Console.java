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
	
	// TODO
	public List<Gare> gares;

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
			System.out.print(" - ");
			System.out.println(gares.get(i));
		}
	}
	
	@Aide("Liste des billets")
	public void billets() {
		

	}

}
