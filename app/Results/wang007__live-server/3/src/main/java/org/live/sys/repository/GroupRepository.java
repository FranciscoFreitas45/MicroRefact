package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GroupRepository extends BaseRepository<Group, String>{


public List<Group> findByIdIn(String[] ids)
;

@Query("select max(g.serialNo) from Group g ")
public Integer getMaxSerialNo()
;

public Page<Group> findGroups(Pageable pageable,Group group)
;

@Query("from Group g where g.serialNo=:serialNo")
public List<Group> findGroupByGroupSerialNo(int serialNo)
;

}