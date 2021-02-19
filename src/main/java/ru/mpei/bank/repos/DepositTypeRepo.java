package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.domain.DepositType;

import java.util.List;

public interface DepositTypeRepo extends CrudRepository<DepositType, Long> { //1
    List<DepositType> findAllByNameContaining(String name); //2
    List<DepositType> findAllByConditionsContaining(String conditions);
    List<DepositType> findAllByPercent(double percent); //3

    DepositType findByNameContaining(String name);

    DepositType findByName(String name);
}
