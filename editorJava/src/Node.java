/**
 * Created by Yining on 7/17/18.
 */

 public class Node {

        protected Object o;
        protected Node prev;
        protected Node next;
        protected double posX;
        protected double posY;

        public Node() {
            o = null;
            prev = null;
            next = null;

        }

        public Node(Object o, double posX, double posY) {
            this.o = o;
            this.posX = posX;
            this.posY = posY;
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

        public double getPosX() {
            return posX;
        }

        public void setPosX(double posX) {
            this.posX = posX;
        }

        public double getPosY() {
            return posY;
        }

        public void setPosY(double posY) {
            this.posY = posY;
        }


}


