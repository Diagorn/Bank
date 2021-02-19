package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.Deposit;
import ru.mpei.bank.service.DepositService;
import ru.mpei.bank.service.ServiceUtils;

import java.text.ParseException;

@Controller
public class DepositController {
    @Autowired
    private DepositService depositService;

    @Autowired
    private ServiceUtils serviceUtils;

    @GetMapping("/deposit")
    public String getDepositPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Deposit> deposits;
        if (filter.equals("") || filter.isEmpty()) {
            deposits = depositService.findAll();
        } else {
            deposits = depositService.findAllByQuery(filter);
        }
        model.addAttribute("deposits", deposits);
        model.addAttribute("serviceUtils", serviceUtils);
        model.addAttribute("message", "");
        return "deposit";
    }

    @PostMapping("/deposit")
    public String addDeposit(
            @RequestParam(required = true) String clientName,
            @RequestParam(required = true) String depositType,
            @RequestParam(required = true) String openDate,
            @RequestParam(required = false, defaultValue = "") String closeDate,
            @RequestParam(required = true) int summ
    ) throws ParseException {
        depositService.createDeposit(clientName, depositType, openDate, closeDate, summ);
        return "redirect:/deposit";
    }

    @GetMapping("/deposit/edit/{id}")
    public String getDepositEditingPage(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("deposit", depositService.findDepositByID(id));
        return "depositEdit";
    }
    @PostMapping("/deposit/edit/{id}")
    public String saveEditedDeposit(
        @PathVariable Long id,
        @RequestParam(required = true) String clientName,
        @RequestParam(required = true) String depositType,
        @RequestParam(required = true) String openDate,
        @RequestParam(required = false, defaultValue = "") String closeDate,
        @RequestParam(required = true) int summ
    ) throws ParseException {
        depositService.saveEditedDeposit(id, clientName, depositType, openDate, closeDate, summ);
        return "redirect:/deposit";
    }

    @PostMapping("/deposit/delete/{id}")
    public String deleteDeposit(
            @PathVariable Long id
    ) {
        depositService.deleteByID(id);
        return "redirect:/deposit";
    }
}
