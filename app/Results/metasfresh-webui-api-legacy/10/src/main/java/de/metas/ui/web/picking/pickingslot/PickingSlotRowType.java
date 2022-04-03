package de.metas.ui.web.picking.pickingslot;
 import javax.annotation.Nullable;
import com.google.common.annotations.VisibleForTesting;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.view.IViewRowType;
import lombok.NonNull;
import lombok.Value;
@Value
public class PickingSlotRowType implements IViewRowType{

@VisibleForTesting
 static  String M_PICKING_SLOT;

@NonNull
 private String name;

@Nullable
 private HUEditorRowType huEditorRowType;


public boolean isLU(){
    return huEditorRowType != null && huEditorRowType == HUEditorRowType.LU;
}


public PickingSlotRowType forPickingSlotRow(){
    final HUEditorRowType huEditorRowType = null;
    return new PickingSlotRowType(M_PICKING_SLOT, huEditorRowType);
}


public boolean isCUOrStorage(){
    return huEditorRowType != null && (huEditorRowType == HUEditorRowType.VHU || huEditorRowType == HUEditorRowType.HUStorage);
}


public boolean isTU(){
    return huEditorRowType != null && huEditorRowType == HUEditorRowType.TU;
}


public boolean isCU(){
    return huEditorRowType != null && huEditorRowType == HUEditorRowType.VHU;
}


public PickingSlotRowType forPickingHuRow(HUEditorRowType huEditorRowType){
    return new PickingSlotRowType(huEditorRowType.getName(), huEditorRowType);
}


}