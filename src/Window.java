package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
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
    private int w_width, w_height;
    private int m_width = (int) this.monitorSize.getWidth(), m_height = (int) this.monitorSize.getHeight();

    private String userInput = "";
    private String[] validOptions;
    public enum ValidateTypes {
        ARRAY,
        ALPHABETIC
    }
    private ValidateTypes inputCheckType;

    public Window(String title, int w_width, int w_height) { // init and add window components
        super(title);
        this.w_width = w_width;
        this.w_height = w_height;

        this.setLayout(new BorderLayout(20, 10));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(236, 236, 236)));

        middlePanel = new JPanel(new GridLayout());
        console.setMargin(new Insets(10, 10, 10, 10));
        console.setEditable(false);

        inputPanel = new JPanel(new GridLayout());
        input.setFont(new Font("Monospaced", Font.PLAIN, 15));
        input.addActionListener(this);
        input.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        new SmartScroller(scrollPane);
        middlePanel.add(scrollPane);
        inputPanel.add(input);
        this.getContentPane().add(middlePanel, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);

        ImageIcon img = new ImageIcon("./../assets/jake.png");
        this.setIconImage(img.getImage());

        this.setLocation((this.m_width - this.w_width) / 2, (this.m_height - this.w_height) / 2);
        this.pack();
        this.setSize(this.w_width, this.w_height);
        this.setResizable(true);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.game.exitGame();
            }
        });
        this.setVisible(true);
    }

    /******************************************* input fetcher methods *******************************************/
    public synchronized String getInput(ValidateTypes type, String[] options, boolean canExit) {
        this.inputCheckType = type;
        this.validOptions = options;

        input.setEnabled(true);
        input.requestFocusInWindow();
        try {
            wait();
        } catch(InterruptedException err) {
            err.printStackTrace();
            Thread.currentThread().interrupt();
        } catch(IllegalMonitorStateException er) {
            er.printStackTrace();
        }
        return this.userInput;
    }
    
    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        /*** gating input ***/
        String rawUserInput = input.getText();
        if(rawUserInput.isEmpty()) return; // if you hit enter when you didn't enter anything, nothing happens
        if(this.inputCheckType == ValidateTypes.ARRAY) {
            if(!InputValidator.checkFromArray(this, this.validOptions, rawUserInput)) {
                input.setText("");
                return;
            }
        } else if(this.inputCheckType == ValidateTypes.ALPHABETIC) {
            if(!InputValidator.checkAlphabetic(this, rawUserInput)) {
                input.setText("");
                return;
            }
        }

        input.setEnabled(false);
        this.userInput = rawUserInput;
        this.println(this.userInput, Color.RED);
        input.setText("");
        notify(); // using multithreading to 'poll' user input (sleep/wake up thread process for efficiency)
    }

    /******************************************* print methods for console *******************************************/
    public void print(String s) {
        this.appendColorTxtToPane(s, Color.BLACK);
    }

    public void print(String s, Color c) { // print to certain color
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
        AttributeSet a = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        a = sc.addAttribute(a, StyleConstants.FontFamily, "Lucida Console");
        a = sc.addAttribute(a, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        a = sc.addAttribute(a, StyleConstants.FontSize, 15);

        StyledDocument d = console.getStyledDocument();
        try {
            d.insertString(d.getLength(), s, a); // append string directly to doc so user cant edit textpane, yet doc already can
        } catch(BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String getConsoleText() {
        return console.getText();
    }
}