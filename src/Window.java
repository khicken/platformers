package src;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField input = new JTextField();
    private JTextArea console = new JTextArea();
    private JButton enter = new JButton("Enter");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    public Window(String title) { // init and add window components
        super(title);
        enter.addActionListener(this);
        this.add(console);
        this.add(input);
        this.add(enter);
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
}