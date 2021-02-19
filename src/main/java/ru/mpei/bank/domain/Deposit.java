package ru.mpei.bank.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    private GregorianCalendar openDate;

    private GregorianCalendar closeDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private DepositType type;

    private int sum;

    public Deposit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GregorianCalendar getOpenDate() {
        return openDate;
    }

    public void setOpenDate(GregorianCalendar openDate) {
        this.openDate = openDate;
    }

    public GregorianCalendar getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(GregorianCalendar closeDate) {
        this.closeDate = closeDate;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
