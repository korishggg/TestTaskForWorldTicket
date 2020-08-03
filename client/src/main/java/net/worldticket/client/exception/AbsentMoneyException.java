package net.worldticket.client.exception;

public class AbsentMoneyException extends Exception {

    private String notification;

    public AbsentMoneyException(String notification) {
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
