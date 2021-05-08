package graph_vis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPanel extends JPanel implements ActionListener{

    private final JFrame frame;
    private JPanel mainPanel;

    private JCheckBox checkboxDir;
    private JCheckBox checkboxUnDir;

    private JTextField v1Field;
    private JTextField v2Field;
    private JTextField lblField;

    private JButton generateButton;
    private JButton addButton;

    private Graph graph;
    private int graphTypeDirected;

    private static final int GRAPH_PANEL_WIDTH = 600;
    private static final int GRAPH_PANEL_HEIGHT = 600;

    public DataPanel(JFrame frame){
        super();
        this.frame = frame;

        //layout
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridBag.setConstraints(this, constraints);
        setLayout(gridBag);

        createComponents();
    }

    //data input
    public void setData(){

        String v1 = v1Field.getText();
        String v2 = v2Field.getText();
        String lbl = lblField.getText();

        graph.addNode(v1);
        graph.addNode(v2);
        graph.addEdge(v1, v2, lbl);

    }

    //creating window components for data input
    private void createComponents() {

        JLabel v1 = new JLabel("V1");
        JLabel v2 = new JLabel("V2");
        JLabel et = new JLabel("Label");

        v1Field = new JTextField();
        v2Field = new JTextField();
        lblField = new JTextField();

        JTextField nodeNumField = new JTextField();
        nodeNumField.setText("Node number");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        okButton.setActionCommand("Ok");

        //panel with graph type choice
        JPanel checkboxMenu = new JPanel();
        checkboxMenu.setLayout(new GridLayout(2,2));

        checkboxDir = new JCheckBox("Directed graph");
        checkboxDir.addActionListener(this);
        checkboxDir.setActionCommand("Directed");
        checkboxUnDir = new JCheckBox("Undirected graph");
        checkboxUnDir.addActionListener(this);
        checkboxUnDir.setActionCommand("Undirected");

        checkboxMenu.add(checkboxUnDir);
        checkboxMenu.add(checkboxDir);

        //message panel
        JPanel msgPanel = new JPanel(new BorderLayout());
        JTextField msg = new JTextField();
        msg.setText("Maximum node number: 20");
        msg.setEditable(false);
        msgPanel.add(msg, BorderLayout.NORTH);
        msgPanel.add(checkboxMenu, BorderLayout.SOUTH);

        //side panel for data input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2));

        inputPanel.add(v1);
        inputPanel.add(v1Field);
        inputPanel.add(v2);
        inputPanel.add(v2Field);
        inputPanel.add(et);
        inputPanel.add(lblField);

        //graph generating button
        generateButton = new JButton("Generate graph");
        generateButton.addActionListener(this);
        generateButton.setActionCommand("Generate");

        //add edge button
        addButton = new JButton("Add edge");
        addButton.addActionListener(this);
        addButton.setActionCommand("Add");

        //centering elements
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(msgPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(generateButton, BorderLayout.SOUTH);
        mainPanel.add(addButton, BorderLayout.EAST);

        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        //graph type chosen
        if(event.getActionCommand().equals("Undirected")){
            graph = new UnDiGraph();
            this.graphTypeDirected = 0;
            checkboxDir.removeActionListener(this);
            checkboxUnDir.removeActionListener(this);
        }

        if(event.getActionCommand().equals("Directed")){
            graph = new DiGraph();
            this.graphTypeDirected = 1;
            checkboxDir.removeActionListener(this);
            checkboxUnDir.removeActionListener(this);
        }

        //ADD button clicked
        if (event.getActionCommand().equals("Add")) {
            if(UserValidator.isGraphTypeSelected(checkboxDir, checkboxUnDir)) {
                setData();
                v1Field.setText("");
                v2Field.setText("");
                lblField.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Choose (1) graph type!");
            }

            //GENERATE button clicked
        } else if(event.getActionCommand().equals("Generate")){

            if(UserValidator.isGraphTypeSelected(checkboxDir, checkboxUnDir)) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        JPanel graphPanel = new GraphPanel(graph, graphTypeDirected);

                        //deleting data input panel
                        frame.getContentPane().removeAll();

                        //adding graph panel and refreshing view
                        frame.add(graphPanel);
                        frame.setSize(GRAPH_PANEL_WIDTH,GRAPH_PANEL_HEIGHT);
                        frame.setLocationRelativeTo(null);
                        frame.validate();
                    }
                });
            }
            else{
                JOptionPane.showMessageDialog(null, "Choose (1) graph type!");
            }
        }
    }
}
