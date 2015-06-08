/**Klasa definiujaca wezel drzewa.*/
class Node<K extends Comparable<K>,V>
{
  /**Klucz wezla*/
  K key;
  /**Przechiwywana wartosc wezla*/
  V value;
  /**Wskazanie na rodzica wezla*/
  Node<K,V> parent;
  /**Wskazanie na lewe dziecko*/
  Node<K,V> left;
  /**Wskazanie na prawe dziecko*/
  Node<K,V> right;
  
  /**Konstruktor klasy Node.
   * @param key klucz wezla
   * @param value warotsc wezla*/
  Node( K key, V value )
  {
    this.key = key;
    this.value = value;
    left = null;
    right = null;
  }
}

/**Klasa definiujaca drzewo binarne*/
public class BinarySearchTree<K extends Comparable<K>,V>
{
  /**Wskazanie na korzen drzewa*/
  private Node<K,V> root;
  /**Konstruktor tworzacy puste drzewo*/
  public BinarySearchTree() { root = null; }
  
  /**Metoda wyszukujaca wartosc po kluczu.
   * @param key klucz, wedlug ktorego wyszukiwany jest wezel
   * @return zwraca wartosc wezla spod klucza key*/
  public V searchValueByKey(K key)
  {
    try{
      Node<K,V> node = searchNode(root, key);   //wywolanie wyszukiwania wezla o kluczu key
      if( node != null )                        //jesli znaleziono wezel zwroc jego wartosc
        return node.value;
      else                                      //jesli nie znalezione wezla zwroc null
        return null; 
    } catch( Exception e ){ return null; }
  }
  /**Metoda wyszukujaca wezel po kluczu.
   * @param x korzen drzewa
   * @param key klucz, wedlug ktorego wyszukiwany jest wezel
   * @return zwraca wezel o podanym kluczu*/
  public Node<K,V> searchNode(Node<K,V> x, K key)   //x na poczatku jest korzeniem, szukamy klucza key
  {
    try{
      while( x != null && key != x.key )              //gdy x (korzen) nie jest pusty i klucz nie jest kluczem aktualnego x
        if( key.compareTo(x.key) < 0 )                //porownanie key i klucza x     
          x = x.left;                                 //jesli key jest niejszy przechodzimy do lewego poddrzewa x
        else
          x = x.right;                                //w przeciwnym wypadku do prawego poddrzewa
      return x;                                       //zwracamy wezel w ktorym wartosci kluczy sa rowne
    } catch( Exception e ){ return null; }
  }
  /**Metoda wstawiajaca nowy wezel do drzewa.
   * @param key klucz do wstawienia
   * @param value wartosc do wstawienia*/
  public void insert(K key, V value) //throws TreeException
  {
    try{
      Node<K,V> node = new Node<>(key, value);  //tworzymy nowy wezel, ktory wstawimy w odpowiednie miejsce
      Node<K,V> x = root;                       //przegladanie zaczynamy w korzeniu, x przebiega po sciezce
      Node<K,V> y = null;                       //y zawiera zawsze wskazanie na ojca x
      while( x != null )                        //x i y sa przesuwane w dol drzewa w prawo lub lewo zaleznie od porownania node.key z x.key az x przyjmie wartosc null
      {
        y = x;
        if( (node.key).compareTo(x.key) < 0 )
          x = x.left;
        else
          x = x.right;
      }
      node.parent = y;                          //znalezlismy miejsce do wstawienia wezla, wiec y wskazuje na rodzica naszego nowego wezla
      if( y == null )                           //jesli nie da sie znalezc rodzica drzewo jest puste, wstawiamy wezel jako korzen
        root = node;
      else
      {
        if( (node.key).compareTo(y.key) < 0 )   //sprawdzamy czy wezel nalezy przypisac po lewej czy prawej stronie y (rodzica)
          y.left = node;
        else
          y.right = node;
      }
    } catch( Exception e ){}
  }
  /**Metoda usuwająca wezel z drzewa.
   * @param key klucz do usuniecia z drzewa*/
  public void delete(K key)
  {
    try{
      Node<K,V> y;                                  //y to wezel, ktory zostanie ostatecznie usuniety z drzewa
      Node<K,V> x;                                  //x pomocniczy wezel
      Node<K,V> node = searchNode(root, key);       //wezel ktorego klucz jest argumentem metody
      
      if( node.left == null || node.right == null)  //jesli node ma co najwyzej jednego syna y jest wezlem wejsciowym node
        y = node;
      else                                          //jesli node ma 2 synow y jest nastepnikiem (nastepna wartoscia wieksza od node) node
        y = succesor(node);
      if( y.left != null )                          //jesli y ma lewego syna to zapisz go pod x
        x = y.left;
      else                                          //jesli y ma prawego syna to zapisz go pod x  
        x = y.right;
      if( x != null )                               //111-119 wezel y zostaje usuniety
        x.parent = y.parent;                        //jesli pod x przypisano jakas wartosc to x i y sa w tym samym miejscu drzewa
      if( y.parent == null )
        root = x;                                   //jesli y nie ma rodzica (jest korzeniem) to korzeniem staje sie x
      else                                          //w przeciwnym wypadku
        if( y == (y.parent).left )                  //jesli y jest lewym dzieckiem zamien je na x
          (y.parent).left = x;
        else                                        //jesli y jest prawym dzieckiem zamien je na x
          (y.parent).right = x;
      if( y != node )                               //zawartosc y zostaje przepisana do node
      {
        node.key = y.key;
        node.value = y.value;
      }
    } catch( Exception e ){}
  }
  /**Metoda wypisujaca wartosci w drzewie w sposob uporzatkowany wedlug kluczy
   * @param x korzen drzewa*/
  public void inorder(Node<K,V> x)
  {
    try{
      if( x != null)
      {
        inorder(x.left);
        System.out.println(x.value);
        inorder(x.right);
      }
    } catch( Exception e ){}
  }
  /**Metoda wyszukujaca nastepnik wezla
   * @param x wezel, ktorego nastepnika szukamy
   * @return zwraca wezel o kluczu kolejnym wzgledem x*/
  public Node<K,V> succesor(Node<K,V> x)
  {
    try{
      if( x.right != null )             //jesli prawe poddrzewo x nie jest puste to nastepnikiem jest wezel najbardziej z lewej
        return minimum(x.right);
      Node<K,V> y = x.parent;           //y jest rodzicem x
      while( y != null && x == y.right) //jesli x nie ma prawego poddrzewa, ale ma nastepnik y to y jest najnizszym przodkiem x, ktorego lewy syn tez jest przodkiem x
      {
        x = y;                          //przechodzimy do kolejnych rodzicow zaczynajac od y az dojdziemy do korzenia lub x nie bedzie prawym dzieckiem swojego rodzica
        y = y.parent;
      }
      return y;                         //zwracamy y, ktory jest nastepnikiem x
    } catch( Exception e ){ return null; }
  }
  /**Metoda wyznaczajaca minimum w poddrzewie danego wezla
   * @param x wezel od ktorego zaczynamy poszukiwanie
   * @return zwraca wezel o najmniejszym kluczu*/
  public Node<K,V> minimum(Node<K,V> x)
  {
    try{
      while( x.left != null ) //podazamy po lewych dzieciach az dojdziemy do liscia czyli wezla o najmniejszym kluczu w danym poddrzewie
        x = x.left;
      return x;
    } catch( Exception e ){ return null; }
  }
  
