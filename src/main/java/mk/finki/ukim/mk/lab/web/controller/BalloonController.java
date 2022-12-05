package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;

    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Balloon> balloonList=this.balloonService.listAll();
        model.addAttribute("balloons",balloonList);
        return "listBalloons";
    }
    @PostMapping("/add")
    public String saveBalloon(@RequestParam String ime, @RequestParam String description, @RequestParam Long idManu)
    {
        balloonService.save(ime,description,idManu);
        return "redirect:/balloons";
    }
    @PostMapping("/str")
    public String deleteByStr(@RequestParam String text)
    {
        balloonService.deleteByStr(text);
        return "redirect:/balloons";
    }
    @GetMapping("/add-form")
    public String getAddBalloonPage(Model m)
    {
        List<Manufacturer> manufacturers=manufacturerService.findAll();
        m.addAttribute("manufacturers",manufacturers);
        return "add-balloon";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id)
    {
    this.balloonService.deleteById(id);
    return "redirect:/balloons";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id,Model model)
    {
        if(this.balloonService.findById(id).isPresent())
        {
            Balloon balloon=this.balloonService.findById(id).get();

            List<Manufacturer> manufacturers=manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("balloon",balloon);
           deleteBalloon(id);
            return "add-balloon";
        }
        return "redirect:/balloons?error=ProductNotFound";
    }
}
