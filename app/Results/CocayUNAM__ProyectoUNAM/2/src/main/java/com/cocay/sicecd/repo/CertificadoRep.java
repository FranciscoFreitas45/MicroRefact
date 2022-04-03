package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Certificado;
import com.cocay.sicecd.model.Curso;
public interface CertificadoRep extends PagingAndSortingRepository<Certificado, Integer>{


@Query("SELECT c FROM Certificado c where c.ruta = :ruta")
public Certificado findByRuta(String ruta)
;

@Query(value = "SELECT * FROM Certificado WHERE fk_id_profesor = :id_profesor AND fk_id_curso = :id_curso AND fk_id_grupo = :id_grupo", nativeQuery = true)
public Certificado findCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo)
;

public void setCertificados(int pk_id_profesor,List<Certificado> certificados);

public List<Certificado> getCertificados(int pk_id_profesor);

public void setCertificados(int pk_id_grupo,List<Certificado> certificados);

public List<Certificado> getCertificados(int pk_id_grupo);

public void setCertificados(int pk_id_curso,List<Certificado> certificados);

public List<Certificado> getCertificados(int pk_id_curso);

}