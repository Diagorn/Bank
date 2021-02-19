package ru.mpei.bank.domain;

import javax.persistence.*;
import java.util.Set;

@Entity //1
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //2
    private Long id; //3

    private String firstName;

    private String patronymic;

    private String lastName;

    private String adress;

    private String phoneNumber;

    public Client() { //4
    }

    public Long getId() {
        return id;
    } //5

    public void setId(Long id) {
        this.id = id;
    } //6

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameWithInitials() {
        return lastName + " " +
                firstName.toCharArray()[0] +
                ". " +
                patronymic.toCharArray()[0] +
                ".";
    }
}
