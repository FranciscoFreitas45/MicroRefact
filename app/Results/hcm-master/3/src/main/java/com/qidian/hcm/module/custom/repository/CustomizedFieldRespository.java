package com.qidian.hcm.module.custom.repository;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface CustomizedFieldRespository extends JpaRepository<CustomizedField, Long>{


public List<CustomizedField> findByTargetTypeAndTargetIdInOrderByIdx(TargetType targetType,List<Long> targetIds)
;

public List<CustomizedField> findByTargetTypeOrderByIdx(TargetType targetType)
;

@Modifying
@Query(value = "DELETE  FROM CustomizedField  WHERE targetId=:targetId AND  id NOT IN :ids ")
public void deleteNotExistenceByIds(Long targetId,List<Long> ids)
;

public List<CustomizedField> findByTargetTypeAndTargetIdOrderByIdx(TargetType targetType,Long targetId)
;

public void deleteCustomizedFieldByTargetId(Long targetId)
;

public List<CustomizedField> findByTargetTypeInOrderByIdx(List<TargetType> targetTypes)
;

public List<CustomizedField> findByTargetTypeInAndEnableOrderByIdx(List<TargetType> targetTypes,YesNo enable)
;

public CustomizedField findByTargetTypeAndCode(TargetType targetType,String code)
;

}