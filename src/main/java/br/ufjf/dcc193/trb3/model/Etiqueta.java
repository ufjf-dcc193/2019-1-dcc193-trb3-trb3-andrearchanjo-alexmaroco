package br.ufjf.dcc193.trb3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotBlank;

/**
 * Etiqueta
 */
@Entity
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    private String titulo;

    @NotBlank(message = "O campo não pode estar vazio")
    private String descricao;

    @NotBlank(message = "O campo não pode estar vazio")
    private String url;

    @ManyToMany()
    private List<Item> itemEtiqueta;

    @ManyToMany()
    private List<Vinculo> vinculoEtiqueta;

    public Etiqueta() {}

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Etiqueta [descricao=" + descricao + ", id=" + id + ", titulo=" + titulo + ", url=" + url + "]";
    }

    
    // Remove a etiqueta a ser deletada das outras entidades
    @PreRemove
    public void preremove() {
        for(Item i: itemEtiqueta) {
            i.removeEtiqueta(this);
        }
        for(Vinculo v: vinculoEtiqueta) {
            v.removeEtiqueta(this);
        }
    }

    public List<Item> getItemEtiqueta() {
        return itemEtiqueta;
    }

    public void setItemEtiqueta(List<Item> itemEtiqueta) {
        this.itemEtiqueta = itemEtiqueta;
    }

    public List<Vinculo> getVinculoEtiqueta() {
        return vinculoEtiqueta;
    }

    public void setVinculoEtiqueta(List<Vinculo> vinculoEtiqueta) {
        this.vinculoEtiqueta = vinculoEtiqueta;
    }

    public void addItem(Item i) {
        this.itemEtiqueta.add(i);
    }

    public boolean removeItem(Item i) {
        return this.itemEtiqueta.remove(i);
    }

    public void addVinculo(Vinculo v) {
        this.vinculoEtiqueta.add(v);
    }

    public boolean removeVinculo(Vinculo v) {
        return this.vinculoEtiqueta.remove(v);
    }
    
}