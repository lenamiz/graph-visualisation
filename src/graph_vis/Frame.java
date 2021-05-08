/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

import javax.swing.*;
import java.awt.*;

/**
 * App window
 */
public class Frame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public Frame(){
        super("Graph visualisation");

        JPanel dataPanel = new DataPanel(this);
        add(dataPanel);

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}