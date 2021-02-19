package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.CreditType;
import ru.mpei.bank.domain.Expiration;
import ru.mpei.bank.repos.CreditRepo;

import java.text.ParseException;
import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditTypeService creditTypeService;

    @Autowired
    private ExpirationService expirationService;

    public Iterable<Credit> findAllCredits() {
        return creditRepo.findAll();
    }

    public Credit getByID(Long id) {
        return creditRepo.findById(id).get();
    }

    public boolean existsByID (Long id) {
        return creditRepo.existsById(id);
    }

    public List<Credit> findAllCreditsByQuery(String query) {
        List<Credit> creditsBySumm = null;
        if (query.matches("s.matches(\"[-+]?\\\\d+\")")) //is integer
            creditsBySumm = creditRepo.findAllBySumm(Integer.parseInt(query));
        List<Credit> creditsByClient = creditRepo.findAllByClient(clientService.findOneByQuery(query));
        List<Credit> creditsByCreditType = creditRepo.findAllByCreditType(creditTypeService.findOneByQuery(query));
        if (creditsBySumm != null)
            creditsByClient = ServiceUtils.collideLists(creditsBySumm, creditsByClient);
        ServiceUtils.collideLists(creditsByClient, creditsByCreditType);
        return creditsBySumm;
    }

    public void addCredit(String creditType, int summ, String date, String client, boolean closed) throws ParseException {
        Credit credit = new Credit();
        credit.setCreditType(creditTypeService.findOneByQuery(creditType));
        credit.setSumm(summ);
        credit.setStartingDate(ServiceUtils.parseStringToCalendar(date));
        credit.setClosed(closed);
        credit.setClient(clientService.findOneByQuery(client));
        creditRepo.save(credit);
    }


    public void saveEditedCredit(Long id, String creditType, int summ, String date, String client, boolean isExpired) throws ParseException {
        Credit credit = creditRepo.findById(id).get();
        credit.setSumm(summ);
        credit.setStartingDate(ServiceUtils.parseStringToCalendar(date));
        credit.setClient(clientService.findOneByQuery(client));
        credit.setCreditType(creditTypeService.findOneByQuery(creditType));
        credit.setClosed(isExpired);
        creditRepo.save(credit);
    }

    public void deleteByID(Long id) {
        Credit credit = creditRepo.findById(id).get();
        List<Expiration> expirations = expirationService.findAllByCredit(credit);
        for (Expiration e: expirations) {
            expirationService.deleteByID(e.getId());
        }
        creditRepo.deleteById(id);
    }

    public List<Credit> findAllByCreditType(CreditType creditType) {
        return creditRepo.findAllByCreditType(creditType);
    }

    public List<Credit> findAllByClient(Client client) {
        return creditRepo.findAllByClient(client);
    }

    public void setClosed(Credit credit) {
        credit.setClosed(true);
        creditRepo.save(credit);
    }
}
