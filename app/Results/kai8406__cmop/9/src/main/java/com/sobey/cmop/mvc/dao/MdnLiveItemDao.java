package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.MdnLiveItem;
public interface MdnLiveItemDao extends PagingAndSortingRepository<MdnLiveItem, Integer>, JpaSpecificationExecutor<MdnLiveItem>{


public List<MdnLiveItem> findByMdnItemId(Integer mdnId)
;

}