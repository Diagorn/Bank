package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.domain.DepositType;

import java.util.List;

public interface DepositRepo extends CrudRepository<Deposit, Long> {
    List<Deposit> findAllByClient(Client client);
    List<Deposit> findAllByType(DepositType dt);
}
