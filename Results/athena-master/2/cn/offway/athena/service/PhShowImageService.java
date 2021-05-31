import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhShowImage;
public interface PhShowImageService {


public PhShowImage findByOrderNo(String orderNo)


public PhShowImage save(PhShowImage phShowImage)


public PhShowImage findOne(Long id)


public Page<PhShowImage> findByPage(String realName,String position,String orderNo,String unionid,String status,Long brandId,String isOffway,List<Long> brandIds,Pageable page)


}