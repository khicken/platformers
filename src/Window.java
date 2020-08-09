package src;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextArea console = new JTextArea();
    private JTextField input = new JTextField();
    private JButton enter = new JButton("Enter");
    private JPanel mainPanel = new JPanel(new FlowLayout());
    // private JPanel displayPanel = new JPanel(), inputPanel = new JPanel();
    public Window(String title) { // init and add window components
        super(title);

        enter.addActionListener(this);

        console.setColumns(150);
        console.setRows(10);
        console.setFont(new Font("Monospaced", Font.PLAIN, 15));
        console.setEditable(false);
        console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        input.setColumns(120);

        mainPanel.add(console);
        mainPanel.add(input);
        mainPanel.add(enter);
        this.getContentPane().add(mainPanel);
    }

    public void displayWindow() { // meant to config settings
        this.getRootPane().setDefaultButton(enter);
        this.setSize(1280, 720);
        this.setResizable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // idk
    }

    public void print() {

    }
}