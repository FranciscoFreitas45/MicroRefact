package de.metas.ui.web.dataentry.window.descriptor.factory;
 import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.model.DocumentsRepository;
public class DataEntryTabBindingDescriptorBuilder implements DocumentEntityDataBindingDescriptorBuilder{

 public  DataEntryTabBindingDescriptorBuilder instance;

 private  DocumentEntityDataBindingDescriptor dataBinding;


@Override
public DocumentEntityDataBindingDescriptor getOrBuild(){
    return dataBinding;
}


@Override
public DocumentsRepository getDocumentsRepository(){
    throw new UnsupportedOperationException("DocumentEntityDataBindingDescriptor " + this + " has no DocumentsRepository");
}


}