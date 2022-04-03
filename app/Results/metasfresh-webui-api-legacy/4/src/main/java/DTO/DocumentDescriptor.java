package DTO;
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


public DocumentLayoutDescriptor getLayout(){
    return layout;
}


@Override
public ETag getETag(){
    return eTag;
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