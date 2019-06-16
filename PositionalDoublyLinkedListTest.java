
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
/**
 *
 * @author Javan Oyugi
 */
import static java.awt.EventQueue.invokeLater;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PositionalDoublyLinkedListTest {

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            invokeLater(new PositionalDoublyLinkedListTestGui()::initComponents);
        }
        invokeLater(new PositionalDoublyLinkedListTestGui()::initComponents);
    }
}
