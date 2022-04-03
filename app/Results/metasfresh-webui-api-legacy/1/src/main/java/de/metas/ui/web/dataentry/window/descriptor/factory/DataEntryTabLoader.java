package de.metas.ui.web.dataentry.window.descriptor.factory;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.X_AD_UI_ElementField;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import de.metas.dataentry.FieldType;
import de.metas.dataentry.layout.DataEntryField;
import de.metas.dataentry.layout.DataEntryLayout;
import de.metas.dataentry.layout.DataEntryLayoutRepository;
import de.metas.dataentry.layout.DataEntryLine;
import de.metas.dataentry.layout.DataEntrySection;
import de.metas.dataentry.layout.DataEntrySubTab;
import de.metas.dataentry.layout.DataEntryTab;
import de.metas.dataentry.layout.DataEntryTab.DocumentLinkColumnName;
import de.metas.dataentry.model.I_DataEntry_SubTab;
import de.metas.dataentry.model.I_DataEntry_Tab;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvidersService;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutColumnDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementGroupDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementLineDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor.CaptionMode;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor.ClosableMode;
import de.metas.ui.web.window.descriptor.DocumentLayoutSingleRow;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class DataEntryTabLoader {

 private  DocumentFilterDescriptorsProvidersService filterDescriptorsProvidersService;

 private AdWindowId adWindowId;

 private WindowId windowId;

 private DataEntrySubTabBindingDescriptorBuilder dataEntrySubTabBindingDescriptorBuilder;

 private DataEntryWebuiTools dataEntryWebuiTools;


public DocumentLayoutDetailDescriptor createSubTabLayoutDescriptor(WindowId windowId,DataEntrySubTab subTab){
    final DetailId subgroupDetailId = createDetailIdFor(subTab);
    final DocumentLayoutDetailDescriptor.Builder subGroupDescriptor = DocumentLayoutDetailDescriptor.builder(windowId, subgroupDetailId).caption(subTab.getCaption()).description(subTab.getDescription()).internalName(subTab.getInternalName()).queryOnActivate(true).supportQuickInput(false);
    final DocumentLayoutSingleRow.Builder singleRowLayoutBuilder = DocumentLayoutSingleRow.builder().setWindowId(windowId);
    for (final DataEntrySection dataEntrySection : subTab.getSections()) {
        final DocumentLayoutSectionDescriptor.Builder section = createLayoutSection(dataEntrySection);
        singleRowLayoutBuilder.addSection(section);
    }
    subGroupDescriptor.singleRowDetailLayout(true);
    subGroupDescriptor.singleRowLayout(singleRowLayoutBuilder);
    return subGroupDescriptor.build();
}


@VisibleForTesting
public List<DocumentLayoutDetailDescriptor> createLayoutDescriptors(DataEntryLayout layout){
    final ImmutableList.Builder<DocumentLayoutDetailDescriptor> result = ImmutableList.builder();
    for (final DataEntryTab dataEntryTab : layout.getTabs()) {
        final ImmutableList<DocumentLayoutDetailDescriptor> // 
        groupLayoutDescriptors = createTabLayoutDescriptors(windowId, dataEntryTab);
        result.addAll(groupLayoutDescriptors);
    }
    return result.build();
}


public DocumentLayoutElementLineDescriptor.Builder createLayoutElemementLine(DataEntryField field){
    // note that each element builder can be used/"consumed" only ones
    final DocumentLayoutElementDescriptor.Builder dataElement = createFieldElementDescriptor(field);
    final DocumentLayoutElementLineDescriptor.Builder elementLine = DocumentLayoutElementLineDescriptor.builder().addElement(dataElement);
    return elementLine;
}


public DocumentFieldDescriptor.Builder createIDField(){
    final DocumentFieldDataBindingDescriptor dataBinding = DataEntryFieldBindingDescriptor.builder().columnName(I_DataEntry_SubTab.COLUMNNAME_DataEntry_SubTab_ID).mandatory(true).fieldType(FieldType.SUB_TAB_ID).build();
    return DocumentFieldDescriptor.builder(I_DataEntry_SubTab.COLUMNNAME_DataEntry_SubTab_ID).setCaption(I_DataEntry_SubTab.COLUMNNAME_DataEntry_SubTab_ID).setWidgetType(// not an int; we construct the DocumentId as string
    DocumentFieldWidgetType.Text).setDisplayLogic(ConstantLogicExpression.FALSE).setKey(true).addCharacteristic(Characteristic.PublicField).setDataBinding(dataBinding);
}


public String createInfoFieldName(DataEntryField dataEntryField){
    return dataEntryWebuiTools.computeFieldName(dataEntryField.getId()) + "_Info";
}


public DetailId createDetailIdFor(DataEntrySubTab subTab){
    return DetailId.fromPrefixAndId(I_DataEntry_SubTab.Table_Name, subTab.getId().getRepoId());
}


public ImmutableList<DocumentLayoutElementGroupDescriptor.Builder> createLayoutElemementTab(DataEntrySection dataEntrySection){
    final int columnCount = dataEntrySection.getLines().stream().map(DataEntryLine::getFields).map(Collection::size).max(Comparator.naturalOrder()).orElse(0);
    final ImmutableList.Builder<DocumentLayoutElementGroupDescriptor.Builder> elementGroups = ImmutableList.builder();
    final List<DataEntryLine> dataEntryLines = dataEntrySection.getLines();
    for (final DataEntryLine dataEntryLine : dataEntryLines) {
        final DocumentLayoutElementGroupDescriptor.Builder elementGroup = DocumentLayoutElementGroupDescriptor.builder().setColumnCount(columnCount);
        final ImmutableList<DocumentLayoutElementLineDescriptor.Builder> elementLines = createLayoutElemementLine(dataEntryLine, columnCount);
        elementGroup.addElementLines(elementLines);
        elementGroups.add(elementGroup);
    }
    return elementGroups.build();
}


public ImmutableList<DocumentEntityDescriptor> createTabEntityDescriptors(DataEntryTab dataEntryTab){
    final ImmutableList.Builder<DocumentEntityDescriptor> subGroupEntityDescriptors = ImmutableList.builder();
    for (final DataEntrySubTab dataEntrySubTab : dataEntryTab.getSubTabs()) {
        final DocumentEntityDescriptor subGroupEntityDescriptor = createSubTabEntityDescriptor(dataEntrySubTab, dataEntryTab.getDocumentLinkColumnName());
        subGroupEntityDescriptors.add(subGroupEntityDescriptor);
    }
    final DataEntryTabBindingDescriptorBuilder dataEntryDocumentBinding = new DataEntryTabBindingDescriptorBuilder();
    final DocumentEntityDescriptor documentEntityDescriptor = DocumentEntityDescriptor.builder().setDocumentType(getAdWindowId()).setDetailId(createDetailIdFor(dataEntryTab)).setInternalName(dataEntryTab.getInternalName()).setCaption(dataEntryTab.getCaption()).setDescription(dataEntryTab.getDescription()).disableCallouts().setReadonlyLogic(ConstantLogicExpression.FALSE).setDisplayLogic(ConstantLogicExpression.TRUE).setAllowCreateNewLogic(ConstantLogicExpression.FALSE).setAllowDeleteLogic(ConstantLogicExpression.FALSE).addAllIncludedEntities(subGroupEntityDescriptors.build()).setDataBinding(dataEntryDocumentBinding).setFilterDescriptorsProvidersService(filterDescriptorsProvidersService).build();
    return ImmutableList.of(documentEntityDescriptor);
}


public DocumentLayoutSectionDescriptor.Builder createLayoutSection(DataEntrySection dataEntrySection){
    final DocumentLayoutColumnDescriptor.Builder column = DocumentLayoutColumnDescriptor.builder();
    final ClosableMode closableMode = dataEntrySection.isInitiallyClosed() ? ClosableMode.INITIALLY_CLOSED : ClosableMode.INITIALLY_OPEN;
    final DocumentLayoutSectionDescriptor.Builder layoutSection = DocumentLayoutSectionDescriptor.builder().setCaption(dataEntrySection.getCaption()).setDescription(dataEntrySection.getDescription()).setInternalName(dataEntrySection.getInternalName()).addColumn(column).setClosableMode(closableMode).setCaptionMode(CaptionMode.DISPLAY);
    final ImmutableList<DocumentLayoutElementGroupDescriptor.Builder> // 
    elementGroups = createLayoutElemementTab(dataEntrySection);
    column.addElementTabs(elementGroups);
    return layoutSection;
}


public DocumentFieldDescriptor.Builder createDataFieldDescriptor(DataEntryField dataEntryField){
    final String fieldName = dataEntryWebuiTools.computeFieldName(dataEntryField.getId());
    final DocumentFieldDataBindingDescriptor dataBinding = DataEntryFieldBindingDescriptor.builder().columnName(fieldName).mandatory(dataEntryField.isMandatory()).dataEntryFieldId(dataEntryField.getId()).fieldType(dataEntryField.getType()).build();
    final LookupDescriptorProvider fieldLookupDescriptorProvider;
    if (FieldType.LIST.equals(dataEntryField.getType())) {
        final DataEntryListValueLookupDescriptor lookupDescriptor = DataEntryListValueLookupDescriptor.of(dataEntryField.getListValues());
        fieldLookupDescriptorProvider = LookupDescriptorProviders.singleton(lookupDescriptor);
    } else {
        fieldLookupDescriptorProvider = LookupDescriptorProviders.NULL;
    }
    return DocumentFieldDescriptor.builder(fieldName).setCaption(dataEntryField.getCaption()).setDescription(dataEntryField.getDescription()).setWidgetType(ofFieldType(dataEntryField.getType())).setLookupDescriptorProvider(fieldLookupDescriptorProvider).addCharacteristic(Characteristic.PublicField).setMandatoryLogic(ConstantLogicExpression.of(dataEntryField.isMandatory())).setDataBinding(dataBinding);
}


public DocumentEntityDescriptor createSubTabEntityDescriptor(DataEntrySubTab dataEntrySubTab,DocumentLinkColumnName documentLinkColumnName){
    final DocumentEntityDescriptor.Builder documentEntityDescriptor = DocumentEntityDescriptor.builder().setSingleRowDetail(true).setDocumentType(getAdWindowId()).setDetailId(createDetailIdFor(dataEntrySubTab)).setInternalName(dataEntrySubTab.getInternalName()).setCaption(dataEntrySubTab.getCaption()).setDescription(dataEntrySubTab.getDescription()).disableCallouts().setReadonlyLogic(ConstantLogicExpression.FALSE).setAllowCreateNewLogic(ConstantLogicExpression.TRUE).setAllowDeleteLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).setDataBinding(dataEntrySubTabBindingDescriptorBuilder);
    // 
    documentEntityDescriptor.setFilterDescriptorsProvidersService(filterDescriptorsProvidersService);
    // 
    documentEntityDescriptor.addField(createIDField());
    documentEntityDescriptor.addField(createParentLinkField(documentLinkColumnName));
    for (final DataEntrySection dataEntrySection : dataEntrySubTab.getSections()) {
        for (final DataEntryLine dataEntryLine : dataEntrySection.getLines()) {
            for (final DataEntryField dataEntryField : dataEntryLine.getFields()) {
                final DocumentFieldDescriptor.Builder dataField = createDataFieldDescriptor(dataEntryField);
                // final DocumentFieldDescriptor.Builder createdField = createFieldDescriptor(dataEntryField.getId(), FieldType.CREATED);
                // final DocumentFieldDescriptor.Builder createdByField = createFieldDescriptor(dataEntryField.getId(), FieldType.CREATED_BY);
                // final DocumentFieldDescriptor.Builder updatedField = createFieldDescriptor(dataEntryField.getId(), FieldType.UPDATED);
                // final DocumentFieldDescriptor.Builder updatedByField = createFieldDescriptor(dataEntryField.getId(), FieldType.UPDATED_BY);
                documentEntityDescriptor.addField(dataField);
                // documentEntityDescriptor.addField(createdField);
                // documentEntityDescriptor.addField(createdByField);
                // documentEntityDescriptor.addField(updatedField);
                // documentEntityDescriptor.addField(updatedByField);
                final DocumentFieldDescriptor.Builder createdUpdatedInfoField = createCreatedUpdatedInfoFieldDescriptor(dataEntryField);
                documentEntityDescriptor.addField(createdUpdatedInfoField);
            }
        }
    }
    return documentEntityDescriptor.build();
}


