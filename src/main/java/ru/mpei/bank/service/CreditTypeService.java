package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.CreditType;
import ru.mpei.bank.repos.CreditTypeRepo;

import java.util.List;

@Service
public class CreditTypeService {
    @Autowired
    private CreditTypeRepo creditTypeRepo;

    @Autowired
    private CreditService creditService;

    public CreditType getByID(Long id) {
        return creditTypeRepo.findById(id).get();
    }

    public Iterable<CreditType> findAll() {
        return creditTypeRepo.findAll();
    }

    public List<CreditType> findAllByQuery(String query) {
        List<CreditType> resultList;
        List<CreditType> creditTypesByNames = creditTypeRepo.findAllByNameContaining(query);
        List<CreditType> creditTypesByConditions = creditTypeRepo.findAllByConditionsContaining(query);
        List<CreditType> creditTypesByPercent = null;
        if (query.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) { //checking if it is double
            creditTypesByPercent = creditTypeRepo.findAllByPercent(Double.parseDouble(query));
        }
        List<CreditType> creditTypesByNumberOfDays = null;
        if (query.matches("(\"[-+]?\\\\d+\")")) //is integer
            creditTypesByNumberOfDays = creditTypeRepo.findAllByNumberOfDays(Integer.parseInt(query));
        resultList = ServiceUtils.collideLists(creditTypesByNames, creditTypesByConditions);
        ServiceUtils.collideLists(resultList, creditTypesByPercent);
        ServiceUtils.collideLists(resultList, creditTypesByNumberOfDays);
        return resultList;
    }

    public void createCreditType(String name, String conditions, double percent, int numberOfDays) {
        CreditType creditType = new CreditType();
        creditType.setName(name);
        creditType.setConditions(conditions);
        creditType.setPercent(percent);
        creditType.setNumberOfDays(numberOfDays);
        creditTypeRepo.save(creditType);
    }

    public void saveEditedCreditType(Long id, String name, String conditions, double percent, int numberOfDays) {
        CreditType creditType = getByID(id);
        creditType.setNumberOfDays(numberOfDays);
        creditType.setPercent(percent);
        creditType.setConditions(conditions);
        creditType.setName(name);
        creditTypeRepo.save(creditType);
    }

    public void deleteByID(Long id) {
        CreditType creditType = creditTypeRepo.findById(id).get();
        CreditType defaultCreditType = creditTypeRepo.findByNameContaining("Стандартный");
        if (creditType.equals(defaultCreditType))
            return;

        List<Credit> credits = creditService.findAllByCreditType(creditType);
        for (Credit c: credits) {
            c.setCreditType(defaultCreditType);
        }

        creditTypeRepo.delete(creditType);
    }

    public CreditType findOneByQuery(String query) {
        CreditType creditType = creditTypeRepo.findByNameContaining(query);
        if (creditType != null)
            return creditType;
        creditType = creditTypeRepo.findByConditionsContaining(query);
        if (creditType != null)
            return creditType;
        if (query.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) {
            creditType = creditTypeRepo.findByPercentContaining(Double.parseDouble(query));
            return creditType;
        }
        if (query.matches("[-+]?\\d+")) {
            creditType = creditTypeRepo.findByNumberOfDaysContaining(Integer.parseInt(query));
            return creditType;
        }
        return null;
    }
}