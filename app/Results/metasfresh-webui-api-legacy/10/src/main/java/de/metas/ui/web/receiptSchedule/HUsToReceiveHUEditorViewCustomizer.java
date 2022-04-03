package de.metas.ui.web.receiptSchedule;
 import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.ui.web.handlingunits.HUEditorRowIsProcessedPredicate;
import de.metas.ui.web.handlingunits.HUEditorRowIsProcessedPredicates;
import de.metas.ui.web.handlingunits.HUEditorViewBuilder;
import de.metas.ui.web.handlingunits.HUEditorViewCustomizer;
import de.metas.ui.web.handlingunits.process.WEBUI_M_HU_Transform;
public class HUsToReceiveHUEditorViewCustomizer implements HUEditorViewCustomizer{

 public  HUsToReceiveHUEditorViewCustomizer instance;


@Override
public String getReferencingTableNameToMatch(){
    return I_M_ReceiptSchedule.Table_Name;
}


@Override
public HUEditorRowIsProcessedPredicate getHUEditorRowIsProcessedPredicate(){
    return HUEditorRowIsProcessedPredicates.IF_NOT_PLANNING_HUSTATUS;
}


@Override
public void beforeCreate(HUEditorViewBuilder viewBuilder){
    viewBuilder.setParameter(WEBUI_M_HU_Transform.PARAM_CheckExistingHUsInsideView, true);
}


@Override
public Boolean isAttributesAlwaysReadonly(){
    return Boolean.FALSE;
}


}