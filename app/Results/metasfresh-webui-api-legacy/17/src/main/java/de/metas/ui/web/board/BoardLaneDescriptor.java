package de.metas.ui.web.board;
 import lombok.Builder;
import lombok.Value;
import de.metas.i18n.ITranslatableString;
@Builder
@Value
public class BoardLaneDescriptor {

 private  int laneId;

 private  ITranslatableString caption;


}