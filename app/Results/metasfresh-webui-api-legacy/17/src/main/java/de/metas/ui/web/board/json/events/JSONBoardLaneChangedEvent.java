package de.metas.ui.web.board.json.events;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@EqualsAndHashCode(callSuper = true)
public class JSONBoardLaneChangedEvent extends JSONBoardChangedEvent{

 private  int laneId;

 private  List<Integer> cardIds;


public JSONBoardLaneChangedEvent of(int boardId,int laneId,List<Integer> cardIds){
    return new JSONBoardLaneChangedEvent(boardId, laneId, cardIds);
}


}