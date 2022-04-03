package DTO;
 import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.lang.UIDStringUtil;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class ViewId {

 private  String SEPARATOR;

 private  Splitter SPLITTER;

 private  Joiner JOINER;

 private  WindowId windowId;

 private  String viewId;

 private  ImmutableList<String> parts;


public int getPartAsInt(int index){
    try {
        final String partStr = getPart(index);
        return Integer.parseInt(partStr);
    } catch (final Exception ex) {
        throw new AdempiereException("Cannot extract part with index " + index + " as integer from " + this, ex);
    }
}


public String getViewIdPart(){
    return parts.get(1);
}


public String getPart(int index){
    return parts.get(index);
}


// IMPORTANT: for some reason, without this annotation the json deserialization does not work even if we have toJson() method annotated with @JsonValue
@JsonIgnore
public ImmutableList<String> getOtherParts(){
    return parts.size() > 2 ? parts.subList(2, parts.size()) : ImmutableList.of();
}


public String getViewId(){
    return viewId;
}


public WindowId getWindowId(){
    return windowId;
}


}