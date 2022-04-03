package de.metas.ui.web.shipment_candidates_editor;
 import javax.annotation.Nullable;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.ShipmentScheduleUserChangeRequestsList;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IEditableView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import lombok.Builder;
import lombok.NonNull;
public class ShipmentCandidatesView extends AbstractCustomView<ShipmentCandidateRow>implements IEditableView{

 private  IShipmentScheduleBL shipmentScheduleBL;


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    throw new UnsupportedOperationException();
}


@Override
public ShipmentCandidateRows getRowsData(){
    return ShipmentCandidateRows.cast(super.getRowsData());
}


public void saveChanges(){
    final ShipmentScheduleUserChangeRequestsList userChanges = getRowsData().createShipmentScheduleUserChangeRequestsList().orElse(null);
    if (userChanges == null) {
        return;
    }
    shipmentScheduleBL.applyUserChangesInTrx(userChanges);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    throw new UnsupportedOperationException();
}


@Override
public void close(ViewCloseAction closeAction){
    if (closeAction.isDone()) {
        saveChanges();
    }
}


}