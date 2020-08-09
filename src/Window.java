package src;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Insets;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextArea console = new JTextArea();
    private JTextField input = new JTextField();
    private JButton enter = new JButton("Enter");

    private Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int w_width, w_height, m_width = (int)this.monitorSize.getWidth(), m_height = (int)this.monitorSize.getHeight();
    // private JPanel displayPanel = new JPanel(), inputPanel = new JPanel();
    public Window(String title, int w_width, int w_height) { // init and add window components
        super(title);
        this.w_width = w_width;
        this.w_height = w_height;

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(20, 5));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.GRAY));

        enter.addActionListener(this);

        // console.setColumns(150);
        // console.setRows(10);
        console.setFont(new Font("Monospaced", Font.PLAIN, 15));
        console.setEditable(false);    
        console.setMargin(new Insets(10, 10, 10, 10));
        // console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        input.setColumns(120);
        input.setFont(new Font("Monospaced", Font.PLAIN, 15));

        mainContainer.add(console);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(15));
        inputPanel.add(input);
        inputPanel.add(enter);
        mainContainer.add(inputPanel, BorderLayout.SOUTH);
    }

    public void displayWindow() { // meant to config settings
        this.getRootPane().setDefaultButton(enter);

        this.setLocation((this.m_width - this.w_width)/2, (this.m_height - this.w_height)/2);
        this.pack();
        this.setSize(this.w_width, this.w_height);
        this.setResizable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        input.setText("");
        input.requestFocusInWindow();
    }

    public void print(String s) {
        console.append(s);
    }
}