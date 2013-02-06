
public class Main2 {
	public static void main(String[] args) {
		DBVersantOperations vo = new DBVersantOperations();
		for(Double d : vo.getPrixBillets()) {
			System.out.println(d);
		}
	}
}
