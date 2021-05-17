import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.Artifact;
import org.activiti.bpmn.model.Association;
import org.activiti.bpmn.model.AssociationDirection;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BoundaryEvent;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.BusinessRuleTask;
import org.activiti.bpmn.model.CallActivity;
import org.activiti.bpmn.model.CompensateEventDefinition;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ErrorEventDefinition;
import org.activiti.bpmn.model.Event;
import org.activiti.bpmn.model.EventDefinition;
import org.activiti.bpmn.model.EventGateway;
import org.activiti.bpmn.model.EventSubProcess;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowElementsContainer;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.IntermediateCatchEvent;
import org.activiti.bpmn.model.Lane;
import org.activiti.bpmn.model.ManualTask;
import org.activiti.bpmn.model.MessageEventDefinition;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.Pool;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.ReceiveTask;
import org.activiti.bpmn.model.ScriptTask;
import org.activiti.bpmn.model.SendTask;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.SignalEventDefinition;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.Task;
import org.activiti.bpmn.model.TextAnnotation;
import org.activiti.bpmn.model.ThrowEvent;
import org.activiti.bpmn.model.TimerEventDefinition;
import org.activiti.bpmn.model.UserTask;
import org.activiti.image.ProcessDiagramGenerator;
public class CustomProcessDiagramGenerator implements ProcessDiagramGenerator{

 protected  Map<Class<? extends BaseElement>,ActivityDrawInstruction> activityDrawInstructions;