public List<DocumentLayoutDetailDescriptor> loadDocumentLayout(){
    final DataEntryLayoutRepository dataEntryRepository = Adempiere.getBean(DataEntryLayoutRepository.class);
    final DataEntryLayout layout = dataEntryRepository.getByWindowId(adWindowId);
    return createLayoutDescriptors(layout);
}


public DocumentFieldDescriptor.Builder createParentLinkField(DocumentLinkColumnName documentLinkColumnName){
    final String columnNameAsString = documentLinkColumnName.getAsString();
    final DocumentFieldDataBindingDescriptor dataBinding = DataEntryFieldBindingDescriptor.builder().columnName(columnNameAsString).mandatory(true).fieldType(FieldType.PARENT_LINK_ID).build();
    return DocumentFieldDescriptor.builder(columnNameAsString).setCaption(columnNameAsString).setWidgetType(DocumentFieldWidgetType.Integer).setParentLink(true, columnNameAsString).setDisplayLogic(ConstantLogicExpression.FALSE).addCharacteristic(Characteristic.PublicField).setDataBinding(dataBinding);
}


public DocumentFieldWidgetType ofFieldType(FieldType fieldType){
    switch(fieldType) {
        case DATE:
            return DocumentFieldWidgetType.LocalDate;
        case LIST:
            return DocumentFieldWidgetType.List;
        case NUMBER:
            return DocumentFieldWidgetType.Number;
        case TEXT:
            return DocumentFieldWidgetType.Text;
        case LONG_TEXT:
            return DocumentFieldWidgetType.LongText;
        case YESNO:
            return DocumentFieldWidgetType.YesNo;
        case CREATED_UPDATED_INFO:
            return DocumentFieldWidgetType.Text;
        case SUB_TAB_ID:
            return DocumentFieldWidgetType.Integer;
        case PARENT_LINK_ID:
            return DocumentFieldWidgetType.Integer;
        default:
            throw new AdempiereException("Unexpected DataEntryField.Type=" + fieldType);
    }
}


