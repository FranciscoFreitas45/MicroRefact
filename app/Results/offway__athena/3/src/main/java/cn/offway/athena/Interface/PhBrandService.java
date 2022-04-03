package cn.offway.athena.Interface;
public interface PhBrandService {

   public List<PhBrand> findByIds(List<Long> ids);
   public PhBrand findOne(Long id);
}