 protected  Map<Class<? extends BaseElement>,ArtifactDrawInstruction> artifactDrawInstructions;


public void setArtifactDrawInstructions(Map<Class<? extends BaseElement>,ArtifactDrawInstruction> artifactDrawInstructions){
    this.artifactDrawInstructions = artifactDrawInstructions;
}


public GraphicInfo getLineCenter(List<GraphicInfo> graphicInfoList){
    GraphicInfo gi = new GraphicInfo();
    int[] xPoints = new int[graphicInfoList.size()];
    int[] yPoints = new int[graphicInfoList.size()];
    double length = 0;
    double[] lengths = new double[graphicInfoList.size()];
    lengths[0] = 0;
    double m;
    for (int i = 1; i < graphicInfoList.size(); i++) {
        GraphicInfo graphicInfo = graphicInfoList.get(i);
        GraphicInfo previousGraphicInfo = graphicInfoList.get(i - 1);
        if (i == 1) {
            xPoints[0] = (int) previousGraphicInfo.getX();
            yPoints[0] = (int) previousGraphicInfo.getY();
        }
        xPoints[i] = (int) graphicInfo.getX();
        yPoints[i] = (int) graphicInfo.getY();
        length += Math.sqrt(Math.pow((int) graphicInfo.getX() - (int) previousGraphicInfo.getX(), 2) + Math.pow((int) graphicInfo.getY() - (int) previousGraphicInfo.getY(), 2));
        lengths[i] = length;
    }
    m = length / 2;
    int p1 = 0, p2 = 1;
    for (int i = 1; i < lengths.length; i++) {
        double len = lengths[i];
        p1 = i - 1;
        p2 = i;
        if (len > m) {
            break;
        }
    }
    GraphicInfo graphicInfo1 = graphicInfoList.get(p1);
    GraphicInfo graphicInfo2 = graphicInfoList.get(p2);
    double AB = (int) graphicInfo2.getX() - (int) graphicInfo1.getX();
    double OA = (int) graphicInfo2.getY() - (int) graphicInfo1.getY();
    double OB = lengths[p2] - lengths[p1];
    double ob = m - lengths[p1];
    double ab = AB * ob / OB;
    double oa = OA * ob / OB;
    double mx = graphicInfo1.getX() + ab;
    double my = graphicInfo1.getY() + oa;
    gi.setX(mx);
    gi.setY(my);
    return gi;
}


public Map<Class<? extends BaseElement>,ArtifactDrawInstruction> getArtifactDrawInstructions(){
    return artifactDrawInstructions;
}


public void drawArtifact(CustomProcessDiagramCanvas processDiagramCanvas,BpmnModel bpmnModel,Artifact artifact){
    ArtifactDrawInstruction drawInstruction = artifactDrawInstructions.get(artifact.getClass());
    if (drawInstruction != null) {
        drawInstruction.draw(processDiagramCanvas, bpmnModel, artifact);
    }
}


public void draw(CustomProcessDiagramCanvas processDiagramCanvas,BpmnModel bpmnModel,Artifact artifact)


public InputStream generateJpgDiagram(BpmnModel bpmnModel,double scaleFactor){
    return generateDiagram(bpmnModel, "jpg", Collections.<String>emptyList(), Collections.<String>emptyList());
}


public List<FlowNode> gatherAllFlowNodes(FlowElementsContainer flowElementsContainer){
    List<FlowNode> flowNodes = new ArrayList<FlowNode>();
    for (FlowElement flowElement : flowElementsContainer.getFlowElements()) {
        if (flowElement instanceof FlowNode) {
            flowNodes.add((FlowNode) flowElement);
        }
        if (flowElement instanceof FlowElementsContainer) {
            flowNodes.addAll(gatherAllFlowNodes((FlowElementsContainer) flowElement));
        }
    }
    return flowNodes;
}


public Map<Class<? extends BaseElement>,ActivityDrawInstruction> getActivityDrawInstructions(){
    return activityDrawInstructions;
}


public void setActivityDrawInstructions(Map<Class<? extends BaseElement>,ActivityDrawInstruction> activityDrawInstructions){
    this.activityDrawInstructions = activityDrawInstructions;
}


@Override
public InputStream generateDiagram(BpmnModel bpmnModel,String imageType,String activityFontName,String labelFontName,String annotationFontName,ClassLoader customClassLoader,double scaleFactor){
    // TODO Auto-generated method stub
    return generateDiagram(bpmnModel, imageType, Collections.<String>emptyList(), Collections.<String>emptyList(), activityFontName, labelFontName, customClassLoader, scaleFactor);
}


public void drawActivity(CustomProcessDiagramCanvas processDiagramCanvas,BpmnModel bpmnModel,FlowNode flowNode,List<String> highLightedActivities,List<String> highLightedFlows,double scaleFactor){
    ActivityDrawInstruction drawInstruction = activityDrawInstructions.get(flowNode.getClass());
    if (drawInstruction != null) {
        drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);
        // Gather info on the multi instance marker
        boolean multiInstanceSequential = false, multiInstanceParallel = false, collapsed = false;
        if (flowNode instanceof Activity) {
            Activity activity = (Activity) flowNode;
            MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = activity.getLoopCharacteristics();
            if (multiInstanceLoopCharacteristics != null) {
                multiInstanceSequential = multiInstanceLoopCharacteristics.isSequential();
                multiInstanceParallel = !multiInstanceSequential;
            }
        }
        // Gather info on the collapsed marker
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
        if (flowNode instanceof SubProcess) {
            collapsed = graphicInfo.getExpanded() != null && !graphicInfo.getExpanded();
        } else if (flowNode instanceof CallActivity) {
            collapsed = true;
        }
        if (scaleFactor == 1.0) {
            // Actually draw the markers
            processDiagramCanvas.drawActivityMarkers((int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(), (int) graphicInfo.getHeight(), multiInstanceSequential, multiInstanceParallel, collapsed);
        }
        // Draw highlighted activities
        if (highLightedActivities.contains(flowNode.getId())) {
            drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()));
        }
    }
    // Outgoing transitions of activity
    for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
        boolean highLighted = (highLightedFlows.contains(sequenceFlow.getId()));
        String defaultFlow = null;
        if (flowNode instanceof Activity) {
            defaultFlow = ((Activity) flowNode).getDefaultFlow();
        } else if (flowNode instanceof Gateway) {
            defaultFlow = ((Gateway) flowNode).getDefaultFlow();
        }
        boolean isDefault = false;
        if (defaultFlow != null && defaultFlow.equalsIgnoreCase(sequenceFlow.getId())) {
            isDefault = true;
        }
        boolean drawConditionalIndicator = sequenceFlow.getConditionExpression() != null && !(flowNode instanceof Gateway);
        String sourceRef = sequenceFlow.getSourceRef();
        String targetRef = sequenceFlow.getTargetRef();
        FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
        FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
        List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
        if (graphicInfoList != null && graphicInfoList.size() > 0) {
            graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
            int[] xPoints = new int[graphicInfoList.size()];
            int[] yPoints = new int[graphicInfoList.size()];
            for (int i = 1; i < graphicInfoList.size(); i++) {
                GraphicInfo graphicInfo = graphicInfoList.get(i);
                GraphicInfo previousGraphicInfo = graphicInfoList.get(i - 1);
                if (i == 1) {
                    xPoints[0] = (int) previousGraphicInfo.getX();
                    yPoints[0] = (int) previousGraphicInfo.getY();
                }
                xPoints[i] = (int) graphicInfo.getX();
                yPoints[i] = (int) graphicInfo.getY();
            }
            processDiagramCanvas.drawSequenceflow(xPoints, yPoints, drawConditionalIndicator, isDefault, highLighted, scaleFactor);
            // Draw sequenceflow label
            GraphicInfo labelGraphicInfo = bpmnModel.getLabelGraphicInfo(sequenceFlow.getId());
            if (labelGraphicInfo != null) {
                GraphicInfo lineCenter = getLineCenter(graphicInfoList);
                processDiagramCanvas.drawLabel(sequenceFlow.getName(), lineCenter, false);
            }
        }
    }
    // Nested elements
    if (flowNode instanceof FlowElementsContainer) {
        for (FlowElement nestedFlowElement : ((FlowElementsContainer) flowNode).getFlowElements()) {
            if (nestedFlowElement instanceof FlowNode) {
                drawActivity(processDiagramCanvas, bpmnModel, (FlowNode) nestedFlowElement, highLightedActivities, highLightedFlows, scaleFactor);
            }
        }
    }
}


