package br.ufjf.dcc193.trb3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    
    
}