public DocumentLayoutElementDescriptor.Builder createFieldElementDescriptor(DataEntryField field){
    final DocumentLayoutElementDescriptor.Builder element = DocumentLayoutElementDescriptor.builder().setCaption(field.getCaption()).setDescription(field.getDescription()).setViewEditorRenderMode(ViewEditorRenderMode.ALWAYS).setWidgetType(ofFieldType(field.getType())).setWidgetSize(WidgetSize.Default);
    if (FieldType.LONG_TEXT.equals(field.getType())) {
        element.setMultilineText(true);
        element.setMultilineTextLines(10);
    }
    final String fieldName = dataEntryWebuiTools.computeFieldName(field.getId());
    final DocumentLayoutElementFieldDescriptor.Builder dataField = DocumentLayoutElementFieldDescriptor.builder(fieldName).setEmptyFieldText(TranslatableStrings.empty());
    element.addField(dataField);
    final DocumentLayoutElementFieldDescriptor.Builder infoField = DocumentLayoutElementFieldDescriptor.builder(createInfoFieldName(field)).setEmptyFieldText(TranslatableStrings.empty()).setFieldType(DocumentLayoutElementFieldDescriptor.FieldType.Tooltip).setTooltipIconName(X_AD_UI_ElementField.TOOLTIPICONNAME_Text).setLookupSourceAsText();
    element.addField(infoField);
    return element;
}


