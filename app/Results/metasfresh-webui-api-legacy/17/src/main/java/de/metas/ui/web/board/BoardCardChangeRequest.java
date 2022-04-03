package de.metas.ui.web.board;
 import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
@Builder
@Value
public class BoardCardChangeRequest {

@Default
 private  int newLaneId;

@Default
 private  int newPosition;


}