package br.ufjf.dcc193.trb3.model;

import javax.persistence.Entity;

/**
 * Etiqueta
 */
@Entity
public class Etiqueta {

    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    public Etiqueta() {}

    
}