//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.worldticket.gui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiInterface {
    private MainWindow mainWindow;
    private JTextArea feedBackArea = null;
    private List<OrderRequestListener> orderRequestListeners;

    @Autowired
    public GuiInterface(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.orderRequestListeners = new ArrayList();
        this.initActionListener();
    }

    public void addServerFeedback(String feedBack) {
        if (this.feedBackArea == null) {
            this.feedBackArea = this.mainWindow.getFeedBackArea();
        }

        this.feedBackArea.setText(feedBack + "\n" + this.feedBackArea.getText());
    }

    public void addOrderRequestListener(OrderRequestListener listener) {
        this.orderRequestListeners.add(listener);
    }

    public void removeOrderRequestListener(OrderRequestListener listener) {
        this.orderRequestListeners.remove(listener);
    }

    public void removeAllOrderRequestListener() {
        this.orderRequestListeners.clear();
    }

    private void initActionListener() {
        this.mainWindow.getMainPanel().getOrderSheepButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int nofSheep;
                try {
                    nofSheep = Integer.parseInt(GuiInterface.this.mainWindow.getMainPanel().getOrderSheepTextField().getText());
                } catch (NumberFormatException var5) {
                    return;
                }

                Iterator var3 = GuiInterface.this.orderRequestListeners.iterator();

                while(var3.hasNext()) {
                    OrderRequestListener listener = (OrderRequestListener)var3.next();
                    listener.orderRequest(nofSheep);
                }

            }
        });
    }
}
