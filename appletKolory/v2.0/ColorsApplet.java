import java.awt.*;
import javax.swing.*;
import java.applet.*;

/**Klasa apletu, odpowiada za pobranie danych i odwolanie sie do metody tworzacej plansze przy uzyciu tych danych.
 * @author Radoslaw Kowalski*/
public class ColorsApplet extends Applet
{
  /**Funkcja inicjujaca aplet
   * pobiera rozmiar planszy, szybkosc dzialania i prawdopodobienstwo zmiany koloru.*/
  public void init()
  {
    add( new Board2(Integer.parseInt(getParameter("wid")),
                    Integer.parseInt(getParameter("hei")),
                    Integer.parseInt(getParameter("k")),
                    Double.parseDouble(getParameter("p"))) );
  }
  /**Glowna klasa testowa aplikacji 
   * odpowiada za stworzenie okna aplikacji z odpowiednimi danymi pobranymi jako parametry uruchomienia.
   * Program jest plansza, ktorej pola zmieniaja kolor na losowy lub sredni kolor jego sasiadow zaleznie od prawdopodobienstwa, 
   * losowanego przy uzyciu generatora liczb pseudolosowych.
   * @param args {String} Tablica parametrow uruchomienia.*/
  public static void main(String[] args)
  {
		int wid;
		int hei;
		int k;
		double p;
		
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    
    try{
			wid = Integer.parseInt(args[0]);
			hei = Integer.parseInt(args[1]);
			k = Integer.parseInt(args[2]);
			p = Double.parseDouble(args[3]);
    } catch( Exception e ){
			wid = 20;
			hei = 20;
			k = 500;
			p = 0.3;
		}
		
		JFrame frame = new JFrame( "Colors " + wid + "x" + hei );
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout( new BorderLayout() );
    
    frame.add( new Board2( wid, hei, k, p ), BorderLayout.CENTER );
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
