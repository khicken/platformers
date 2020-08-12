package src;

import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Window extends JFrame, InputValidator implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel middlePanel, inputPanel;
    private JTextPane console = new JTextPane();
    private JTextField input = new JTextField();

    private Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int w_width, w_height;
    private int m_width = (int) this.monitorSize.getWidth(), m_height = (int) this.monitorSize.getHeight();

    private String userInput = "";
    private boolean validInput = false, userCanExit;
    private String[] validOptions;
    private String inputCheckType;

    public static final String ARRAY = "checkFromArray";
    public static final String NAME = "checkName";

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

    // getInput runs first, then actionperformed is waited for to notify getInput() to successfully return input
    public synchronized String getInput(String type, String[] options, boolean canExit) {
        this.inputCheckType = type;
        this.validOptions = options;
        this.userCanExit = canExit;

        input.setEnabled(true);
        input.requestFocusInWindow();
        try {
            wait();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        return userInput;
    }
    
    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        /*** gating input ***/
        String rawUserInput = input.getText();
        if(rawUserInput.isEmpty()) return; // if you hit enter when you didn't enter anything, nothing happens
        if(this.inputCheckType.equals(this.ARRAY)) {
            if(checkFromArray(rawUserInput)) return;
        } else if(this.inputCheckType.equals(this.NAME)) {
            if(checkName(rawUserInput)) return;
        }
        

        input.setEnabled(false);
        userInput = rawUserInput;
        this.println(this.userInput, new Color(255, 0, 0));
        input.setText("");
        notify(); // using multithreading to 'poll' user input (sleep/wake up thread process for efficiency)
    }

    /******************************************* input validator methods *******************************************/
    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};

    private boolean checkFromArray(String inp) {
        
        // iterate thru option array to find if theres a match
        for(String a: this.validOptions) {
            if(inp.toLowerCase().equals(a)) {
                this.validInput = true;
                break;
            } else if(inp.toLowerCase().equals("exit") && this.userCanExit) System.exit(0);
        }
        
        if(!this.validInput) { // if invalid input
            this.print("Invalid command. Possible options: ", Color.RED);
            if(this.userCanExit) {
                for(String a: this.validOptions) {
                    this.print(a + ", ", Color.RED);
                } this.println("exit", Color.RED);
            } else {
                for(int i = 0; i < this.validOptions.length-1; i++) {
                    this.print(this.validOptions[i] + ", ", Color.RED);
                } this.println(this.validOptions[this.validOptions.length-1], Color.RED);
            }
            return true;
        }
    }

    private boolean checkName(String inp) {
        if(alphabetCheck.matcher(input).find() && input.length() >= 2 && input.length() <= 20) {
            Main.w.println("Are you sure? (y/n)");
            String temp = Main.w.getInput(confirmValidate, false);
            if(temp.equals("y") || temp.equals("yes")) {
                return true;
            }
            int nameChangeCount = Main.easterEggs.get("nameChangeCount");
            if(nameChangeCount == 8) {
                Main.w.print("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                output = "Ryan";
                return;
            } else if(nameChangeCount == 5) Main.w.println("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
            else if(nameChangeCount == 3) Main.w.println("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) Main.w.println("A bit of a typo here, or a bit undecisive?");
            else System.out.print("That's fine, choose another name: ");
            // temp = Main.w.getInput();
            checkName(temp, output, true);
        }
        Main.w.println("Invalid name, please try a different one!");
        // input = Main.w.getInput();
        checkName(input, output, true);
    }

    /******************************************* print methods for console *******************************************/
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
}