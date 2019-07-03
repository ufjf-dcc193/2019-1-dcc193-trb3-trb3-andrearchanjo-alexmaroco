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

import br.ufjf.dcc193.trb3.model.Usuario;
import br.ufjf.dcc193.trb3.repository.UsuarioRepository;
import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * UsuarioController
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private LoginService ls;

    @Autowired
    UsuarioRepository uRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroUsuario() {
        ModelAndView mv = new ModelAndView();
        System.err.println(uRepo.findAll());
        mv.addObject("usuario", new Usuario());
        mv.setViewName("form-cadastro-usuario");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroUsuario(@Valid Usuario user, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-cadastro-usuario");
            mv.addObject("usuario", user);
            return mv;
        }
        uRepo.save(user);
        mv.setViewName("redirect:/index.html");
        return mv;
    }

    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView();
        List<Usuario> users = uRepo.findAll();
        mv.addObject("usuarios", users);
        System.err.println(users.toString());
        mv.setViewName("list-usuarios");
        return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirUsuario(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        if(ls.getUser().getId() == id) {
            ls.logout();
        }
        uRepo.deleteById(id);
        mv.setViewName("redirect:/usuario/listar.html");
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarUsuario(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = uRepo.findById(id).get();
        mv.addObject("usuario", usuario);
        mv.setViewName("form-edit-usuario");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarUsuario(@RequestParam Long id, @Valid Usuario usuario, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("form-edit-usuario");
                mv.addObject("usuario", usuario);
                return mv;
            }
            Usuario u = uRepo.findById(id).get();
            String[] ignorar = {"id"};
            BeanUtils.copyProperties(usuario, u, ignorar);
            uRepo.save(u);
            mv.setViewName("redirect:/usuario/listar.html");
            return mv;
    }

    @GetMapping(value={"/usuario-logado.html" })
    public ModelAndView logado() {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = ls.getUser();
        mv.addObject("usuario", usuario);
        mv.setViewName("usuario-logado");
        return mv;
    }
    
}