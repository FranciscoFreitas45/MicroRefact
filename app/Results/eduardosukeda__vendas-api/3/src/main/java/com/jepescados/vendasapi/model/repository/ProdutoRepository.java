package com.jepescados.vendasapi.model.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.jepescados.vendasapi.model.entity.Produto;
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{


}