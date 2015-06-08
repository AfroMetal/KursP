/** 2015 (C) RADOSŁAW KOWALSKI program GUI RzymArab**/ 

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class ZamienButtonAdapter implements ActionListener
{
  Program p;
  ZamienButtonAdapter( Program p ) { this.p = p; }
  public void actionPerformed(ActionEvent e) { p.actionZamien(); }
}

class ZamienButton extends Button 
{
  ZamienButton( Program p )
  {
    super("Zamień"); addActionListener( new ZamienButtonAdapter(p) );
  }
}

class MyPanel extends Panel
{
  GridLayout GLayout = new GridLayout(0,1); 
  MyPanel()
  {
    super();
    setLayout( GLayout );
    setFont( new Font(Font.SANS_SERIF,Font.PLAIN,15) );
  }
}

class MyFrame extends Frame
{
  MyFrame( Program p )
  {
    super("Rzymskie i Arabskie");
    addWindowListener( new MyWindowAdapter() );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,15) );
    setLayout( new BorderLayout() );
    setBackground(new Color(125,255,100));
    setForeground(new Color(235,80,0));
    
    ZamienButton akcjaZamienButton = new ZamienButton(p);

    akcjaZamienButton.setBackground(new Color(235,80,0));
    akcjaZamienButton.setForeground(new Color(125,255,100));
    p.daneTextField = new TextField();
    p.panel = new MyPanel();
    
    akcjaZamienButton.setPreferredSize(new Dimension(300,40));
    
    p.daneTextField.setPreferredSize(new Dimension(300,40));
    p.daneTextField.setForeground(new Color(235,80,0));
    p.daneTextField.setBackground(new Color(200,255,175));

    add(p.daneTextField, BorderLayout.NORTH);
    add(akcjaZamienButton, BorderLayout.CENTER);
    add(p.panel, BorderLayout.SOUTH);
    
    pack();
    setLocationRelativeTo(null);
    setResizable(false);
  //  setVisible(true);
  }
}

public class Program 
{
  MyFrame frame;
  MyPanel panel;
  TextField daneTextField;
  
  void actionZamien()
  {
    frame.setResizable(true);
    
    panel.removeAll();
    panel.GLayout.setRows(0);
    try
    {    
      Runtime runtime = Runtime.getRuntime();
      Process process = runtime.exec( "./RzymArab " + daneTextField.getText() );
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String c;
      
      daneTextField.setText("");
      
      while( (c = reader.readLine()) != null )
      {
        Label wiersz = new Label( c, Label.CENTER );
        panel.GLayout.setRows( (panel.GLayout.getRows()) + 1 );
        panel.add(wiersz);
      }
      
      reader.close();
    } catch(IOException e){
      Label wiersz = new Label( (e.getMessage()), Label.CENTER );
      panel.GLayout.setRows(1);
      panel.add(wiersz);
    } catch(IllegalArgumentException e){
      Label wiersz = new Label( (e.getMessage()), Label.CENTER );
      panel.GLayout.setRows(1);
      panel.add(wiersz);
    }
    
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }
  
  public static void main(String[] args)
  {
    Program p = new Program();
    p.frame = new MyFrame(p);
    p.frame.setVisible(true);
  }
}