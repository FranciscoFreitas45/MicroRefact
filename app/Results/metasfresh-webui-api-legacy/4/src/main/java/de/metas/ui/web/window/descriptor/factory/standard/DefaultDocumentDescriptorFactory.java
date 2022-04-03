package de.metas.ui.web.window.descriptor.factory.standard;
 import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import org.compiere.model.I_AD_Window;
import org.springframework.stereotype.Service;
import de.metas.cache.CCache;
import de.metas.ui.web.dataentry.window.descriptor.factory.DataEntrySubTabBindingDescriptorBuilder;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import lombok.NonNull;
@Service
public class DefaultDocumentDescriptorFactory implements DocumentDescriptorFactory{

@NonNull
 final  DataEntrySubTabBindingDescriptorBuilder dataEntrySubTabBindingDescriptorBuilder;

 private  CCache<WindowId,DocumentDescriptor> documentDescriptorsByWindowId;

 private  Set<WindowId> unsupportedWindowIds;


public DefaultDocumentDescriptorLoader createDocumentDescriptorLoader(WindowId windowId){
    return new DefaultDocumentDescriptorLoader(windowId.toAdWindowId(), dataEntrySubTabBindingDescriptorBuilder);
}


public void addUnsupportedWindowId(WindowId windowId){
    unsupportedWindowIds.add(windowId);
}


@Override
public boolean isWindowIdSupported(WindowId windowId){
    if (windowId == null || !windowId.isInt() || unsupportedWindowIds.contains(windowId)) {
        return false;
    }
    return true;
}


@Override
public void invalidateForWindow(WindowId windowId){
    documentDescriptorsByWindowId.remove(windowId);
}


@Override
public DocumentDescriptor getDocumentDescriptor(WindowId windowId){
    try {
        return documentDescriptorsByWindowId.getOrLoad(windowId, () -> createDocumentDescriptorLoader(windowId).load());
    } catch (final Exception e) {
        throw DocumentLayoutBuildException.wrapIfNeeded(e);
    }
}


}