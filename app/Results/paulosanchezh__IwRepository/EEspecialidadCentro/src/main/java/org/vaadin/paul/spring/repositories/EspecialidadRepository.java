package org.vaadin.paul.spring.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vaadin.paul.spring.entities.Especialidad;
import java.util.List;
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer>{


@Query(value = "SELECT * FROM especialidad e WHERE e.id NOT IN (SELECT c.especialidades_id FROM centro_especialidades c WHERE c.centro_id = ?1)", nativeQuery = true)
public List<Especialidad> especialidadesQueNoTengaEseCentro(int id)
;

public List<Especialidad> findAll()
;

@Query(value = "SELECT e.* FROM especialidad e WHERE NOT EXISTS ( SELECT c.especialidades_id FROM centro_especialidades c WHERE e.id = c.especialidades_id )", nativeQuery = true)
public List<Especialidad> findByEspecialidadSinCentro()
;

@Query(value = "SELECT COUNT(*) FROM cita c where c.id_sanitario IN (SELECT s.id FROM sanitario s where s.id_especialidad = ?1)", nativeQuery = true)
public int countByEspecialidad(int id)
;

}