package br.ufjf.dcc193.trb3.model;

import javax.persistence.Entity;

/**
 * Usuario
 */
@Entity
public class Usuario {

    private Long id;

    private String nome;

    private String codAcesso;

    private String descricao;

    private String email;

    public Usuario() {}
}