package ru.mpei.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.repos.ClientRepo;

import java.util.ArrayList;
import java.util.List;

@Service //1
public class ClientService {
    @Autowired
    private ClientRepo clientRepo; //2

    @Autowired
    private CreditService creditService; //3

    @Autowired
    private DepositService depositService;

    public void createNewClient( //4
            String firstname, String patronymic, String lastname,
            String adress, String phoneNumber) {
        Client client = new Client();
        client.setFirstName(firstname);
        client.setPatronymic(patronymic);
        client.setLastName(lastname);
        client.setAdress(adress);
        client.setPhoneNumber(phoneNumber);
        clientRepo.save(client);
    }

    public Client findOneByQuery(String query) {
        return clientRepo.findByFirstNameContainingOrPatronymicContainingOrLastNameContainingOrAdressContaining(
                query, query, query, query);
    }

    public List<Client> findAllByQuery(String query) {
        return clientRepo.findDistinctByFirstNameContainingOrPatronymicContainingOrLastNameContainingOrAdressContaining
                (query, query, query, query);
    }

    public Iterable<Client> findAll() {
        return clientRepo.findAll();
    }

    public Client findByID(Long ID) {
        return clientRepo.findById(ID).get();
    }



    public void saveEditedClient(Long id, String firstname, String patronymic, String lastname, String adress, String phoneNumber) {
        Client client = clientRepo.findById(id).get();
        client.setFirstName(firstname);
        client.setPatronymic(patronymic);
        client.setLastName(lastname);
        client.setAdress(adress);
        client.setPhoneNumber(phoneNumber);
        clientRepo.save(client);
    }

    public void deleteClient(Long id) {
        Client client = clientRepo.findById(id).get();
        List<Credit> credits = creditService.findAllByClient(client);
        for (Credit c: credits) {
            creditService.deleteByID(c.getId());
        }

        List<Deposit> deposits = depositService.findAllByClient(client);
        for (Deposit d: deposits) {
            depositService.deleteByID(d.getId());
        }
        clientRepo.deleteById(id);
    }

    public float getMiddle() {
        Iterable<Client> clients = clientRepo.findAll();
        long summ = 0L;
        long count = 0L;
        for (Client c: clients) {
            summ += c.getId();
            count++;
        }
        float result = summ / count;
        return result;
    }

    public List<Client> getMiddleList(boolean b) {
        float middle = getMiddle();
        Iterable<Client> clients = clientRepo.findAll();
        List<Client> result = new ArrayList<>();
        for (Client c: clients) {
            if (b) {
                if (c.getId() > middle) {
                    result.add(c);
                }
            } else {
                if (c.getId() < middle) {
                    result.add(c);
                }
            }
        }
        return result;
    }
}
