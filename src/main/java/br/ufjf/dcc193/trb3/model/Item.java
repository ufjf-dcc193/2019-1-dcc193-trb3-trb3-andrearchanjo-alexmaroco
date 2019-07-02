package br.ufjf.dcc193.trb3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Item
 */
@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    private String titulo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anotacao> item_anotacoes;

    @ManyToMany()
    private List<Etiqueta> item_etiquetas;

    @ManyToMany()
    private List<Vinculo> item_vinculos;

    public Item() {
        item_anotacoes = new ArrayList<Anotacao>();
        item_etiquetas = new ArrayList<Etiqueta>();
        item_vinculos = new ArrayList<Vinculo>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Anotacao> getItem_anotacoes() {
        return item_anotacoes;
    }

    public void setItem_anotacoes(List<Anotacao> item_anotacoes) {
        this.item_anotacoes = item_anotacoes;
    }

    public List<Etiqueta> getItem_etiquetas() {
        return item_etiquetas;
    }

    public void setItem_etiquetas(List<Etiqueta> item_etiquetas) {
        this.item_etiquetas = item_etiquetas;
    }

    public List<Vinculo> getItem_vinculos() {
        return item_vinculos;
    }

    public void setItem_vinculos(List<Vinculo> item_vinculos) {
        this.item_vinculos = item_vinculos;
    }

    public void addEtiqueta(Etiqueta e){
        this.item_etiquetas.add(e);
    }

    public void addVinculo(Vinculo v){
        this.item_vinculos.add(v);
    }

    public void removeEtiqueta(Etiqueta e) {
        this.item_etiquetas.remove(e);
    }

    public void removeVinculo(Vinculo v) {
        this.item_vinculos.remove(v);
    }

    @Override
    public String toString() {
        List<Long> etqIds = new ArrayList<Long>();
        for(Etiqueta e: item_etiquetas) {
            etqIds.add(e.getId());
        }
        List<Long> vincIds = new ArrayList<Long>();
        for(Vinculo v: item_vinculos) {
            vincIds.add(v.getId());
        }
        List<Long> anotIds = new ArrayList<Long>();
        for(Anotacao a: item_anotacoes) {
            anotIds.add(a.getId());
        }
        return "Item [anotacoes=" + anotIds + ", etiquetas=" + etqIds + ", id=" + id + ", titulo=" + titulo
                + ", vinculos=" + vincIds + "]";
    }

    @PreRemove
    public void preRemove() {
        for(Etiqueta e: item_etiquetas) {
            e.removeItem(this);
        }
    }

    
}