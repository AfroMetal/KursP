public class RozkladLiczby
{
    public int[] sitoErato;
    public RozkladLiczby(int n)			// konstruktor
    {
        sitoErato = new int[n-1];			// tab[0]=2, tab[1]=3, ... , tab[n-2]=n

        for( int i = 0; i < n-1; i++ )
        {
					sitoErato[i] = i + 2;
        }
        
        int pierwsza = 2;
        
        for( int j = pierwsza-2; j <= Math.ceil(Math.sqrt(n)); j++ )
				{
					for( int i = pierwsza-2; i < n-1; i++ )
					{
						if( sitoErato[i] % pierwsza == 0 )
						{
							sitoErato[i] = pierwsza;
						}
					}
					
					if ( sitoErato[j] > pierwsza )
					{
						pierwsza = sitoErato[j];
					}
				}
		}
		public int[] czynnikiPierwsze(int m)
		{
			int rozmiar = 0;
			int m2 = m;
			
			while( m2 >= 2 )
			{
				m2 = m2 / sitoErato[m2-2];
				rozmiar++;
			}
			
			int[] czynnikiP = new int[rozmiar];
			rozmiar = 0;
			
			while( m >= 2 )
			{
				czynnikiP[rozmiar] = sitoErato[m-2];
				m = m / sitoErato[m-2];
				rozmiar++;
			}

			return czynnikiP;
		}
}
