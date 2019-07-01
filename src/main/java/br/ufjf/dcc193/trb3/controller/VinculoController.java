package br.ufjf.dcc193.trb3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trb3.model.Item;
import br.ufjf.dcc193.trb3.model.Vinculo;
import br.ufjf.dcc193.trb3.repository.ItemRepository;
import br.ufjf.dcc193.trb3.repository.VinculoRepository;
import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * VinculoController
 */
@Controller
@RequestMapping("/vinculo")
public class VinculoController {

    @Autowired
    private LoginService ls;

    @Autowired
    private ItemRepository iRepo;

    @Autowired
    private VinculoRepository vRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroItem(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        System.err.println(id);
        List<Item> itens = iRepo.getAlmostAllItems(id);
        for (Item var : itens) {
            System.err.println(var.toString());
        }
        Item item = iRepo.findById(id).get();
        mv.addObject("itens", itens);
        mv.addObject("item", item);
        mv.addObject("vinculo", new Vinculo());
        mv.setViewName("form-cadastro-vinculo");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroItem(@Valid Vinculo vinculo, @RequestParam Long id, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        Item item = iRepo.findById(id).get();
        if(binding.hasErrors()){
            List<Item> itens = iRepo.getAlmostAllItems(id);
            System.err.println(item);
            System.err.println(vinculo);
            mv.setViewName("form-cadastro-item");
            mv.addObject("vinculo", vinculo);
            mv.addObject("item", item);
            mv.addObject("itens", itens);
            return mv;
        }
        System.err.println(item);
        System.err.println(vinculo);
        vinculo.setId(null);
        vinculo.setIdItemOrigem(item.getId());
        vinculo = vRepo.save(vinculo);
        System.err.println(vinculo);
        mv.setViewName("redirect:/item/listar.html");
        return mv;
    }
}