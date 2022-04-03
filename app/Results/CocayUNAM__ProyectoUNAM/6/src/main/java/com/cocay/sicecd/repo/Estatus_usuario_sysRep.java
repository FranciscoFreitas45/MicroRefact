package com.cocay.sicecd.repo;
 import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Estatus_usuario_sys;
@Repository
public interface Estatus_usuario_sysRep extends PagingAndSortingRepository<Estatus_usuario_sys, Integer>{


public List<Estatus_usuario_sys> findByNombre(String name)
;

}