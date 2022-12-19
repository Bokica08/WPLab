package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;

    }

    @GetMapping
    public String getManuPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Manufacturer> manufacturerList=this.manufacturerService.findAll();
        model.addAttribute("manufacturers",manufacturerList);
        return "AllManufacturers";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name, @RequestParam String country, @RequestParam String address)
    {
        manufacturerService.save(name,country,address);
        return "redirect:/balloons";
    }

    @GetMapping("/add-form")
    public String getManuAddPAge(Model m)
    {
        List<Manufacturer> manufacturers=this.manufacturerService.findAll();

        m.addAttribute("manufacturers",manufacturers);
        return "add-manu";
    }
}
