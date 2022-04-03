package de.metas.ui.web.pickingslotsClearing.process;
 import de.metas.util.Check;
import lombok.Builder;
import lombok.Value;
@Value
public class HUExtractedFromPickingSlotEvent {

 private  int huId;

 private  int bpartnerId;

 private  int bpartnerLocationId;


}