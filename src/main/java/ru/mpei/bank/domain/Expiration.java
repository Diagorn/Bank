package ru.mpei.bank.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Expiration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private GregorianCalendar dateOfExpiration;

    private double summ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_name")
    private Money money;

    @OneToOne(fetch = FetchType.EAGER)
    private Credit credit;

    public Expiration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GregorianCalendar getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(GregorianCalendar dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}
