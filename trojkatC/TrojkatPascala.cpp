#include <iostream>
#include <string>
#include <exception>

using namespace std;

class TrojkatPascalaException : public exception
{
private:
  string s;
public:
  TrojkatPascalaException(string ss) : s(ss) {};
  virtual ~TrojkatPascalaException() throw() {};
  virtual const char* what() const throw() { return s.c_str(); };
};

class TrojkatPascala
{
  int **wierszWyraz;
  int size;
  
public:
  TrojkatPascala(int n) throw (TrojkatPascalaException)
  {
    if( n < 0 || n > 33 ) 
      throw TrojkatPascalaException(" - nieprawidlowy rozmiar");
    
    wierszWyraz = new int*[n + 1];
    size = n + 1;
    
    for( int i = 0; i <= n; i++ ) // i-wiersz j-wyraz w wierszu
    {
      wierszWyraz[i] = new int[i+1];
      for( int j = 0; j <= i; j++ )
      {
        wierszWyraz[i][j] = newton(i, j);
      }
    }
  }
  
  ~TrojkatPascala()
  {
    delete wierszWyraz;
  }
  
  long long int newton(int n, int k) // liczy n po k
  {
    long long int wynik = 1;
    int i;
    
    for( i = 1; i <= k; i++ )
    {
      wynik = wynik * ( n - i + 1 ) / i;
    }
    return wynik;
  }
  
  int wspolczynnik(int n, int m) throw (TrojkatPascalaException) // zwraca n po m z trojkata
  {
    if( n < 0 || n >= size )
      throw TrojkatPascalaException(" - pierwszy argument spoza zakresu");
    
    if( m > n || m < 0 || m >= size )
      throw TrojkatPascalaException(" - drugi argument spoza zakresu");
    
    return wierszWyraz[n][m];
  }
};


int main(int argc, char *argv[]) //throw (TrojkatPascalaException)
{
  try
  {
    int rozmiar = stoi(argv[1]);
    TrojkatPascala TP(rozmiar);

    for ( int i = 2; i < argc - 1 ; i+=2 )
    {
      try
      {
        int wynik = TP.wspolczynnik(stoi(argv[i]), stoi(argv[i+1]));
        cout << argv[i] << " po " << argv[i+1] << "\t-> " << wynik << endl;
      } catch( TrojkatPascalaException& w) {
        cout << argv[i] << " po " << argv[i+1] << w.what() << endl;
        continue;
      } catch( exception& w ) {
        cout << argv[i] << " po " << argv[i+1] << " - nieprawidlowe dane" << endl;
        continue;
      }
    }
  
    if( (argc - 1) % 2 == 0 )
    {
      cout << argv[argc - 1] << " - za malo danych" << endl;
    }
      
  } catch( TrojkatPascalaException& t ) {
    cout << argv[1] << t.what() << endl;
    return 1;
  } catch( exception& t ) {
    cout << argv[1] << " - niepoprawny argument" << endl;
    return 1;
  }
    
  return 0;
};
