package de.metas.ui.web.pickingV2.packageable;
 import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class PackageableView extends AbstractCustomView<PackageableRow>{

 private  ImmutableList<RelatedProcessDescriptor> relatedProcessDescriptors;


@Override
public PackageableRowsData getRowsData(){
    return PackageableRowsData.cast(super.getRowsData());
}


public PackageableView cast(IView view){
    return (PackageableView) view;
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
    return getRowsData().getFilters();
}


@Override
public DocumentFilterList getStickyFilters(){
    return getRowsData().getStickyFilters();
}


}