/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Javan Oyugi
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;
import positionallist.Position;
import positionallist.PositionalDoublyLinkedList;
import positionallist.PositionalDoublyLinkedList.Node;
import positionallist.PositionalList;

public class PositionalDoublyLinkedListTestGui implements ActionListener {

    private JFrame frame;

    /** JPanel for buttons */
    private final JPanel btnPanel = new JPanel();
    /** Jpanel for labels and input */
    private final JPanel lblPanel = new JPanel();
    /** JPanel for candy objects */
    private final JPanel rsPanel = new JPanel();
    /** Main panel */
    private final JPanel panel = new JPanel();
    /** Text panel */
    private final JPanel txtPanel = new JPanel();

    private final String[] shapes = { "Square", "Oval", "Circle", "Random" };
    private final PositionalDoublyLinkedList lst;
    private final Random rand;

    private final JComboBox<String> shape = new JComboBox<>(shapes);;

    private final JButton btnAddBefore = new JButton("Add Before");
    private final JButton btnAddAfter = new JButton("Add After");
    private final JButton btnAddFirst = new JButton("Add First");
    private final JButton btnAddLast = new JButton("Add Last");
    private final JButton btnReplace = new JButton("Replace");
    private final JButton btnRemove = new JButton("Remove");
    private final JButton btnBefore = new JButton("Before");
    private final JButton btnEmpty = new JButton("Empty");
    private final JButton btnFirst = new JButton("First");
    private final JButton btnAfter = new JButton("After");
    private final JButton btnSwap = new JButton("Swap");
    private final JButton btnLast = new JButton("Last");
    private final JButton btnSize = new JButton("Size");

    private final JTextField pos = new JTextField(10);
    private final JTextField red = new JTextField(3);
    private final JTextField green = new JTextField(3);
    private final JTextField blue = new JTextField(3);

    private final JLabel elementLbl;
    private final JLabel colorLbl = new JLabel("Candy Color: ");
    private final JLabel shapeLbl = new JLabel("Candy Shape: ");

    public PositionalDoublyLinkedListTestGui() {
        this.lst = new PositionalDoublyLinkedList();
        this.rand = new Random(System.currentTimeMillis());
        this.elementLbl = new JLabel("Candy Number: ");
        red.setToolTipText("Red");
        green.setToolTipText("Green");
        blue.setToolTipText("Blue");
    }

    protected void initComponents() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(txtPanel);
        panel.add(rsPanel);
        panel.add(lblPanel);
        panel.add(btnPanel);

        txtPanel.add(new JLabel("Note that candy numbers begin from zero. You can employ colour(r,g,b) modifications to candy before addition"));

