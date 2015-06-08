public class TrojkatPascala
{
    public int[][] wierszWyraz;
    public TrojkatPascala(int n)
    {
        wierszWyraz = new int[n+1];
				
				for( int i = 0; i <= n; i++ ) // i-wiersz j-wyraz w wierszu
				{
					for( int j = 0; j <= i; i++ )
					{
						wierszWyraz[i][j] = newton(i, j);
					}
				}
    }

    public static int newton(int n, int k) // liczy n po k
    {
        int wynik = 1;
        int i;

        for( i = 1; i <= k; i++ )
        {
            wynik = wynik * ( n - i + 1 ) / i;
        }
        return wynik;
    }

    public int wspolczynnik(int n, int m) // n po m
    {
        return wierszWyraz[n][m];
    }
}
