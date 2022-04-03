package com.cocay.sicecd.dao;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cocay.sicecd.dto.CursoDto;
import com.cocay.sicecd.dto.ErroresDTO;
import com.cocay.sicecd.dto.GrupoDto;
import com.cocay.sicecd.dto.InscripcionDto;
import com.cocay.sicecd.dto.ProfesorDto;
@SuppressWarnings("deprecation")
@Component
public class CargaBatchServiceDAO {

@Autowired
 private  EntityManager em;


@SuppressWarnings({ "unchecked" })
public List<InscripcionDto> lstInscripciones(Integer limit,Integer offset,String search,String name,String order){
    List<InscripcionDto> lstInscr = new ArrayList<InscripcionDto>();
    String consulta = "SELECT pk_id_inscripcion, idGrupo, idProfesor, calif, temp_curso FROM (\n" + "	SELECT pk_id_inscripcion,\n" + "				temp_grupo idGrupo,\n" + "				temp_profesor idProfesor,\n" + "				calif, \n" + "				temp_curso" + "				FROM inscripcion \n" + "               WHERE st_Tabla = 1) foo";
    if (search != null && !search.equals("")) {
    }
    consulta += " ORDER BY" + (name != null && !"".equals(name) ? " " + name + " " : " pk_id_inscripcion") + (order != null && !order.equals("") ? " " + order : " DESC");
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("idGrupo", StringType.INSTANCE).addScalar("idProfesor", StringType.INSTANCE).addScalar("calif", StringType.INSTANCE).addScalar("temp_curso", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(InscripcionDto.class));
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    lstInscr = (List<InscripcionDto>) query.getResultList();
    return lstInscr;
}


public Integer totalLstInscripciones(String search){
    Query query = em.createNativeQuery("SELECT count(1) FROM inscripcion WHERE st_Tabla = 1");
    if (search != null && !search.equals("")) {
    }
    int total = ((Number) query.getSingleResult()).intValue();
    return total;
}


@SuppressWarnings({ "unchecked" })
public List<ProfesorDto> lstProfesores(Integer limit,Integer offset,String search,String name,String order){
    List<ProfesorDto> lstProfesores = new ArrayList<ProfesorDto>();
    // Query queryMax = em.createNativeQuery("select max(pk_id_profesor) from profesor");
    // 
    // int total = ((Number) queryMax.getSingleResult()).intValue();
    String consulta = "SELECT idProfesor, nombres, aPaterno, aMaterno, rfc, curp, correo, telefono FROM (SELECT pk_id_profesor idProfesor, \n" + "		nombre nombres, \n" + "		apellido_paterno aPaterno, \n" + "		apellido_materno aMaterno,\n" + "		rfc,\n" + "		curp,\n" + "		correo,\n" + "		telefono\n" + "FROM Profesor\n " + "WHERE st_Tabla = 1) foo";
    if (search != null && !search.equals("")) {
    }
    consulta += " ORDER BY" + (name != null && !"".equals(name) ? " " + name + " " : " idProfesor") + (order != null && !order.equals("") ? " " + order : " DESC");
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("idProfesor", IntegerType.INSTANCE).addScalar("nombres", StringType.INSTANCE).addScalar("aPaterno", StringType.INSTANCE).addScalar("aMaterno", StringType.INSTANCE).addScalar("rfc", StringType.INSTANCE).addScalar("curp", StringType.INSTANCE).addScalar("correo", StringType.INSTANCE).addScalar("telefono", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ProfesorDto.class));
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    lstProfesores = (List<ProfesorDto>) query.getResultList();
    return lstProfesores;
}


@SuppressWarnings({ "unchecked" })
public List<GrupoDto> lstGrupo(Integer limit,Integer offset,String search,String name,String order){
    List<GrupoDto> lstGrupo = new ArrayList<GrupoDto>();
    String consulta = "SELECT idGrupo, clave, inicio, termino, temp_curso FROM (\n" + "	SELECT pk_id_grupo idGrupo,\n" + "				clave,\n" + "				fecha_inicio inicio,\n" + "				fecha_fin termino, \n" + "				temp_curso " + "				FROM Grupo\n " + "			WHERE st_Tabla = 1) foo";
    if (search != null && !search.equals("")) {
    }
    consulta += " ORDER BY" + (name != null && !"".equals(name) ? " " + name + " " : " idGrupo") + (order != null && !order.equals("") ? " " + order : " DESC");
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("idGrupo", StringType.INSTANCE).addScalar("clave", StringType.INSTANCE).addScalar("inicio", StringType.INSTANCE).addScalar("termino", StringType.INSTANCE).addScalar("temp_curso", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(GrupoDto.class));
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    lstGrupo = (List<GrupoDto>) query.getResultList();
    return lstGrupo;
}


