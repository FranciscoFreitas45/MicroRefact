public interface PhBrandService {

   public List<PhBrand> findByIds(List<Long> ids);
   public List<PhBrand> findAll(String prefix);
}