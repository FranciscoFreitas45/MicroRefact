package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cocay.sicecd.model.Url_ws;
public interface Url_wsRep extends PagingAndSortingRepository<Url_ws, Integer>{


@Query("SELECT c FROM Url_ws c where c.varios = false AND c.activa = true")
public List<Url_ws> findSimples()
;

@Query("SELECT c FROM Url_ws c where c.varios = true AND c.activa = true")
public List<Url_ws> findVarios()
;

}