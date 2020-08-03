package net.worldticket.gui.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {
    private JButton orderSheepButton;
    private JTextField textField;

    public MainPanel() {
        this.add(this.textField = new JTextField(3));
        this.add(this.orderSheepButton = new JButton("Order sheep"));
    }

    public JButton getOrderSheepButton() {
        return this.orderSheepButton;
    }

    public JTextField getOrderSheepTextField() {
        return this.textField;
    }
}