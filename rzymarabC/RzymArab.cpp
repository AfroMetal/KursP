#include <iostream>
#include <string>
#include <exception>
#include <algorithm>
using namespace std;

static const char* liczbyRzym[] = { "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I" };
static const int liczbyArab[] = { 1000, 900, 500, 400 , 100, 90, 50, 40, 10 , 9 , 5 , 4 , 1 };

class RzymArab
{	
public: 
	static int rzym2arab(string rzym)
	{
		transform(rzym.begin(), rzym.end(), rzym.begin(), ::toupper);
			
		if( rzym.length() == 0 ) throw exception();
		
		int arab = 0;
		
		int indeksZnaku = 0;
		
		for( int i = 0; i < 13; i++ )
		{
			while( rzym.compare(indeksZnaku, char_traits<char>::length(liczbyRzym[i]), liczbyRzym[i]) == 0 )
			{
				arab += liczbyArab[i];
				indeksZnaku += char_traits<char>::length(liczbyRzym[i]);
			}
		}
		return arab;
	}
	
	static string arab2rzym(int arab)
	{
		if( arab < 1 || arab > 3999 ) throw exception();
		
		string rzym;
		
		for( int i = 0; i < 13; i++ )
		{
			while( arab >= liczbyArab[i] )
			{
				rzym += liczbyRzym[i];
				arab -= liczbyArab[i];
			}
		}
		return rzym;
	}
	
	static bool czyInteger(char *s)
	{
		try
		{
			stoi(s);
		} catch(invalid_argument e) {
			return false;
		}
		return true;
	}
};

int main(int argc, char *argv[])
{
	for( int i = 1; i < argc; i++ )
	{
		if( RzymArab::czyInteger(argv[i]) == true )
		{
		try
			{
				string a = RzymArab::arab2rzym(stoi(argv[i]));
				cout << argv[i] << "\t-> " << a << endl;
			} catch( exception a ){
				cout << argv[i] << "\t-> BLAD! Wprowadz poprawna liczbe" << endl;
			}
		} else{
			try
			{
				string rz = string(argv[i]);
				transform(rz.begin(), rz.end(), rz.begin(), ::toupper);
				if( rz != (RzymArab::arab2rzym(RzymArab::rzym2arab(argv[i]))) ) throw exception();
				cout << rz << "\t-> " << RzymArab::rzym2arab(argv[i]) << endl;
			} catch( exception r){
				cout << argv[i] << "\t-> BLAD! Wprowadz poprawna liczbe" << endl;
			}
		}
	}
};
