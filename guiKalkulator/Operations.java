/**@author RADOSŁAW KOWALSKI
  * Operations zawiera opis poszczególnych operacji przeprowadzanych przez kalkulator.*/
public class Operations
{
  /**Metoda obslugujaca dodawanie.
    * @param a pierwsza liczba
    * @param b druga liczba
    * @param mode aktualny tryb aplikacaji, true dla dziesietnego, false dla binarnego
    * @return zwraca wynik dzialania w postaci lancucha znakow*/ 
  static String plus(String a, String b, boolean mode)
  {
    long aa, bb;
    //try
    //{
      if(mode == true)
      {
        aa = Long.parseLong(a);
        bb = Long.parseLong(b);
        
        return Long.toString( (aa + bb) );
      }
      else
      {
        aa = Long.parseLong(a,2);
        bb = Long.parseLong(b,2);
        
        return Long.toBinaryString( (aa + bb) );
      }
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca odejmowanie.
    * @param a pierwsza liczba
    * @param b druga liczba
    * @param mode aktualny tryb aplikacji, true dla dziesietnego, false dla binarnego
    * @return zwraca wynik dzialania w postaci lancucha znakow*/   
  static String minus(String a, String b, boolean mode)
  {
    long aa, bb;
    //try
    //{
      if(mode == true)
      {
        aa = Long.parseLong(a);
        bb = Long.parseLong(b);
        
        return Long.toString( (aa - bb) );
      }
      else
      {
        aa = Long.parseLong(a,2);
        bb = Long.parseLong(b,2);
        
        return aa >= bb ? Long.toBinaryString( (aa - bb) ) : "-" + Long.toBinaryString( (bb - aa) );
      }
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca mnozenie.
    * @param a pierwsza liczba
    * @param b druga liczba
    * @param mode aktualny tryb aplikacji, true dla dziesietnego, false dla binarnego
    * @return zwraca wynik dzialania w postaci lancucha znakow*/   
  static String star(String a, String b, boolean mode)
  {
    long aa, bb;
    //try
    //{
      if(mode == true)
      {
        aa = Long.parseLong(a);
        bb = Long.parseLong(b);
        
        return Long.toString( (aa * bb) );
      }
      else
      {
        aa = Long.parseLong(a,2);
        bb = Long.parseLong(b,2);
        
        return aa * bb >= 0 ? Long.toBinaryString( (aa * bb) ) : "-" + Long.toBinaryString( -(aa * bb) );
      }
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca dzielenie calkowitoliczbowe.
    * @param a pierwsza liczba
    * @param b druga liczba
    * @param mode aktualny tryb aplikacji, true dla dziesietnego, false dla binarnego
    * @return zwraca wynik dzialania w postaci lancucha znakow*/   
  static String slash(String a, String b, boolean mode)
  {
    long aa, bb;
    //try
    //{
      if(mode == true)
      {
        aa = Long.parseLong(a);
        bb = Long.parseLong(b);
        if(bb==0) return "0";
        
        return Long.toString( (aa / bb) );
      }
      else
      {
        aa = Long.parseLong(a,2);
        bb = Long.parseLong(b,2);
        if(bb==0) return "0";
        
        return  aa / bb >= 0 ? Long.toBinaryString( (aa / bb) ) : "-" + Long.toBinaryString( -(aa / bb) );
      }
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca obliczanie reszty z dzielenia.
    * @param a pierwsza liczba
    * @param b druga liczba
    * @param mode aktualny tryb aplikacji, true dla dziesietnego, false dla binarnego
    * @return zwraca wynik dzialania w postaci lancucha znakow*/  
  static String perc(String a, String b, boolean mode)
  {
    long aa, bb;
    //try
    //{
      if(mode == true)
      {
        aa = Long.parseLong(a);
        bb = Long.parseLong(b);
        
        return Long.toString( (aa % bb) );
      }
      else
      {

        aa = Long.parseLong(a,2);
        bb = Long.parseLong(b,2);
        
        return Long.toBinaryString( (aa % bb) );
        }
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca zmiane liczby z systemu dziesietnego na binarny.
    * @param a liczba do zmiany
    * @return zwraca wynik dzialania w postaci lancucha znakow*/
  static String toBin(String a)
  {
    long aa;
    //try
    //{
    aa = Long.parseLong(a);
    
    return Long.toBinaryString(aa);
    //} catch(NumberFormatException e){ return; }
  }
  /**Metoda obslugujaca zmiane liczby z systemu binarnego na dziesietny.
    * @param a liczba do zmiany
    * @return zwraca wynik dzialania w postaci lancucha znakow*/     
  static String toDec(String a)
  {
    long aa;
    //try
    //{
    aa = Long.parseLong(a,2);
    
    return Long.toString(aa);
    //} catch(NumberFormatException e){ return; }
  }
}
