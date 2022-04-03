package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


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


public MaterialCockpitView cast(IView view){
    return (MaterialCockpitView) view;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("view",view);
MaterialCockpitView aux = restTemplate.getForObject(builder.toUriString(),MaterialCockpitView.class);
return aux;
}


}