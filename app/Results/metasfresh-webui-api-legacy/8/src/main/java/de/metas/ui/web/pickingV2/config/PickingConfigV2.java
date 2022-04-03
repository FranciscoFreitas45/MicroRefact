package de.metas.ui.web.pickingV2.config;
 import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class PickingConfigV2 {

 private boolean pickingReviewRequired;

 private boolean considerAttributes;


}