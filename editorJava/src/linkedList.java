import java.util.Iterator;

/**
 * Created by Yining on 7/17/18.
 */
public class linkedList implements Iterable{
    protected Node sentinel;
    protected int length;

    public linkedList(){
        this.sentinel = new Node();
    }

    public void insert(Object o) {
        if (sentinel.next == null || sentinel.prev == null) {
            Node insertNode = new Node();
            insertNode.setO(o);
            insertNode.next = sentinel;
            insertNode.prev = sentinel;
            sentinel.next = insertNode;
            sentinel.prev = insertNode;
            length++;
        } else {
            Node insertNode = new Node();
            insertNode.setO(o);
            insertNode.next = sentinel;
            insertNode.prev = sentinel.prev;
            sentinel.prev.next = insertNode;
            sentinel.prev = insertNode;
            length++;
        }

    }

//  removes right value left of sentinel
    public void remove() {

    }

//    move the sentinel
    public void moveSentinel() {

    }

    @Override
    public Iterator iterator() {
        Iterator iter = new Iterator() {

            private Node curr = sentinel;

            @Override
            public boolean hasNext() {
                return curr.next != sentinel;
            }

            @Override
            public Node next() {
                curr = curr.getNext();
                return curr;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
    }

    @Override
    public String toString() {
        String fin = "";
        for (Object o : this) {
            Node n = (Node) o;
            fin += n.getO();
        }
        return fin;
    }
}
