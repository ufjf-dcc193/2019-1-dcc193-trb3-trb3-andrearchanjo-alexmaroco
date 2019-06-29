package br.ufjf.dcc193.trb3.model;

import java.util.List;

import javax.persistence.Entity;

/**
 * Vinculo
 */
@Entity
public class Vinculo {

    private Long id;

    // Nao fazer manytoone com item(deixar unilateral), pq nao precisa fazer nada com itens de um vinculo
    private Long idItemOrigem;

    private Long idItemDestino;

    private List<Anotacao> anotacoes;

    private List<Etiqueta> etiquetas;

    public Vinculo() {}
}