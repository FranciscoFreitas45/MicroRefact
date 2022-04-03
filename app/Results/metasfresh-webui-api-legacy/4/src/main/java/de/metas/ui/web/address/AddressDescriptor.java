package de.metas.ui.web.address;
 import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
public class AddressDescriptor {

 public  DocumentId DocumentTypeId;

 private  DocumentEntityDescriptor entityDescriptor;

 private  AddressLayout layout;


public AddressLayout getLayout(){
    return layout;
}


public AddressDescriptor of(DocumentEntityDescriptor entityDescriptor,AddressLayout layout){
    return new AddressDescriptor(entityDescriptor, layout);
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return entityDescriptor;
}


}