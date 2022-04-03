package de.metas.ui.web.order.sales.hu.reservation;
 import com.google.common.collect.ImmutableList;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorViewBuilder;
import de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository.SqlHUEditorViewRepositoryBuilder;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = HUsReservationViewFactory.WINDOW_ID_STRING, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class HUsReservationViewFactory extends HUEditorViewFactoryTemplate{

 static  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  IADProcessDAO adProcessDAO;


@Override
public void customizeViewLayout(ViewLayout.Builder viewLayoutBuilder,JSONViewDataType viewDataType){
    viewLayoutBuilder.clearElements().addElementsFromViewRowClassAndFieldNames(HUEditorRow.class, viewDataType, ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUCode).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Locator), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Product), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HU_UnitType).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_PackingInfo).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_QtyCU), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_UOM), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUStatus).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_BestBeforeDate), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Locator));
}


@Override
public void customizeHUEditorViewRepository(SqlHUEditorViewRepositoryBuilder huEditorViewRepositoryBuilder){
    huEditorViewRepositoryBuilder.showBestBeforeDate(true);
}


@Override
public void customizeHUEditorView(HUEditorViewBuilder huViewBuilder){
    huViewBuilder.addAdditionalRelatedProcessDescriptor(createProcessDescriptor(de.metas.ui.web.order.sales.hu.reservation.process.WEBUI_C_OrderLineSO_Make_HUReservation.class)).clearOrderBys().orderBy(DocumentQueryOrderBy.builder().fieldName(HUEditorRow.FIELDNAME_BestBeforeDate).ascending(true).nullsLast(true).build()).orderBy(DocumentQueryOrderBy.byFieldName(HUEditorRow.FIELDNAME_M_HU_ID));
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClassIfUnique(processClass)).displayPlace(DisplayPlace.ViewQuickActions).webuiDefaultQuickAction().build();
}


}