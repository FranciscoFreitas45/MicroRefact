package de.metas.ui.web.window.descriptor;
 import javax.annotation.Nullable;
import de.metas.ui.web.window.model.DocumentsRepository;
public interface DocumentEntityDataBindingDescriptor {

 final  DocumentEntityDataBindingDescriptorBuilder NULL;


public boolean isVersioningSupported(){
    return false;
}
;

@Nullable
public DocumentEntityDataBindingDescriptor getOrBuild()
;

public DocumentsRepository getDocumentsRepository()
;

}