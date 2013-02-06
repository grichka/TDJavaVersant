
public class Main2 {
	public static void main(String[] args) {
		DBVersantOperations vo = new DBVersantOperations();
		System.out.println(">>> Test <<<");
		for(Passager p : vo.searchPassager("rard")) {
			System.out.println(p);
		}
		//System.out.println(vo.getNbBillets());
	}
}