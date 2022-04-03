package de.metas.ui.web.view;
 import java.util.List;
import java.util.stream.Collectors;
import de.metas.ui.web.pattribute.ASIDocument;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.IDocumentFieldView;
public class ASIViewRowAttributes implements IViewRowAttributes{

 private  DocumentPath documentPath;

 private  ASIDocument asiDoc;

 private  ViewRowAttributesLayout layout;


@Override
public ViewRowAttributesLayout getLayout(){
    return layout;
}


@Override
public JSONViewRowAttributes toJson(JSONOptions jsonOpts){
    final DocumentPath documentPath = getDocumentPath();
    final JSONViewRowAttributes jsonDocument = new JSONViewRowAttributes(documentPath);
    final List<JSONDocumentField> jsonFields = asiDoc.getFieldViews().stream().map(field -> toJSONDocumentField(field, jsonOpts)).collect(Collectors.toList());
    jsonDocument.setFields(jsonFields);
    return jsonDocument;
}


@Override
public LookupValuesList getAttributeTypeahead(String attributeName,String query){
    throw new UnsupportedOperationException();
// return asiDoc.getFieldLookupValuesForQuery(attributeName, query);
}


public JSONDocumentField toJSONDocumentField(IDocumentFieldView field,JSONOptions jsonOpts){
    final String fieldName = field.getFieldName();
    final Object jsonValue = field.getValueAsJsonObject(jsonOpts);
    final DocumentFieldWidgetType widgetType = field.getWidgetType();
    return JSONDocumentField.ofNameAndValue(fieldName, jsonValue).setDisplayed(true).setMandatory(false).setReadonly(true).setWidgetType(JSONLayoutWidgetType.fromNullable(widgetType));
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


@Override
public LookupValuesList getAttributeDropdown(String attributeName){
    throw new UnsupportedOperationException();
// return asiDoc.getFieldLookupValues(attributeName);
}


@Override
public void processChanges(List<JSONDocumentChangedEvent> events){
    throw new UnsupportedOperationException();
}


}