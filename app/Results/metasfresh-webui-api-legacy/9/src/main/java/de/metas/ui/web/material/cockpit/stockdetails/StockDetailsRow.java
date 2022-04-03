package de.metas.ui.web.material.cockpit.stockdetails;
 import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.I_M_Locator;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import de.metas.adempiere.model.I_M_Product;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.stock.HUStockInfo;
import de.metas.ui.web.material.cockpit.MaterialCockpitUtil;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import lombok.NonNull;
import lombok.Value;
@Value
public class StockDetailsRow implements IViewRow{

@ViewColumn(captionKey = "M_Product_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 10), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 10) })
 private LookupValue product;

@ViewColumn(captionKey = "M_Attribute_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 20), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 20) })
 private LookupValue attribute;

@ViewColumn(captionKey = "AttributeValue", widgetType = DocumentFieldWidgetType.Text, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 30), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 30) })
 private String attributeValue;

@ViewColumn(captionKey = "M_Locator_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 40), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 40) })
 private LookupValue locator;

@ViewColumn(captionKey = "M_HU_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 50), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 50) })
 private LookupValue hu;

@ViewColumn(captionKey = "HUStatus", widgetType = DocumentFieldWidgetType.Text, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 60), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 60) })
 private String huStatus;

@ViewColumn(captionKey = "C_BPartner_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 70), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 70) })
 private LookupValue bPartner;

@ViewColumn(captionKey = "Qty", widgetType = DocumentFieldWidgetType.Quantity, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 80), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 80) })
 private  BigDecimal qty;

@ViewColumn(captionKey = "C_UOM_ID", widgetType = DocumentFieldWidgetType.Lookup, layouts = { @ViewColumnLayout(when = JSONViewDataType.grid, seqNo = 90), @ViewColumnLayout(when = JSONViewDataType.includedView, seqNo = 90) })
 private LookupValue uom;

 private HUStockInfo huStockInfo;


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return ViewColumnHelper.extractJsonMap(this);
}


public StockDetailsRow of(HUStockInfo huStockInfo){
    return new StockDetailsRow(huStockInfo);
}


@Override
public DocumentPath getDocumentPath(){
    return DocumentPath.rootDocumentPath(MaterialCockpitUtil.WINDOW_MaterialCockpit_StockDetailView, getId());
}


@Override
public boolean isProcessed(){
    return true;
}


@Override
public DocumentId getId(){
    final ImmutableList<Object> keyParts = ImmutableList.of(huStockInfo.getHuStorageRepoId(), huStockInfo.getHuAttributeRepoId());
    return DocumentId.ofComposedKeyParts(keyParts);
}


@Override
public Set<String> getFieldNames(){
    return ViewColumnHelper.extractFieldNames(this);
}


@Override
public Collection<? extends IViewRow> getIncludedRows(){
    return ImmutableList.of();
}


}