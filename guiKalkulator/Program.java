/**@author RADOSŁAW KOWALSKI 
  * Kalkulator operujacy w systemie binarnym i dziesietnym.*/
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**MyWindowAdapter umozliwia zamykanie okna.*/
class MyWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent e) { System.exit(0); }
}
/**MyButtonAdapter wykonuje operacje wywolane przez MyButton.*/
class MyButtonAdapter implements ActionListener
{
  /**Obiekt p klasy Program.*/
  Program p;
  /**Konstruktor klasy MyButtonAdapter.
    * @param p Obiekt klasy Program*/
  MyButtonAdapter( Program p ) { this.p = p; }
  /**Metoda wywolana przy kliknieciu przycisku MyButton, ktora wykonuje odpowiednia operacje.
    * @param e Klinkiecie przycisku MyButton.*/
  public void actionPerformed(ActionEvent e) { p.operation( e.getActionCommand() ); }
}
/**MyButton jest przyciskiem obslugujacym operacje kalkulatora.*/
class MyButton extends JButton 
{
  /**Konstuktor klasy MyButton.
    * @param p Obiekt klasy Program.
    * @param s Etykieta przycisku i operacja.*/
  MyButton( Program p, String s )
  {
    super(s);
    setActionCommand(s);
    addActionListener( new MyButtonAdapter(p) );
    setBackground( new Color(255,255,80) );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,16) );
  }
}
/**ModeButtonAdapter wykonuje akcje wywolane przez ModeButton.*/
class ModeButtonAdapter implements ActionListener
{
  /**Obiekt p klasy Program.*/
  Program p;
  /**Konstruktor klasy ModeButtonAdapter.
    * @param p Obiekt klasy Program.*/
  ModeButtonAdapter( Program p ) { this.p = p; }
  /**Metoda wywolana przy klinknieciu przycisku ModeButton, ktora zmienia tryb kalkulatora.
    * @param e Klinkniecie przycisku ModeButton.*/
  public void actionPerformed(ActionEvent e) { p.action(); }
}
/**ModeButton jest przyciskiem do zmiany trybu kalkulatora.*/
class ModeButton extends JButton 
{
  /**Konstuktor klasy ModeButton.
    * @param p Obiekt klasy Program.
    * @param s Etykieta przycisku.*/
  ModeButton( Program p, String s )
  {
    super(s);
    addKeyListener( new MyFrameKeyAdapter(p) );
    addActionListener( new ModeButtonAdapter(p) );
    setBackground( new Color(255,255,80) );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,15) );
  }
}
/**InfoButtonAdapter wyswietla okno dialogowe wywolane przez InfoButton.*/
class InfoButtonAdapter implements ActionListener
{
  /**Obiekt p klasy Program.*/
  Program p;
  /**Konstruktor klasy InfoButtonAdapter.
    * @param p Obiekt klasy Program.*/
  InfoButtonAdapter( Program p ) { this.p = p; }
  /**Metoda wywolana przy kliknieciu przycisku InfoButton, ktora wyswietla okno dialogowe z informacjami o programie.
    * @param e Klnikniecie przycisku InfoButton.*/
  public void actionPerformed(ActionEvent e) { JOptionPane.showMessageDialog(p.frame,"Kalkulator operujacy na liczbach w systemie dziesietnym i binarnym.\n"
                                                                                    + "Wpisywanie danych jest mozliwe zarowno z klawiatury jak i za pomoca myszki.\n\n"
                                                                                    + "Obsluga kalkulatora:\n"
                                                                                    + "  1) Wprowadzamy pierwsza liczbe,\n"
                                                                                    + "  2) Wybieramy dzialanie,\n"
                                                                                    + "  3a) operacje na 1 liczbie: wyswietla sie wynik,\n"
                                                                                    + "  3b) operacje na 2 liczbach: wprowadzamy druga liczbe,\n"
                                                                                    + "  4) Aby otrzymac wynik klikamy '='.\n\n"
                                                                                    + "Kalkulator potrafi:\n"
                                                                                    + "  -dodawac (klawisz/przycisk '+'),\n"
                                                                                    + "  -odejmowac (klawisz/przycisk '-'),\n"
                                                                                    + "  -mnozyc (klawisz/przycisk '*'),\n"
                                                                                    + "  -dzielic calkowitoliczbowo (klawisz/przycisk '/'),\n"
                                                                                    + "  -zwracac reszte z dzielenia (klawisz/przycisk '%'),\n"
                                                                                    + " dodatkowo:\n"
                                                                                    + "  -klawisz/przycisk '=' wyswietla wynik,\n"
                                                                                    + "  -klawisz 'n'/przycisk '+/-' zmienia liczbe na przeciwna,"
                                                                                    + "  -klawisz 'c'/przycisk 'CE' resetuje kalkulator,\n"
                                                                                    + "  -klawisz 'd'/przycisk 'DEL' usuwa ostatnio wprowadzony na wyswietlacz znak,\n"
                                                                                    + "  -klawisz 'm'/przycisk 'Mode' zmienia tryb kalkulatora,\n"
                                                                                    + "  -klawisze 'b','d'/przyciski 'toBin', 'toDec' odpowiednio zmieniaja wprowadzona liczbe\n"
                                                                                    + "   na system binarny i dziesietny,\n"
                                                                                    + "  -klawisz 'i'/przycisk 'Info' wyswietla to okno.\n\n"
                                                                                    + "Autor: Radoslaw Kowalski"
                                                                                    ,"Info"
                                                                                    ,JOptionPane.PLAIN_MESSAGE); }
}
/**InfoButton jest przyciskiem do wyswietlania informacji o programie.*/
class InfoButton extends JButton 
{
  /**Konstruktor klasy InfoButton.
  * @param p Obiekt p klasy Program.
  * @param s Etykieta przycisku.*/
  InfoButton( Program p, String s )
  {
    super(s);
    addKeyListener( new MyFrameKeyAdapter(p) );
    addActionListener( new InfoButtonAdapter(p) );
    setBackground(new Color(80,255,80));
  }
}
/**InputButtonAdapter wywoluje wypisanie cyfry na wyswietlaczu.*/
class InputButtonAdapter implements ActionListener
{
  /**Obiekt klasy Program.*/
  Program p;
  /**Konstuktor klasy InputButtonAdapter.
    * @param p Obiekt p klasy Program.*/
  InputButtonAdapter( Program p ) { this.p = p; }
  public void actionPerformed(ActionEvent e) { p.write(e.getActionCommand()); }
}
/**InputButton jest przyciskiem do wprowadzania cyfr na wyswietlacz.*/
class InputButton extends JButton 
{
  /**Konstruktor klasy InputButton.
    * @param p Obiekt klasy Program.
    * @param s Etykieta przycisku i cyfra do wyswietlenia.*/
  InputButton( Program p, String s )
  {
    super(s);
    setActionCommand(s);
    addActionListener( new InputButtonAdapter(p) );
    addKeyListener( new MyFrameKeyAdapter(p) );
    setBackground( new Color(80,80,80) );
    setForeground( new Color(255,255,255) );
    setFont( new Font(Font.SANS_SERIF,Font.BOLD,18) );
  }
}
/**MyFrameKeyAdapter jest sluchaczem klawiatury.*/
class MyFrameKeyAdapter implements KeyListener {
  /**Obiekt p klasy Program.*/
  Program p;
  /**Konstruktor klasy MyFrameKeyAdapter.
    * @param p Obiekt p klasy program.*/
  MyFrameKeyAdapter(Program p) { this.p = p; }
  /**Metoda rozpoznaje wcisniety klawisz i wywoluje odpowiednie operacje.
    * @param e Klikniecie kalwisza.*/
  public void keyPressed(KeyEvent e) {
    
    switch( e.getKeyChar() ) 
    {
      case('2'):
      case('3'):
      case('4'):
      case('5'):
      case('6'):
      case('7'):
      case('8'):
      case('9'):
      if( p.activeKeyboardDec == false)
        break;
      case('0'):
      case('1'):  
        p.write( String.valueOf(e.getKeyChar()) );
        break;
      case('i'):
        { JOptionPane.showMessageDialog(p.frame,"Kalkulator operujacy na liczbach w systemie dziesietnym i binarnym.\n"
                                                                                    + "Wpisywanie danych jest mozliwe zarowno z klawiatury jak i za pomoca myszki.\n\n"
                                                                                    + "Obsluga kalkulatora:\n"
                                                                                    + "  1) Wprowadzamy pierwsza liczbe,\n"
                                                                                    + "  2) Wybieramy dzialanie,\n"
                                                                                    + "  3a) operacje na 1 liczbie: wyswietla sie wynik,\n"
                                                                                    + "  3b) operacje na 2 liczbach: wprowadzamy druga liczbe,\n"
                                                                                    + "  4) Aby otrzymac wynik klikamy '='.\n\n"
                                                                                    + "Kalkulator potrafi:\n"
                                                                                    + "  -dodawac (klawisz/przycisk '+'),\n"
                                                                                    + "  -odejmowac (klawisz/przycisk '-'),\n"
                                                                                    + "  -mnozyc (klawisz/przycisk '*'),\n"
                                                                                    + "  -dzielic calkowitoliczbowo (klawisz/przycisk '/'),\n"
                                                                                    + "  -zwracac reszte z dzielenia (klawisz/przycisk '%'),\n"
                                                                                    + " dodatkowo:\n"
                                                                                    + "  -klawisz/przycisk '=' wyswietla wynik,\n"
                                                                                    + "  -klawisz 'n'/przycisk '+/-' zmienia liczbe na przeciwna,"
                                                                                    + "  -klawisz 'c'/przycisk 'CE' resetuje kalkulator,\n"
                                                                                    + "  -klawisz 'e'/przycisk 'DEL' usuwa ostatnio wprowadzony na wyswietlacz znak,\n"
                                                                                    + "  -klawisz 'm'/przycisk 'Mode' zmienia tryb kalkulatora,\n"
                                                                                    + "  -klawisze 'b','d'/przyciski 'toBin', 'toDec' odpowiednio zmieniaja wprowadzona liczbe\n"
                                                                                    + "   na system binarny i dziesietny,\n"
                                                                                    + "  -klawisz 'i'/przycisk 'Info' wyswietla to okno.\n\n"
                                                                                    + "Autor: Radoslaw Kowalski"
                                                                                    ,"Info"
                                                                                    ,JOptionPane.PLAIN_MESSAGE); }
        break;
      case('m'):
        p.action();
        break;
      case('+'):
        p.operation("+");
        break;
      case('-'):
        p.operation("-");
        break;
      case('*'):
        p.operation("*");
        break;
      case('/'):
        p.operation("/");
        break;
      case('%'):
        p.operation("%");
        break;
      case('='):
        p.operation("=");
        break;
      case('c'):
        p.operation("CA");
        break;
      case('e'):
        p.operation("DEL");
        break;
      case('n'):
        p.operation("+/-");
        break;
      case('d'):
        p.operation("toDec");
        break;
      case('b'):
        p.operation("toBin");
        break;
    }
  }
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}
}
/**OperationPanel jest panelem przyciskow MyButton odpowiedzialnych za operacje i przycisku InfoButton wyswietlajacym informacje o programie.*/
class OperationsPanel extends Panel
{
  GridLayout GLayout = new GridLayout(5,2,5,5); 
  /**Konstruktor klasy OperationPanel.
    * Tworzy 10 przyciskow i dodaje je do panelu.
    * @param p Obiekt p klasy Program.*/
  OperationsPanel( Program p )

