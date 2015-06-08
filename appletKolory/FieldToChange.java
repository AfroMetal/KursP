import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

class FieldToChange extends Thread
{
  Board b;
  public void run()
  { 
    int R,redL,redR,redU,redD;
    int G,greenL,greenR,greenU,greenD;
    int B,blueL,blueR,blueU,blueD;
    double probability;
    int delay;    
    while(true){
      synchronized(b.board)
      {
        try
        {
          for( int y = 0; y < b.hei; y++ )
          {
            for( int x = 0; x < b.wid; x++ )
            {
              delay = (int) ( ( b.random.nextDouble() + 0.5 ) * b.k );
              probability = b.random.nextDouble();
              
              if(probability <= b.p) //random color
              {
                b.board[x][y].setBackground( new Color( b.random.nextInt(256),
                                                        b.random.nextInt(256),
                                                        b.random.nextInt(256) ));
              }
              else //neighbors color
              {
                try{ redL = ((b.board[x-1][y].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redL = 0; }
                try{ redR = ((b.board[x+1][y].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redR = 0; }
                try{ redU = ((b.board[x][y+1].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redU = 0; }
                try{ redD = ((b.board[x][y-1].getBackground()).getRed()); } catch( ArrayIndexOutOfBoundsException e ) { redD = 0; }
                
                R = (int) ( (redL + redR + redU + redD) / 4 );
                
                try{ greenL = ((b.board[x-1][y].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenL = 0; }
                try{ greenR = ((b.board[x+1][y].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenR = 0; }
                try{ greenU = ((b.board[x][y+1].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenU = 0; }
                try{ greenD = ((b.board[x][y-1].getBackground()).getGreen()); } catch( ArrayIndexOutOfBoundsException e ) { greenD = 0; }
                
                G = (int) ( (greenL + greenR + greenU + greenD) / 4 );
                
                try{ blueL = ((b.board[x-1][y].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueL = 0; }
                try{ blueR = ((b.board[x+1][y].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueR = 0; }
                try{ blueU = ((b.board[x][y+1].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueU = 0; }
                try{ blueD = ((b.board[x][y-1].getBackground()).getBlue()); } catch( ArrayIndexOutOfBoundsException e ) { blueD = 0; }
                
                B = (int) ( (blueL + blueR + blueU + blueD) / 4 );
                
                b.board[x][y].setBackground( new Color(R,G,B) );
              }
              sleep(delay);
            }
          }
        } catch( InterruptedException e ){};
        Thread.yield();
      }
    }
  }
}
  
/*
  public void run()
  {
    synchronized(b.board)
    {
      while(true)
      {
        delay = (int) ( ( b.random.nextDouble() + 0.5 ) * b.k );
        probability = b.random.nextDouble();
        
        try
        {
          if(probability <= b.p)
          {
            R = b.random.nextInt(256);
            G = b.random.nextInt(256);
            B = b.random.nextInt(256);
            for( int i = 0; i < b.hei; i++ )
            {
              for( int j = 0; j < b.wid; j++ )
              {
                b.board[j][i].setBackground( new Color( b.random.nextInt(256),
                                                      b.random.nextInt(256),
                                                      b.random.nextInt(256) ));
              }
            }
          }
          sleep(delay);
        } catch( InterruptedException e ){};
        
        Thread.yield();
      }
    }
  }*/