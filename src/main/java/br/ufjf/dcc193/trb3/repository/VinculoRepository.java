package br.ufjf.dcc193.trb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trb3.model.Vinculo;

/**
 * VinculoRepository
 */
@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Long>{

    
}