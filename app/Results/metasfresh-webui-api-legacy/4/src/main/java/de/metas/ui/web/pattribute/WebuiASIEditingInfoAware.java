package de.metas.ui.web.pattribute;
 import org.adempiere.mm.attributes.AttributeSetInstanceId;
@FunctionalInterface
public interface WebuiASIEditingInfoAware {


public WebuiASIEditingInfo getWebuiASIEditingInfo(AttributeSetInstanceId asiId)
;

}