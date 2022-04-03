package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Profesor;
@Repository
public interface ProfesorRep extends CrudRepository<Profesor, Integer>{


@Query("SELECT p FROM Profesor p where p.correo = :correo")
public Profesor findByCorreo(String correo)
;

@Query("SELECT p FROM Profesor p " + "WHERE upper(p.nombre) LIKE CONCAT('%',:nombre,'%') " + "AND upper(p.apellido_paterno) LIKE CONCAT('%',:apellido_paterno,'%') " + "AND upper(p.apellido_materno) LIKE CONCAT('%',:apellido_materno,'%')")
public List<Profesor> findByCompleteNameList(String nombre,String apellido_paterno,String apellido_materno)
;

@Query("SELECT p FROM Profesor p " + "WHERE TRANSLATE(UPPER(p.nombre),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:nombre,'%') " + "AND TRANSLATE(UPPER(p.apellido_paterno),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:apellido_paterno,'%')")
public List<Profesor> findByName(String nombre,String apellido_paterno)
;

@Query(value = "SELECT * FROM Profesor", nativeQuery = true)
public List<Profesor> loadAllProfesor()
;

@Query("SELECT p FROM Profesor p")
public List<Profesor> findAll()
;

@Query("SELECT p FROM Profesor p ORDER BY p.pk_id_profesor DESC")
public List<Profesor> findHigherID()
;

@Modifying(clearAutomatically = true)
@Query("update Profesor p set p.nombre =:nombre, p.apellido_paterno=:apellido_paterno, p.apellido_materno=:apellido_materno,p.correo=:correo, p.clave_plantel=:clave_plantel, p.ciudad_localidad=:ciudad_localidad where p.curp =:curp")
@Transactional
public void updateProfesor(String nombre,String apellido_paterno,String apellido_materno,String correo,String clave_plantel,String ciudad_localidad,String curp)
;

@Query("SELECT p FROM Profesor p where UPPER(p.curp) = UPPER(:curp) ")
public Profesor findByCurp(String curp)
;

@Modifying(clearAutomatically = true)
@Query("update Profesor p set p.nombre =:nombre, p.apellido_paterno=:apellido_paterno, p.apellido_materno=:apellido_materno,p.correo=:correo, p.clave_plantel=:clave_plantel, p.ciudad_localidad=:ciudad_localidad where p.rfc =:rfc")
@Transactional
public void updateProfesorByRFC(String nombre,String apellido_paterno,String apellido_materno,String correo,String clave_plantel,String ciudad_localidad,String rfc)
;

@Query("SELECT p FROM Profesor p where UPPER(p.rfc) = UPPER(:rfc) ")
public Profesor findByRfc(String rfc)
;

@Query("SELECT p FROM Profesor p where UPPER(p.rfc) = UPPER(:rfc) ")
public Profesor findByRFC(String rfc)
;

@Modifying
@Query(value = "insert into Profesor (nombre,apellido_paterno,apellido_materno,curp,rfc,correo,clave_plantel,ciudad_localidad,fk_id_estado,fk_id_grado_profesor,fk_id_turno,id_genero) VALUES (:nombre,:apellido_paterno,:apellido_materno,:curp,:rfc,:email,:institution,:city,:id_estado,:id_grado_profesor,:id_turno,:id_genero)", nativeQuery = true)
@Transactional
public void saveT(String firstname,String apellido_paterno,String apellido_materno,String curp,String rfc,String email,String institution,String city)
;

@Query("SELECT p FROM Profesor p where p.pk_id_profesor = :id ")
public Profesor findByID(Integer id)
;

@Query(value = "SELECT * FROM Profesor " + "WHERE AND rfc LIKE CONCAT('%',:rfc,'%') " + "AND TRANSLATE(UPPER(p.nombre),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:nombre,'%') " + "AND TRANSLATE(UPPER(p.apellido_paterno),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:apellido_paterno,'%') " + "AND TRANSLATE(UPPER(p.apellido_materno),'ÁÉÍÓÚÄËÏÖÜ','AEIOUAEIOU') LIKE CONCAT('%',:apellido_materno,'%') " + "AND fk_id_estado=:estado", nativeQuery = true)
public List<Profesor> findByParams(String rfc,String nombre,String apellido_paterno,String apellido_materno,Integer estado)
;

public Profesor getFk_id_profesor(int pk_id_profesor);

public void setFk_id_profesor(int pk_id_profesor,Profesor fk_id_profesor);

public Profesor getFk_id_profesor(int pk_id_profesor);

public void setFk_id_profesor(int pk_id_profesor,Profesor fk_id_profesor);

public Profesor getFk_id_profesor(int pk_id_profesor);

public void setFk_id_profesor(int pk_id_profesor,Profesor fk_id_profesor);

}