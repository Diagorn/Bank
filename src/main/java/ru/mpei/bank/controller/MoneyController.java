package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.Money;
import ru.mpei.bank.service.MoneyService;

@Controller
public class MoneyController {
    @Autowired
    private MoneyService moneyService;

    @GetMapping("/money")
    public String getMoneyPage(@RequestParam(required = false, defaultValue = "") String filter,
                               Model model) {
        Iterable<Money> moneyTypes;
        if (filter.equals("") || filter.isEmpty()) {
            moneyTypes = moneyService.findAllMoneyTypes();
        }
        else {
            moneyTypes = moneyService.findAllByFilter((filter));
        }
        model.addAttribute("currencies", moneyTypes);
        return "money";
    }

    @PostMapping("/money")
    public String addCurrency(
        @RequestParam(required = true) String name,
        @RequestParam(required = true) double sum,
        Model model
    ) {
        if (moneyService.existsInDB(name)) {
            model.addAttribute("currencies", moneyService.findAllMoneyTypes());
            model.addAttribute("message", "Такая валюта уже существует!");
            return "money";
        }
        moneyService.create(name, sum);
        return "redirect:/money";
    }

    @GetMapping("/money/edit/{moneyName}")
    public String getCurrencyEditingPage(
            @PathVariable String moneyName,
            Model model
    ) {
        if (!moneyService.existsInDB(moneyName)) {
            model.addAttribute("currencies", moneyService.findAllMoneyTypes());
            model.addAttribute("message", "Такой валюты нет!");
            return "money";
        }
        Money money = moneyService.findSingleByMoneyName(moneyName);
        model.addAttribute("money", money);
        return "moneyEdit";
    }

    @PostMapping("money/edit/{moneyName}")
    public String editCurrency(
            @PathVariable String moneyName,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) double sum,
            Model model
    ) {
        if (!moneyService.existsInDB(moneyName)) {
            model.addAttribute("currencies", moneyService.findAllMoneyTypes());
            model.addAttribute("message", "Такой валюты нет, кто-то дал вам неактуальную ссылку!");
            return "money";
        }
        moneyService.updateByName(moneyName, name, sum);
        return "redirect:/money";
    }

    @PostMapping("/money/delete/{moneyName}")
    public String deleteCurrency (
            @PathVariable String moneyName,
            Model model
    ) {
        if (!moneyService.existsInDB(moneyName)) {
            model.addAttribute("currencies", moneyService.findAllMoneyTypes());
            model.addAttribute("message", "Такой валюты нет, кто-то дал вам неактуальную ссылку!");
            return "money";
        }
        moneyService.deleteByName(moneyName);
        return "redirect:/money";
    }
}
