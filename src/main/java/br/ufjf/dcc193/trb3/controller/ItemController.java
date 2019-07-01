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
import br.ufjf.dcc193.trb3.repository.EtiquetaRepository;
import br.ufjf.dcc193.trb3.repository.ItemRepository;
import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * ItemController
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private LoginService ls;

    @Autowired
    private EtiquetaRepository etRepo;

    @Autowired
    private ItemRepository iRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroItem() {
        ModelAndView mv = new ModelAndView();
        System.err.println(iRepo.findAll());
        List<Etiqueta> etiquetas = etRepo.findAll();
        mv.addObject("item", new Item());
        mv.addObject("etiquetas", etiquetas);
        mv.setViewName("form-cadastro-item");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroItem(@Valid Item item, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-cadastro-item");
            mv.addObject("item", item);
            return mv;
        }
        /*for (Long i : et) {
            Etiqueta e = etRepo.findById(i).get();
            System.err.println(e);
            item.addEtiqueta(e);
        }*/
    
        iRepo.save(item);
        System.err.println(item);
        mv.setViewName("redirect:/usuario/usuario-logado.html");
        return mv;
    }

    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Item> itens = iRepo.findAll();
        mv.addObject("itens", itens);
        mv.setViewName("list-itens");
        return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirUsuario(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        iRepo.deleteById(id);
        mv.setViewName("redirect:/item/listar.html");
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarItem(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Item item = iRepo.findById(id).get();
        mv.addObject("item", item);
        mv.setViewName("form-edit-item");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarItem(@RequestParam Long id, @Valid Item item, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-edit-item");
                mv.addObject("item", item);
                return mv;
            }
            Item it = iRepo.findById(id).get();
            item.setItem_etiquetas(it.getItem_etiquetas());
            String[] ignorar = {"id", "item_anotacoes", "item_vinculos"};
            BeanUtils.copyProperties(item, it, ignorar);
            iRepo.save(item);
            mv.setViewName("redirect:/item/listar.html");
            return mv;
    }
    
}