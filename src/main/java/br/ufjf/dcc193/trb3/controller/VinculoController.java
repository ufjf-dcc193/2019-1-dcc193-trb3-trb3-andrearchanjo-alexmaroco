package br.ufjf.dcc193.trb3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trb3.model.Etiqueta;
import br.ufjf.dcc193.trb3.model.Item;
import br.ufjf.dcc193.trb3.model.Vinculo;
import br.ufjf.dcc193.trb3.repository.EtiquetaRepository;
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
    private EtiquetaRepository etRepo;

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
        List<Etiqueta> etiquetas = etRepo.findAll();
        mv.addObject("etiquetas", etiquetas);
        mv.addObject("itens", itens);
        mv.addObject("item", item);
        mv.addObject("vinculo", new Vinculo());
        mv.setViewName("form-cadastro-vinculo");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroItem(@Valid Vinculo vinculo, @RequestParam Long id, @RequestParam List<Long> et, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        Item item = iRepo.findById(id).get();
        if(binding.hasErrors()){
            List<Item> itens = iRepo.getAlmostAllItems(id);
            System.err.println(item);
            System.err.println(vinculo);
            List<Etiqueta> etiquetas = etRepo.findAll();
            mv.addObject("etiquetas", etiquetas);
            mv.setViewName("form-cadastro-item");
            mv.addObject("vinculo", vinculo);
            mv.addObject("item", item);
            mv.addObject("itens", itens);
            return mv;
        }
        for (Long i : et) {
            Etiqueta e = etRepo.findById(i).get();
            System.err.println(e);
            vinculo.addEtiqueta(e);
        }
        System.err.println(item);
        System.err.println(vinculo);
        vinculo.setId(null);
        vinculo.setIdItemOrigem(item.getId());
        item.addVinculo(vinculo);
        vinculo = vRepo.save(vinculo);
        System.err.println(vinculo);
        mv.setViewName("redirect:/item/listar.html");
        return mv;
    }

    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Item i = iRepo.findById(id).get();
        List<Vinculo> vinculos = i.getItem_vinculos();
        mv.addObject("vinculos", vinculos);
        mv.addObject("idItem", id);
        mv.setViewName("list-vinculos");
        return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirVinculo(@RequestParam Long id, @RequestParam Long idItem) {
        ModelAndView mv = new ModelAndView();
        Item i = iRepo.findById(idItem).get();
        Vinculo v = vRepo.findById(id).get();
        i.getItem_vinculos().remove(v);
        vRepo.deleteById(id);
        mv.setViewName("redirect:/vinculo/listar.html?id="+idItem);
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarVinculo(@RequestParam Long id, @RequestParam Long idItem) {
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = vRepo.findById(id).get();
        List<Etiqueta> etiquetas = etRepo.findAll();
        mv.addObject("idItem", idItem);
        mv.addObject("etiquetas", etiquetas);
        mv.addObject("vinculo", vinculo);
        mv.setViewName("form-edit-vinculo");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarVinculo(@RequestParam Long id, @RequestParam List<Long> et, @Valid Vinculo vinculo, @RequestParam Long idItem, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-edit-item");
            List<Etiqueta> etiquetas = etRepo.findAll();
            mv.addObject("etiquetas", etiquetas);
            mv.addObject("idItem", idItem);
            mv.addObject("vinculo", vinculo);
            return mv;
        }
        Vinculo v = vRepo.findById(id).get();
        for (Long i : et) {
            Etiqueta e = etRepo.findById(i).get();
            System.err.println(e);
            vinculo.addEtiqueta(e);
        }
        vinculo.setIdItemOrigem(v.getIdItemOrigem());
        vinculo.setIdItemDestino(v.getIdItemDestino());
        vinculo.setVinculo_anotacoes(v.getVinculo_anotacoes());
        String[] ignorar = {"id", "vinculo_anotacoes"};
        BeanUtils.copyProperties(vinculo, v, ignorar);
        vRepo.save(vinculo);
        mv.setViewName("redirect:/vinculo/listar.html?id="+idItem);
        return mv;
    }
    
}