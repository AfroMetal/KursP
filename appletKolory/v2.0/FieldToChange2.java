import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/**Klasa (Watek) opisujaca zmiane kolorow pol, jest rozszerzeniem Thread.*/
public class FieldToChange2 extends Thread
{
  Board2 b;
  int x,y;
  Color color;
  /**Konstruktor klasy {@link FieldToChange2} pobierajacy wspolrzedne pola.
   * @param b objekt b klasy {@link Board2}.
   * @param x {int} wspolrzedna x.
   * @param y {int} wspolrzedna y.*/
  FieldToChange2(Board2 b, int x, int y)
  {
    this.x = x;
    this.y = y;
    this.b = b;
    
    //try{ start(); } catch( OutOfMemoryError e ) {}
  }
  /**Metoda sprawdzajaca czy pole, z ktorego chcemy pobrac nie znajduje sie poza plansza.
   * Gdy tak, to pobiera kolor z pola po przeciwnej stronie (symulacja torusa).
   * @param x {int} wspolrzedna x;
   * @param y {int} wspolrzedna y;
   * @param c {int} kod koloru (0 = red, 1 = green, 2 = blue) do pobrania;
   * @return {int} zwraca wartosc koloru w rgb;*/
  int GetField(int x, int y, int c)
  {
    if( x < 0 ) x = b.wid - 1;
    if( x >= b.wid ) x = 0;
    if( y < 0 ) y = b.hei - 1;
    if( y >= b.hei ) y = 0;
    
    int result;
    
    if( c == 0 ) result = b.board[x][y].getBackground().getRed(); 
    else if( c == 1 ) result = b.board[x][y].getBackground().getGreen();
    else result = b.board[x][y].getBackground().getBlue();
    
    return result;
  }
  
  /**Metoda opisujaca dzialanie watku. 
   * Kolor zostaje zmieniony na losowy lub kolor sredni sasiadow w zaleznosci od wartosci prawdopodobienstwa.*/
  public void run()
  {
    int R,redL,redR,redU,redD;
    int G,greenL,greenR,greenU,greenD;
    int B,blueL,blueR,blueU,blueD;
    
    double probability;
    int delay; 
    
    while(true)
    {
      try
      {
        delay = (int) ( ( b.random.nextDouble() + 0.5 ) * b.k );
        probability = b.random.nextDouble();
                
        if(probability <= b.p) //random color
        {
          b.setColor( new Color(b.random.nextInt(256),
                                b.random.nextInt(256),
                                b.random.nextInt(256)), x, y);
        }
        else //neighbors color
        {
          redL = ( GetField(x-1,y,0) );
          redR = ( GetField(x+1,y,0) );
          redU = ( GetField(x,y+1,0) );
          redD = ( GetField(x,y-1,0) );
                  
          R = (int) ( (redL + redR + redU + redD) / 4 );
                  
          greenL = ( GetField(x-1,y,1) );
          greenR = ( GetField(x+1,y,1) );
          greenU = ( GetField(x,y+1,1) );
          greenD = ( GetField(x,y-1,1) );
                  
          G = (int) ( (greenL + greenR + greenU + greenD) / 4 );
                  
          blueL = ( GetField(x-1,y,2) );
          blueR = ( GetField(x+1,y,2) );
          blueU = ( GetField(x,y+1,2) );
          blueD = ( GetField(x,y-1,2) );
                  
          B = (int) ( (blueL + blueR + blueU + blueD) / 4 );
                  
          b.setColor( new Color(R,G,B), x, y );
        }
        sleep(delay);
        Thread.yield();
      } catch( InterruptedException e ){};
    } 
  }
}
