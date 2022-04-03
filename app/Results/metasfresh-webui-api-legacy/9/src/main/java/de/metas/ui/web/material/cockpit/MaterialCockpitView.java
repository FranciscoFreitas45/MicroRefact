package de.metas.ui.web.material.cockpit;
 import java.util.List;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.material.cockpit.process.MD_Cockpit_DocumentDetail_Display;
import de.metas.ui.web.process.view.ViewActionDescriptorsFactory;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class MaterialCockpitView extends AbstractCustomView<MaterialCockpitRow>{

 private  DocumentFilterList filters;

 private  List<RelatedProcessDescriptor> relatedProcessDescriptors;


public MaterialCockpitView cast(IView view){
    return (MaterialCockpitView) view;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return relatedProcessDescriptors;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


@Override
public DocumentFilterList getFilters(){
    return filters;
}


@Override
public ViewActionDescriptorsList getActions(){
    return ViewActionDescriptorsFactory.instance.getFromClass(MD_Cockpit_DocumentDetail_Display.class);
}


@Override
public boolean isEligibleInvalidateEvent(TableRecordReference recordRef){
    final String tableName = recordRef.getTableName();
    return I_MD_Cockpit.Table_Name.equals(tableName) || I_MD_Stock.Table_Name.equals(tableName);
}


}