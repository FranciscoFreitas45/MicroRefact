package hei2017.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.dao.SprintDAO;
import hei2017.entity.Sprint;
@Service
public class SprintStoryService {

@Autowired
 private SprintDAO sprintdao;


public Sprint getStorySprint(Long id){
return sprintdao.getStorySprint(id);
}


public void setStorySprint(Long id,Sprint storySprint){
sprintdao.setStorySprint(id,storySprint);
}


}