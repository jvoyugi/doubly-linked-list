
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
public interface PositionalList {

    public Position addAfter(Position p, Object obj) throws InvalidValueException;

    public Position addBefore(Position p, Object obj) throws InvalidValueException;

    public Position addFirst(Object obj);

    public Position addLast(Object obj);

    public Position after(Position p) throws InvalidValueException;

    public Position before(Position p) throws InvalidValueException;

    public Position first() throws EmptyListException;

    public Position last() throws EmptyListException;

    public Object remove(Position p) throws InvalidValueException;

    public int size();

    public boolean isEmpty();

    public Object replace(Position p, Object obj) throws InvalidValueException;
    
    public void swapElement(Position p1, Position p2) throws InvalidValueException;
    
    @SuppressWarnings("serial")
    public static class EmptyListException extends Exception {

        public EmptyListException(String args) {
            super(args);
        }
    }
    
    @SuppressWarnings("serial")
    public static class InvalidValueException extends Exception {

        public InvalidValueException(String args) {
            super(args);
        }
    }
}


