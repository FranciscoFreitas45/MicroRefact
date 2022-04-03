package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListTourGuideDao {

   public List<TCreepersListTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo,String certNo1,String cardNo,String certNo2);
   public List<TCreepersListTourGuide> findByGuideNoOrCertNo(String guideNo,String cardNo);
   public List<TCreepersListTourGuide> findByGuideNoAndCardNo(String guideNo,String cardNo);
   public List<TCreepersListTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo,String cardNo,String certNo);
   public Object findAll(Object Object);
   public TCreepersListTourGuide findTop1ByGuideNoOrCardNo(String guideNo,String cardNo);
   public Object saveAndFlush(Object Object);
}