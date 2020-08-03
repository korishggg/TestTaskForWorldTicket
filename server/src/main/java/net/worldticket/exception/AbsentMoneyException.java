package net.worldticket.exception;

import javax.validation.constraints.NotNull;

public class AbsentMoneyException extends Exception {

    @NotNull
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
