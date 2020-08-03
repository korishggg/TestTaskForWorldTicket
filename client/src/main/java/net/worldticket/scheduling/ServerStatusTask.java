package net.worldticket.scheduling;

import net.worldticket.client.CurrentBalanceRestClient;
import net.worldticket.client.SheepRestClient;
import net.worldticket.client.ShepherdRestClient;
import net.worldticket.gui.ui.GuiInterface;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerStatusTask {

    private GuiInterface guiInterface;
    private CurrentBalanceRestClient currentBalanceRestClient;
    private SheepRestClient sheepRestClient;
    private ShepherdRestClient shepherdRestClient;

    public ServerStatusTask(
            GuiInterface guiInterface,
            CurrentBalanceRestClient currentBalanceRestClient,
            SheepRestClient sheepRestClient, ShepherdRestClient shepherdRestClient) {
        this.guiInterface = guiInterface;
        this.currentBalanceRestClient = currentBalanceRestClient;
        this.sheepRestClient = sheepRestClient;
        this.shepherdRestClient = shepherdRestClient;
    }

    @Scheduled(cron = "${scheduling.server_status.cron}")
    public void getServerStatus() {

        guiInterface.addServerFeedback("Server status: healthy sheep = " +
                sheepRestClient.getSheepStatuses().getNumberOfHealthySheep() +
                " dead sheep " +
                sheepRestClient.getSheepStatuses().getNumberOfDeadSheep()
        );

    }

    @Scheduled(cron = "${scheduling.server_status.cron}")
    public void getBalance() {
        guiInterface.addServerFeedback("Current balance = " +
                currentBalanceRestClient.getCurrentBalance().getBalance()
        );
    }

}
