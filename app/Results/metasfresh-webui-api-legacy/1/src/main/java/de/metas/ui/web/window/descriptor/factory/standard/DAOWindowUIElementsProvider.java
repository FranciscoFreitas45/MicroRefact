package de.metas.ui.web.window.descriptor.factory.standard;
 import java.util.List;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.compiere.model.I_AD_UI_Column;
import org.compiere.model.I_AD_UI_Element;
import org.compiere.model.I_AD_UI_ElementField;
import org.compiere.model.I_AD_UI_ElementGroup;
import org.compiere.model.I_AD_UI_Section;
import org.compiere.model.X_AD_UI_Element;
import de.metas.util.Services;
import lombok.NonNull;
public class DAOWindowUIElementsProvider implements IWindowUIElementsProvider{

 private  IADWindowDAO windowDAO;


@Override
public List<I_AD_UI_Element> getUIElements(I_AD_UI_ElementGroup uiElementGroup){
    return windowDAO.retrieveUIElements(uiElementGroup);
}


@Override
public List<I_AD_UI_ElementField> getUIElementFields(I_AD_UI_Element uiElement){
    return windowDAO.retrieveUIElementFields(uiElement);
}


@Override
public List<I_AD_UI_ElementGroup> getUIElementGroups(I_AD_UI_Column uiColumn){
    return windowDAO.retrieveUIElementGroups(uiColumn);
}


@Override
public List<I_AD_UI_Section> getUISections(AdTabId adTabId){
    return windowDAO.retrieveUISections(adTabId);
}


@Override
public List<I_AD_UI_Element> getUIElementsOfTypeLabels(AdTabId adTabId){
    return windowDAO.retrieveUIElementsQueryByTabId(adTabId).addEqualsFilter(I_AD_UI_Element.COLUMN_AD_UI_ElementType, X_AD_UI_Element.AD_UI_ELEMENTTYPE_Labels).create().list(I_AD_UI_Element.class);
}


@Override
public List<I_AD_UI_Column> getUIColumns(I_AD_UI_Section uiSection){
    return windowDAO.retrieveUIColumns(uiSection);
}


}