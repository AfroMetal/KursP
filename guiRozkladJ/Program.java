/** 2015 (C) RADOSŁAW KOWALSKI program GUI Trojkat Pascala**/ 

import java.awt.*;
import java.awt.event.*;

class MyWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class SitoButtonAdapter implements ActionListener
{
  Program p;
  SitoButtonAdapter( Program p ) { this.p = p; }
  public void actionPerformed(ActionEvent e) { p.actionSito(); }
}

class RozkladButtonAdapter implements ActionListener
{
  Program p;
  RozkladButtonAdapter( Program p ) { this.p = p; }
  public void actionPerformed(ActionEvent e) { p.actionRozklad(); }
}

class SitoButton extends Button 
{
  SitoButton( Program p )
  {
    super("Utwórz sito"); addActionListener( new SitoButtonAdapter(p) );
  }
}

class RozkladButton extends Button 
{
  RozkladButton( Program p )
  {
    super("Rozłóż liczbę"); addActionListener( new RozkladButtonAdapter(p) );
  }
}

class MyFrame extends Frame
{
  MyFrame( Program p )
  {
    super("Rozklad Liczby");
    setBounds(640,480,640,480);
    addWindowListener( new MyWindowAdapter() );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,14) );
    setLayout( new GridLayout(6,1) );
    setBackground(new Color(125,255,100));
    setForeground(new Color(235,80,0));
    
    SitoButton akcjaSitoButton = new SitoButton(p);
    RozkladButton akcjaRozkladButton = new RozkladButton(p);
    
    akcjaSitoButton.setBackground(new Color(235,80,0));
    akcjaSitoButton.setForeground(new Color(125,255,100));
    akcjaRozkladButton.setBackground(new Color(235,80,0));
    akcjaRozkladButton.setForeground(new Color(125,255,100));
   
    p.infoLabel = new Label();
    p.infoLabel.setAlignment(Label.CENTER);
    p.wynikLabel = new Label("",Label.CENTER);
    p.sitoTextField = new TextField(40);
    p.rozkladTextField = new TextField("Najpierw utwórz sito.",40);
    
    p.sitoTextField.setForeground(new Color(235,80,0));
    p.sitoTextField.setBackground(new Color(200,255,175));
    p.rozkladTextField.setForeground(new Color(125,255,100));
    p.rozkladTextField.setBackground(new Color(200,255,175));
    p.rozkladTextField.setEnabled(false);

    add(p.sitoTextField);
    add(akcjaSitoButton);
    add(p.infoLabel);
    add(p.rozkladTextField);
    add(akcjaRozkladButton);
    add(p.wynikLabel);
    
    pack();
    setResizable(false);
    setVisible(true);
  }
}

public class Program 
{
  MyFrame frame;
  RozkladLiczby RL;
  Label infoLabel;
  Label wynikLabel;
  TextField sitoTextField;
  TextField rozkladTextField;
 
  int danesito;
  int[] wynikrozklad;
  String wynikstring;
  String danerozklad;
  
  void actionSito()
  {    
    try{
      danesito = Integer.parseInt(sitoTextField.getText());
      infoLabel.setText("Proszę czekać tworzę sito...");
      RL = new RozkladLiczby(danesito);
      sitoTextField.setText("");
      infoLabel.setText("Sito utworzone dla " + Integer.toString(danesito));
      rozkladTextField.setText("");
      rozkladTextField.setEnabled(true);
      rozkladTextField.setForeground(new Color(235,80,0));
    } catch ( OutOfMemoryError ex ){
      infoLabel.setText( "BLAD! Za duza liczba. Podaj mniejsza liczbe." );
      sitoTextField.setText("");
      return;
    } catch ( NumberFormatException ex ){
      infoLabel.setText("BLAD! Zle dane. Podaj poprawna liczbe.");
      sitoTextField.setText("");
      return;
    }
  }
  
  void actionRozklad()
  {
    wynikstring = "";
    danerozklad = rozkladTextField.getText();
    try{
      wynikrozklad = RL.czynnikiPierwsze(Integer.parseInt(danerozklad));
      rozkladTextField.setText("");
      for( int j = 0; j < wynikrozklad.length-1; j++ )
      {
        wynikstring = wynikstring + (Integer.toString(wynikrozklad[j])) + " * ";
      }
      wynikstring = danerozklad + " = " + wynikstring + (Integer.toString(wynikrozklad[wynikrozklad.length-1]));
      wynikLabel.setText(wynikstring);
    } catch ( NumberFormatException ex ){
      wynikLabel.setText("BLAD! Zle dane. Podaj poprawna liczbe.");
      rozkladTextField.setText("");
    } catch ( ArrayIndexOutOfBoundsException ex){
      wynikLabel.setText("BLAD! Liczba wieksza od sita.");
      rozkladTextField.setText("");
    }
  }
  
  public static void main(String[] args)
  {
    Program p = new Program();
    p.frame = new MyFrame(p);
    //p.rozkladTextField.setEnabled(false);
  }
}