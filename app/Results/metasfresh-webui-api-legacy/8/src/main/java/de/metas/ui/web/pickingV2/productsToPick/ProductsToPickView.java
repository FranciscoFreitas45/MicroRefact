package de.metas.ui.web.pickingV2.productsToPick;
 import java.util.List;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.i18n.ITranslatableString;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsData;
import de.metas.ui.web.view.IEditableView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewHeaderProperties;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class ProductsToPickView extends AbstractCustomView<ProductsToPickRow>implements IEditableView{

 private  ImmutableList<RelatedProcessDescriptor> relatedProcessDescriptors;

 private  ProductsToPickRowsData rowsData;

 private  ViewHeaderProperties headerProperties;


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    throw new UnsupportedOperationException();
}


public ProductsToPickView cast(IView view){
    return (ProductsToPickView) view;
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return rowsData.getOrderBys();
}


@Override
public boolean isAllowClosingPerUserRequest(){
    // don't allow closing per user request because the same view is used the the Picker and the Reviewer.
    // So the first one which is closing the view would delete it.
    return false;
}


@Override
public ViewHeaderProperties getHeaderProperties(){
    return headerProperties;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return relatedProcessDescriptors;
}


public void updateViewRowFromPickingCandidate(DocumentId rowId,PickingCandidate pickingCandidate){
    rowsData.updateViewRowFromPickingCandidate(rowId, pickingCandidate);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    // TODO Auto-generated method stub
    return null;
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    throw new UnsupportedOperationException();
}


public boolean isApproved(){
    if (size() == 0) {
        return false;
    }
    return streamByIds(DocumentIdsSelection.ALL).allMatch(ProductsToPickRow::isApproved);
}


public boolean isEligibleForReview(){
    if (size() == 0) {
        return false;
    }
    final boolean allApproved = streamByIds(DocumentIdsSelection.ALL).allMatch(ProductsToPickRow::isApproved);
    if (allApproved) {
        return false;
    }
    return streamByIds(DocumentIdsSelection.ALL).allMatch(ProductsToPickRow::isEligibleForReview);
}


}