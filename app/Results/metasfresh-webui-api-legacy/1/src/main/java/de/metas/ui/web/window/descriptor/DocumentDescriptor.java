package de.metas.ui.web.window.descriptor;
 import java.util.function.Supplier;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
public class DocumentDescriptor implements ETagAware{

 private  DocumentLayoutDescriptor layout;

 private  DocumentEntityDescriptor entityDescriptor;

 private  Supplier<ETag> nextETagSupplier;

 private  ETag eTag;

 private  DocumentLayoutDescriptor layout;

 private  DocumentEntityDescriptor entityDescriptor;


public Builder setEntityDescriptor(DocumentEntityDescriptor entityDescriptor){
    this.entityDescriptor = entityDescriptor;
    return this;
}


public DocumentLayoutDescriptor getLayout(){
    return layout;
}


@Override
public ETag getETag(){
    return eTag;
}


public DocumentDescriptor build(){
    return new DocumentDescriptor(this);
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("entity", entityDescriptor).add("layout", layout).add("eTag", eTag).toString();
}


public Builder setLayout(DocumentLayoutDescriptor layout){
    this.layout = layout;
    return this;
}


public ViewLayout getViewLayout(JSONViewDataType viewDataType){
    switch(viewDataType) {
        case grid:
            {
                return layout.getGridViewLayout();
            }
        case list:
            {
                return layout.getSideListViewLayout();
            }
        default:
            {
                throw new IllegalArgumentException("Invalid viewDataType: " + viewDataType);
            }
    }
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return entityDescriptor;
}


}