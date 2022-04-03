package de.metas.ui.web.quickinput;
 import java.util.Objects;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
@Immutable
public class QuickInputPath {

 private  DocumentPath rootDocumentPath;

 private  DetailId detailId;

 private  DocumentId quickInputId;

 private  Integer _hashCode;


@Override
public int hashCode(){
    if (_hashCode == null) {
        _hashCode = Objects.hash(rootDocumentPath, detailId, quickInputId);
    }
    return _hashCode;
}


public QuickInputPath of(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr){
    final DocumentPath rootDocumentPath = DocumentPath.rootDocumentPath(WindowId.fromJson(windowIdStr), documentIdStr);
    final DetailId detailId = DetailId.fromJson(tabIdStr);
    final DocumentId quickInputId = DocumentId.of(quickInputIdStr);
    return new QuickInputPath(rootDocumentPath, detailId, quickInputId);
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    } else if (obj instanceof QuickInputPath) {
        final QuickInputPath other = (QuickInputPath) obj;
        return Objects.equals(rootDocumentPath, other.rootDocumentPath) && Objects.equals(detailId, other.detailId) && Objects.equals(quickInputId, other.quickInputId);
    } else {
        return false;
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(rootDocumentPath).add("detailId", detailId).add("quickInputId", quickInputId).toString();
}


public DocumentPath getRootDocumentPath(){
    return rootDocumentPath;
}


public DetailId getDetailId(){
    return detailId;
}


public DocumentId getQuickInputId(){
    return quickInputId;
}


}