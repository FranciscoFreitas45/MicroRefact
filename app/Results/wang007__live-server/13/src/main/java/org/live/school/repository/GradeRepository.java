package org.live.school.repository;
 import org.live.common.base.BaseRepository;
import org.live.school.entity.Grade;
import org.live.school.vo.GradeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
public interface GradeRepository extends BaseRepository<Grade, String>{


public Page<GradeVo> find(PageRequest pageRequest,Map<String,Object> filter)
;

@Query("select g from Grade g where g.enableFlag = :enableFlag")
public List<Grade> findByEnableFlag(boolean enableFlag)
;

public void setGrade(String id2FF1,Grade grade);

public Grade getGrade(String id2FF1);

}