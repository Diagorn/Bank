package ru.mpei.bank.domain;

import javax.persistence.*;

@Entity
public class DepositType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String conditions;

    private double percent;

    private boolean canRefill;

    private boolean canWithdrawal;

    public DepositType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public boolean isCanRefill() {
        return canRefill;
    }

    public void setCanRefill(boolean canRefill) {
        this.canRefill = canRefill;
    }

    public boolean isCanWithdrawal() {
        return canWithdrawal;
    }

    public void setCanWithdrawal(boolean canWithdrawal) {
        this.canWithdrawal = canWithdrawal;
    }
}
