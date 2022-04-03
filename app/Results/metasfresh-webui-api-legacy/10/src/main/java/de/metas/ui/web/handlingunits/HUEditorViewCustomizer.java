package de.metas.ui.web.handlingunits;
 import org.springframework.stereotype.Component;
public interface HUEditorViewCustomizer {


public String getReferencingTableNameToMatch()
;

public HUEditorRowIsProcessedPredicate getHUEditorRowIsProcessedPredicate(){
    return null;
}
;

public void beforeCreate(HUEditorViewBuilder viewBuilder)
;

public Boolean isAttributesAlwaysReadonly(){
    return null;
}
;

}