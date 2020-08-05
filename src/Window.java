import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField t;
    public Window(String title) {
        super(title);
        t = new JTextField();
        this.add(t);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayWindow() {
        this.setSize(1280, 720);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // idk
    }
}