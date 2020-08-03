package net.worldticket.model;

import java.util.Date;

public class SheepDTO {

    private Date date;
    private String state;

    public SheepDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
