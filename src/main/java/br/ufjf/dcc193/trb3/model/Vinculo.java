package br.ufjf.dcc193.trb3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Vinculo
 */
@Entity
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    // Nao fazer manytoone com item(deixar unilateral), pq nao precisa fazer nada com itens de um vinculo
    private Long idItemOrigem;

    private Long idItemDestino;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anotacao> vinculo_anotacoes;

    @ManyToMany()
    private List<Etiqueta> vinculo_etiquetas;

    public Vinculo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdItemOrigem() {
        return idItemOrigem;
    }

    public void setIdItemOrigem(Long idItemOrigem) {
        this.idItemOrigem = idItemOrigem;
    }

    public Long getIdItemDestino() {
        return idItemDestino;
    }

    public void setIdItemDestino(Long idItemDestino) {
        this.idItemDestino = idItemDestino;
    }

    public List<Anotacao> getVinculo_anotacoes() {
        return vinculo_anotacoes;
    }

    public void setVinculo_anotacoes(List<Anotacao> vinculo_anotacoes) {
        this.vinculo_anotacoes = vinculo_anotacoes;
    }

    public List<Etiqueta> getVinculo_etiquetas() {
        return vinculo_etiquetas;
    }

    public void setVinculo_etiquetas(List<Etiqueta> vinculo_etiquetas) {
        this.vinculo_etiquetas = vinculo_etiquetas;
    }

    @Override
    public String toString() {
        return "Vinculo [anotacoes=" + vinculo_anotacoes + ", etiquetas=" + vinculo_etiquetas + ", id=" + id + ", idItemDestino="
                + idItemDestino + ", idItemOrigem=" + idItemOrigem + "]";
    }

    

    

}