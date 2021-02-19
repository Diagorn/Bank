package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.CreditType;

import java.util.List;

public interface CreditTypeRepo extends CrudRepository<CreditType, Long> { //1
    List<CreditType> findAllByNameContaining(String name);
    List<CreditType> findAllByConditionsContaining(String conditions);
    List<CreditType> findAllByPercent(double percent);
    List<CreditType> findAllByNumberOfDays(int numberOfDays);
    CreditType findByNameContaining(String name);
    CreditType findByConditionsContaining(String conditions);
    CreditType findByPercentContaining(double percent);
    CreditType findByNumberOfDaysContaining(int numberOfDays);
}
