package ro.deiutzblaxo.bank.utils.datastructure;

public class ListaLiniara<T> {

    private final int maxAmount; // numarul maxim de noduri in aceasta lista ,
    private int size = 0; // dimensiunea listei
    private Node<T> head;// primul nod din lista

    /**
     * Constructorul pentru clasa ListaLiniara<T> care are maximul de elemente =
     * Integer.MAX_VALUE
     *
     * @param value - primul element din lista
     */
    public ListaLiniara(T value) {
        head = new Node<T>(value);// declaram ca primul nod din lista este un nou nod de tip t cu valoarea value
        maxAmount = Integer.MAX_VALUE; // numarul maxim de noduri in aceasta lista este de 2,147,483,647
        size++; // incrementam cu 1 dimensiunea listei
    }

    /**
     * Constructorul pentru clasa ListaLiniara<T> care are maximul de elemente =
     * maxAmount
     *
     * @param value     - primul element din lista
     * @param maxAmount - numarul maxim de elemente
     */
    public ListaLiniara(T value, int maxAmount) {

        head = new Node<T>(value);// declaram ca primul nod din lista este un nou nod de tip t cu valoarea value
        this.maxAmount = maxAmount;// numarul maxim de noduri in aceasta lista este de maxAmount
        size++; // incrementam cu 1 dimensiunea listei

    }

    /**
     * Constructorul pentru clasa ListaLiniara<T> care are maximul de elemente =
     * Integer.MAX_VALUE
     */
    public ListaLiniara() {
        this.maxAmount = Integer.MAX_VALUE;// setam doar numarul maxim de elemente 2,147,483,647

    }

    /**
     * Constructorul pentru clasa ListaLiniara<T> care are maximul de elemente =
     * maxAmount
     *
     * @param maxAmount - numarul maxim de elemente
     */
    public ListaLiniara(int maxAmount) {
        this.maxAmount = maxAmount;// setam doar numarul maxim de elemente maxAmount
    }



    /**
     * Se adauga elementul in lista pe ultima pozitie
     *
     * @param value - elementul care se adauga
     */
    public void add(T value) {
        if (isFull()) {// daca este plin
            head = head.getNext();
            size -=1;
    }
        if (head == null) { // daca head este null
            head = new Node<T>(value);// cream mai intai head ul
            size++;// incrementam dimensiunea listei
            return;
        }
        Node<T> node = head;// un variabila temporala care este setat ca si head deoarece head este primul
        // nod din
        // lista
        while (node.getNext() != null) { // cat timp exista un urmator nod
            node = node.getNext();// nodului temporar ii setam urmatorul nod

        }
        node.setNext(new Node<T>(value));// // setam la ultimul nod valoarea
        size++;// incrementam dimensiunea listei
    }

    /**
     * Se adauga elementul in lista pe pozitia i
     *
     * @param i     - pozitia noului element
     * @param value - elementul care se adauga
     *
     */
    public void add(int i, T value) {
        if (isFull()) { // daca este plin
            head = head.getNext();
        size -=1;
        }
        if (isEmpty()) { // daca este gol
            head = new Node<T>(value, null);// primul nod este un nou nod cu valoarea value si urmatorul nod null
            size++;// incrementam dimensiunea listei
            return;
        }
        if (i == size) { // daca pozitia i a noului element este la finalul listei
            add(value); // o sa folosim metoda add() pentru comoditate.
            return;
        } else if (i > size - 1) {// daca i este peste dimensiunea listei
            throw new RuntimeException("Index inafara listei.");

        }
        Node<T> prev, next, now;// variabile temporare
        prev = get(i - 1);// nodul inainte de noul nod de la pozitia i
        next = get(i);// nodul care este acum la pozitia i si va fii urmatorul nod
        now = new Node<T>(value, next);// nodul nou care va fii la pozitia i
        prev.setNext(now);// setam la nodul antetior de pozitia i ca noul nod este acum urmatorul nod
        size++;// incrementam dimensiunea listei

    }

    public int size(){
        return size;
    }

    /**
     * Se sterge elementul
     *
     * @param i - pozitia elementului care sa fie sters
     */
    public void remove(int i) {
        if (i < 0) {// daca i este mai mic de cat 0
            throw new RuntimeException("Aceasta pozitie nu exista");

        }
        if (i == 0) {// daca i este 0
            if (head == null) { // daca primul nod este null
                throw new RuntimeException("UNDERFLOW");

            }
            head = head.getNext();// primul nod este urmatorul lu primul
            size--;// scadem cu 1
            return;
        }
        if (i + 1 > size) {// daca i este peste dimensiunea listei
            throw new RuntimeException("Index inafara listei.");
        }
        Node<T> prev, next;// variabile temporare

        prev = get(i - 1);// nodul de la pozitia i-1
        next = get(i + 1);// nodul de la pozitia i+1
        prev.setNext(next);// setam urmatorul nod la nodul i-1 ca este nodul din pozitia i+1
        size--;// scadem cu 1
    }


    public Node<T> get(T value) throws Exception {

        if (isEmpty()) {// daca este gol
            System.out.println("Lista este vida : UNDERFLOW");
            return null;
        }

        Node<T> nodeNow = head;
        while (nodeNow.getNext() != null && nodeNow.getNext().getValue() != value) {
            nodeNow = nodeNow.getNext();

        }
        if (nodeNow.getNext() != null) {

            return nodeNow.getNext();
        } else {
            throw new Exception("Elementul nu exista in lista");
        }


    }
    public Node<T> get(int i) {

        if (isEmpty()) {// daca este gol
            System.out.println("Lista este vida : UNDERFLOW");
            return null;
        }
        if (i > size - 1) {// daca i este peste dimensiunea listei
            throw new RuntimeException("Index inafara listei.");

        }
        int pos = 0;// pozitia unde ne aflam
        Node<T> now = head;// un variabila temporala care este setat ca si head deoarece head este primul nod din lista
        while (pos < i) {// cat timp i este mai mare de cat pozitie
            pos++;// incrementare
            now = now.getNext();// variabila temporala
        }
        return now;

    }

    /**
     * Se modifica elementul
     *
     * @param i     - pozitia elementului care sa fie sters
     * @param value - valoarea noua
     */
    public void modifyNode(int i, T value) {
        if (i < 0) {// daca i este mai mic de cat 0
            throw new RuntimeException("Aceasta pozitie nu exista");
        }
        if (i == 0) {// daca i este 0 atunci inseamna ca index este varful
            head.setValue(value);// schimbam valoarea varfului
            return;
        }
        get(i).setValue(value);// atunci schimbam valoarea nodului de la pozitia i
    }

    /**
     * Verifica daca lista este full
     *
     * @return daca este lista full
     */
    public boolean isFull() {
        return size == maxAmount;
    }

    /**
     * Se verifica daca este lista goala
     *
     * @return - este goala lista sau nu
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Verifica dimensiunea listei
     *
     * @return - dimensiunea listei
     */

    public int getSize() {
        return size;
    }

    /**
     *
     * @return - cat de mare poate sa fie lista
     */
    public int getMax() {
        return maxAmount;
    }
    @Override
    public String toString() {
        if (!isEmpty()) {
            Node<?> node = head;
            String str = "[ " + head.toString();
            while (node.getNext() != null) {

                node = node.getNext();
                str += " , " + node.toString();

            }

            return str + " ]";
        } else {
            return "[]";
        }
    }



}
