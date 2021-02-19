package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.Expiration;
import ru.mpei.bank.service.ExpirationService;
import ru.mpei.bank.service.ServiceUtils;

import java.text.ParseException;

@Controller
public class ExpirationController {
    @Autowired
    private ExpirationService expirationService;

    @Autowired
    private ServiceUtils serviceUtils;

    @GetMapping("/expiration")
    public String getExpirationPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Expiration> expirations;
        if (filter.equals("") || filter.isEmpty()) {
            expirations = expirationService.findAllExpirations();
        } else {
            expirations = expirationService.findExpirationsByQuery(filter);
        }
        model.addAttribute("expirations", expirations);
        model.addAttribute("serviceUtils", serviceUtils);
        return "expiration";
    }

    @PostMapping("/expiration")
    public String addExpiration(
            @RequestParam(required = true) String moneyName,
            @RequestParam(required = true) double sum,
            @RequestParam(required = true) Long creditNumber,
            @RequestParam(required = true) String date
            ) throws ParseException {
        expirationService.addExpiration(moneyName, sum, creditNumber, date);
        return "redirect:/expiration";
    }

    @GetMapping("/expiration/edit/{expirationID}")
    public String editExpiration (
            @PathVariable Long expirationID,
            Model model
    ) {
        if (!expirationService.existsInDB(expirationID)) {
            model.addAttribute("expirations", expirationService.findAllExpirations());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "expiration";
        }
        model.addAttribute("expiration", expirationService.getByID(expirationID));
        return "expirationEdit";
    }

    @PostMapping("/expiration/edit/{expirationID}")
    public String saveEditedExpiration (
            @PathVariable Long expirationID,
            @RequestParam(required = true) String moneyName,
            @RequestParam(required = true) double sum,
            @RequestParam(required = true) Long creditNumber,
            @RequestParam(required = true) String date,
            Model model
    ) throws ParseException {
        if (!expirationService.existsInDB(expirationID)) {
            model.addAttribute("expirations", expirationService.findAllExpirations());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "expiration";
        }
        expirationService.saveEditedExpiration(moneyName, sum, creditNumber, date, expirationID);
        return "redirect:/expiration";
    }

    @PostMapping("/expiration/delete/{expirationID}")
    public String deleteExpiration(
            @PathVariable Long expirationID,
            Model model
    ) {
        if (!expirationService.existsInDB(expirationID)) {
            model.addAttribute("expirations", expirationService.findAllExpirations());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "expiration";
        }
        expirationService.deleteByID(expirationID);
        return "redirect:/expiration";
    }
}
