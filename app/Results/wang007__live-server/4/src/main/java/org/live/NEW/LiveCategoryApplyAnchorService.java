package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.LiveCategoryRepository;
import org.live.live.entity.LiveCategory;
@Service
public class LiveCategoryApplyAnchorService {

@Autowired
 private LiveCategoryRepository livecategoryrepository;


public LiveCategory getCategory(String idHCR5){
return livecategoryrepository.getCategory(idHCR5);
}


public void setCategory(String idHCR5,LiveCategory category){
livecategoryrepository.setCategory(idHCR5,category);
}


}