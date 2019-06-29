package br.ufjf.dcc193.trb3.model;

import java.util.List;

import javax.persistence.Entity;

/**
 * Item
 */
@Entity
public class Item {

    private Long id;

    private String titulo;

    private List<Anotacao> anotacoes;

    private List<Etiqueta> etiquetas;

    private List<Vinculo> vinculos;

    public Item() {}
}