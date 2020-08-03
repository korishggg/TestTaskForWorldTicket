package net.worldticket.gui;

import net.worldticket.client.ShepherdRestClient;
import net.worldticket.client.exception.AbsentMoneyException;
import net.worldticket.gui.ui.GuiInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class GuiInterfaceServiceImpl implements GuiInterfaceService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private GuiInterface guiInterface;
    private ShepherdRestClient shepherdRestClient;

    public GuiInterfaceServiceImpl(GuiInterface guiInterface, ShepherdRestClient shepherdRestClient) {
        this.guiInterface = guiInterface;
        this.shepherdRestClient = shepherdRestClient;
        initBasicFunctionalityOfButton();
    }

    public void initBasicFunctionalityOfButton() {
        guiInterface.addOrderRequestListener(n -> {
            try {
                String message = this.shepherdRestClient.orderSheep(n);
                this.guiInterface.addServerFeedback(message);

            } catch (AbsentMoneyException exception) {
                log.info(exception.getNotification());
                this.guiInterface.addServerFeedback(exception.getNotification());
                return;
            }
        });
    }

}