public List<GraphicInfo> connectionPerfectionizer(CustomProcessDiagramCanvas processDiagramCanvas,BpmnModel bpmnModel,BaseElement sourceElement,BaseElement targetElement,List<GraphicInfo> graphicInfoList){
    GraphicInfo sourceGraphicInfo = bpmnModel.getGraphicInfo(sourceElement.getId());
    GraphicInfo targetGraphicInfo = bpmnModel.getGraphicInfo(targetElement.getId());
    CustomProcessDiagramCanvas.SHAPE_TYPE sourceShapeType = getShapeType(sourceElement);
    CustomProcessDiagramCanvas.SHAPE_TYPE targetShapeType = getShapeType(targetElement);
    return processDiagramCanvas.connectionPerfectionizer(sourceShapeType, targetShapeType, sourceGraphicInfo, targetGraphicInfo, graphicInfoList);
}


public BufferedImage generatePngImage(BpmnModel bpmnModel,double scaleFactor){
    return generateImage(bpmnModel, "png", Collections.<String>emptyList(), Collections.<String>emptyList(), scaleFactor);
}


public InputStream generatePngDiagram(BpmnModel bpmnModel,double scaleFactor){
    return generateDiagram(bpmnModel, "png", Collections.<String>emptyList(), Collections.<String>emptyList(), scaleFactor);
}


public CustomProcessDiagramCanvas generateProcessDiagram(BpmnModel bpmnModel,String imageType,List<String> highLightedActivities,List<String> highLightedFlows,String activityFontName,String labelFontName,ClassLoader customClassLoader,double scaleFactor){
    CustomProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, activityFontName, labelFontName, customClassLoader);
    // Draw pool shape, if process is participant in collaboration
    for (Pool pool : bpmnModel.getPools()) {
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
        processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo);
    }
    // Draw lanes
    for (Process process : bpmnModel.getProcesses()) {
        for (Lane lane : process.getLanes()) {
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
            processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo);
        }
    }
    // Draw activities and their sequence-flows
    for (FlowNode flowNode : bpmnModel.getProcesses().get(0).findFlowElementsOfType(FlowNode.class)) {
        drawActivity(processDiagramCanvas, bpmnModel, flowNode, highLightedActivities, highLightedFlows, scaleFactor);
    }
    // Draw artifacts
    for (Process process : bpmnModel.getProcesses()) {
        for (Artifact artifact : process.getArtifacts()) {
            drawArtifact(processDiagramCanvas, bpmnModel, artifact);
        }
    }
    return processDiagramCanvas;
}


