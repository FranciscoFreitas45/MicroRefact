package de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
 import javax.annotation.Nullable;
import de.metas.ui.web.window.model.DocumentsRepository;
@FunctionalInterface
public interface DocumentEntityDataBindingDescriptorBuilder {

 final  DocumentEntityDataBindingDescriptorBuilder NULL;


@Nullable
public DocumentEntityDataBindingDescriptor getOrBuild()
;

}