public class Test {
	public static void main(String[] args) {
		WierszTrojkataPascala WTP;
		try {
			WTP = new WierszTrojkataPascala(Integer.parseInt(args[0]));
		} catch (NegativeArraySizeException ex) {
			System.out.println(args[0] + "\t- nieprawidlowy numer wiersza (musi byc dodatni)");
			return;
		} catch (NumberFormatException ex) {
			System.out.println(args[0] + "\t- nieprawidlowy numer wiersza (musi byc liczba calkowita)");
			return;
		}

		for (int i = 1; i < args.length; i++) {
			try {
				System.out.println(args[i] + "\t-> " + WTP.wspolczynnik(Integer.parseInt(args[i])));
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println(args[i] + "\t-  liczba spoza zakresu");
			} catch (NumberFormatException ex) {
				System.out.println(args[i] + "\t-  nieprawidlowa dana");
			}
		}
	}	    
}
