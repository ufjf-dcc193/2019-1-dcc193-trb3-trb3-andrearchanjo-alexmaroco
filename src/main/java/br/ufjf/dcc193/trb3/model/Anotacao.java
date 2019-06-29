package br.ufjf.dcc193.trb3.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Anotacao
 */
@Entity
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    private String titulo;

    @NotBlank(message = "O campo não pode estar vazio")
    private String descricao;

    private String url;

    private Long idCriador;

    private Date dataInclusao;

    private Date dataAlteracao;

    public Anotacao() {}

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

    public Long getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(Long idCriador) {
        this.idCriador = idCriador;
    }
    
    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "Anotacao [idCriador=" + idCriador + ", dataAlteracao=" + dataAlteracao + ", dataInclusao=" + dataInclusao
                + ", descricao=" + descricao + ", id=" + id + ", titulo=" + titulo + ", url=" + url + "]";
    }

    

    

}