#include <iostream>
#include <string>
#include <stdexcept>

// Klasa zaklada prawidlowe dane wejsciowe, wyjatki sprawdzane sa w klasie wywolujacej

class WierszTrojkataPascala
{
	int *wyrazWiersza;
	
public:
	WierszTrojkataPascala(int n)			// konstruktor
	{
		wyrazWiersza = new int[n+1];			// wiersze liczymy od 0, dlatego wiersz n ma n+1 wyrazow
		
		for(int i = 0; i <= n; i++)			// obliczanie wartosci wyrazow wiersza z symbolu newtona
		{
			wyrazWiersza[i] = newton(n, i);
		}
	}

	~WierszTrojkataPascala()
	{
		delete[] wyrazWiersza;
	}
	
	int newton(int n, int k)		// metoda liczaca z symbolu newtona wyraz k wiersza n
	{
		int wynik = 1;
		int i;
		
		for(i = 1; i <= k; i++)
		{
			wynik = wynik * ( n - i + 1 ) / i;
		}
		return wynik;
	}
	
	int wspolczynnik(int m)			// metoda zwracajaca m-ty wyraz wiersza
	{
		return wyrazWiersza[m];
	}
};

int main(int argc, char *argv[])
{
	int numerWiersza = 0;
	
	try {
		numerWiersza = std::stoul(std::string(argv[1]));
		if (numerWiersza < 0)
			throw std::exception();
	} catch (std::invalid_argument ex) {
		std::cout << argv[1] << "\t- nieprawidlowy numer wiersza (musi byc liczba calkowita)" << std::endl;
		return 1;
	} catch (std::exception ex) {
		std::cout << argv[1] << "\t- nieprawidlowy numer wiersza (musi byc dodatni)" << std::endl;
		return 1;
	}
	
	WierszTrojkataPascala WTP(numerWiersza);
	
	for (int i = 2; i < argc; i++) {
		try {
			if (std::stoi(argv[i]) < 0 || std::stoi(argv[i]) > numerWiersza)
				throw std::exception();
			std::cout << argv[i] << "\t-> " << WTP.wspolczynnik(std::stoi(argv[i])) << std::endl;
		} catch (std::invalid_argument ex) {
			std::cout << argv[i] << "\t-  nieprawidlowa dana" << std::endl;
		} catch (std::exception ex) {
			std::cout << argv[i] << "\t-  liczba spoza zakresu" << std::endl;
		}
	}
	
	return 0;
};