public ImmutableList<DocumentLayoutDetailDescriptor> createTabLayoutDescriptors(WindowId windowId,DataEntryTab dataEntryTab){
    final ImmutableList.Builder<DocumentLayoutDetailDescriptor> subGroupLayoutDescriptors = ImmutableList.builder();
    for (final DataEntrySubTab subTab : dataEntryTab.getSubTabs()) {
        final DocumentLayoutDetailDescriptor // 
        subGroupLayoutDescriptor = createSubTabLayoutDescriptor(windowId, subTab);
        subGroupLayoutDescriptors.add(subGroupLayoutDescriptor);
    }
    final DocumentLayoutDetailDescriptor.Builder builder = DocumentLayoutDetailDescriptor.builder(windowId, createDetailIdFor(dataEntryTab)).caption(dataEntryTab.getCaption()).description(dataEntryTab.getDescription()).internalName(dataEntryTab.getInternalName()).queryOnActivate(true).supportQuickInput(false).addAllSubTabLayouts(subGroupLayoutDescriptors.build());
    return ImmutableList.of(builder.build());
}


public List<DocumentEntityDescriptor> loadDocumentEntity(){
    final DataEntryLayoutRepository dataEntryRepository = Adempiere.getBean(DataEntryLayoutRepository.class);
    final DataEntryLayout layout = dataEntryRepository.getByWindowId(adWindowId);
    return createTabEntityDescriptors(layout);
}


public DocumentFieldDescriptor.Builder createCreatedUpdatedInfoFieldDescriptor(DataEntryField dataEntryField){
    final String fieldName = createInfoFieldName(dataEntryField);
    final boolean mandatory = false;
    final DocumentFieldDataBindingDescriptor dataBinding = DataEntryFieldBindingDescriptor.builder().columnName(fieldName).mandatory(mandatory).dataEntryFieldId(dataEntryField.getId()).fieldType(FieldType.CREATED_UPDATED_INFO).build();
    return DocumentFieldDescriptor.builder(fieldName).setWidgetType(ofFieldType(FieldType.CREATED_UPDATED_INFO)).setLookupDescriptorProvider(LookupDescriptorProviders.NULL).addCharacteristic(Characteristic.PublicField).setMandatoryLogic(ConstantLogicExpression.of(mandatory)).setDataBinding(dataBinding);
}


}