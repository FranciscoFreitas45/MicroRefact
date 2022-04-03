package de.metas.ui.web.window.descriptor;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.Getter;
import lombok.NonNull;
@SuppressWarnings("serial")
public class DocumentLayoutSectionDescriptor implements Serializable{

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  String internalName;

@Getter
 private  ClosableMode closableMode;

@Getter
 private  CaptionMode captionMode;

@Getter
 private  List<DocumentLayoutColumnDescriptor> columns;

 private  Logger logger;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  String internalName;

 private  List<DocumentLayoutColumnDescriptor.Builder> columnsBuilders;

@Getter
 private  String invalidReason;

 private  ClosableMode closableMode;

 private  CaptionMode captionMode;


public List<DocumentLayoutColumnDescriptor> buildColumns(){
    return columnsBuilders.stream().map(columnBuilder -> columnBuilder.build()).filter(column -> checkValid(column)).collect(GuavaCollectors.toImmutableList());
}


public Builder addColumn(List<DocumentLayoutElementDescriptor.Builder> elementsBuilders){
    if (elementsBuilders == null || elementsBuilders.isEmpty()) {
        return this;
    }
    final DocumentLayoutElementGroupDescriptor.Builder elementsGroupBuilder = DocumentLayoutElementGroupDescriptor.builder();
    elementsBuilders.stream().map(elementBuilder -> DocumentLayoutElementLineDescriptor.builder().addElement(elementBuilder)).forEach(elementLineBuilder -> elementsGroupBuilder.addElementLine(elementLineBuilder));
    final DocumentLayoutColumnDescriptor.Builder column = DocumentLayoutColumnDescriptor.builder().addElementGroup(elementsGroupBuilder);
    addColumn(column);
    return this;
}


public Builder setCaptionMode(CaptionMode captionMode){
    this.captionMode = captionMode;
    return this;
}


public boolean checkValid(DocumentLayoutColumnDescriptor column){
    if (!column.hasElementGroups()) {
        logger.trace("Skip adding {} to {} because it does not have elements groups", column, this);
        return false;
    }
    return true;
}


public boolean isValid(){
    return invalidReason == null;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public boolean isInvalid(){
    return invalidReason != null;
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders(){
    return columnsBuilders.stream().flatMap(DocumentLayoutColumnDescriptor.Builder::streamElementBuilders);
}


public Builder setExcludeSpecialFields(){
    streamElementBuilders().forEach(elementBuilder -> elementBuilder.setExcludeSpecialFields());
    return this;
}


public DocumentLayoutSectionDescriptor build(){
    if (isInvalid()) {
        throw new IllegalStateException("Builder is invalid: " + getInvalidReason());
    }
    return new DocumentLayoutSectionDescriptor(this);
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("internalName", internalName).add("closableMode", closableMode).add("invalidReason", invalidReason).add("columns-count", columnsBuilders.size()).toString();
}


public boolean isNotEmpty(){
    return streamElementBuilders().findAny().isPresent();
}


public Builder setInvalid(String invalidReason){
    Check.assumeNotEmpty(invalidReason, "invalidReason is not empty");
    this.invalidReason = invalidReason;
    logger.trace("Layout section was marked as invalid: {}", this);
    return this;
}


public boolean hasColumns(){
    return !columns.isEmpty();
}


public Builder setInternalName(String internalName){
    this.internalName = internalName;
    return this;
}


public Builder setClosableMode(ClosableMode closableMode){
    this.closableMode = closableMode;
    return this;
}


}