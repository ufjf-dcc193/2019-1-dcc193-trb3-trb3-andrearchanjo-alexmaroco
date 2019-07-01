package br.ufjf.dcc193.trb3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trb3.model.Item;

/**
 * ItemRepository
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

    @Query(value = "select * from itens i where i.id != :id", nativeQuery = true)
    List<Item> getAlmostAllItems(@Param("id") Long id);

}