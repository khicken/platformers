package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Insets;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel middlePanel, inputPanel;
    private JTextArea console = new JTextArea();
    private JTextField input = new JTextField();

    private Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int w_width, w_height, m_width = (int) this.monitorSize.getWidth(),
            m_height = (int) this.monitorSize.getHeight();

    private String userInput = "";

    public Window(String title, int w_width, int w_height) { // init and add window components
        super(title);
        this.w_width = w_width;
        this.w_height = w_height;

        this.setLayout(new BorderLayout(20, 10));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(236, 236, 236)));

        middlePanel = new JPanel(new GridLayout());
        console.setFont(new Font("Monospaced", Font.PLAIN, 15));
        console.setEditable(false);
        console.setMargin(new Insets(10, 10, 10, 10));

        inputPanel = new JPanel(new GridLayout());
        input.setFont(new Font("Monospaced", Font.PLAIN, 15));
        input.addActionListener(this);
        input.setEnabled(false);

        middlePanel.add(new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        inputPanel.add(input);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);

        ImageIcon img = new ImageIcon("./../assets/jake.png");
        this.setIconImage(img.getImage());

        this.setLocation((this.m_width - this.w_width) / 2, (this.m_height - this.w_height) / 2);
        this.pack();
        this.setSize(this.w_width, this.w_height);
        this.setResizable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        input.setEnabled(false);
        userInput = input.getText();
        this.println(this.userInput, Color.GREEN);
        input.setText("");
        notify(); // using multithreading to 'poll' user input (sleep/wake up thread process for efficiency)
    }

    public synchronized String getInput() {
        input.setEnabled(true);
        input.requestFocusInWindow();
        try {
            wait();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        return userInput;
    }

    // print methods for console
    public void print(String s) {
        console.append(s);
    }

    public void print(String s, Color c) { // print line to certain color
        Color beforeColor = console.getForeground();
        console.setFont(new Font("Monospaced", Font.PLAIN, 15));
        console.setForeground(c);
        console.append(s);
        console.setForeground(beforeColor);
    }

    public void println(String s) {
        console.append(s + "\n");
    }

    public void println(String s, Color c) { // print line to certain color
        Color beforeColor = console.getForeground();
        console.setFont(new Font("Monospaced", Font.PLAIN, 15));
        console.setForeground(c);
        console.append(s + "\n");
        console.setForeground(beforeColor);
    }
}