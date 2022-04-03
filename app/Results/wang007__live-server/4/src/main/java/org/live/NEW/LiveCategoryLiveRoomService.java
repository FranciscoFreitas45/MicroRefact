package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.LiveCategoryRepository;
import org.live.live.entity.LiveCategory;
@Service
public class LiveCategoryLiveRoomService {

@Autowired
 private LiveCategoryRepository livecategoryrepository;


public LiveCategory getLiveCategory(String idW6JM){
return livecategoryrepository.getLiveCategory(idW6JM);
}


public void setLiveCategory(String idW6JM,LiveCategory liveCategory){
livecategoryrepository.setLiveCategory(idW6JM,liveCategory);
}


}