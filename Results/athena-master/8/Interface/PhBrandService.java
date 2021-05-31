public interface PhBrandService {

   public PhBrand findOne(Long id);
   public List<PhBrand> findAll(String prefix);
   public List<PhBrand> findByIds(List<Long> ids);
}