public void drawHighLight(CustomProcessDiagramCanvas processDiagramCanvas,GraphicInfo graphicInfo){
    processDiagramCanvas.drawHighLight((int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(), (int) graphicInfo.getHeight());
}


public CustomProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel,String imageType,String activityFontName,String labelFontName,ClassLoader customClassLoader){
    // We need to calculate maximum values to know how big the image will be in its entirety
    double minX = Double.MAX_VALUE;
    double maxX = 0;
    double minY = Double.MAX_VALUE;
    double maxY = 0;
    for (Pool pool : bpmnModel.getPools()) {
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
        minX = graphicInfo.getX();
        maxX = graphicInfo.getX() + graphicInfo.getWidth();
        minY = graphicInfo.getY();
        maxY = graphicInfo.getY() + graphicInfo.getHeight();
    }
    List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
    for (FlowNode flowNode : flowNodes) {
        GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
        // width
        if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > maxX) {
            maxX = flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth();
        }
        if (flowNodeGraphicInfo.getX() < minX) {
            minX = flowNodeGraphicInfo.getX();
        }
        // height
        if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > maxY) {
            maxY = flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight();
        }
        if (flowNodeGraphicInfo.getY() < minY) {
            minY = flowNodeGraphicInfo.getY();
        }
        for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
            if (graphicInfoList != null) {
                for (GraphicInfo graphicInfo : graphicInfoList) {
                    // width
                    if (graphicInfo.getX() > maxX) {
                        maxX = graphicInfo.getX();
                    }
                    if (graphicInfo.getX() < minX) {
                        minX = graphicInfo.getX();
                    }
                    // height
                    if (graphicInfo.getY() > maxY) {
                        maxY = graphicInfo.getY();
                    }
                    if (graphicInfo.getY() < minY) {
                        minY = graphicInfo.getY();
                    }
                }
            }
        }
    }
    List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
    for (Artifact artifact : artifacts) {
        GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact.getId());
        if (artifactGraphicInfo != null) {
            // width
            if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > maxX) {
                maxX = artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth();
            }
            if (artifactGraphicInfo.getX() < minX) {
                minX = artifactGraphicInfo.getX();
            }
            // height
            if (artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight() > maxY) {
                maxY = artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight();
            }
            if (artifactGraphicInfo.getY() < minY) {
                minY = artifactGraphicInfo.getY();
            }
        }
        List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
        if (graphicInfoList != null) {
            for (GraphicInfo graphicInfo : graphicInfoList) {
                // width
                if (graphicInfo.getX() > maxX) {
                    maxX = graphicInfo.getX();
                }
                if (graphicInfo.getX() < minX) {
                    minX = graphicInfo.getX();
                }
                // height
                if (graphicInfo.getY() > maxY) {
                    maxY = graphicInfo.getY();
                }
                if (graphicInfo.getY() < minY) {
                    minY = graphicInfo.getY();
                }
            }
        }
    }
    int nrOfLanes = 0;
    for (Process process : bpmnModel.getProcesses()) {
        for (Lane l : process.getLanes()) {
            nrOfLanes++;
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(l.getId());
            // // width
            if (graphicInfo.getX() + graphicInfo.getWidth() > maxX) {
                maxX = graphicInfo.getX() + graphicInfo.getWidth();
            }
            if (graphicInfo.getX() < minX) {
                minX = graphicInfo.getX();
            }
            // height
            if (graphicInfo.getY() + graphicInfo.getHeight() > maxY) {
                maxY = graphicInfo.getY() + graphicInfo.getHeight();
            }
            if (graphicInfo.getY() < minY) {
                minY = graphicInfo.getY();
            }
        }
    }
    // Special case, see http://jira.codehaus.org/browse/ACT-1431
    if (flowNodes.isEmpty() && bpmnModel.getPools().isEmpty() && nrOfLanes == 0) {
        // Nothing to show
        minX = 0;
        minY = 0;
    }
    return new CustomProcessDiagramCanvas((int) maxX + 10, (int) maxY + 10, (int) minX, (int) minY, imageType, activityFontName, labelFontName, customClassLoader);
}


public List<Artifact> gatherAllArtifacts(BpmnModel bpmnModel){
    List<Artifact> artifacts = new ArrayList<Artifact>();
    for (Process process : bpmnModel.getProcesses()) {
        artifacts.addAll(process.getArtifacts());
    }
    return artifacts;
}


public BufferedImage generateImage(BpmnModel bpmnModel,String imageType,List<String> highLightedActivities,List<String> highLightedFlows,double scaleFactor){
    return generateImage(bpmnModel, imageType, highLightedActivities, highLightedFlows, null, null, null, scaleFactor);
}


public CustomProcessDiagramCanvas.SHAPE_TYPE getShapeType(BaseElement baseElement){
    if (baseElement instanceof Task || baseElement instanceof Activity || baseElement instanceof TextAnnotation) {
        return CustomProcessDiagramCanvas.SHAPE_TYPE.Rectangle;
    } else if (baseElement instanceof Gateway) {
        return CustomProcessDiagramCanvas.SHAPE_TYPE.Rhombus;
    } else if (baseElement instanceof Event) {
        return CustomProcessDiagramCanvas.SHAPE_TYPE.Ellipse;
    } else {
    // unknown source element, just do not correct coordinates
    }
    return null;
}


}