package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Tipo_curso;
@Repository
public interface Tipo_cursoRep extends PagingAndSortingRepository<Tipo_curso, Integer>{


@Query("SELECT t FROM Tipo_curso t where t.nombre = :nombre")
public Tipo_curso findByNombreTipo(String nombre)
;

@Query("SELECT t FROM Tipo_curso t where t.pk_id_tipo_curso = :fk_id_tipo_curso ")
public List<Tipo_curso> findByID(int fk_id_tipo_curso)
;

public List<Tipo_curso> findByNombre(String name)
;

}