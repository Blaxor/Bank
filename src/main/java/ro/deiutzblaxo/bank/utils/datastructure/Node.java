package ro.deiutzblaxo.bank.utils.datastructure;

/**
 *
 * Node reprezinta fiecare nod al listei liniare
 *
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    /**
     *
     * Constructorul aceste primeste 2 parametrii , value si next.
     *
     * @param value - valoarea pe care o stocheaza nodul
     * @param next  - urmatorul nod
     */
    public Node(T value, Node<T> next) {
        this.value = value; // ii setam valoarea acestui nod
        this.next = next;// ii setam care este urmatorul nod

    }

    public Node(T value) {
        this.value = value;// ii setam valoarea acestui nod
        next = null;// ii supenm ca nu are un urmator nod

    }

    public Node<T> getNext() {
        return next;// returnam urmatorul nod
    }

    public void setNext(Node<T> next) {
        this.next = next;// setam urmatorul nod
    }

    public T getValue() {
        return value;// returnam valoarea acestui nod
    }

    public void setValue(T value) {
        this.value = value;// setam valoarea acestui nod
    }

    @Override
    public String toString() {

        return value.toString();// returnam o valoare de tip string care reprezinta traducerea acestui nod in
        // String
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node<?>) {
            Node<?> t = (Node<?>) obj;
            if (t.value == this.value) {
                return true;
            }
        }
        return false;
    }


}