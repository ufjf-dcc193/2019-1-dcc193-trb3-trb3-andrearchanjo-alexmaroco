package br.ufjf.dcc193.trb3.service;

import org.springframework.stereotype.Service;

import br.ufjf.dcc193.trb3.model.Usuario;

/**
 * LoginService
 */

@Service
public class LoginService {

    private static Usuario user = null;

    public LoginService() {
        
    }

    public void login(Usuario u) {
        user = u;
    }

    public Usuario getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }

}