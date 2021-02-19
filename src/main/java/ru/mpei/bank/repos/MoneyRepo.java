package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Money;

import java.util.List;

public interface MoneyRepo extends CrudRepository<Money, Long> {
    List<Money> findAllByMoneyNameContaining(String filter);
    Money findByMoneyName(String moneyName);
    boolean existsByMoneyName(String moneyName);
    void deleteByMoneyName(String moneyName);
}
