package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.CreditType;

import java.util.List;

public interface CreditRepo extends CrudRepository<Credit, Long> {
    List<Credit> findAllBySumm(int summ);
    List<Credit> findAllByCreditType(CreditType creditType);
    List<Credit> findAllByClient(Client client);
}