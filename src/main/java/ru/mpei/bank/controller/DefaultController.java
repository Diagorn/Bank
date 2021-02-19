package ru.mpei.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String getStartingPage() {
        return "startingPage";
    }

    @GetMapping("/error")
    public String getErrorPage(
            HttpServletRequest request,
            Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error", 404);
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error", 500);
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error", 403);
            }
            else model.addAttribute("error", "совершенно нетипичную и непонятную");
        }
        return "error";
    }

    @GetMapping("/static/css/mystyle.css")
    public String getHomePage() {
        return "redirect:/";
    }
}