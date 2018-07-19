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
        Node insertNode = new Node();
        insertNode.setO(o);
        insertNode.next = sentinel;
        insertNode.prev = sentinel.prev;
        sentinel.prev.next = insertNode;
        sentinel.prev = insertNode;
        length++;
    }



    @Override
    public String toString() {
        String toReturn = "";

        return toReturn;
    }


    @Override
    public Iterator iterator() {
        return null;
    }
}
