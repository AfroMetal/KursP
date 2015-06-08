import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.util.Random;

/*class MyKeyAdapter implements KeyListener
{
  Board b;
  MyKeyAdapter(Board b) { this.b = b; }
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyCode() == 10)
    {
      try
      {
        startall();
      } catch (Exception ex){}
    }
  }
  public void keyReleased(KeyEvent ex) {}
  public void keyTyped(KeyEvent ex) {}
}*/


/**Klasa definiujaca plansze, na ktorej dziala program.
 * @author Radoslaw Kowalski*/
public class Board extends JPanel
{
  /**Tablica etykiet pol planszy*/
  JLabel[][] board;
  
  Random random;
  
  int wid;  //default 30
  
  int hei;  //default 30
  
  int k;    //default 300
  
  final double p; //default 0.5

  /**Konstruktor klasy Board.
   * Tworzy generator liczb i plansze.*/
  public Board( final int wid, final int hei, final int k, final double p )
  {
    super();
    this.wid = wid;
    this.hei = hei;
    this.k = k;
    this.p = p;
    setLayout( new GridLayout(hei,wid) );
    setFocusable(true);
    requestFocusInWindow();
    
    random = new Random();
    board = new JLabel[wid][hei];
    

    for( int i = 0; i < hei; i++ )
    {
      for( int j = 0; j < wid; j++ )
      {
        board[j][i] = new JLabel();
        board[j][i].setBackground( new Color( random.nextInt(256),
                                              random.nextInt(256),
                                              random.nextInt(256) ));
        board[j][i].setOpaque(true);
        add(board[j][i]);
      }
    }
    setPreferredSize(new Dimension(wid*10,(hei*10)));
    setVisible(true);
    
    new Thread()
    {
      public void run()
      {
              for( int y = 0; y < hei; y++ )
              {
                for( int x = 0; x < wid; x++ )
                {
                        int i = x;
                        int j = y;
                Thread thread = new Thread(new Runnable() {
 
                                        @Override
                                        public void run() {
                                                int R,redL,redR,redU,redD;
                                        int G,greenL,greenR,greenU,greenD;
                                        int B,blueL,blueR,blueU,blueD;
                                        double probability;
                                        int delay;    
                                               
                                                while (true) {
                                          delay = (int) ( ( random.nextDouble() + 0.5 ) * k );
                                          probability = random.nextDouble();
                                         
                                          if(probability <= p) //random color
                                          {
                                            board[i][j].setBackground( new Color( random.nextInt(256),
                                                                                    random.nextInt(256),
                                                                                    random.nextInt(256) ));
                                          }
                                          else //neighbors color
                                          {
                                            try{ redL = ((board[i-1][j].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redL = 0; }
                                            try{ redR = ((board[i+1][j].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redR = 0; }
                                            try{ redU = ((board[i][j+1].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redU = 0; }
                                            try{ redD = ((board[i][j-1].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redD = 0; }
                                           
                                            R = (int) ( (redL + redR + redU + redD) / 4 );
                                           
                                            try{ greenL = ((board[i-1][j].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenL = 0; }
                                            try{ greenR = ((board[i+1][j].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenR = 0; }
                                            try{ greenU = ((board[i][j+1].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenU = 0; }
                                            try{ greenD = ((board[i][j-1].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenD = 0; }
                                           
                                            G = (int) ( (greenL + greenR + greenU + greenD) / 4 );
                                           
                                            try{ blueL = ((board[i][j].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueL = 0; }
                                            try{ blueR = ((board[i+1][j].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueR = 0; }
                                            try{ blueU = ((board[i][j+1].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueU = 0; }
                                            try{ blueD = ((board[i][j-1].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueD = 0; }
                                           
                                            B = (int) ( (blueL + blueR + blueU + blueD) / 4 );
                                           
                                            board[i][j].setBackground( new Color(R,G,B) );
                                          }
                                          try {
                                                                sleep(delay);
                                                        } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                        }
                                                }
                                                 
                                        }
                       
                });
                thread.start();
               
                }
              }
            }
 
    }.start();
    
  }
/*  void startall()
  {
    for( int y = 0; y < hei; y++ )
    {
      for( int x = 0; x < wid; x++ )
      {
        boardthread[x][j].start();
      }
    }
//    FieldToChange f = null;
//    f.start();
  }*/
}
