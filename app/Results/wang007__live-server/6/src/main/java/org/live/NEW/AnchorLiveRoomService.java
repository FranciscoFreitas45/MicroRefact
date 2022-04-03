package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.AnchorRepository;
import org.live.live.entity.Anchor;
@Service
public class AnchorLiveRoomService {

@Autowired
 private AnchorRepository anchorrepository;


public Anchor getAnchor(String idFK1I){
return anchorrepository.getAnchor(idFK1I);
}


public void setAnchor(String idFK1I,Anchor anchor){
anchorrepository.setAnchor(idFK1I,anchor);
}


}