  {
    super();
    addKeyListener( new MyFrameKeyAdapter(p) );
    setLayout( GLayout );
    setPreferredSize( new Dimension(150,300) );
    requestFocusInWindow();
  
    InfoButton ButtonInf = new InfoButton(p,"info");
    MyButton ButtonCA = new MyButton(p,"CA");
    
    MyButton ButtonNegative = new MyButton(p,"+/-");
    MyButton ButtonDEL = new MyButton(p,"DEL");
    
    MyButton ButtonStar = new MyButton(p,"*");
    MyButton ButtonSlash = new MyButton(p,"/");
    
    MyButton ButtonPlus = new MyButton(p,"+");
    MyButton ButtonPerc = new MyButton(p,"%");
    
    MyButton ButtonMinus = new MyButton(p,"-");
    MyButton ButtonEXE = new MyButton(p,"=");
    
    ButtonCA.setBackground(new Color(255,80,80));
    ButtonDEL.setBackground(new Color(255,80,80));
    ButtonEXE.setBackground(new Color(255,80,80));
    
    add(ButtonInf);
    add(ButtonCA);
    
    add(ButtonNegative);
    add(ButtonDEL);
    
    add(ButtonStar);
    add(ButtonSlash);
    
    add(ButtonPlus);
    add(ButtonPerc);
    
    add(ButtonMinus);
    add(ButtonEXE);
  }
}
/**KeyboardDecPanel jest panelem przyciskow
  * InputButton wprowadzajacych cyfry na wyswietlacz, 
  * ModeButton zmieniajacego tryb, 
  * MyButton obslugujacego operacje zmiany liczby na system binarny.*/
