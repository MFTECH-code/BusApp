package br.com.app.bus.controller;

import br.com.app.bus.model.dto.bus.CreateBusDTO;
import br.com.app.bus.model.entity.Bus;
import br.com.app.bus.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/bus")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        var busList = service.getAll();

        model.addAttribute("busList", busList);

        return "index";
    }

    @GetMapping("/form")
    public ModelAndView newBus(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("/form.html");
        Bus bus;
        if (id == null){
            bus = new Bus();
        }else{
            try{
                bus = service.getById(id);
            }catch (Exception e){
                bus = new Bus();
                mv.addObject("message ", e.getMessage());
            }
        }
        mv.addObject("bus", bus);
        return mv;
    }

    @RequestMapping(path="/form", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("bus") Bus bus, BindingResult binding) {
        ModelAndView mv = new ModelAndView("redirect:/bus");
        boolean newBus = true;
        if(bus != null) {
            newBus = false;
        }

        if(binding.hasErrors()){
            mv.addObject("bus", bus);
            return mv;
        }

        if(service.create(bus)){
            Bus savedBus = bus;
            if(newBus) {
                mv.addObject("bus", new Bus());
            }else{
                mv.addObject("bus", savedBus);
            }
        };

        mv.addObject("msg", "Onibus salvo com Sucesso");
        return mv;
    }

    @RequestMapping(path = "/delete")
    public ModelAndView deleteBus(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/bus");
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Onibus excluido com Sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erro ao remover Evento: " + e.getMessage());
            e.printStackTrace();
        }
        return mv;
    }
}
