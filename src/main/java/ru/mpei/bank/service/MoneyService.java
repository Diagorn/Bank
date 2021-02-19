package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Expiration;
import ru.mpei.bank.domain.Money;
import ru.mpei.bank.repos.ExpirationRepo;
import ru.mpei.bank.repos.MoneyRepo;

import java.util.List;

@Service
public class MoneyService {
    @Autowired
    private MoneyRepo moneyRepo;

    @Autowired
    private ExpirationRepo expirationRepo;

    public Iterable<Money> findAllMoneyTypes() {
        return moneyRepo.findAll();
    }

    public List<Money> findAllByFilter(String filter) {
        return moneyRepo.findAllByMoneyNameContaining(filter);
    }

    public Money findSingleByMoneyName(String name) {
        return moneyRepo.findByMoneyName(name);
    }

    public void create(String name, double sum) {
        Money money = new Money();
        money.setMoneyName(name);
        money.setSummInRoubles(sum);
        moneyRepo.save(money);
    }

    public void updateByName(String name, String moneyName, double sum) {
        Money money = moneyRepo.findByMoneyName(name);
        money.setMoneyName(moneyName);
        money.setSummInRoubles(sum);
        moneyRepo.save(money);
    }

    public void deleteByName(String moneyName) {
        if (moneyName.equals("Рубль"))
            return;
        Money money = moneyRepo.findByMoneyName(moneyName);
        List<Expiration> expirations = expirationRepo.findAllByMoney(money);
        for (Expiration e: expirations) {
            e.setSumm(e.getSumm() * money.getSummInRoubles());
            e.setMoney(moneyRepo.findByMoneyName("Рубль"));
        }
        moneyRepo.delete(money);
    }

    public boolean existsInDB(String moneyName) {
        return moneyRepo.existsByMoneyName(moneyName);
    }
}
