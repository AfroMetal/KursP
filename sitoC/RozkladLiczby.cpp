#include <iostream>
#include <string>
#include <stdexcept>
#include <cmath>

class RozkladLiczby
{
	int *sitoErato;
	
public:
	RozkladLiczby(int n)
	{
		sitoErato = new int [n-1];
		
		for( int i = 0; i < n-1; i++)
		{
			sitoErato[i] = i + 2;
		}
		int pierwsza = 2;
		
		for( int j = pierwsza-2; j <= ceil(sqrt(n)); j++ )
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
	
	~RozkladLiczby()
	{
		delete[] sitoErato;
	}
	
	int *czynnikiPierwsze(int m, int& rozmiar)
	{
		int m2 = m;
		
		while( m2 >= 2 )
		{
			m2 = m2 / sitoErato[m2-2];
			rozmiar++;
		}
		
		int *czynnikiP = new int[rozmiar];
		rozmiar = 0;
		
		while( m >= 2 )
		{
			czynnikiP[rozmiar] = sitoErato[m-2];
			m = m / sitoErato[m-2];
			rozmiar++;
		}
		
		return czynnikiP;
	}
};

int main(int argc, char *argv[])
{
	RozkladLiczby* RL = nullptr;
	
	int max = 0;

	for( int i = 1; i < argc; i++ )
	{
		try{
			max = std::stoul(std::string(argv[0]));
			break;
		} catch( std::invalid_argument ex ){
			continue;
		}
	}
	
	for( int i = 1; i < argc; i++ )
	{
		try{
			std::stoi(std::string(argv[i]));
		} catch ( std::exception ex ) {
			continue;
		}
		if( max < std::stoi(argv[i]) )
		{
			max = std::stoi(argv[i]);
		}
	}
	
	try{
		if( max < 2 )
			throw std::exception();
		RL = new RozkladLiczby(max);
	} catch ( std::bad_alloc ex ){
		std::cout << max << "\t BLAD! za duza liczba - nie utworzono sita" << std::endl;
		return 0;
	} catch ( std::exception ex ){
		std::cout << "BLAD! podaj przynajmniej jeden poprawny argument (liczba calkowita >=2)" << std::endl;
		return 0;
	}
	
	int w = 0;
	
	for( int i = 1; i < argc; i++ )
	{
		try{
			if( (w = std::stoi(argv[i])) < 2 )
			{
				throw std::exception();
			}
		} catch( std::invalid_argument ex ){
			std::cout << argv[i] << "\t BLAD! argument musi byc liczba calkowita" << std::endl;
			continue;
		} catch( std::exception ex ){
			std::cout << argv[i] << "\t BLAD! liczba musi byc wieksza lub rowna 2" << std::endl;
			continue;
		}
		
		int rozmiar = 0;
		RL->czynnikiPierwsze(w, rozmiar);
		
		int *cP = RL->czynnikiPierwsze(w, rozmiar);
		
		std::cout << argv[i] << "\t = ";
		
		for( int j = 0; j < rozmiar - 1; j++ )
		{
			std::cout << cP[j] << " * ";
		}
		std::cout << cP[rozmiar - 1] << std::endl;
	}
	delete RL;
};
