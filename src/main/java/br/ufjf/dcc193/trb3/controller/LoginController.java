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
import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * LoginController
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService ls;

    @Autowired
    UsuarioRepository uRepo;

    @GetMapping(value={"/login.html"})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("login");
        return mv;
    }

    @PostMapping(value={"/login.html"})
    public ModelAndView login(@Valid Usuario usuario, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            // System.err.println(usuario);
            Usuario user = uRepo.findOneByEmailAndCodAcesso(usuario.getEmail(), usuario.getCodAcesso());
            System.err.println(user);
            if(user != null){
                ls.login(user);
                mv.setViewName("redirect:/index.html");
                return mv;
            }
            mv.setViewName("redirect:/login.html");
            return mv;
    }

    @GetMapping(value={"/logout.html"})
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView();
        ls.logout();
        mv.setViewName("redirect:/index.html");
        return mv;
    }
    
}