package de.metas.ui.web.board.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONBoardCardAddRequest {

 private  int laneId;

 private  Integer position;

 private  DocumentId documentId;


}