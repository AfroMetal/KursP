#include <iostream>
#include <exception>
#include <functional>

using namespace std;

/**Klasa definiujaca wezel drzewa.*/
template <typename K, typename V>
class Node
{
public:
  /**Klucz wezla*/
  K key;
  /**Przechiwywana wartosc wezla*/
  V value;
  /**Wskazanie na rodzica wezla*/
  Node<K,V>* parent;
  /**Wskazanie na lewe dziecko*/
  Node<K,V>* left;
  /**Wskazanie na prawe dziecko*/
  Node<K,V>* right;
  
  /**Konstruktor klasy Node.
   * @param key klucz wezla
   * @param value warotsc wezla*/
  Node( K key, V value )
  {
    this->key = key;
    this->value = value;
    left = 0;
    right = 0;
  }
  
  ~Node()
  {
    parent = 0;
    left = 0;
    right = 0;
  }
};

/**Klasa definiujaca drzewo binarne*/
template <typename K, typename V>
class BinarySearchTree
{
public:
  /**Wskazanie na korzen drzewa*/
  Node<K,V>* root;
  /**Konstruktor tworzacy puste drzewo*/
public:
  BinarySearchTree() { root = 0; }
  
  ~BinarySearchTree() {}
  
  /**Metoda wyszukujaca wartosc po kluczu.
   * @param key klucz, wedlug ktorego wyszukiwany jest wezel
   * @return zwraca wartosc wezla spod klucza key*/
  V searchValueByKey(K key)
  {
    Node<K,V>* node = searchNode(root, key);   //wywolanie wyszukiwania wezla o kluczu key
    if( node != 0 )                        //jesli znaleziono wezel zwroc jego wartosc
      return node->value;
    else                                      //jesli nie znalezione wezla zwroc null
      return 0; 
  }
  /**Metoda wyszukujaca wezel po kluczu.
   * @param x korzen drzewa
   * @param key klucz, wedlug ktorego wyszukiwany jest wezel
   * @return zwraca wezel o podanym kluczu*/
  Node<K,V>* searchNode(Node<K,V>* x, K key)   //x na poczatku jest korzeniem, szukamy klucza key
  {
    while( x != 0 && key != x->key )              //gdy x (korzen) nie jest pusty i klucz nie jest kluczem aktualnego x
      if( key < x->key )                //porownanie key i klucza x     
        x = x->left;                                 //jesli key jest niejszy przechodzimy do lewego poddrzewa x
      else
        x = x->right;                                //w przeciwnym wypadku do prawego poddrzewa
    return x;                                       //zwracamy wezel w ktorym wartosci kluczy sa rowne
  }
  /**Metoda wstawiajaca nowy wezel do drzewa.
   * @param key klucz do wstawienia
   * @param value wartosc do wstawienia*/
  void insert(K key, V value) //throws TreeException
  {
    Node<K,V>* node = new Node<K,V>(key, value);  //tworzymy nowy wezel, ktory wstawimy w odpowiednie miejsce
    Node<K,V>* x = root;                       //przegladanie zaczynamy w korzeniu, x przebiega po sciezce
    Node<K,V>* y = 0;                       //y zawiera zawsze wskazanie na ojca x
    while( x != 0 )                        //x i y sa przesuwane w dol drzewa w prawo lub lewo zaleznie od porownania node.key z x.key az x przyjmie wartosc null
    {
      y = x;
      if( node->key < x->key )
        x = x->left;
      else
        x = x->right;
    }
    node->parent = y;                          //znalezlismy miejsce do wstawienia wezla, wiec y wskazuje na rodzica naszego nowego wezla
    if( y == 0 )                           //jesli nie da sie znalezc rodzica drzewo jest puste, wstawiamy wezel jako korzen
      root = node;
    else
    {
      if( node->key < y->key )   //sprawdzamy czy wezel nalezy przypisac po lewej czy prawej stronie y (rodzica)
        y->left = node;
      else
        y->right = node;
    }
  }
  /**Metoda usuwająca wezel z drzewa.
   * @param key klucz do usuniecia z drzewa*/
  void del(K key)
  {
    Node<K,V>* y;                                  //y to wezel, ktory zostanie ostatecznie usuniety z drzewa
    Node<K,V>* x;                                  //x pomocniczy wezel
    Node<K,V>* node = searchNode(root, key);       //wezel ktorego klucz jest argumentem metody
    
    if( node->left == 0 || node->right == 0 )  //jesli node ma co najwyzej jednego syna y jest wezlem wejsciowym node
      y = node;
    else                                          //jesli node ma 2 synow y jest nastepnikiem (nastepna wartoscia wieksza od node) node
      y = succesor(node);
    if( y->left != 0 )                          //jesli y ma lewego syna to zapisz go pod x
      x = y->left;
    else                                          //jesli y ma prawego syna to zapisz go pod x  
      x = y->right;
    if( x != 0 )                               //111-119 wezel y zostaje usuniety
      x->parent = y->parent;                        //jesli pod x przypisano jakas wartosc to x i y sa w tym samym miejscu drzewa
    if( y->parent == 0 )
      root = x;                                   //jesli y nie ma rodzica (jest korzeniem) to korzeniem staje sie x
    else                                          //w przeciwnym wypadku
      if( y == (y->parent)->left )                  //jesli y jest lewym dzieckiem zamien je na x
        (y->parent)->left = x;
      else                                        //jesli y jest prawym dzieckiem zamien je na x
        (y->parent)->right = x;
    if( y != node )                               //zawartosc y zostaje przepisana do node
    {
      node->key = y->key;
      node->value = y->value;
    }
  }
  /**Metoda wypisujaca wartosci w drzewie w sposob uporzatkowany wedlug kluczy
   * @param x korzen drzewa*/
  void inorder(Node<K,V>* x)
  {
    if( x != 0 )
    {
      inorder(x->left);
      cout << x->value << endl;
      inorder(x->right);
    }
  }
  /**Metoda wyszukujaca nastepnik wezla
   * @param x wezel, ktorego nastepnika szukamy
   * @return zwraca wezel o kluczu kolejnym wzgledem x*/
  Node<K,V>* succesor(Node<K,V>* x)
  {
    if( x->right != 0 )             //jesli prawe poddrzewo x nie jest puste to nastepnikiem jest wezel najbardziej z lewej
      return minimum(x->right);
    Node<K,V>* y = x->parent;           //y jest rodzicem x
    while( y != 0 && x == y->right) //jesli x nie ma prawego poddrzewa, ale ma nastepnik y to y jest najnizszym przodkiem x, ktorego lewy syn tez jest przodkiem x
    {
      x = y;                          //przechodzimy do kolejnych rodzicow zaczynajac od y az dojdziemy do korzenia lub x nie bedzie prawym dzieckiem swojego rodzica
      y = y->parent;
    }
    return y;                         //zwracamy y, ktory jest nastepnikiem x
  }
  /**Metoda wyznaczajaca minimum w poddrzewie danego wezla
   * @param x wezel od ktorego zaczynamy poszukiwanie
   * @return zwraca wezel o najmniejszym kluczu*/
  Node<K,V>* minimum(Node<K,V>* x)
  {
    while( x->left != 0 ) //podazamy po lewych dzieciach az dojdziemy do liscia czyli wezla o najmniejszym kluczu w danym poddrzewie
      x = x->left;
    return x;
  }
};

int main(int argc, char* argv[])
{
  BinarySearchTree<int, int>* t = new BinarySearchTree<int, int>();
  
  cout << "Wprowadzam 2,3,5,7,11" << endl;
  t->insert(5,5);
  t->insert(2,2);
  t->insert(3,3);
  t->insert(7,7);
  t->insert(11,11);
  
  cout << "Teraz wypisuje 2,3,5,7,11" << endl;
  t->inorder(t->root);
  
  cout << "Teraz usuwam 11" << endl;
  t->del(11);
  
  cout << "Teraz wypisuje 2,3,5,7" << endl;
  t->inorder(t->root);
  
  cout << "Wprowadzam 20,1 i usuwam 3, 20" << endl;
  t->insert(20,20);
  t->insert(1,1);
  t->del(3);
  t->del(20);
  
  cout << "Teraz wypisuje 1,2,5,7" << endl;
  t->inorder(t->root);
  
  cout << "Szukam wartosci pod kluczem 5: \n" << t->searchValueByKey(5) << endl;
  cout << "Szukam wartosci pod kluczem 3: \n" << t->searchValueByKey(3) << endl;
  cout << "Szukam wartosci pod kluczem 11: \n" << t->searchValueByKey(11) << endl;
  
  return 0;
};
  
