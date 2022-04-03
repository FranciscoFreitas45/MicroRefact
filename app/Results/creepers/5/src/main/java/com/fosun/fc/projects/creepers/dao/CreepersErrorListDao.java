package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersErrorList;
public interface CreepersErrorListDao extends JpaSpecificationExecutor<TCreepersErrorList>, JpaRepository<TCreepersErrorList, Long>{


@Query("select t from TCreepersErrorList t where t.merName = :merName and t.taskType = :taskType")
public List<TCreepersErrorList> queryListByMerName(String merName,String taskType)
;

}