package de.metas.ui.web.receiptSchedule;
 import com.google.common.collect.ImmutableList;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.WindowId;
@ViewFactory(windowId = HUsToReceiveViewFactory.WINDOW_ID_STRING, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class HUsToReceiveViewFactory extends HUEditorViewFactoryTemplate{

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;


@Override
public void customizeViewLayout(ViewLayout.Builder viewLayoutBuilder,JSONViewDataType viewDataType){
    viewLayoutBuilder.clearElements().addElementsFromViewRowClassAndFieldNames(HUEditorRow.class, viewDataType, ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUCode).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_Product), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HU_UnitType).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_PackingInfo).restrictToMediaType(MediaType.SCREEN).build(), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_QtyCU), ClassViewColumnOverrides.ofFieldName(HUEditorRow.FIELDNAME_UOM), ClassViewColumnOverrides.builder(HUEditorRow.FIELDNAME_HUStatus).restrictToMediaType(MediaType.SCREEN).build());
}


}