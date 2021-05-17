import cn.offway.athena.domain.PhBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhBrandService {


public void updateChildren(Long id,String logo,String name)


public List<PhBrand> findByIds(List<Long> ids)


public List<PhBrand> save(List<PhBrand> phBrands)


public PhBrand findOne(Long id)


public List<PhBrand> findByShowImgId(Long showImgId)


public Page<PhBrand> findByPage(Long brandId,String brandName,List<Long> brandIds,Pageable page)


public List<PhBrand> findAll(String prefix)


}