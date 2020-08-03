package net.worldticket.model;

import java.math.BigInteger;
import java.util.Date;

public class CurrentBalanceDto {

    private BigInteger balance;
    private Date date;

    public CurrentBalanceDto() {
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
