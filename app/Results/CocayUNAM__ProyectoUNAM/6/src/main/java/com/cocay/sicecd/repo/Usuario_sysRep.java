package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Usuario_sys;
@Repository
public interface Usuario_sysRep extends PagingAndSortingRepository<Usuario_sys, Integer>{


public List<Usuario_sys> findByRfc(String name)
;

public boolean existsByRfc(String rfc)
;

public boolean existsByCorreo(String correo)
;

@Query(value = "SELECT * FROM Usuario_sys WHERE pk_id_usuario_sys != 1", nativeQuery = true)
public List<Usuario_sys> sinprecargado()
;

}