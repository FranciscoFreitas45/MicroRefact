package de.metas.ui.web.order.sales.purchasePlanning.view;
 import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
public class PurchaseViewLayoutFactory {

 private  CCache<LayoutKey,ViewLayout> viewLayoutCache;

 private  ITranslatableString caption;

 private WindowId windowId;

 private JSONViewDataType viewDataType;


public ViewLayout createViewLayout(LayoutKey key){
    return ViewLayout.builder().setWindowId(key.getWindowId()).setCaption(caption).setHasAttributesSupport(false).setHasTreeSupport(true).setTreeCollapsible(true).setTreeExpandedDepth(ViewLayout.TreeExpandedDepth_AllCollapsed).addElementsFromViewRowClass(PurchaseRow.class, key.getViewDataType()).build();
}


public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType){
    final LayoutKey key = LayoutKey.builder().windowId(windowId).viewDataType(viewDataType).build();
    return viewLayoutCache.getOrLoad(key, this::createViewLayout);
}


}