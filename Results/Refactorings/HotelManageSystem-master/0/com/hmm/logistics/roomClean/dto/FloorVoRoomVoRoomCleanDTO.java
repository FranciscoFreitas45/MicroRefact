import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.roomClean.entity.FloorVoRoomVoRoomClean;
import com.hmm.logistics.roomClean.util.RoomCleanState;
public class FloorVoRoomVoRoomCleanDTO {

 private  String floorName;

 private  String roomNo;

 private  String type;

 private  String roomCleanState;

 private  String roomOther;


public String getRoomCleanState(){
    return roomCleanState;
}


public String getRoomOther(){
    return roomOther;
}


public void setRoomOther(String roomOther){
    this.roomOther = roomOther;
}


public String getType(){
    return type;
}


public void setRoomCleanState(String roomCleanState){
    this.roomCleanState = roomCleanState;
}


public String getFloorName(){
    return floorName;
}


public void setFloorName(String floorName){
    this.floorName = floorName;
}


@SuppressWarnings({ "serial" })
public Specification<FloorVoRoomVoRoomClean> getWhereClause(FloorVoRoomVoRoomCleanDTO floorVoRoomVoRoomCleanDTO){
    return new Specification<FloorVoRoomVoRoomClean>() {

        @Override
        public Predicate toPredicate(Root<FloorVoRoomVoRoomClean> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getFloorName())) {
                predicate.add(criteriaBuilder.like(root.get("floorName").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getFloorName() + "%"));
            }
            if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getRoomCleanState())) {
                predicate.add(criteriaBuilder.like(root.get("roomCleanState").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getRoomCleanState() + "%"));
            }
            if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getRoomNo())) {
                predicate.add(criteriaBuilder.like(root.get("roomNo").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getRoomNo() + "%"));
            }
            if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getType())) {
                predicate.add(criteriaBuilder.like(root.get("type").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getType() + "%"));
            }
            predicate.add(criteriaBuilder.notLike(root.get("roomCleanState").as(String.class), "等待中"));
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


public void setType(String type){
    this.type = type;
}


public String getRoomNo(){
    return roomNo;
}


@Override
public Predicate toPredicate(Root<FloorVoRoomVoRoomClean> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getFloorName())) {
        predicate.add(criteriaBuilder.like(root.get("floorName").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getFloorName() + "%"));
    }
    if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getRoomCleanState())) {
        predicate.add(criteriaBuilder.like(root.get("roomCleanState").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getRoomCleanState() + "%"));
    }
    if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getRoomNo())) {
        predicate.add(criteriaBuilder.like(root.get("roomNo").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getRoomNo() + "%"));
    }
    if (StringUtils.isNotBlank(floorVoRoomVoRoomCleanDTO.getType())) {
        predicate.add(criteriaBuilder.like(root.get("type").as(String.class), "%" + floorVoRoomVoRoomCleanDTO.getType() + "%"));
    }
    predicate.add(criteriaBuilder.notLike(root.get("roomCleanState").as(String.class), "等待中"));
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


}