package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.CreditType;
import ru.mpei.bank.service.CreditTypeService;

@Controller
public class CreditTypeController {
    @Autowired
    CreditTypeService creditTypeService;

    @GetMapping("/credit-types")
    public String getCreditTypePage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<CreditType> creditTypes;
        if (filter.equals("") || filter.isEmpty()) {
            creditTypes = creditTypeService.findAll();
        } else {
            creditTypes = creditTypeService.findAllByQuery(filter);
        }
        model.addAttribute("creditTypes", creditTypes);
        model.addAttribute("message", "");
        return "creditTypes";
    }

    @PostMapping("/credit-types")
    public String addCreditType(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String conditions,
            @RequestParam(required = true) double percent,
            @RequestParam(required = true) int numberOfDays
    ) {
        creditTypeService.createCreditType(name, conditions, percent, numberOfDays);
        return "redirect:/credit-types";
    }

    @GetMapping("/credit-types/edit/{id}")
    public String getCreditTypeEditingPage(
            @PathVariable Long id,
            Model model
    ) {
        CreditType creditType = creditTypeService.getByID(id);
        model.addAttribute("creditType", creditType);
        return "creditTypesEdit";
    }

    @PostMapping("/credit-types/edit/{id}")
    public String editCreditType(
            @PathVariable Long id,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String conditions,
            @RequestParam(required = true) double percent,
            @RequestParam(required = true) int numberOfDays
    ) {
        creditTypeService.saveEditedCreditType(id, name, conditions, percent, numberOfDays);
        return "redirect:/credit-types";
    }

    @PostMapping("/credit-types/delete/{id}")
    public String deleteCreditType(
            @PathVariable Long id
    ) {
        creditTypeService.deleteByID(id);
        return "redirect:/credit-types";
    }
}
