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
import br.ufjf.dcc193.trb3.repository.EtiquetaRepository;

/**
 * EtiquetaController
 */
@Controller
@RequestMapping("/etiqueta")
public class EtiquetaController {

    @Autowired
    private EtiquetaRepository etRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroUsuario() {
        ModelAndView mv = new ModelAndView();
        System.err.println(etRepo.findAll());
        mv.addObject("etiqueta", new Etiqueta());
        mv.setViewName("form-cadastro-etiqueta");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroUsuario(@Valid Etiqueta etiqueta, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-cadastro-etiqueta");
            mv.addObject("etiqueta", etiqueta);
            return mv;
        }
        etRepo.save(etiqueta);
        mv.setViewName("redirect:/index.html");
        return mv;
    }

    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Etiqueta> etiquetas = etRepo.findAll();
        mv.addObject("etiquetas", etiquetas);
        mv.setViewName("list-etiquetas");
        return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirUsuario(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        etRepo.deleteById(id);
        mv.setViewName("redirect:/etiqueta/listar.html");
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarEtiqueta(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Etiqueta etiqueta = etRepo.findById(id).get();
        mv.addObject("etiqueta", etiqueta);
        mv.setViewName("form-edit-etiqueta");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarEtiqueta(@RequestParam Long id, @Valid Etiqueta etiqueta, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-edit-etiqueta");
                mv.addObject("etiqueta", etiqueta);
                return mv;
            }
            Etiqueta etq = etRepo.findById(id).get();
            String[] ignorar = {"id"};
            BeanUtils.copyProperties(etiqueta, etq, ignorar);
            etRepo.save(etiqueta);
            mv.setViewName("redirect:/etiqueta/listar.html");
            return mv;
    }
}