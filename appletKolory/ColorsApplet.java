import java.awt.*;
import javax.swing.*;
import java.applet.*;

public class ColorsApplet extends Applet
{
  /**Funkcja inicjujaca aplet
   * pobiera rozmiar planszy, szybkosc dzialania i prawdopodobienstwo zmiany koloru*/
  public void init()
  {
    add( new Board( Integer.parseInt(getParameter("wid")),
                    Integer.parseInt(getParameter("hei")),
                    Integer.parseInt(getParameter("k")),
                    Double.parseDouble(getParameter("p"))
    ) );
  }
  
  public static void main(String[] args)
  {
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    JFrame frame = new JFrame("Colors");
    //frame.setPreferredSize(new Dimension(300,300));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Board b;
    
    try{
      b = new Board( Integer.parseInt(args[0]),   //wid
                     Integer.parseInt(args[1]),   //hei
                     Integer.parseInt(args[2]),   //k
                     Double.parseDouble(args[3])  //p
      );
    } catch( Exception e ) { b = new Board( 30,30,300,0.5 ); }
    frame.add(b);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
