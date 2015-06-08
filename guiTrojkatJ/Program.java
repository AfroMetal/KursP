/** 2015 (C) RADOSŁAW KOWALSKI program GUI Trojkat Pascala**/ 

import java.awt.*;
import java.awt.event.*;

class MyWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class TrojkatButtonAdapter implements ActionListener
{
  Program p;
  TrojkatButtonAdapter( Program p ) { this.p = p; }
  public void actionPerformed(ActionEvent e) { p.actionTrojkat(); }
}

class TrojkatButton extends Button 
{
  TrojkatButton( Program p )
  {
    super("Utwórz trojkąt pascala"); addActionListener( new TrojkatButtonAdapter(p) );
  }
}

class MyPanel extends Panel
{
  GridLayout GLayout = new GridLayout(1,1); 
  MyPanel()
  {
    super();
    setLayout( GLayout );
    setFont( new Font(Font.SANS_SERIF,Font.PLAIN,14) );
  }
}

class MyFrame extends Frame
{
  MyFrame( Program p )
  {
    super("Trojkat Pascala");
    addWindowListener( new MyWindowAdapter() );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,14) );
    setLayout( new BorderLayout() );
    setBackground(new Color(125,255,100));
    setForeground(new Color(235,80,0));
    
    TrojkatButton akcjaTrojkatButton = new TrojkatButton(p);

    akcjaTrojkatButton.setBackground(new Color(235,80,0));
    akcjaTrojkatButton.setForeground(new Color(125,255,100));
   
    p.infoLabel = new Label("Podaj rozmiar trojkata (0-33)");
    p.infoLabel.setAlignment(Label.CENTER);
    p.rozmiarTextField = new TextField(15);
    p.wynik = new MyPanel();
    
    p.rozmiarTextField.setForeground(new Color(235,80,0));
    p.rozmiarTextField.setBackground(new Color(200,255,175));

    add(p.infoLabel, BorderLayout.NORTH);
    add(p.rozmiarTextField, BorderLayout.WEST);
    add(akcjaTrojkatButton, BorderLayout.CENTER);
    add(p.wynik,BorderLayout.SOUTH);
    
    pack();
    setLocationRelativeTo(null);
    //setResizable(false);
    setVisible(true);
  }
}

public class Program 
{
  MyFrame frame;
  MyPanel wynik;
  TrojkatPascala TP;
  TextField rozmiarTextField;
  Label infoLabel;
  int rozmiar;
  
  void actionTrojkat()
  {
   // frame.setResizable(true);
    try
    {
      rozmiar = Integer.parseInt(rozmiarTextField.getText());
      TP = new TrojkatPascala(rozmiar);
      infoLabel.setText("Utworzono trojkat");
      rozmiarTextField.setText("");
    } catch( TrojkatPascalaException e ){
      wynik.removeAll();
      wynik.GLayout.setRows(1);
      frame.setResizable(false);
      frame.pack();
      rozmiarTextField.setText("");
      infoLabel.setText(e.getMessage());
      frame.setLocationRelativeTo(null);
      return;
    } catch( NumberFormatException e ){
      wynik.removeAll();
      wynik.GLayout.setRows(1);
      frame.setResizable(false);
      frame.pack();
      rozmiarTextField.setText("");
      infoLabel.setText("BLAD! Podaj poprawny rozmiar trojkata");
      frame.setLocationRelativeTo(null);
      return;
    }
    wynik.removeAll();
    
    if( rozmiar > 20 )
      wynik.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,13) );
    if( rozmiar > 25 )
      wynik.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,11) );
    if( rozmiar > 30 )
      wynik.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,10) );
    
    wynik.GLayout.setRows(0);
    for( int i = 0; i <= rozmiar; i++)
    {
      Label wiersz = new Label(wiersz(i), Label.CENTER);
      wynik.GLayout.setRows( (wynik.GLayout.getRows()) + 1 );
      wynik.add(wiersz);
    }
    
  //  frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);

  }
  
  String wiersz(int n)
  {
    String wynik = "";
    for( int i = 0; i <= n; i++)
    {
      wynik += Integer.toString(TP.wierszWyraz[n][i]);
      if (i < n)
        wynik += (" ");
    }
    return wynik;
  }
  
  public static void main(String[] args)
  {
    Program p = new Program();
    p.frame = new MyFrame(p);
  }
}
