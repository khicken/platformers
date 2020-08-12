package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel middlePanel, inputPanel;
    private JTextPane console = new JTextPane();
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
        console.setMargin(new Insets(10, 10, 10, 10));

        inputPanel = new JPanel(new GridLayout());
        input.setFont(new Font("Monospaced", Font.PLAIN, 15));
        input.addActionListener(this);
        input.setEnabled(false);

        middlePanel.add(new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        inputPanel.add(input);
        this.getContentPane().add(middlePanel, BorderLayout.CENTER);
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
        this.println(this.userInput, new Color(255, 0, 0));
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
        this.appendColorTxtToPane(s, Color.BLACK);
    }

    public void print(String s, Color c) { // print line to certain color
        this.appendColorTxtToPane(s, c);
    }

    public void println(String s) {
        this.appendColorTxtToPane(s + "\n", Color.BLACK);
    }

    public void println(String s, Color c) { // print line to certain color
        this.appendColorTxtToPane(s + "\n", c);
    }

    private void appendColorTxtToPane(String s, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);


        console.setCaretPosition(console.getDocument().getLength());
        console.setCharacterAttributes(aset, false);
        console.replaceSelection(s);
    }
}