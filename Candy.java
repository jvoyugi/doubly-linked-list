
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javan Oyugi
 *
 */
@SuppressWarnings("serial")
public class Candy extends JPanel implements Cloneable {

    public Color color;
    public String choice;
    public Random rand = new Random(System.nanoTime());

    public Candy(Color c, String choice) {
        this.color = c;
        this.choice = choice;
        if ("Random".equals(this.choice)) {
            this.choice = String.valueOf(1 + rand.nextInt(Integer.MAX_VALUE - 1) % 3);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawOval(0, 15, 15, 15);
        if ("Square".equals(this.choice) || "1".equals(this.choice)) {
            g.setColor(color);
            g.fillRect(15, 0, 50, 50);
        }
        if ("Oval".equals(this.choice) || "2".equals(this.choice)) {
            g.setColor(color);
            g.fillOval(15, 0, 50, 40);
        }
        if ("Circle".equals(this.choice) || "3".equals(this.choice)) {
            g.setColor(color);
            g.fillOval(15, 0, 50, 50);
        }
        g.setColor(Color.BLACK);
        g.drawOval(65, 15, 15, 15);

    }

    @Override
    public Candy clone() throws CloneNotSupportedException {
        final Candy copy;

        copy = (Candy) super.clone();

        return copy;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 50);
    }

}
