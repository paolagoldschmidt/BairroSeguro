package br.com.gavazzoni.bairroseguro.controllers;

import br.com.gavazzoni.bairroseguro.service.CrimeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CrimeService crimeService;

    public HomeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        var cities = this.crimeService.getCities();
        String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        var environments = this.crimeService.getEnvironments();
        var natures = this.crimeService.getNatures();

        model.addAttribute("cities", cities);
        model.addAttribute("months", months);
        model.addAttribute("environments", environments);
        model.addAttribute("natures", natures);
        model.addAttribute("crimes", crimeService.getPaginated(1, 10, java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));


        return "home";
    }
}
