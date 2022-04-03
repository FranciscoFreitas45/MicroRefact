package de.metas.ui.web.window.model;
 import java.util.Set;
public interface IDocumentFieldValueProvider {


public Set<String> getDependsOnFieldNames()
;

public Object calculateValue(Document document)
;

}