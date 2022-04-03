package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersListTourGuide;
public interface CreepersListTourGuideDao extends JpaRepository<TCreepersListTourGuide, Long>, JpaSpecificationExecutor<TCreepersListTourGuide>{


public TCreepersListTourGuide findTop1ByGuideNoOrCardNo(String guideNo,String cardNo)
;

public List<TCreepersListTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo,String certNo1,String cardNo,String certNo2)
;

public List<TCreepersListTourGuide> findByGuideNoOrCertNo(String guideNo,String cardNo)
;

public List<TCreepersListTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo,String cardNo,String certNo)
;

public List<TCreepersListTourGuide> findByGuideNoAndCardNo(String guideNo,String cardNo)
;

}