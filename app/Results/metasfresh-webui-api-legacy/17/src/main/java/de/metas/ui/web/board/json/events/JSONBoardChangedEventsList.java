package de.metas.ui.web.board.json.events;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@Builder
public class JSONBoardChangedEventsList {

@Singular
 private  ImmutableList<JSONBoardChangedEvent> events;


public boolean isEmpty(){
    return events.isEmpty();
}


}