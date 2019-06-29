package br.ufjf.dcc193.trb3.model;

import java.sql.Date;

import javax.persistence.Entity;

/**
 * Anotacao
 */
@Entity
public class Anotacao {

    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    private Usuario criador;

    private Date dataInclusao;

    private Date dataAlteracao;

    public Anotacao() {}

}