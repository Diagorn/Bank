package ru.mpei.bank.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String moneyName;

    private double summInRoubles;

    @OneToMany
    private List<Expiration> expirations;

    public Money() {
    }

    @PreRemove
    public void setDefaultCurrencyForExpiration() {
        for (Expiration e: expirations) {
            e.setSumm(e.getSumm() * summInRoubles);
            e.setMoney(null);
        }
    }

    public String getMoneyName() {
        return moneyName;
    }

    public void setMoneyName(String moneyName) {
        this.moneyName = moneyName;
    }

    public double getSummInRoubles() {
        return summInRoubles;
    }

    public void setSummInRoubles(double summInRoubles) {
        this.summInRoubles = summInRoubles;
    }

    public List<Expiration> getExpirations() {
        return expirations;
    }

    public void setExpirations(List<Expiration> expirations) {
        this.expirations = expirations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