public Integer totalLstErrores(String search){
    Query query = em.createNativeQuery("SELECT count(1) FROM errores");
    if (search != null && !search.equals("")) {
    }
    int total = ((Number) query.getSingleResult()).intValue();
    return total;
}


public Integer totalLstProfesores(String search){
    Query query = em.createNativeQuery("SELECT count(1) FROM Profesor WHERE st_Tabla = 1");
    if (search != null && !search.equals("")) {
    }
    int total = ((Number) query.getSingleResult()).intValue();
    return total;
}


public Integer totalLstGrupo(String search){
    Query query = em.createNativeQuery("SELECT count(1) FROM Grupo WHERE st_Tabla = 1");
    if (search != null && !search.equals("")) {
    }
    int total = ((Number) query.getSingleResult()).intValue();
    return total;
}


public int ultimoValor(){
    Query query = em.createNativeQuery("select max(pk_id_curso) total from curso");
    int total = ((Number) query.getSingleResult()).intValue();
    System.out.println(total);
    return total;
}


@Transactional
public void lstLimpiar(){
    Query query = em.createNativeQuery("DELETE FROM errores WHERE estado = 1");
    query.executeUpdate();
}


@Transactional
public void limpiarTabla(String tabla){
    Query query = em.createNativeQuery("UPDATE " + tabla + " set st_tabla = 2 WHERE st_tabla = 1");
    query.executeUpdate();
}


@SuppressWarnings({ "unchecked" })
public List<CursoDto> lstCursos(Integer limit,Integer offset,String search,String name,String order){
    List<CursoDto> lstCursos = new ArrayList<CursoDto>();
    String consulta = "SELECT idCurso, clave, nombre, horas, tipoCurso FROM (\n" + "	SELECT pk_id_curso idCurso,\n" + "				clave,\n" + "				nombre,\n" + "				horas, \n" + "				(SELECT nombre FROM tipo_curso WHERE pk_id_tipo_curso = fk_id_tipo_curso) tipoCurso" + "				FROM Curso \n" + "				WHERE st_Tabla = 1) foo";
    if (search != null && !search.equals("")) {
    }
    consulta += " ORDER BY" + (name != null && !"".equals(name) ? " " + name + " " : " idCurso") + (order != null && !order.equals("") ? " " + order : " DESC");
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("idCurso", StringType.INSTANCE).addScalar("clave", StringType.INSTANCE).addScalar("nombre", StringType.INSTANCE).addScalar("horas", StringType.INSTANCE).addScalar("tipoCurso", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(CursoDto.class));
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    lstCursos = (List<CursoDto>) query.getResultList();
    return lstCursos;
}


public Integer totalLstCursos(String search){
    Query query = em.createNativeQuery("SELECT count(1) FROM Curso WHERE st_Tabla = 1");
    if (search != null && !search.equals("")) {
    }
    int total = ((Number) query.getSingleResult()).intValue();
    return total;
}


@SuppressWarnings({ "unchecked" })
public List<ErroresDTO> lstErrores(Integer limit,Integer offset,String search,String name,String order){
    List<ErroresDTO> lstErr = new ArrayList<ErroresDTO>();
    String consulta = "SELECT cdMensaje, stEstado FROM (\n" + "	SELECT mensaje cdMensaje,\n" + "				estado stEstado \n" + "				FROM errores \n" + "				WHERE estado = 1 ) foo";
    if (search != null && !search.equals("")) {
    }
    consulta += " ORDER BY" + (name != null && !"".equals(name) ? " " + name + " " : " cdMensaje") + (order != null && !order.equals("") ? " " + order : " DESC");
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("cdMensaje", StringType.INSTANCE).addScalar("stEstado", IntegerType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ErroresDTO.class));
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    lstErr = (List<ErroresDTO>) query.getResultList();
    return lstErr;
}


}