package de.metas.ui.web.document.filter.provider;
 import java.util.Collection;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdTabId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
public interface DocumentFilterDescriptorsProviderFactory {


@Nullable
public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId,String tableName,Collection<DocumentFieldDescriptor> fields)
;

public boolean isActive(){
    return true;
}
;

}