package de.metas.ui.web.view;
 import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class ViewHeaderProperties {

 public  ViewHeaderProperties EMPTY;

@NonNull
@Singular
 private ImmutableList<ViewHeaderProperty> entries;


}