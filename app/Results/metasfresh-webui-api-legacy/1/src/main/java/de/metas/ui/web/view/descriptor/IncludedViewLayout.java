package de.metas.ui.web.view.descriptor;
 import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
@Builder
@Value
public class IncludedViewLayout {

 public  IncludedViewLayout DEFAULT;

 private  boolean openOnSelect;

@Default
 private  boolean blurWhenOpen;


}