package de.metas.ui.web.view.descriptor;
 import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import de.metas.cache.CCache;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.geo_location.GeoLocationDocumentService;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class ViewLayoutFactory {

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  SqlViewBindingFactory viewBindingsFactory;

 private  SqlViewCustomizerMap viewCustomizers;

 private  GeoLocationDocumentService geoLocationDocumentService;

 private  CCache<ViewLayoutKey,ViewLayout> cache;

@NonNull
 final  WindowId windowId;

@NonNull
 final  JSONViewDataType viewDataType;

@Nullable
 final  ViewProfileId profileId;


public List<ViewProfile> getAvailableProfiles(WindowId windowId){
    return viewCustomizers.getViewProfilesByWindowId(windowId);
}


public SqlViewBinding getViewBinding(WindowId windowId,Characteristic requiredFieldCharacteristic,ViewProfileId profileId){
    return viewBindingsFactory.getViewBinding(windowId, requiredFieldCharacteristic, profileId);
}


public ViewLayout createViewLayout(ViewLayoutKey viewLayoutKey){
    final ViewLayout viewLayoutOrig = documentDescriptorFactory.getDocumentDescriptor(viewLayoutKey.getWindowId()).getViewLayout(viewLayoutKey.getViewDataType());
    final SqlViewBinding sqlViewBinding = getViewBinding(viewLayoutKey.getWindowId(), viewLayoutKey.getViewDataType().getRequiredFieldCharacteristic(), viewLayoutKey.getProfileId());
    final Collection<DocumentFilterDescriptor> filters = sqlViewBinding.getViewFilterDescriptors().getAll();
    final boolean hasTreeSupport = sqlViewBinding.hasGroupingFields();
    final ViewLayout.ChangeBuilder viewLayoutBuilder = viewLayoutOrig.toBuilder().profileId(viewLayoutKey.getProfileId()).filters(filters).treeSupport(hasTreeSupport, true, /* treeCollapsible */
    ViewLayout.TreeExpandedDepth_AllCollapsed).geoLocationSupport(geoLocationDocumentService.hasGeoLocationSupport(viewLayoutOrig.getFieldNames()));
    // 
    // Customize the view layout
    // NOTE to developer: keep it last, right before build().
    final SqlViewCustomizer sqlViewCustomizer = viewCustomizers.getOrNull(viewLayoutKey.getWindowId(), viewLayoutKey.getProfileId());
    if (sqlViewCustomizer != null) {
        sqlViewCustomizer.customizeViewLayout(viewLayoutBuilder);
    }
    return viewLayoutBuilder.build();
}


public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    final ViewLayoutKey viewLayoutKey = new ViewLayoutKey(windowId, viewDataType, profileId);
    return cache.getOrLoad(viewLayoutKey, this::createViewLayout);
}


}