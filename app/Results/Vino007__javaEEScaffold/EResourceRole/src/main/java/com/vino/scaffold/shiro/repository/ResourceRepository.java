package com.vino.scaffold.shiro.repository;
 import com.vino.scaffold.repository.base.BaseRepository;
import com.vino.scaffold.shiro.entity.Resource;
public interface ResourceRepository extends BaseRepository<Resource, Long>{


public void deleteAssociateById(Long resourceIds)
;

public Resource findByName(String name)
;

}