class KeyboardDecPanel extends Panel
{
  GridLayout GLayout = new GridLayout(4,3,5,5); 
  /**Konstruktor klasy KeyboardDecPanel.
    * Tworzy przyciski: do wprowadzania cyfr, do zmiany trybu, do zmiany systemu i dodaje je do panelu.
    * @param p Obiekt p klasy Program.*/
  KeyboardDecPanel( Program p )
  {
    super();
    addKeyListener( new MyFrameKeyAdapter(p) );
    setLayout( GLayout );
    setPreferredSize(new Dimension(250,300));
    
    InputButton Button7 = new InputButton(p,"7");
    InputButton Button8 = new InputButton(p,"8");
    InputButton Button9 = new InputButton(p,"9");
    
    InputButton Button4 = new InputButton(p,"4");
    InputButton Button5 = new InputButton(p,"5");
    InputButton Button6 = new InputButton(p,"6");
    
    InputButton Button1 = new InputButton(p,"1");
    InputButton Button2 = new InputButton(p,"2");
    InputButton Button3 = new InputButton(p,"3");
    
    ModeButton ButtonMode = new ModeButton(p,"Mode");
    InputButton Button0 = new InputButton(p,"0");
    MyButton ButtonToBin = new MyButton(p,"toBin");
    
    ButtonMode.setBackground(new Color(80,80,255));
    
    add(Button7);
    add(Button8);
    add(Button9);
    
    add(Button4);
    add(Button5);
    add(Button6);
    
    add(Button1);
    add(Button2);
    add(Button3);
    
    add(ButtonMode);
    add(Button0);
    add(ButtonToBin);
  }
}
/**KeyboardBinPanel jest panelem przyciskow 
  * InputButton wprowadzajacych cyfry na wyswietlacz, 
  * ModeButton zmieniajacego tryb, 
  * MyButton obslugujacego operacje zmiany liczby na system dziesietny.*/
