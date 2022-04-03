package DTO;
 import org.adempiere.model.InterfaceWrapperHelper.load;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import org.eevolution.api.IPPOrderDAO;
import org.eevolution.api.PPOrderPlanningStatus;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.I_PP_Order_BOMLine;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.material.planning.pporder.IPPOrderBOMDAO;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.order.OrderLineId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class PPOrderLinesView implements IView{

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  ImmutableSet<DocumentPath> referencingDocumentPaths;

 private  PPOrderId ppOrderId;

 private  OrderLineId salesOrderLineId;

 private  PPOrderLinesViewDataSupplier dataSupplier;

 final  List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    throw new UnsupportedOperationException();
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final Stream<PPOrderLineRow> stream = getData().stream().skip(firstRow).limit(pageLength);
    final List<IViewRow> page = stream.collect(GuavaCollectors.toImmutableList());
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), page);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    if (documentId == null) {
        return null;
    }
    final PPOrderLineRow ppOrderLine = getById(documentId);
    if (ppOrderLine == null) {
        // just be sure to avoid an NPE in here
        return null;
    }
    return ppOrderLine.getType().getTableName();
}


@Override
public DocumentId getParentRowId(){
    return parentRowId;
}


@Override
public ITranslatableString getDescription(){
    return getData().getDescription();
}


public PPOrderPlanningStatus getPlanningStatus(){
    return getData().getPlanningStatus();
}


@Override
public JSONViewDataType getViewType(){
    return viewType;
}


public PPOrderId getPpOrderId(){
    return ppOrderId;
}


@Override
public ImmutableSet<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths;
}


@Override
public DocumentFilterList getFilters(){
    return DocumentFilterList.EMPTY;
}


@Override
public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.EMPTY;
}


public PPOrderLinesViewData getData(){
    return dataSupplier.getData();
}


@Override
public String getSqlWhereClause(DocumentIdsSelection viewDocumentIds,SqlOptions sqlOpts){
    // not supported
    return null;
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    throw new UnsupportedOperationException();
}


public Optional<T> getModel(PPOrderLineRow ppOrderLineRow,Class<T> modelClass){
    if (I_PP_Order.class.isAssignableFrom(modelClass)) {
        if (ppOrderLineRow.getOrderId() == null) {
            return Optional.empty();
        } else {
            final I_PP_Order order = Services.get(IPPOrderDAO.class).getById(ppOrderLineRow.getOrderId());
            return Optional.of(InterfaceWrapperHelper.create(order, modelClass));
        }
    } else if (I_PP_Order_BOMLine.class.isAssignableFrom(modelClass)) {
        if (ppOrderLineRow.getOrderBOMLineId() == null) {
            return Optional.empty();
        } else {
            final I_PP_Order_BOMLine orderBOMLine = Services.get(IPPOrderBOMDAO.class).getOrderBOMLineById(ppOrderLineRow.getOrderBOMLineId());
            return Optional.of(InterfaceWrapperHelper.create(orderBOMLine, modelClass));
        }
    } else {
        return Optional.empty();
    }
}


@Override
public int getQueryLimit(){
    return -1;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors;
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return DocumentQueryOrderByList.EMPTY;
}


@Override
public ViewId getParentViewId(){
    return parentViewId;
}


@Override
public PPOrderLineRow getById(DocumentId documentId){
    final PPOrderLineRowId ppOrderLineRowId = PPOrderLineRowId.fromDocumentId(documentId);
    return getData().getById(ppOrderLineRowId);
}


public OrderLineId getSalesOrderLineId(){
    return salesOrderLineId;
}


@Override
public ViewId getViewId(){
    return viewId;
}


@Override
public void invalidateAll(){
    invalidateAllNoNotify();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateAll"))

restTemplate.put(builder.toUriString(),null);
}


}