public class Test
{
  public static void main(String[] args) throws TrojkatPascalaException
  {
    TrojkatPascala TP;
    try
    {
      int rozmiar = Integer.parseInt(args[0]);
      TP = new TrojkatPascala(rozmiar);
      
      for ( int i = 1; i < args.length - 1; i+=2 )
      {
        try
        {
          System.out.println(args[i] + " po " + args[i+1] + "\t-> " + TP.wspolczynnik(Integer.parseInt(args[i]), Integer.parseInt(args[i+1])));
        } catch( TrojkatPascalaException w) {
          System.out.println( args[i] + " po " + args[i+1] + w.getMessage() );
          continue;
        } catch( NumberFormatException w ) {
          System.out.println( args[i] + " po " + args[i+1] + " - nieprawidlowe dane" );
          continue;
        }
      }
      
      if( args.length % 2 == 0 )
        {
          System.out.println( args[args.length-1] + " - za malo danych");
        }
        
    } catch( TrojkatPascalaException t ) {
      System.out.println( args[0] + t.getMessage() );
      System.exit(0);
    } catch( NumberFormatException t ) {
      System.out.println( args[0] + " - niepoprawny argument" );
      System.exit(0);
    } catch( ArrayIndexOutOfBoundsException w ) {
      System.out.println( "Podaj rozmiar trojkata (przynajmniej jeden argument)" );
      return;
    }
  }
}
