public class RzymArab 
{
	private static String[] liczbyRzym = { "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I" };
	
	private static int[] liczbyArab = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	
	public static int rzym2arab(String rzym) throws RzymArabException 
	{
		rzym = rzym.toUpperCase();
		
		if( rzym.length() == 0 ) throw new RzymArabException("\tBLAD! Wprowadz poprawna liczbe");
		
		int arab = 0;
		
		int indeksZnaku = 0;
		
		for( int i = 0; i < liczbyRzym.length; i++ )
		{
			while( rzym.startsWith(liczbyRzym[i], indeksZnaku) )
			{
				arab += liczbyArab[i];
				indeksZnaku += liczbyRzym[i].length();
			}
		}	
		return arab;
	}
	
	public static String arab2rzym(int arab) throws RzymArabException 
	{
		if( arab < 1 || arab > 3999 ) throw new RzymArabException("\tBLAD! Wprowadz poprawna liczbe");
		
		String rzym = "";
		
		for( int i = 0; i < liczbyArab.length; i++ )
		{
			while( arab >= liczbyArab[i] )
			{
				rzym += liczbyRzym[i];
				arab -= liczbyArab[i];
			}
		}
		return rzym;
	}
	
	public static boolean czyInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		} catch( NumberFormatException e ) {
			return false;
		}
		return true;
	}
	
	public static void main (String[] args)
	{
		for( int i = 0; i < args.length; i++ )
		{
			if( czyInteger(args[i]) == true )
			{
				try
				{
					System.out.println( args[i] + "\t-> " + arab2rzym(Integer.parseInt(args[i])) );
				} catch( RzymArabException a ){
					System.out.println( args[i] + a.getMessage() );
				}
			} else{
				try
				{
					if( !(args[i].toUpperCase()).equals(arab2rzym(rzym2arab(args[i]))) ) throw new RzymArabException("\tBLAD! Wprowadz poprawna liczbe");
					System.out.println( args[i] + "\t-> " + rzym2arab(args[i]) );
				} catch( RzymArabException r ){
					System.out.println( args[i] + r.getMessage() );
				}
			}
		}
	}
}