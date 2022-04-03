package com.qidian.hcm.module.organization.repository;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface GradeRepository extends JpaRepository<GradeEntity, Long>{


@Query(value = "SELECT g.* FROM grade g " + "LEFT JOIN POSITION p ON p.grade_id = g.id " + "WHERE p.id IN :ids AND g.enable = 0", nativeQuery = true)
public List<GradeEntity> findAllDisabledGradeByPositionIdIn(List<Long> ids)
;

public Optional<GradeEntity> findByCode(String code)
;

@Modifying
@Query(value = "UPDATE grade g SET  g.deleted=:deleted WHERE g.id=:id", nativeQuery = true)
public void updateStatus(Integer deleted,Long id)
;

@Modifying
@Query("update GradeEntity g set  g.enable=:enable , g.enableTime=:date where g.id=:id")
public void enableGrade(YesNo enable,Long id,Date date)
;

}