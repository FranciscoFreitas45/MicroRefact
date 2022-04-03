package cn.com.cnc.fcc.Interface;
public interface QmsBomTechnologyRepository {

   public List<QmsBomTechnology> findByProcessId(Integer s);
}