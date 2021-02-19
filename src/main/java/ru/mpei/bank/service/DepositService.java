package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.domain.DepositType;
import ru.mpei.bank.repos.DepositRepo;

import java.text.ParseException;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DepositTypeService depositTypeService;

    public Iterable<Deposit> findAll() {
        return depositRepo.findAll();
    }

    public Iterable<Deposit> findAllByQuery(String query) {
        List<Client> clients = clientService.findAllByQuery(query);
        List<Deposit> resultList = null;
        for (Client c: clients) {
            List<Deposit> depositsByClient = depositRepo.findAllByClient(c);
            resultList = ServiceUtils.collideLists(resultList, depositsByClient);
        }

        List<DepositType> depositTypes = depositTypeService.findAllByQuery(query);
        for (DepositType dt: depositTypes) {
            List<Deposit> depositsByType = depositRepo.findAllByType(dt);
            resultList = ServiceUtils.collideLists(resultList, depositsByType);
        }

        return resultList;
    }

    public void createDeposit(String clientName, String depositType, String openDate, String closeDate, int summ) throws ParseException {
        Deposit deposit = new Deposit();
        deposit.setClient(clientService.findOneByQuery(clientName));
        deposit.setType(depositTypeService.findOneByQuery(depositType));
        deposit.setOpenDate(ServiceUtils.parseStringToCalendar(openDate));
        deposit.setCloseDate(ServiceUtils.parseStringToCalendar(closeDate));
        deposit.setSum(summ);
        depositRepo.save(deposit);
    }

    public Deposit findDepositByID(Long id) {
        return depositRepo.findById(id).get();
    }

    public void saveEditedDeposit(Long id, String clientName, String depositType, String openDate, String closeDate, int summ) throws ParseException {
        Deposit deposit = depositRepo.findById(id).get();
        deposit.setClient(clientService.findOneByQuery(clientName));
        deposit.setType(depositTypeService.findOneByQuery(depositType));
        deposit.setOpenDate(ServiceUtils.parseStringToCalendar(openDate));
        deposit.setCloseDate(ServiceUtils.parseStringToCalendar(closeDate));
        deposit.setSum(summ);
        depositRepo.save(deposit);
    }

    public void deleteByID(Long id) {
        Deposit deposit = depositRepo.findById(id).get();
        depositRepo.delete(deposit);
    }

    public List<Deposit> findAllByDepositType(DepositType depositType) {
        return depositRepo.findAllByType(depositType);
    }

    public List<Deposit> findAllByClient(Client client) {
        return depositRepo.findAllByClient(client);
    }
}