        rsPanel.setLayout(new BoxLayout(rsPanel, BoxLayout.LINE_AXIS));

        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnEmpty);
        btnPanel.add(btnFirst);
        btnPanel.add(btnLast);
        btnPanel.add(btnAfter);
        btnPanel.add(btnBefore);
        btnPanel.add(btnAddFirst);
        btnPanel.add(btnAddLast);
        btnPanel.add(btnAddBefore);
        btnPanel.add(btnAddAfter);
        btnPanel.add(btnReplace);
        btnPanel.add(btnSwap);
        btnPanel.add(btnRemove);
        btnPanel.add(btnSize);

        lblPanel.add(shapeLbl);
        lblPanel.add(shape);
        lblPanel.add(elementLbl);
        lblPanel.add(pos);
        lblPanel.add(colorLbl);
        lblPanel.add(red);
        lblPanel.add(green);
        lblPanel.add(blue);

        btnAddBefore.addActionListener(this);
        btnAddAfter.addActionListener(this);
        btnAddFirst.addActionListener(this);
        btnAddLast.addActionListener(this);
        btnReplace.addActionListener(this);
        btnRemove.addActionListener(this);
        btnBefore.addActionListener(this);
        btnFirst.addActionListener(this);
        btnEmpty.addActionListener(this);
        btnAfter.addActionListener(this);
        btnSwap.addActionListener(this);
        btnLast.addActionListener(this);
        btnSize.addActionListener(this);

        frame = new JFrame();
        frame.setTitle("Positional Doubly Linked List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.PAGE_END);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setSize(1000, 400);
    }

    private Candy createCandy() {
        int r, g, b;
        String shapeStr = shape.getSelectedItem().toString();
        try {
            r = Integer.parseInt(red.getText());
            g = Integer.parseInt(green.getText());
            b = Integer.parseInt(blue.getText());
        } catch (NumberFormatException ex) {
            r = rand.nextInt(Integer.MAX_VALUE - 1) % 255;
            g = rand.nextInt(Integer.MAX_VALUE - 1) % 255;
            b = rand.nextInt(Integer.MAX_VALUE - 1) % 255;
        }
        Candy candy = new Candy(new Color(r, g, b), shapeStr);
        candy.setMaximumSize(new Dimension(80, 50));
        return candy;
    }

    public void checkEmpty() {
        JOptionPane.showMessageDialog(null, StringUtils.capitalize(String.valueOf(lst.isEmpty())), "Empty", 1);
    }

    public void getSize() {
        JOptionPane.showMessageDialog(null, lst.size(), "Size", 1);
    }

    public void getFirst() throws PositionalList.EmptyListException, CloneNotSupportedException {
        JOptionPane.showMessageDialog(null, ((Candy) lst.first().getElement()).clone(), String.valueOf(lst.first()), 1);
    }

    public void getLast() throws PositionalList.EmptyListException, CloneNotSupportedException {
        JOptionPane.showMessageDialog(null, ((Candy) lst.last().getElement()).clone(), String.valueOf(lst.last()), 1);
    }

    public void getAfter()
            throws PositionalList.EmptyListException, CloneNotSupportedException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            JOptionPane.showMessageDialog(null, ((Candy) lst.after(node).getElement()).clone(),
                    String.valueOf(lst.last()), 1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Null", "Null", 2);
        }
    }

    public void getBefore()
            throws PositionalList.EmptyListException, CloneNotSupportedException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            JOptionPane.showMessageDialog(null, ((Candy) lst.before(node).getElement()).clone(),
                    String.valueOf(lst.last()), 1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Null", "Null", 2);
        }
    }

    public void addFirstElement() throws PositionalList.EmptyListException {
        Candy candy = createCandy();
        lst.addFirst(candy);
        candy.setToolTipText(String.valueOf(lst.first()));
        rsPanel.add(candy, 0);
        rsPanel.revalidate();

    }

    public void addLastElement() throws PositionalList.EmptyListException {
        Candy candy = createCandy();
        lst.addLast(candy);
        candy.setToolTipText(String.valueOf(lst.last()));
        rsPanel.add(candy);
        rsPanel.revalidate();
    }

    public void addElementBefore() throws PositionalList.EmptyListException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            Candy candy = createCandy();
            candy.setToolTipText(String.valueOf(node));
            lst.addBefore(node, candy);
            rsPanel.add(candy, index);
            rsPanel.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        }

    }

    public void addElementAfter() throws PositionalList.EmptyListException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            Candy candy = createCandy();
            candy.setToolTipText(String.valueOf(node));
            lst.addAfter(node, candy);
            rsPanel.add(candy, index + 1);
            rsPanel.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        }
    }

    public void swapPanels()
            throws PositionalList.EmptyListException, PositionalList.InvalidValueException, CloneNotSupportedException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node1 = lst.getNode((Node) lst.first(), index);
            int index2 = Integer.parseInt(JOptionPane.showInputDialog("Swap with"));
            Position node2 = lst.getNode((Node) lst.first(), index2);
            Candy candy1 = (Candy) rsPanel.getComponent(index);
            Candy temp1 = candy1.clone();
            temp1.setToolTipText(String.valueOf(node2));
            Candy candy2 = (Candy) rsPanel.getComponent(index2);
            Candy temp2 = candy2.clone();
            temp2.setToolTipText(String.valueOf(node1));
            lst.swapElement(node1, node2);
            rsPanel.remove(index);
            rsPanel.add(temp2, index);
            rsPanel.revalidate();
            rsPanel.remove(index2);
            rsPanel.add(temp1, index2);
            rsPanel.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        }
    }

    public void replaceElement() throws PositionalList.EmptyListException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            Candy candy = createCandy();
            candy.setToolTipText(String.valueOf(node));
            lst.replace(node, candy);
            rsPanel.remove(index);
            rsPanel.add(candy, index);
            rsPanel.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid candy number", "Error", 2);
        }
    }

    public void removeElement() throws PositionalList.EmptyListException, PositionalList.InvalidValueException {
        try {
            int index = Integer.parseInt(pos.getText());
            Position node = lst.getNode((Node) lst.first(), index);
            lst.remove(node);
            rsPanel.remove(index);
            rsPanel.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Empty".equals(ae.getActionCommand())) {
            checkEmpty();
        }
        if ("First".equals(ae.getActionCommand())) {
            try {
                getFirst();
            } catch (PositionalList.EmptyListException | CloneNotSupportedException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Last".equals(ae.getActionCommand())) {
            try {
                getLast();
            } catch (PositionalList.EmptyListException | CloneNotSupportedException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Add First".equals(ae.getActionCommand())) {
            try {
                addFirstElement();
            } catch (PositionalList.EmptyListException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Add Last".equals(ae.getActionCommand())) {
            try {
                addLastElement();
            } catch (PositionalList.EmptyListException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Size".equals(ae.getActionCommand())) {
            getSize();
        }
        if ("Add Before".equals(ae.getActionCommand())) {
            try {
                addElementBefore();
            } catch (PositionalList.EmptyListException | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Add After".equals(ae.getActionCommand())) {
            try {
                addElementAfter();
            } catch (PositionalList.EmptyListException | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Replace".equals(ae.getActionCommand())) {
            try {
                replaceElement();
            } catch (PositionalList.EmptyListException | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Swap".equals(ae.getActionCommand())) {
            try {
                swapPanels();
            } catch (PositionalList.EmptyListException | PositionalList.InvalidValueException
                    | CloneNotSupportedException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        if ("Remove".equals(ae.getActionCommand())) {
            try {
                removeElement();
            } catch (PositionalList.EmptyListException | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("After".equals(ae.getActionCommand())) {
            try {
                getAfter();
            } catch (PositionalList.EmptyListException | CloneNotSupportedException
                    | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if ("Before".equals(ae.getActionCommand())) {
            try {
                getBefore();
            } catch (PositionalList.EmptyListException | CloneNotSupportedException
                    | PositionalList.InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

}
