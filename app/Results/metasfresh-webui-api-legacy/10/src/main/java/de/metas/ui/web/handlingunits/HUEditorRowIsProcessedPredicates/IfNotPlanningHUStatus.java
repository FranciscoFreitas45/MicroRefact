package de.metas.ui.web.handlingunits.HUEditorRowIsProcessedPredicates;
 import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import lombok.experimental.UtilityClass;
public class IfNotPlanningHUStatus implements HUEditorRowIsProcessedPredicate{


@Override
public boolean isProcessed(I_M_HU hu){
    return !X_M_HU.HUSTATUS_Planning.equals(hu.getHUStatus());
}


}