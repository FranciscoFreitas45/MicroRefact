package de.metas.ui.web.view;
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
@EqualsAndHashCode
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


public ViewId withWindowId(WindowId newWindowId){
    if (windowId.equals(newWindowId)) {
        return this;
    }
    final ImmutableList<String> newParts = ImmutableList.<String>builder().add(newWindowId.toJson()).addAll(parts.subList(1, parts.size())).build();
    final String newViewId = JOINER.join(newParts);
    return new ViewId(newViewId, newParts, newWindowId);
}


public String getViewIdPart(){
    return parts.get(1);
}


public ViewId ofViewIdString(String viewIdStr,WindowId expectedWindowId){
    final List<String> parts = SPLITTER.splitToList(viewIdStr);
    if (parts.size() < 2) {
        throw new AdempiereException("Invalid viewId: " + viewIdStr);
    }
    final WindowId windowId = WindowId.fromJson(parts.get(0));
    if (expectedWindowId != null) {
        Preconditions.checkArgument(Objects.equals(windowId, expectedWindowId), "Invalid windowId: %s (viewId=%s). Expected windowId was %s", windowId, viewIdStr, expectedWindowId);
    }
    return new ViewId(viewIdStr, ImmutableList.copyOf(parts), windowId);
}


public String getPart(int index){
    return parts.get(index);
}


// IMPORTANT: for some reason, without this annotation the json deserialization does not work even if we have toJson() method annotated with @JsonValue
@JsonIgnore
public ImmutableList<String> getOtherParts(){
    return parts.size() > 2 ? parts.subList(2, parts.size()) : ImmutableList.of();
}


public ViewId random(WindowId windowId){
    final String viewIdPart = UIDStringUtil.createNext();
    final ImmutableList<String> parts = ImmutableList.of(windowId.toJson(), viewIdPart);
    final String viewIdStr = JOINER.join(parts);
    return new ViewId(viewIdStr, parts, windowId);
}


@JsonValue
public String toJson(){
    return viewId;
}


public void assertWindowId(WindowId expectedWindowId){
    if (!windowId.equals(expectedWindowId)) {
        throw new AdempiereException("" + this + " does not have expected windowId: " + expectedWindowId);
    }
}


public ViewId of(String windowIdStr,String viewIdStr){
    final WindowId expectedWindowId = windowIdStr == null ? null : WindowId.fromJson(windowIdStr);
    return ofViewIdString(viewIdStr, expectedWindowId);
}


@JsonCreator
public ViewId fromJson(String json){
    // N/A
    final WindowId expectedWindowId = null;
    return ofViewIdString(json, expectedWindowId);
}


@Override
@Deprecated
public String toString(){
    return toJson();
}


public String getViewId(){
    return viewId;
}


public ViewId ofParts(WindowId windowId,String viewIdPart,String otherParts){
    final ImmutableList.Builder<String> partsBuilder = ImmutableList.<String>builder().add(// 0
    windowId.toJson()).add(// 1
    viewIdPart);
    if (otherParts != null && otherParts.length > 0) {
        // 2..
        partsBuilder.add(otherParts);
    }
    final ImmutableList<String> parts = partsBuilder.build();
    final String viewIdStr = JOINER.join(parts);
    return new ViewId(viewIdStr, parts, windowId);
}


public WindowId getWindowId(){
    return windowId;
}


}