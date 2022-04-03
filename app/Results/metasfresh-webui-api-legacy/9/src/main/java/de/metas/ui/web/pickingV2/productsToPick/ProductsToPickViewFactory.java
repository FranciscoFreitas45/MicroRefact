package de.metas.ui.web.pickingV2.productsToPick;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.i18n.IMsgBL;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.pickingV2.PickingConstantsV2;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_4EyesReview_ProcessAll;
import de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_MarkWillNotPickSelected;
import de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_PickSelected;
import de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_Request4EyesReview;
import de.metas.ui.web.pickingV2.productsToPick.process.ProductsToPick_SetPackingInstructions;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsData;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewHeaderProperties;
import de.metas.ui.web.view.ViewHeaderProperty;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PickingConstantsV2.WINDOWID_ProductsToPickView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class ProductsToPickViewFactory implements IViewFactory{

 private  String MSG_PickCaption;

 private  String MSG_ReviewCaption;

@Autowired
 private  ProductsToPickRowsService rowsService;

 private  IViewsRepository viewsRepository;


public ViewLayout.Builder newViewLayout(){
    return ViewLayout.builder().setHasTreeSupport(true).setTreeCollapsible(false).setTreeExpandedDepth(Integer.MAX_VALUE);
}


public ViewHeaderProperties extractViewHeaderProperties(PackageableRow packageableRow){
    final IMsgBL msgs = Services.get(IMsgBL.class);
    return ViewHeaderProperties.builder().entry(ViewHeaderProperty.builder().caption(msgs.translatable("OrderDocumentNo")).value(packageableRow.getOrderDocumentNo()).build()).entry(ViewHeaderProperty.builder().caption(msgs.translatable("C_BPartner_ID")).value(packageableRow.getCustomer().getDisplayNameTrl()).build()).entry(ViewHeaderProperty.builder().caption(msgs.translatable("PreparationDate")).value(packageableRow.getPreparationDate()).build()).build();
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
    this.viewsRepository = viewsRepository;
}


public ProductsToPickView createView(PackageableRow packageableRow){
    final ViewId viewId = ViewId.random(PickingConstantsV2.WINDOWID_ProductsToPickView);
    final ProductsToPickRowsData rowsData = rowsService.createProductsToPickRowsData(packageableRow);
    final ProductsToPickView view = ProductsToPickView.builder().viewId(viewId).rowsData(rowsData).headerProperties(extractViewHeaderProperties(packageableRow)).relatedProcessDescriptor(createProcessDescriptor(ProductsToPick_PickSelected.class)).relatedProcessDescriptor(createProcessDescriptor(ProductsToPick_MarkWillNotPickSelected.class)).relatedProcessDescriptor(createProcessDescriptor(ProductsToPick_SetPackingInstructions.class)).relatedProcessDescriptor(createProcessDescriptor(ProductsToPick_Request4EyesReview.class)).relatedProcessDescriptor(createProcessDescriptor(ProductsToPick_4EyesReview_ProcessAll.class)).build();
    viewsRepository.getViewsStorageFor(viewId).put(view);
    return view;
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    final IMsgBL msgBL = Services.get(IMsgBL.class);
    // 
    // Reviewer layout profile
    if (PickingConstantsV2.PROFILE_ID_ProductsToPickView_Review.equals(profileId)) {
        return newViewLayout().setWindowId(PickingConstantsV2.WINDOWID_ProductsToPickView).setCaption(msgBL.translatable(MSG_ReviewCaption)).addElementsFromViewRowClassAndFieldNames(ProductsToPickRow.class, viewDataType, ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_Locator), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductValue), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductPackageSize), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductPackageSizeUOM), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_LotNumber), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ExpiringDate), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_RepackNumber), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductName), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_QtyReview), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ApprovalStatus)).build();
    } else // 
    // Picker layout profile
    {
        return newViewLayout().setWindowId(PickingConstantsV2.WINDOWID_ProductsToPickView).setCaption(msgBL.translatable(MSG_PickCaption)).addElementsFromViewRowClassAndFieldNames(ProductsToPickRow.class, viewDataType, ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_Locator), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductValue), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_Qty), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_QtyOverride), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductPackageSize), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductPackageSizeUOM), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_LotNumber), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ExpiringDate), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_RepackNumber), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_ProductName), ClassViewColumnOverrides.ofFieldName(ProductsToPickRow.FIELD_PickStatus)).build();
    }
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClass(processClass);
    return RelatedProcessDescriptor.builder().processId(processId).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


}