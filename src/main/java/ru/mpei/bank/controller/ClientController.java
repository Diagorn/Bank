package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.Client;
import ru.mpei.bank.service.ClientService; //1

import java.util.List;

@Controller //2
public class ClientController { //3
    @Autowired //4
    private ClientService clientService;

    @GetMapping("/client") //5
    public String getClientPage( //6
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model //7
    ) {
        Iterable<Client> clients;
//        if (filter.equals("") || filter.isEmpty()) {
//            clients = clientService.findAll();
//        } else {
//            clients = clientService.findAllByQuery(filter);
//        }
        float middle = clientService.getMiddle();
        List<Client> biggerIDs = clientService.getMiddleList(true);
        List<Client> lowerIDs = clientService.getMiddleList(false);
        model.addAttribute("big", biggerIDs);
        model.addAttribute("low", lowerIDs);
        model.addAttribute("middle", middle);
        return "client"; //9
    }

    @PostMapping("/client")
    public String addClient(
            @RequestParam(required = true) String firstname,
            @RequestParam(required = true) String patronymic,
            @RequestParam(required = true) String lastname,
            @RequestParam(required = true) String adress,
            @RequestParam(required = true) String phoneNumber
    ) {
        clientService.createNewClient(firstname, patronymic, lastname, adress, phoneNumber);
        return "redirect:/client";
    }

    @GetMapping("/client/edit/{id}")
    public String getClientEditingPage(
            @PathVariable Long id,
            Model model
    ) {
        Client client = clientService.findByID(id);
        model.addAttribute("client", client);
        return "clientEdit";
    }

    @PostMapping("/client/edit/{id}")
    public String saveClientChanges(
            @PathVariable Long id,
            @RequestParam(required = true) String firstname,
            @RequestParam(required = true) String patronymic,
            @RequestParam(required = true) String lastname,
            @RequestParam(required = true) String adress,
            @RequestParam(required = true) String phoneNumber
    ) {
        clientService.saveEditedClient(id, firstname, patronymic, lastname, adress, phoneNumber);
        return "redirect:/client";
    }

    @PostMapping("/client/delete/{id}")
    public String deleteClient(
            @PathVariable Long id
    ) {
        clientService.deleteClient(id);
        return "redirect:/client";
    }
}
