package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.domain.DepositType;
import ru.mpei.bank.repos.DepositTypeRepo;

import java.util.List;

@Service
public class DepositTypeService {
    @Autowired
    private DepositTypeRepo depositTypeRepo;

    @Autowired
    private DepositService depositService;

    public Iterable<DepositType> findAll() {
        return depositTypeRepo.findAll();
    }

    public List<DepositType> findAllByQuery(String query) {
        List<DepositType> depositTypesByName = depositTypeRepo.findAllByNameContaining(query);
        List<DepositType> depositTypesByConditions = depositTypeRepo.findAllByConditionsContaining(query);
        depositTypesByName = ServiceUtils.collideLists(depositTypesByConditions, depositTypesByName);
        if (query.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) {
            List<DepositType> depositTypesByPercent = depositTypeRepo.findAllByPercent(Double.parseDouble(query));
            depositTypesByName = ServiceUtils.collideLists(depositTypesByName, depositTypesByPercent);
        }
        return depositTypesByName;
    }

    public void createNewDepositType(String name, String conditions, double percent, boolean refill, boolean withdraw) {
        DepositType depositType = findByName(name);
        if (depositType != null)
            return;
        depositType = new DepositType();
        depositType.setName(name);
        depositType.setConditions(conditions);
        depositType.setPercent(percent);
        depositType.setCanRefill(refill);
        depositType.setCanWithdrawal(withdraw);
        depositTypeRepo.save(depositType);
    }

    public DepositType findByID(Long id) {
        return depositTypeRepo.findById(id).get();
    }

    public void saveEditedDepositType(Long id, String name, String conditions, double percent, boolean refill, boolean withdraw) {
        DepositType depositType = depositTypeRepo.findById(id).get();
        depositType.setName(name);
        depositType.setConditions(conditions);
        depositType.setPercent(percent);
        depositType.setCanRefill(refill);
        depositType.setCanWithdrawal(withdraw);
        depositTypeRepo.save(depositType);
    }

    public void deleteByID(Long id) {
        DepositType depositType = depositTypeRepo.findById(id).get();
        if (depositType.getName().equals("Стандартный"))
            return;

        DepositType defaultType = depositTypeRepo.findByNameContaining("Стандартный");
        List<Deposit> deposits = depositService.findAllByDepositType(depositType);

        for (Deposit d: deposits) {
            if (d.getType().equals(depositType))
                d.setType(defaultType);
        }

        depositTypeRepo.deleteById(id);
    }

    public DepositType findOneByQuery(String name) {
        return depositTypeRepo.findByNameContaining(name);
    }

    public DepositType findByName(String name) {
        return depositTypeRepo.findByName(name);
    }

    public boolean existsInDB(String name) {
        return depositTypeRepo.findByName(name) == null;
    }

    public boolean existsInDB(Long id) {
        return depositTypeRepo.existsById(id);
    }
}
