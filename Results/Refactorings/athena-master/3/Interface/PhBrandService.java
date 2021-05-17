public interface PhBrandService {

   public List<PhBrand> findAll(String prefix);
   public PhBrand findOne(Long id);
}