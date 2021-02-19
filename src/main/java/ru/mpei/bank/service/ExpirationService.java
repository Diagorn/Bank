package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.Expiration;
import ru.mpei.bank.repos.CreditRepo;
import ru.mpei.bank.repos.ExpirationRepo;
import ru.mpei.bank.repos.MoneyRepo;

import java.text.ParseException;
import java.util.List;

@Service
public class ExpirationService {
    @Autowired
    private ExpirationRepo expirationRepo;

    @Autowired
    private MoneyRepo moneyRepo;

    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private CreditService creditService;

    public Iterable<Expiration> findAllExpirations() {
        return expirationRepo.findAll();
    }

    public Expiration getByID (Long ID) {
        return expirationRepo.findById(ID).get();
    }

    public boolean existsInDB(Long ID) {
        return expirationRepo.existsById(ID);
    }

    public List<Expiration> findExpirationsByQuery(String query) {
        List<Expiration> expirationsBySumm;
        if (query.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) { //checking if it is double
            expirationsBySumm = expirationRepo.findAllBySumm(Double.parseDouble(query));
        } else {
            expirationsBySumm = null;
        }
        List<Expiration> expirationsByMoney = expirationRepo.findAllByMoney(moneyRepo.findByMoneyName(query));
        if (expirationsBySumm != null)
            return ServiceUtils.collideLists(expirationsBySumm, expirationsByMoney);
        else return expirationsByMoney;
    }

    public void addExpiration(String moneyName, double sum, Long creditNumber, String date) throws ParseException {
        if (sum < 0)
            return;
        Credit credit = creditRepo.findById(creditNumber).get();
        credit.setSumm(credit.getSumm() - ((int) sum * (int)moneyRepo.findByMoneyName(moneyName).getSummInRoubles()));
        if (credit.getSumm() <= 0)
            creditService.setClosed(credit);
        creditRepo.save(credit);

        Expiration expiration = new Expiration();
        expiration.setMoney(moneyRepo.findByMoneyName(moneyName));
        expiration.setSumm(sum);
        expiration.setCredit(creditRepo.findById(creditNumber).get());
        expiration.setDateOfExpiration(ServiceUtils.parseStringToCalendar(date));
        expirationRepo.save(expiration);
    }

    public void saveEditedExpiration(String moneyName,
                                     double sum,
                                     Long creditNumber,
                                     String date,
                                     Long expirationID) throws ParseException {
        Expiration expiration = expirationRepo.findById(expirationID).get();
        double difference = (sum - expiration.getSumm()) * expiration.getMoney().getSummInRoubles();
        if (difference < 0)
            return;
        expiration.setDateOfExpiration(ServiceUtils.parseStringToCalendar(date));
        expiration.setCredit(creditRepo.findById(creditNumber).get());
        expiration.setSumm(sum);
        expiration.setMoney(moneyRepo.findByMoneyName(moneyName));
        Credit credit = creditRepo.findById(creditNumber).get();
        credit.setSumm(credit.getSumm() - (int) difference);
        if (credit.getSumm() <= 0)
            creditService.setClosed(credit);
        expirationRepo.save(expiration);
        creditRepo.save(credit);
    }

    public void deleteByID(Long expirationID) {
        expirationRepo.deleteById(expirationID);
    }

    public List<Expiration> findAllByCredit(Credit credit) {
        return expirationRepo.findAllByCredit(credit);
    }
}
