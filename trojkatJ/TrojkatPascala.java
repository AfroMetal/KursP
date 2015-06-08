public class TrojkatPascala
{
    public int[][] wierszWyraz;
    public TrojkatPascala(int n) throws TrojkatPascalaException
    {
      if( n < 0 || n > 33 ) 
        throw new TrojkatPascalaException("BLAD! Nieprawidlowy rozmiar");
      
      wierszWyraz = new int[n+1][];
        
      for( int i = 0; i <= n; i++ ) // i-wiersz j-wyraz w wierszu
      {
        wierszWyraz[i] = new int[i+1];
        for( int j = 0; j <= i; j++ )
        {
          wierszWyraz[i][j] = (int) newton(i, j);
        }
      }
    }

    public static long newton(int n, int k) // liczy n po k
    {
      long wynik = 1;
      int i;

      for( i = 1; i <= k; i++ )
      {
        wynik = wynik * ( n - i + 1 ) / i;
      }
      return wynik;
    }

    public int wspolczynnik(int n, int m) throws TrojkatPascalaException // zwraca n po m z trojkata
    {
      if( n < 0 || n >= wierszWyraz.length )
        throw new TrojkatPascalaException(" - pierwszy argument spoza zakresu");
      
      if( m > n || m < 0 || m >= wierszWyraz.length )
        throw new TrojkatPascalaException(" - drugi argument spoza zakresu");
      
      return wierszWyraz[n][m];
    }
}
