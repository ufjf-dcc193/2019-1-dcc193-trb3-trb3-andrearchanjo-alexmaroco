package br.ufjf.dcc193.trb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trb3.model.Usuario;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findOneByEmailAndCodAcesso(String email, String codAcesso);
    
}