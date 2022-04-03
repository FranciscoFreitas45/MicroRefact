package com.cocay.sicecd.repo;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Profesor;
@Repository
public interface CursoRep extends PagingAndSortingRepository<Curso, Integer>{


@Query(value = "SELECT * FROM Curso", nativeQuery = true)
public List<Curso> loadAllCursos()
;

@Query("SELECT c FROM Curso c WHERE upper(c.clave) LIKE CONCAT('%',:clave,'%')")
public List<Curso> findByClave(String clave)
;

@Query("SELECT c FROM Curso c WHERE upper(c.clave) = :clave")
public Curso findByUniqueClave(String clave)
;

@Modifying
@Query(value = "insert into Curso (clave, nombre) VALUES (:clave, :nombre)", nativeQuery = true)
@Transactional
public void saveC(String clave,String nombre)
;

@Query("SELECT c FROM Curso c WHERE c.clave = :clave")
public Curso findForClave(String clave)
;

@Query(value = "SELECT * FROM Curso " + "WHERE TRANSLATE(UPPER(nombre),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:nombre,'%') " + "AND UPPER(clave) LIKE CONCAT('%',:clave,'%') " + "AND fk_id_tipo_curso=:tipo", nativeQuery = true)
public List<Curso> findByParams(String nombre,String clave,Integer tipo)
;

@Query("SELECT c FROM Curso c")
public List<Curso> findAll()
;

@Query("SELECT c FROM Curso c WHERE upper(c.clave) = :clave")
public Curso findByUniqueClaveCurso(String clave)
;

@Query("SELECT c FROM Curso c where c.nombre = :nombre")
public Curso findByNombre(String nombre)
;

public Curso getFk_id_curso(int pk_id_curso);

public void setFk_id_curso(int pk_id_curso,Curso curso);

public Curso getFk_id_curso(int pk_id_curso);

public void setFk_id_curso(int pk_id_curso,Curso fk_id_curso);

}