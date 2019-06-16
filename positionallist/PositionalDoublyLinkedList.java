
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package positionallist;

/**
 *
 * @author Javan Oyugi
 */
public class PositionalDoublyLinkedList implements PositionalList {

    private int size = 0;
    private final Node header;
    private final Node trailer;

    public PositionalDoublyLinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.setNext(trailer);
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position first() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("The list is empty");
        }

        return position(header.getNext());
    }

    @Override
    public Position last() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("The list is empty");
        }

        return position(trailer.getPrev());
    }

    @Override
    public Position addFirst(Object obj) {
        return addBetween(obj, header, header.getNext());
    }

    @Override
    public Position addLast(Object obj) {
        return addBetween(obj, trailer.getPrev(), trailer);
    }

    @Override
    public Position addAfter(Position p, Object obj) throws InvalidValueException {
        Node node = validate(p);

        return addBetween(obj, node, node.getNext());
    }

    @Override
    public Position addBefore(Position p, Object obj) throws InvalidValueException {
        Node node = validate(p);

        return addBetween(obj, node.getPrev(), node);
    }

    @Override
    public Position after(Position p) throws InvalidValueException {
        Node elem = validate(p);

        return position(elem.getNext());
    }

    @Override
    public Position before(Position p) throws InvalidValueException {
        Node elem = validate(p);

        return position(elem.getPrev());
    }

    @Override
    public Object remove(Position p) throws InvalidValueException {
        Node node = validate(p);
        Node before = node.getPrev();
        Node after = node.getNext();

        before.setNext(after);
        after.setPrev(before);
        size--;

        Object ans = node.getElement();

        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);

        return ans;
    }

    @Override
    public Object replace(Position p, Object obj) throws InvalidValueException {
        Node node = validate(p);
        Object ans = node.getElement();

        node.setElement(obj);

        return ans;
    }

    @Override
    public void swapElement(Position p1, Position p2) throws InvalidValueException {
        Object temp = p2.getElement();
        this.replace(p2, p1.getElement());
        this.replace(p1, temp);
    }
    // custom function to get node at index n
    public Position getNode(Node header, int n) throws EmptyListException, InvalidValueException {
        if (n > size()) {
            throw new InvalidValueException("That position is invalid");
        }

        int count = 1;

        if (count == n) {
            return validate(header.getNext());
        }

        if (n == 0) {
            return validate((Node) first());
        }

        return validate((Node) getNode(header.getNext(), n - 1));
    }
    
    private Position position(Node node) {
        if ((node == trailer) || (node == header)) {
            return null;
        }

        return node;
    }
    
    private Node validate(Position p) throws InvalidValueException {
        if (!(p instanceof Node)) {
            throw new InvalidValueException("The position entered is invalid");
        }

        Node node = (Node) p;

        if (node.getNext() == null) {
            throw new InvalidValueException("That position does not exist in the list");
        }

        return node;
    }
    
    private Position addBetween(Object obj, Node before, Node after) {
        Node newest = new Node(obj, before, after);

        before.setNext(newest);
        after.setPrev(newest);
        size++;

        return newest;
    }

    public static class Node implements Position {

        private Object element;
        private Node prev;
        private Node next;

        public Node(Object obj, Node p, Node n) {
            element = obj;
            prev = p;
            next = n;
        }

        @Override
        public Object getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("List is Empty");
            }

            return element;
        }

        public void setElement(Object obj) {
            element = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node p) {
            prev = p;
        }
    }
}
