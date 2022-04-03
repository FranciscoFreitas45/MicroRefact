package org.sdrc.Interface;
public interface SectorRepository {

   public List<Object[]> findByIC_Type(String IC_Type);
}