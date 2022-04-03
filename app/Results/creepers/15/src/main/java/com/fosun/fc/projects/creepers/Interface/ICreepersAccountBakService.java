package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersAccountBakService {

   public TCreepersAccountBak findTop1ByUsrAndCde(String usr,String cde);
   public Map<String,Object> findByRptNoForMap(String rptNo);
}