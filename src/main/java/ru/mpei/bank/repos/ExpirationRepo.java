package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.Expiration;
import ru.mpei.bank.domain.Money;

import java.util.List;

public interface ExpirationRepo extends CrudRepository<Expiration, Long> {
    List<Expiration> findAllByMoney(Money money);
    List<Expiration> findAllBySumm(double summ);
    List<Expiration> findAllByCredit(Credit credit);
}
