/**
 * Created by Yining on 7/17/18.
 */

 public class Node {

        protected Object o;
        protected Node prev;
        protected Node next;

        public Node() {
            o = null;
            prev = null;
            next = null;
        }

        public Node(Object o) {
            this.o = o;
        }

        public Object getO() {
            return o;
        }

        public void setO(Object o) {
            this.o = o;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