class KeyboardBinPanel extends Panel
{
  GridLayout GLayout = new GridLayout(2,2,5,5); 
  /**Konstruktor klasy KeyboardBinPanel.
  * Tworzy przyciski: do wprowadzania cyfr, do zmiany trybu, do zmiany systemu i dodaje je do panelu.
  * @param p Obiekt p klasy Program.*/
  KeyboardBinPanel( Program p )
  {
    super();
    addKeyListener( new MyFrameKeyAdapter(p) );
    setLayout( GLayout );
    setPreferredSize(new Dimension(250,300));

    InputButton Button0 = new InputButton(p,"0");
    InputButton Button1 = new InputButton(p,"1");
    
    ModeButton ButtonMode = new ModeButton(p,"Mode");
    MyButton ButtonToDec = new MyButton(p,"toDec"); 
    
    ButtonMode.setBackground(new Color(100,100,255));
    
    add(Button0);
    add(Button1);
    
    add(ButtonMode);
    add(ButtonToDec);
  }
}
/**MyFrame tworzy okno aplikacji i ustawia jego parametry.*/
class MyFrame extends Frame
{
  /**Konstruktor klasy MyFrame.
    * Okresla wyglad okna aplikacji, jego zawartosc i uklad.
    * @param p Obiekt p klasy Program.*/
  MyFrame( Program p )
  {
    super("Kalkulator Dec/Bin");    
    addKeyListener( new MyFrameKeyAdapter(p) );
    addWindowListener( new MyWindowAdapter() );
    setFont( new Font(Font.SANS_SERIF,Font.PLAIN,18) );
    setLayout( new BorderLayout(10,10) );
    setPreferredSize( new Dimension(420,350) );
    setMinimumSize( new Dimension(420,350) );
    setBackground( new Color(30,30,30) );
    setFocusable(true);
    requestFocusInWindow();
   
    p.inputLabel = new Label("",Label.RIGHT);
    p.operationsPanel = new OperationsPanel(p);
    p.keyboardDec = new KeyboardDecPanel(p);
    p.keyboardBin = new KeyboardBinPanel(p);
    
    p.inputLabel.setPreferredSize( new Dimension(0,30) );
    p.inputLabel.setBackground( new Color(255,255,255) );
    p.inputLabel.setFocusable(true);
    
    add(p.inputLabel,BorderLayout.NORTH);
    add(p.keyboardDec,BorderLayout.CENTER);
    add(p.operationsPanel,BorderLayout.EAST);
  }
}
/**Program jest glowna klasa aplikacji.*/
public class Program 
{
  /**Obiekt operations klasy Operations.*/
  Operations operations;
  MyFrame frame;
  OperationsPanel operationsPanel;
  KeyboardDecPanel keyboardDec;
  KeyboardBinPanel keyboardBin;
  /**inputLabel jest wyswietlaczem kalkulatora.*/
  Label inputLabel;
  /**activeKeyboardDec pamieta obecnie aktywny tryb kalkulatora.*/
  boolean activeKeyboardDec = true;
  String operation;
  String input1;
  String input2;
  String result;
  
