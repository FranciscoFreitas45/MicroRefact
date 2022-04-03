package de.metas.ui.web.handlingunits.process;
 import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class WebuiHUTransformCommandResult {

@Singular("huIdToAddToView")
 private  ImmutableSet<HuId> huIdsToAddToView;

@Singular("huIdToRemoveFromView")
 private  ImmutableSet<HuId> huIdsToRemoveFromView;

@Singular("huIdChanged")
 private  ImmutableSet<HuId> huIdsChanged;

@Singular("huIdCreated")
 private  ImmutableSet<HuId> huIdsCreated;

 private  boolean fullViewInvalidation;


}