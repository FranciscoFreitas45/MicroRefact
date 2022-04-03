package de.metas.ui.web.material.cockpit.stockdetails;
 import de.metas.handlingunits.model.I_M_HU_Stock_Detail_V;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
public class StockDetailsView extends AbstractCustomView<StockDetailsRow>{

 private  ViewId parentViewId;


public StockDetailsView cast(IView view){
    return (StockDetailsView) view;
}


@Override
public ViewId getParentViewId(){
    return parentViewId;
}


@Override
public String getTableNameOrNull(DocumentId IGNORED){
    return I_M_HU_Stock_Detail_V.Table_Name;
}


}