  /**Metoda zmienia tryb kalkulatora.
    * Po zmianie przelacza activeKeyboardDec na przeciwna wartosc logiczna i czysci wyswietlacz.*/
  void action()
  {    
    if( activeKeyboardDec == true)
    {
      frame.remove(keyboardDec);
      frame.add(keyboardBin,BorderLayout.CENTER);
      activeKeyboardDec = false;
      frame.pack();
      frame.setLocationRelativeTo(null);
    }
    else
    {
      frame.remove(keyboardBin);
      frame.add(keyboardDec,BorderLayout.CENTER);
      activeKeyboardDec = true;
      frame.pack();
      frame.setLocationRelativeTo(null);
    }
    inputLabel.setText("");
  }
  
  /**Metoda wprowadza na wyswietlacz cyfre po aktualnym ciagu znakow, chyba ze dlugosc liczby przekroczy 33 znaki.
    * @param s Cyfra do wprowadzenia.*/
  void write(String s)
  {
    if( (inputLabel.getText()).length() < 33 )
      inputLabel.setText( inputLabel.getText() + s );
  }
  
  /**Metoda wykonuje odpowiednia operacje zalezna od wcisnietego przycisku lub klawisza.
    * @param s Okresla jaka operacje wykonac.
    * @throws NumberFormatException przy wystapieniu wyjatku zadna akcja nie jest wykonywana.*/
  void operation(String s)
  {
    
    switch(s)
    {
      case("+"):
        operation = "+";
        input1 = inputLabel.getText();
        inputLabel.setText("");
        break;
      case("-"):
        operation = "-";
        input1 = inputLabel.getText();
        inputLabel.setText("");
        break;
      case("*"):
        operation = "*";
        input1 = inputLabel.getText();
        inputLabel.setText("");
        break;
      case("/"):
        operation = "/";
        input1 = inputLabel.getText();
        inputLabel.setText("");
        break;
      case("%"):
        operation = "%";
        input1 = inputLabel.getText();
        inputLabel.setText("");
        break;
      case("CA"):
        inputLabel.setText("");
        operation = null;
        break;
      case("DEL"):
        if( (inputLabel.getText()).length() != 0 )
          inputLabel.setText( (inputLabel.getText()).substring( 0, (inputLabel.getText()).length()-1 ) );
        break;
      case("+/-"):
        if( !(inputLabel.getText()).startsWith("-") )
          inputLabel.setText("-" + inputLabel.getText());
        else
          inputLabel.setText( (inputLabel.getText()).substring( 1, (inputLabel.getText()).length() ) );
        break;
      case("="):
        input2 = inputLabel.getText();
        try
        {
          switch(operation)
          {
            case("+"):
              inputLabel.setText( operations.plus(input1,input2,activeKeyboardDec) );
              break;
            case("-"):
              inputLabel.setText( operations.minus(input1,input2,activeKeyboardDec) );
              break;
            case("*"):
              inputLabel.setText( operations.star(input1,input2,activeKeyboardDec) );
              break;
            case("/"):
              inputLabel.setText( operations.slash(input1,input2,activeKeyboardDec) );
              break;
            case("%"):
              inputLabel.setText( operations.perc(input1,input2,activeKeyboardDec) );
              break;
          } 
          input1 = input2;
          input2 = inputLabel.getText();
        } catch(NumberFormatException e){
          break;
        }
        
        break;
      case("toBin"):
        operation = "toBin";
        input1 = inputLabel.getText();
        action();
        try
        {
          Long.parseLong(input1);
          inputLabel.setText( operations.toBin(input1) ); 
        }  catch(NumberFormatException e){}
        break;
      case("toDec"):
        operation = "toDec";
        input1 = inputLabel.getText();
        action();
        try{ inputLabel.setText( operations.toDec(input1) ); }  catch(NumberFormatException e){}
        break;
    }
  }
  
  /**Glowna metoda klasy Program.
    * Ustawia wielkosc okna aplikacji i nastepnie ja wyswietla.
    * Aby ustawic niestandardowa wielkosc nalezy wprowadzic dwa argumenty uruchomienia.
    * Jesli wymiary beda za male lub nie zostana podane aplikacja uruchomi sie w rozmiarze 420px/380px.
    * @param args[0] szerokość aplikacji
    * @param args[1] wysokość aplikacji*/
  public static void main(String[] args)
  {
    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    Program p = new Program();
    p.frame = new MyFrame(p);
    int width, heigth;
    
    if( args.length == 2 )
    {
      width = Integer.parseInt(args[0]);
      heigth = Integer.parseInt(args[1]);
    
      if( width >= 420 && heigth >= 380 )
        p.frame.setPreferredSize( new Dimension( width, heigth ) );
      else
      {
        JOptionPane.showMessageDialog(p.frame, "Program nie zostanie uruchomiony.\nMinimalny wymiar okna to 420x380.");
        System.exit(0);
      }
    }
    
    p.frame.pack();
    p.frame.setLocationRelativeTo(null);
    p.frame.setResizable(true);
    p.frame.setVisible(true);
  }
}