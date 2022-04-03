package de.metas.ui.web.handlingunits.report;
 import de.metas.handlingunits.process.api.HUProcessDescriptor;
import de.metas.process.AdProcessId;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class WebuiHUProcessDescriptor {

@NonNull
 private  ProcessDescriptor processDescriptor;

@NonNull
@Getter(AccessLevel.NONE)
 private  HUProcessDescriptor huProcessDescriptor;


public WebuiRelatedProcessDescriptor toWebuiRelatedProcessDescriptor(){
    return WebuiRelatedProcessDescriptor.builder().processId(processDescriptor.getProcessId()).internalName(processDescriptor.getInternalName()).processCaption(processDescriptor.getCaption()).processDescription(processDescriptor.getDescription()).displayPlace(DisplayPlace.ViewQuickActions).preconditionsResolutionSupplier(() -> ProcessPreconditionsResolution.accept()).build();
}


public AdProcessId getReportAdProcessId(){
    return huProcessDescriptor.getProcessId();
}


public DocumentEntityDescriptor getParametersDescriptor(){
    return processDescriptor.getParametersDescriptor();
}


public boolean appliesToHUUnitType(String huUnitType){
    return huProcessDescriptor.appliesToHUUnitType(huUnitType);
}


public ProcessId getProcessId(){
    return processDescriptor.getProcessId();
}


}