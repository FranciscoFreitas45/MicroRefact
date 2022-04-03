package de.metas.ui.web.dataentry.window.descriptor.factory;
 import org.springframework.stereotype.Service;
import de.metas.dataentry.data.DataEntryRecordRepository;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.model.DocumentsRepository;
import lombok.Getter;
import lombok.NonNull;
@Service
public class DataEntrySubTabBindingDescriptorBuilder implements DocumentEntityDataBindingDescriptorBuilder{

 private  DocumentEntityDataBindingDescriptor dataBinding;

@Getter
 private  DataEntryWebuiTools dataEntryWebuiTools;


@Override
public DocumentEntityDataBindingDescriptor getOrBuild(){
    return dataBinding;
}


@Override
public DocumentsRepository getDocumentsRepository(){
    return dataEntrySubGroupBindingRepository;
}


}