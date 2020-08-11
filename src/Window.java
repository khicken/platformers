package src;

import java.awt.Color;
import javax.swing.Box;
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

import java.nio.file.Path;

import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel middlePanel, inputPanel;
    private JTextArea console = new JTextArea();
    private JTextField input = new JTextField();

    private Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int w_width, w_height, m_width = (int)this.monitorSize.getWidth(), m_height = (int)this.monitorSize.getHeight();
    // private JPanel displayPanel = new JPanel(), inputPanel = new JPanel();
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

        middlePanel.add(console);
        inputPanel.add(input);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);

        ImageIcon img = new ImageIcon("./../assets/jake.png");
        this.setIconImage(img.getImage());

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