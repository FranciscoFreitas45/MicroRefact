package de.metas.ui.web.window.descriptor;
 import java.util.OptionalInt;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import lombok.Builder;
import lombok.Value;
@Value
public class DocumentFieldDefaultFilterDescriptor {

 private  boolean defaultFilter;

 private  int defaultFilterSeqNo;

 private  boolean rangeFilter;

 private  boolean showFilterIncrementButtons;

 public  String AUTOFILTER_INITIALVALUE_DATE_NOW;

 private  Object autoFilterInitialValue;

 private  boolean facetFilter;

 private  int facetFilterSeqNo;

 private  OptionalInt maxFacetsToFetch;


}