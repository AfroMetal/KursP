public class RozkladLiczbyTest {
	public static void main(String[] args) {
		
		RozkladLiczby RL;
		
		int max = 0;
		
		for( int i = 0; i < args.length; i++ )
		{
			try{
				max = Integer.parseInt(args[i]);
				break;
			} catch ( NumberFormatException ex ){
				continue;
			}
		}
		
		for( int i = 0; i < args.length; i++ )
		{
			try{
				Integer.parseInt(args[i]);
			} catch ( NumberFormatException ex ) {
				continue;
			}
			if( max < Integer.parseInt(args[i]) )
			{
				max = Integer.parseInt(args[i]);
			}
		}
		
		try{
			RL = new RozkladLiczby(max);
		} catch ( OutOfMemoryError ex ){
			System.out.println( max + "\t BLAD! za duza liczba - nie utworzono sita" );
			return;
		} catch ( NegativeArraySizeException ex ){
			System.out.println( "BLAD! podaj przynajmniej jeden poprawny argument (liczba calkowita >=2)" );
			return;
		}
		
		int aktualnyArgument = 0;
		
		for( int i = 0; i < args.length; i++ )
		{
			try{
				if( (aktualnyArgument = Integer.parseInt(args[i])) < 2 )
				{
					throw new Exception();
				}
			} catch( NumberFormatException ex ){
				System.out.println( args[i] + "\t BLAD! argument musi byc liczba calkowita" );
				continue;
			} catch( Exception ex ){
				System.out.println( args[i] + "\t BLAD! liczba musi byc wieksza lub rowna 2" );
				continue;
			}
			int[] cP = RL.czynnikiPierwsze(aktualnyArgument);
			System.out.print( args[i] + "\t = " );
			
			for( int j = 0; j < cP.length-1; j++ )
			{
				System.out.print( cP[j] + " * " );
			}
			System.out.print( cP[cP.length-1] + "\n" );
		}
	}
}