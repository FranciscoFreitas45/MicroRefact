package de.metas.ui.web.window.model.lookup;
 import de.metas.ui.web.window.datatypes.LookupValue;
@FunctionalInterface
public interface LookupValueByIdSupplier {


public LookupValue findById(Object id)
;

}