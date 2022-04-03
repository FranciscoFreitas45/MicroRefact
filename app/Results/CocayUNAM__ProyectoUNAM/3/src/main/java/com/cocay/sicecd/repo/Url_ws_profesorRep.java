package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cocay.sicecd.model.Url_ws_profesor;
public interface Url_ws_profesorRep extends PagingAndSortingRepository<Url_ws_profesor, Integer>{


@Query("SELECT c FROM Url_ws_profesor c where c.activa = true")
public List<Url_ws_profesor> findVarios()
;

}