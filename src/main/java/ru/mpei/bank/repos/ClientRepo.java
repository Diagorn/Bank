package ru.mpei.bank.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpei.bank.domain.Client;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Long> { //1
    Client findByFirstNameContainingOrPatronymicContainingOrLastNameContainingOrAdressContaining(
            String firstName, String patronymic, String lastName, String adress);
    List<Client> findDistinctByFirstNameContainingOrPatronymicContainingOrLastNameContainingOrAdressContaining(
            String firstName, String patronymic, String lastName, String adress);
}
