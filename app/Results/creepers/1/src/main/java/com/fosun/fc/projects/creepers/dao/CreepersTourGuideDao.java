package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;
public interface CreepersTourGuideDao extends JpaRepository<TCreepersTourGuide, Long>, JpaSpecificationExecutor<TCreepersTourGuide>{


public List<TCreepersTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo,String certNo1,String cardNo,String certNo2)
;

public TCreepersTourGuide findTop1ByGuideNo(String guideNo)
;

public List<TCreepersTourGuide> findByGuideNoOrCertNo(String guideNo,String cardNo)
;

public List<TCreepersTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo,String cardNo,String certNo)
;

public List<TCreepersTourGuide> findByGuideNoAndCardNo(String guideNo,String cardNo)
;

}