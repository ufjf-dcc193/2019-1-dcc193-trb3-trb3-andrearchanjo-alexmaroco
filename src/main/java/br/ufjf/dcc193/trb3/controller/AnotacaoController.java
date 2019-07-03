package br.ufjf.dcc193.trb3.controller;

import java.util.Date;
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

import br.ufjf.dcc193.trb3.model.Anotacao;
import br.ufjf.dcc193.trb3.model.Item;
import br.ufjf.dcc193.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.trb3.repository.ItemRepository;
import br.ufjf.dcc193.trb3.repository.VinculoRepository;
import br.ufjf.dcc193.trb3.service.LoginService;

/**
 * AnotacaoController
 */
@Controller
@RequestMapping("/anotacao")
public class AnotacaoController {

    @Autowired
    private LoginService ls;

    @Autowired
    private AnotacaoRepository aRepo;

    @Autowired
    private ItemRepository iRepo;

    @Autowired
    private VinculoRepository vRepo;

    @GetMapping("/cadastro.html")
    public ModelAndView cadastroAnotacao(@RequestParam(required = false) Long idItem) {
        ModelAndView mv = new ModelAndView();
        // System.err.println(iRepo.findAll());
        
        if(ls.getUser() == null){
            mv.setViewName("redirect:/item/listar.html");
            return mv;
        }

        if(idItem != null){
            System.err.println("ID:" + idItem);
            Item i = iRepo.findById(idItem).get();
            mv.addObject("item", i);
        }
        mv.addObject("anotacao", new Anotacao());
        mv.setViewName("form-cadastro-anotacao");
        return mv;
    }

    @PostMapping("/cadastro.html")
    public ModelAndView cadastroAnotacao(@Valid Anotacao anotacao, @RequestParam(required = false) Long idItem, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-cadastro-anotacao");
            mv.addObject("idItem", idItem);
            mv.addObject("anotacao", anotacao);
            return mv;
        }
        anotacao.setIdCriador(ls.getUser().getId());
        anotacao.setDataInclusao(new Date());;
        if(idItem != null){
            Item i = iRepo.findById(idItem).get();
            i.getItem_anotacoes().add(anotacao);
        }
        aRepo.save(anotacao);
        // System.err.println(item);
        mv.setViewName("redirect:/item/listar.html");
        return mv;
    }

    @GetMapping(value={"/listar.html"})
    public ModelAndView listarTodos(@RequestParam Long idItem) {
        ModelAndView mv = new ModelAndView();
        Item i = iRepo.findById(idItem).get();
        List<Anotacao> anotacoes = i.getItem_anotacoes();
        mv.addObject("anotacoes", anotacoes);
        mv.addObject("idItem", i.getId());
        mv.setViewName("list-anotacoes");
        return mv;
    }

    @GetMapping(value={"/excluir.html" })
    public ModelAndView excluirAnotacao(@RequestParam Long id, @RequestParam Long idItem) {
        ModelAndView mv = new ModelAndView();
        Anotacao a = aRepo.findById(id).get();
        Item i = iRepo.findById(idItem).get();
        
        i.getItem_anotacoes().remove(a);
        aRepo.delete(a);
        
        mv.setViewName("redirect:/anotacao/listar.html?idItem="+idItem);
        return mv;
    }

    @GetMapping(value={"/editar.html" })
    public ModelAndView editarAnotacao(@RequestParam Long id, @RequestParam Long idItem) {
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = aRepo.findById(id).get();
        mv.addObject("idItem", idItem);
        mv.addObject("anotacao", anotacao);
        mv.setViewName("form-edit-anotacao");
        return mv;
    }

    @PostMapping(value={"/editar.html" })
    public ModelAndView editarAnotacao(@RequestParam Long id, @Valid Anotacao anotacao, @RequestParam Long idItem, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("form-edit-item");
            mv.addObject("idItem", idItem);
            mv.addObject("anotacao", anotacao);
            return mv;
        }
        Anotacao a = aRepo.findById(id).get();
        anotacao.setIdCriador(a.getIdCriador());
        anotacao.setDataInclusao(a.getDataInclusao());
        anotacao.setDataAlteracao(new Date());
        // Remove a referencia aos antigos vinculos
        
        String[] ignorar = {"id"};
        BeanUtils.copyProperties(anotacao, a, ignorar);
        aRepo.save(anotacao);
        //System.err.println(v);
        mv.setViewName("redirect:/anotacao/listar.html?idItem="+idItem);
        return mv;
    }


}