  public static void main(String[] args)
  {
    BinarySearchTree<Integer,Integer> t = new BinarySearchTree<Integer,Integer>();
    try{
      System.out.println("Wprowadzam 2,3,5,7,11");
      t.insert(5,5);
      t.insert(2,2);
      t.insert(3,3);
      t.insert(7,7);
      t.insert(11,11);
      
      System.out.println("Teraz wypisuje 2,3,5,7,11");
      t.inorder(t.root);
      
      System.out.println("Teraz usuwam 11");
      t.delete(11);
      
      System.out.println("Teraz wypisuje 2,3,5,7");
      t.inorder(t.root);
      
      System.out.println("Wprowadzam 20,1 i usuwam 3, 20");
      t.insert(20,20);
      t.insert(1,1);
      t.delete(3);
      t.delete(20);
      
      System.out.println("Teraz wypisuje 1,2,5,7");
      t.inorder(t.root);
      
      System.out.println("Szukam wartosci pod kluczem 5: \n" 
                        + t.searchValueByKey(5));
      System.out.println("Szukam wartosci pod kluczem 3: \n" 
                        + t.searchValueByKey(3));
      System.out.println("Szukam wartosci pod kluczem 11: \n" 
                        + t.searchValueByKey(11));
    } catch( Exception e ){}
  }
}