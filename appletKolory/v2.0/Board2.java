import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.util.Random;

/**Klasa definiujaca plansze, na ktorej dziala program.
 * @author Radoslaw Kowalski
 * @version 2.0*/
public class Board2 extends JPanel
{
  /**Tablica dwuwymiarowa etykiet pol planszy.*/
  JLabel[][] board;
  /**Tablica dwuwymiarowa obiektow klasy {@link FieldToChange2 FieldToChange2}.*/
  FieldToChange2[][] field;
  /**Generator liczb pseudolosowych.*/
  Random random;
  /**Szerokosc pola, domyslnie 20.*/
  int wid;  //default 20
  /**Wysokosc pola, domyslnie 20.*/
  int hei;  //default 20
  /**Opoznienie zmiany koloru, domyslnie 500 ms.*/
  int k;    //default 500
  /**Prawdopodobienstwo zmiany koloru na losowy, domyslnie 0.3.*/
  final double p; //default 0.3

  /**Konstruktor klasy Board.
   * Tworzy generator liczb. Plansze zapelnia polami JLabel i nadaje im losowe kolory. Uruchamia watki.
   * @param wid {int} szerokosc pola.
   * @param hei {int} wysokosc pola.
   * @param k {int} opoznienie w ms.
   * @param p {double} prawdopodobienstwo.*/
  public Board2( final int wid, final int hei, final int k, final double p )
  {
    super();
    this.wid = wid;
    this.hei = hei;
    this.k = k;
    this.p = p;
    setLayout( new GridLayout(hei,wid) );
    setFocusable(true);
    requestFocusInWindow();
    setVisible(true);
    random = new Random();
    board = new JLabel[wid][hei];
    field = new FieldToChange2[wid][hei];
    
    
    for( int i = 0; i < hei; i++ )
    {
      for( int j = 0; j < wid; j++ )
      {
        board[j][i] = new JLabel();
        board[j][i].setBackground( new Color(random.nextInt(256),
                                             random.nextInt(256),
                                             random.nextInt(256)));
        board[j][i].setOpaque(true);
        
        add(board[j][i]);
        field[j][i] = new FieldToChange2(this,j,i);
      }
    }
    
    for( int i = 0; i < hei; i++ )
    {
      for( int j = 0; j < wid; j++ )
      {
        try{ field[j][i].start(); } catch( OutOfMemoryError e ) {}
      }
    }
    
    setPreferredSize( new Dimension(wid*10,hei*10) );
    
  }
  /**Klasa zmieniajaca kolor pola {@link Board2 board}.
   * @param color kolor na, ktory zostanie zmieniony.
   * @param x {int} wspolrzedna x pola do zmiany.
   * @param y {int} wspolrzedna y pola do zmiany.*/
  public synchronized void setColor(Color color, int x, int y)
  {
    board[x][y].setBackground(color);
  }
}