package de.metas.ui.web.window.descriptor.DocumentLayoutElementGroupDescriptor;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.CoalesceUtil;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  Logger logger;

 private  String internalName;

 private  LayoutType layoutType;

 public  Integer columnCount;

 private  List<DocumentLayoutElementLineDescriptor.Builder> elementLinesBuilders;


public List<DocumentLayoutElementLineDescriptor> buildElementLines(){
    return elementLinesBuilders.stream().map(elementLinesBuilder -> elementLinesBuilder.build()).collect(GuavaCollectors.toImmutableList());
}


public Builder addElementLines(List<DocumentLayoutElementLineDescriptor.Builder> elementLineBuilders){
    elementLinesBuilders.addAll(elementLineBuilders);
    return this;
}


public Builder addElementLine(DocumentLayoutElementLineDescriptor.Builder elementLineBuilder){
    elementLinesBuilders.add(elementLineBuilder);
    return this;
}


public Builder setLayoutType(String layoutTypeStr){
    layoutType = LayoutType.fromNullable(layoutTypeStr);
    return this;
}


public boolean hasElementLines(){
    return !elementLinesBuilders.isEmpty();
}


public DocumentLayoutElementGroupDescriptor build(){
    final DocumentLayoutElementGroupDescriptor result = new DocumentLayoutElementGroupDescriptor(this);
    logger.trace("Built {} for {}", result, this);
    return result;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("internalName", internalName).add("layoutType", layoutType).add("elementsLines-count", elementLinesBuilders.size()).toString();
}


public Builder setColumnCount(int columnCount){
    this.columnCount = CoalesceUtil.firstGreaterThanZero(columnCount, 1);
    return this;
}


public Builder setInternalName(String internalName){
    this.internalName = internalName;
    return this;
}


public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders(){
    return elementLinesBuilders.stream().flatMap(DocumentLayoutElementLineDescriptor.Builder::streamElementBuilders);
}


}