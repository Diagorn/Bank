package ru.mpei.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpei.bank.domain.DepositType;
import ru.mpei.bank.service.DepositTypeService;

@Controller
public class DepositTypeController {
    @Autowired
    private DepositTypeService depositTypeService;

    @GetMapping("/deposit-types")
    public String getDepositTypePage (
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<DepositType> depositTypes;
        if (filter.equals("") || filter.isEmpty()) {
            depositTypes = depositTypeService.findAll();
        } else {
            depositTypes = depositTypeService.findAllByQuery(filter);
        }
        model.addAttribute("message", "");
        model.addAttribute("depositTypes", depositTypes);
        return "depositType";
    }

    @PostMapping("/deposit-types")
    public String addNewDepositType(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String conditions,
            @RequestParam(required = true) double percent,
            @RequestParam(required = false) boolean refill,
            @RequestParam(required = false) boolean withdraw,
            Model model
    ) {
        if (!depositTypeService.existsInDB(name)) {
            model.addAttribute("depositTypes", depositTypeService.findAll());
            model.addAttribute("message", "Такое уже есть в базе данных!");
            return "depositType";
        }
        depositTypeService.createNewDepositType(name, conditions, percent, refill, withdraw);
        return "redirect:/deposit-types";
    }

    @GetMapping("/deposit-types/edit/{id}")
    public String getCreditTypeEditingPage(
            @PathVariable Long id,
            Model model
    ) {
        DepositType depositType = depositTypeService.findByID(id);
        model.addAttribute("depositType", depositType);
        return "depositTypeEdit";
    }

    @PostMapping("/deposit-types/edit/{id}")
    public String editCreditType(
            @PathVariable Long id,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String conditions,
            @RequestParam(required = true) double percent,
            @RequestParam(required = false) boolean refill,
            @RequestParam(required = false) boolean withdraw,
            Model model
    ) {
        if (!depositTypeService.existsInDB(id)) {
            model.addAttribute("DepositTypes", depositTypeService.findAll());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "depositType";
        }
        depositTypeService.saveEditedDepositType(id, name, conditions, percent, refill, withdraw);
        return "redirect:/deposit-types";
    }

    @PostMapping("/deposit-types/delete/{id}")
    public String deleteCreditType(
            @PathVariable Long id,
            Model model
    ) {
        if (!depositTypeService.existsInDB(id)) {
            model.addAttribute("DepositTypes", depositTypeService.findAll());
            model.addAttribute("message", "Кто-то дал вам левую ссылку");
            return "depositType";
        }
        depositTypeService.deleteByID(id);
        return "redirect:/deposit-types";
    }
}
