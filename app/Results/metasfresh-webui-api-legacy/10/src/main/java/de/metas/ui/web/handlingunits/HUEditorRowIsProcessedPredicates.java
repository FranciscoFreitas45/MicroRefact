package de.metas.ui.web.handlingunits;
 import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import lombok.experimental.UtilityClass;
@UtilityClass
public class HUEditorRowIsProcessedPredicates {

 public  HUEditorRowIsProcessedPredicate NEVER;

 public  HUEditorRowIsProcessedPredicate IF_NOT_PLANNING_HUSTATUS;


@Override
public boolean isProcessed(I_M_HU hu){
    return !X_M_HU.HUSTATUS_Planning.equals(hu.getHUStatus());
}


}