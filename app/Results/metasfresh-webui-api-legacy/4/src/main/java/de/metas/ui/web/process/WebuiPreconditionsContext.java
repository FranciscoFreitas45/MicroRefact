package de.metas.ui.web.process;
 import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
public interface WebuiPreconditionsContext extends IProcessPreconditionsContext{


public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return ImmutableList.of();
}
;

@Nullable
public DisplayPlace getDisplayPlace()
;

}