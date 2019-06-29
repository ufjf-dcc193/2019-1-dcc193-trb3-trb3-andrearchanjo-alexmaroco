package br.ufjf.dcc193.trb3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Usuario
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    private String nome;

    @NotBlank(message = "O campo não pode estar vazio")
    private String codAcesso;

    @NotBlank(message = "O campo não pode estar vazio")
    private String descricao;

    @NotBlank(message = "O campo não pode estar vazio")
    private String email;

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(String codAcesso) {
        this.codAcesso = codAcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [codAcesso=" + codAcesso + ", descricao=" + descricao + ", email=" + email + ", id=" + id
                + ", nome=" + nome + "]";
    }

    

}