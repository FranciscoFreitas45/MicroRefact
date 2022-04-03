package de.metas.ui.web.window.descriptor.DocumentDescriptor;
 import java.util.function.Supplier;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
public class Builder {

 private  DocumentLayoutDescriptor layout;

 private  DocumentEntityDescriptor entityDescriptor;


public Builder setEntityDescriptor(DocumentEntityDescriptor entityDescriptor){
    this.entityDescriptor = entityDescriptor;
    return this;
}


public DocumentDescriptor build(){
    return new DocumentDescriptor(this);
}


public Builder setLayout(DocumentLayoutDescriptor layout){
    this.layout = layout;
    return this;
}


}