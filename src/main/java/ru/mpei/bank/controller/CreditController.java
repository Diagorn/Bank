package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.Credit;
import ru.mpei.bank.service.CreditService;
import ru.mpei.bank.service.ServiceUtils;

import java.text.ParseException;

@Controller
public class CreditController {
    @Autowired
    private CreditService creditService;

    @Autowired
    private ServiceUtils serviceUtils;

    @GetMapping("/credit")
    public String getCreditPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Credit> credits;
        if (filter.equals("") || filter.isEmpty()) {
            credits = creditService.findAllCredits();
        } else {
            credits = creditService.findAllCreditsByQuery(filter);
        }
        model.addAttribute("credits", credits);
        model.addAttribute("serviceUtils", serviceUtils);
        model.addAttribute("message", "");
        return "credit";
    }

    @PostMapping("/credit")
    public String addNewCredit(
            @RequestParam(required = true) String creditType,
            @RequestParam(required = true) int summ,
            @RequestParam(required = true) String date,
            @RequestParam(required = true) String client,
            @RequestParam(required = false) boolean closed,
            Model model
    ) throws ParseException {
        creditService.addCredit(creditType, summ, date, client, closed);
        return "redirect:/credit";
    }

    @GetMapping("/credit/edit/{id}")
    public String getCreditEditingPage(
            @PathVariable Long id,
            Model model
    ) {
        if (!creditService.existsByID(id)) {
            model.addAttribute("credits", creditService.findAllCredits());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "credit";
        }
        model.addAttribute("credit", creditService.getByID(id));
        return "creditEdit";
    }

    @PostMapping("/credit/edit/{id}")
    public String saveEditedCredit(
            @PathVariable Long id,
            @RequestParam(required = true) String creditType,
            @RequestParam(required = true) int summ,
            @RequestParam(required = true) String date,
            @RequestParam(required = true) String client,
            @RequestParam(required = false) boolean isExpired,
            Model model
    ) throws ParseException {
        if (!creditService.existsByID(id)) {
            model.addAttribute("credits", creditService.findAllCredits());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "credit";
        }
        creditService.saveEditedCredit(id, creditType, summ, date, client, isExpired);
        return "redirect:/credit";
    }

    @PostMapping("/credit/delete/{id}")
    public String deleteCreditByID (
            @PathVariable Long id,
            Model model
    ) {
        if (!creditService.existsByID(id)) {
            model.addAttribute("credits", creditService.findAllCredits());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "credit";
        }
        creditService.deleteByID(id);
        return "redirect:/credit";
    }
}