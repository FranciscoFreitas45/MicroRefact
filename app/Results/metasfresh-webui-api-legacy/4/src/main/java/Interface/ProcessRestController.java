package Interface;
public interface ProcessRestController {

   public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext);
   public Object filter(Object Object);
}