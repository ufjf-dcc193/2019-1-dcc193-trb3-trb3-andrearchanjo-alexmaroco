package br.ufjf.dcc193.trb3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trb3.model.Usuario;
import br.ufjf.dcc193.trb3.repository.UsuarioRepository;

/**
 * UsuarioController
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository uRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroTrabalho() {
        ModelAndView mv = new ModelAndView();
        System.err.println(uRepo.findAll());
        mv.addObject("usuario", new Usuario());
        mv.setViewName("form-cadastro-usuario");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroTrabalho(@Valid Usuario user, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-cadastro-trabalho");
            mv.addObject("usuario", user);
            return mv;
        }
        //System.err.println(trab.toString());
        uRepo.save(user);
        System.err.println(uRepo.findAll());
        mv.setViewName("redirect:/index.html");
        return mv;
    }
    
}