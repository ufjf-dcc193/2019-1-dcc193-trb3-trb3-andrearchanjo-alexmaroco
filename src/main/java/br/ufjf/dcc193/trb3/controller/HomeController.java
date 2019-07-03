package br.ufjf.dcc193.trb3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired
    LoginService ls;

    @GetMapping({ "", "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", ls.getUser());
        mv.setViewName("index");
        return mv;
    }
}