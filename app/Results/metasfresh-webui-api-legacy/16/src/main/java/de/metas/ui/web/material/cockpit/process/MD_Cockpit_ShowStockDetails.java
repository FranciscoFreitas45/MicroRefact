package de.metas.ui.web.material.cockpit.process;
 import java.util.List;
import org.adempiere.mm.attributes.AttributeId;
import org.compiere.model.I_M_Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.dimension.DimensionSpec;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.handlingunits.model.I_M_HU_Stock_Detail_V;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilter.Builder;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.material.cockpit.MaterialCockpitUtil;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import lombok.NonNull;
public class MD_Cockpit_ShowStockDetails extends MaterialCockpitViewBasedProcess{

@Autowired
 private  IViewsRepository viewsRepo;


@Override
public String doIt(){
    final String viewId = createView().getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No MaterialCockpitrows are selected");
    }
    return ProcessPreconditionsResolution.accept();
}


public ViewId createView(){
    final ViewId materialCockpitViewId = getView().getViewId();
    final List<MaterialCockpitRow> rows = streamSelectedRows().collect(ImmutableList.toImmutableList());
    final CreateViewRequest.Builder viewRequestBuilder = CreateViewRequest.builder(MaterialCockpitUtil.WINDOW_MaterialCockpit_StockDetailView).setParentViewId(materialCockpitViewId);
    // create at least one filter per row; filters will be ORed
    for (final MaterialCockpitRow row : rows) {
        DocumentFilter.Builder filterBuilder = newFilterBuilder(row);
        boolean attributeFilterAdded = false;
        final DimensionSpecGroup dimensionGroup = row.getDimensionGroupOrNull();
        if (dimensionGroup == null) {
            // create filters to include all attributes that belong to the material cockpit's dimenstion spec
            final DimensionSpec dimensionSpec = MaterialCockpitUtil.retrieveDimensionSpec();
            for (final I_M_Attribute attributeRecord : dimensionSpec.retrieveAttributes()) {
                final int attributeRepoId = attributeRecord.getM_Attribute_ID();
                final DocumentFilterParam attributeParameter = DocumentFilterParam.builder().setFieldName(I_M_HU_Stock_Detail_V.COLUMNNAME_M_Attribute_ID).setValue(attributeRepoId).build();
                filterBuilder.addParameter(attributeParameter);
                final DocumentFilterParam attributeValueParameter = DocumentFilterParam.builder().setFieldName(I_M_HU_Stock_Detail_V.COLUMNNAME_AttributeValue).setValue(MaterialCockpitUtil.DONT_FILTER).build();
                filterBuilder.addParameter(attributeValueParameter);
                final DocumentFilter filter = filterBuilder.setFilterId(row.getId().toString() + "_" + attributeRepoId).build();
                viewRequestBuilder.addStickyFilters(filter);
                attributeFilterAdded = true;
                filterBuilder = newFilterBuilder(row);
            }
        } else if (dimensionGroup.getAttributeId().isPresent()) {
            // recreate a filter to include the attribute of the (detail-)row.
            final AttributeId attributeId = dimensionGroup.getAttributeId().get();
            final int attributeRepoId = attributeId.getRepoId();
            final DocumentFilterParam attributeParameter = DocumentFilterParam.builder().setFieldName(I_M_HU_Stock_Detail_V.COLUMNNAME_M_Attribute_ID).setValue(attributeRepoId).build();
            filterBuilder.addParameter(attributeParameter);
            final DocumentFilterParam attributeValueParameter = DocumentFilterParam.builder().setFieldName(I_M_HU_Stock_Detail_V.COLUMNNAME_AttributeValue).setValue(MaterialCockpitUtil.NON_EMPTY).build();
            filterBuilder.addParameter(attributeValueParameter);
            final DocumentFilter filter = filterBuilder.setFilterId(row.getId().toString() + "_" + attributeRepoId).build();
            viewRequestBuilder.addStickyFilters(filter);
            attributeFilterAdded = true;
            filterBuilder = newFilterBuilder(row);
        }
        if (!attributeFilterAdded) {
            final DocumentFilter filter = filterBuilder.setFilterId(row.getId().toString()).build();
            viewRequestBuilder.addStickyFilters(filter);
        }
    }
    final IView view = viewsRepo.createView(viewRequestBuilder.build());
    return view.getViewId();
}


public DocumentFilter.Builder newFilterBuilder(MaterialCockpitRow row){
    final Builder builder = DocumentFilter.builder();
    final DocumentFilterParam productParameter = DocumentFilterParam.builder().setFieldName(I_M_HU_Stock_Detail_V.COLUMNNAME_M_Product_ID).setValue(row.getProductId()).build();
    builder.addParameter(productParameter);
    return builder;
}


}