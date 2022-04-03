package com.qidian.hcm.module.organization.repository;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.organization.dto.PositionGradeDTO;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.organization.response.PositionTreeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
public interface PositionRepository extends JpaRepository<PositionEntity, Long>, JpaSpecificationExecutor<PositionEntity>{


public Optional<PositionEntity> findByCode(String code)
;

@Query(value = "select new com.qidian.hcm.module.organization.dto.PositionGradeDTO" + "(p.id,p.name,g.id,g.name) " + "from PositionEntity p " + "left join GradeEntity g on p.gradeId = g.id " + "where p.departmentId =:departmentId and p.enable=1 " + "and p.deleted=0 order by p.updateTime desc")
public List<PositionGradeDTO> getPositionByDepartmentId(Long departmentId)
;

public List<PositionEntity> findByIdInAndDeleted(List<Long> ids,YesNo deleted)
;

public List<PositionEntity> findAllByDepartmentIdInAndEnableAndDeleted(List<Long> departmentIds,YesNo enable,YesNo deleted)
;

public List<PositionEntity> findAllByDepartmentIdAndEnableAndDeleted(Long departmentId,YesNo enable,YesNo deleted)
;

@Query(value = "SELECT p_parent.* FROM POSITION p_child " + "INNER JOIN POSITION p_parent ON p_child.parent_position_id = p_parent.id AND p_parent.enable = 0 " + "WHERE p_child.id in :ids AND p_child.deleted = 0", nativeQuery = true)
public List<PositionEntity> findAllDisabledParentPositionByIdIn(List<Long> ids)
;

public List<PositionEntity> findByGradeIdAndEnableAndDeleted(Long gradeId,YesNo enable,YesNo deleted)
;

@Query(value = "select new com.qidian.hcm.module.organization.response.PositionTreeResponse" + "(b.id,b.name,b.code,b.parentPositionId) " + "from  PositionEntity b " + "where b.deleted=0 and b.enable=1 " + "order by b.parentPositionId asc")
public List<PositionTreeResponse> findPositionTree()
;

@Query(value = "select new com.qidian.hcm.module.organization.response.PositionTreeResponse" + "(b.id,b.name,b.code,b.parentPositionId) " + "from  PositionEntity b " + "where b.deleted=0 and  b.enable=1 and b.parentPositionId=-1")
public List<PositionTreeResponse> findRootPosition()
;

public List<PositionEntity> findByParentPositionIdInAndEnableAndDeleted(List<Long> parentPositionIds,YesNo enable,YesNo deleted)
;

}