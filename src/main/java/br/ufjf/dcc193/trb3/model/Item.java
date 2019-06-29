package br.ufjf.dcc193.trb3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

/**
 * Item
 */
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    private String titulo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anotacao> anotacoes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etiqueta> item_etiquetas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vinculo> item_vinculos;

    public Item() {}

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

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
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

    @Override
    public String toString() {
        return "Item [anotacoes=" + anotacoes + ", etiquetas=" + item_etiquetas + ", id=" + id + ", titulo=" + titulo
                + ", vinculos=" + item_vinculos + "]";
    }

    

    
}