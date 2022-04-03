package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cocay.sicecd.model.Url_ws_curso;
public interface Url_ws_cursoRep extends PagingAndSortingRepository<Url_ws_curso, Integer>{


@Query("SELECT c FROM Url_ws_curso c where c.activa = true")
public List<Url_ws_curso> findVarios()
;

}