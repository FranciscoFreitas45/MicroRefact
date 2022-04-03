package de.metas.ui.web.board.json.events;
 import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@AllArgsConstructor
@EqualsAndHashCode
public class JSONBoardChangedEvent {

@NonNull
 private  ChangeType changeType;

 private  int boardId;


}