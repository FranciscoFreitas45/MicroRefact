package co.edu.uniquindio.gri.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniquindio.gri.model.Produccion;
import co.edu.uniquindio.gri.model.ProduccionB;
import co.edu.uniquindio.gri.model.ProduccionBGrupo;
import co.edu.uniquindio.gri.model.ProduccionGrupo;
@Repository
public interface ProduccionRepository extends JpaRepository<Produccion, Long>{


@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.centro c join c.facultad f where f.id =:entityId  and pr.tipo.id =:tipoId")
public List<ProduccionGrupo> getProduccionFacultadCentro(Long entityId,Long tipoId)
;

@Query("FROM co.edu.uniquindio.gri.model.Produccion where investigador.id =:entityId and (tipo.id = 1 or tipo.id = 41 or tipo.id = 42 or tipo.id = 43)")
public List<Produccion> getTrabajosDirigidos(Long entityId)
;

@SuppressWarnings("rawtypes")
@Query(value = "SELECT b.id, b.referencia, b.autores, b.anio, t.nombre, t.tipoproduccion_id, b.inventario FROM gri.bibliograficasg b JOIN gri.tipos t ON t.id = b.tipo_id WHERE b.grupos_id =:id UNION ALL SELECT p.id, p.referencia, p.autores, p.anio, t.nombre, t.tipoproduccion_id, p.inventario FROM gri.produccionesg p JOIN gri.tipos t ON t.id = p.tipo_id WHERE p.grupos_id =:id", nativeQuery = true)
public List getAllProducciones(Long id)
;

@SuppressWarnings("rawtypes")
// Grupos
@Query(value = "SELECT bg.referencia, bg.autores, bg.anio, tp.nombre as tipo,  gr.nombre FROM gri.bibliograficasg bg  JOIN gri.tipos tp ON tp.id=bg.tipo_id JOIN gri.grupos gr ON bg.grupos_id= gr.id WHERE unaccent(bg.referencia) LIKE unaccent('%'||:cadena||'%') UNION SELECT pg.referencia, pg.autores, pg.anio, tp.nombre as tipo,  gr.nombre FROM gri.produccionesg pg  JOIN gri.tipos tp ON tp.id=pg.tipo_id JOIN gri.grupos gr ON pg.grupos_id= gr.id WHERE unaccent(pg.referencia) LIKE unaccent('%'||:cadena||'%')", nativeQuery = true)
public List getProduccionGBusqueda(String cadena)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionBGrupo(pb.id, pb.identificador, pb.autores, pb.anio, pb.referencia)  from co.edu.uniquindio.gri.model.ProduccionBGrupo pb join pb.grupo g join g.centro c join c.facultad f where f.id =:entityId  and pb.tipo.id =:tipoId ")
public List<ProduccionBGrupo> getProduccionBFacultadCentro(Long entityId,Long tipoId)
;

@Query("FROM co.edu.uniquindio.gri.model.Produccion where investigador.id =:entityId and tipo.id =:tipoId")
public List<Produccion> getProduccion(Long entityId,Long tipoId)
;

@Query("FROM co.edu.uniquindio.gri.model.ProduccionGrupo where grupo.id =:entityId and tipo.id =:tipoId")
public List<ProduccionGrupo> getProduccionGrupo(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(id, autores, anio, referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr where tipo.id = 1 or tipo.id = 41 or tipo.id = 42 or tipo.id = 43")
public List<ProduccionGrupo> getTrabajosDirigidosGeneral()
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.centro c where c.id = :entityId and pr.tipo.id = :tipoId")
public List<ProduccionGrupo> getProduccionCentro(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.programas p where p.id =:entityId and pr.tipo.id =:tipoId ")
public List<ProduccionGrupo> getProduccionPrograma(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.programas p join p.facultad f where f.id =:entityId  and pr.tipo.id =:tipoId ")
public List<ProduccionGrupo> getProduccionFacultadPrograma(Long entityId,Long tipoId)
;

@Transactional
@Modifying
@Query("UPDATE co.edu.uniquindio.gri.model.ProduccionBGrupo p SET  p.estado=:estado WHERE p.id=:id")
public void updateProduccionBGrupo(Long id,int estado)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionBGrupo(pb.id, pb.identificador, pb.autores, pb.anio, pb.referencia)  from co.edu.uniquindio.gri.model.ProduccionBGrupo pb join pb.grupo g join g.centro c where c.id =:entityId and pb.tipo.id =:tipoId")
public List<ProduccionBGrupo> getProduccionBCentro(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.centro c where c.id =:entityId  and (pr.tipo.id = 1 or pr.tipo.id = 41 or pr.tipo.id = 42 or pr.tipo.id = 43)")
public List<ProduccionGrupo> getTrabajosDirigidosCentro(Long entityId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.programas p where p.id =:entityId and (pr.tipo.id = 1 or pr.tipo.id = 41 or pr.tipo.id = 42 or pr.tipo.id = 43)")
public List<ProduccionGrupo> getTrabajosDirigidosPrograma(Long entityId)
;

@Query("FROM co.edu.uniquindio.gri.model.ProduccionB where investigador.id =:entityId and tipo.id =:tipoId ")
public List<ProduccionB> getProduccionB(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.programas p join p.facultad f where f.id =:entityId  and (pr.tipo.id =  1 or pr.tipo.id = 41 or pr.tipo.id = 42 or pr.tipo.id = 43)")
public List<ProduccionGrupo> getTrabajosDirigidosFacultadPrograma(Long entityId)
;

@Transactional
@Modifying
@Query("UPDATE co.edu.uniquindio.gri.model.ProduccionGrupo p SET  p.estado=:estado WHERE p.id=:id")
public void updateProduccionGrupo(Long id,int estado)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionBGrupo(pb.id, pb.identificador, pb.autores, pb.anio, pb.referencia)  from co.edu.uniquindio.gri.model.ProduccionBGrupo pb join pb.grupo g join g.programas p  where p.id =:entityId  and pb.tipo.id =:tipoId")
public List<ProduccionBGrupo> getProduccionBPrograma(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(id, autores, anio, referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo where tipo.id = :tipoId")
public List<ProduccionGrupo> getProduccionGeneral(Long tipoId)
;

@Query("FROM co.edu.uniquindio.gri.model.ProduccionGrupo where grupo.id =:entityId and (tipo.id = 1 or tipo.id = 41 or tipo.id = 42 or tipo.id = 43)")
public List<ProduccionGrupo> getTrabajosDirigidosGrupo(Long entityId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionGrupo(pr.id, pr.autores, pr.anio, pr.referencia)  from co.edu.uniquindio.gri.model.ProduccionGrupo pr join pr.grupo g join g.programas p join p.facultad f where f.id =:entityId  and (pr.tipo.id =  1 or pr.tipo.id = 41 or pr.tipo.id = 42 or pr.tipo.id = 43)")
public List<ProduccionGrupo> getTrabajosDirigidosFacultadCentro(Long entityId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionBGrupo(pb.id, pb.identificador, pb.autores, pb.anio, pb.referencia)  from co.edu.uniquindio.gri.model.ProduccionBGrupo pb join pb.grupo g join g.programas p join p.facultad f where f.id =:entityId  and pb.tipo.id =:tipoId ")
public List<ProduccionBGrupo> getProduccionBFacultadPrograma(Long entityId,Long tipoId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.ProduccionBGrupo(id, identificador, autores, anio, referencia)  from co.edu.uniquindio.gri.model.ProduccionBGrupo where tipo.id =:tipoId")
public List<ProduccionBGrupo> getProduccionBGeneral(Long tipoId)
;

@Query("FROM co.edu.uniquindio.gri.model.ProduccionBGrupo where grupo.id =:entityId and tipo.id =:tipoId ")
public List<ProduccionBGrupo> getProduccionBGrupo(Long entityId,Long tipoId)
;

@SuppressWarnings("rawtypes")
@Query(value = "SELECT bg.referencia, bg.autores, bg.anio, tp.nombre as tipo,  inv.nombre FROM gri.bibliograficas bg  JOIN gri.tipos tp ON tp.id=bg.tipo_id JOIN gri.investigadores inv ON bg.investigadores_id= inv.id WHERE unaccent(bg.referencia) LIKE unaccent('% '||:cadena||' %') UNION SELECT pg.referencia, pg.autores, pg.anio, tp.nombre as tipo,  inv.nombre FROM gri.producciones pg  JOIN gri.tipos tp ON tp.id=pg.tipo_id JOIN gri.investigadores inv ON pg.investigadores_id= inv.id WHERE unaccent(pg.referencia) LIKE unaccent('% '||:cadena||' %')", nativeQuery = true)
public List getProduccionBusqueda(String cadena)
;

public List<Produccion> getProduccion(long id);

public void setProduccion(long id,List<Produccion> produccion);

public void setProducciones(long id,List<Produccion> producciones);

public List<Produccion> getProducciones(long id);

}