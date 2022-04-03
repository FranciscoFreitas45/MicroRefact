package de.metas.ui.web.view.descriptor.ViewLayoutFactory;
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
@Value
public class ViewLayoutKey {

@NonNull
 final  WindowId windowId;

@NonNull
 final  JSONViewDataType viewDataType;

@Nullable
 final  ViewProfileId profileId;


}