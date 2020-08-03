package net.worldticket.gui.ui;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.springframework.stereotype.Component;

@Component
public class MainWindow {
    private JTextArea feedBackArea;
    private MainPanel mainPanel;

    public MainWindow() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                MainWindow.this.createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Shepherd App Client");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().setLayout(new BorderLayout());
        JPanel pane = (JPanel)frame.getContentPane();
        this.addMainPanel(pane);
        this.addServerStatusArea(pane);
        frame.pack();
        frame.setVisible(true);
    }

    private void addServerStatusArea(JPanel pane) {
        pane.add(new JScrollPane(this.feedBackArea = new JTextArea()), "Center");
    }

    private void addMainPanel(JPanel pane) {
        pane.add(this.mainPanel = new MainPanel(), "First");
    }

    public MainPanel getMainPanel() {
        return this.mainPanel;
    }

    public JTextArea getFeedBackArea() {
        return this.feedBackArea;
    }
}