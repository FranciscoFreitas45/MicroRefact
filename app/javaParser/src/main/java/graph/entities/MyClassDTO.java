package graph.entities;

import com.github.javaparser.Range;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.google.gson.annotations.Expose;
import edu.stanford.nlp.ling.CoreAnnotations;
import org.apache.xml.utils.SystemIDResolver;
import utils.StringUtils;
import visitors.ClassOrInterfaceDeclarationVisitor;
import visitors.MethodCallExprVisitor;
import visitors.VariableDeclaratorVisitor;

import javax.sound.midi.SysexMessage;
import java.awt.desktop.OpenFilesEvent;
import java.util.*;
import java.util.stream.Collectors;

public class MyClassDTO {


    @Expose
    private String qualifiedName;
    @Expose
    private Integer begin;
    @Expose
    private Integer end;

    @Expose
    private Boolean isInterface;
    @Expose
    private List<String> annotations;
    @Expose
    private List<String> variables;
    @Expose
    private List<InstanceVariableDTO> instance_variables;
    @Expose
    private List<String> dependencies;
    @Expose
    private Map<String, MyMethod> methods; // method name, method reference
    @Expose
    private Map<String,MyMethodDTO> myMethods;
    @Expose
    private List<MethodInvocationDTO> methodInvocations;
    @Expose
    private List<String> implementedTypes;
    @Expose
    private List<String> extendedTypes;
    private Set<String> validClasses;
    @Expose
    private List<String> imports;

    public MyClassDTO(MyClass myClass, Set<String> validClasses) {
        this.validClasses = validClasses;
        this.qualifiedName = myClass.getQualifiedName();
        this.begin = myClass.getBegin();
        this.end = myClass.getEnd();
        this.isInterface = myClass.getInterface();
        this.annotations = myClass.getAnnotations().stream().map(x-> x.toString()).collect(Collectors.toList());
        this.variables = myClass.getVariables().stream().map(v -> v.getType().asString() + " " + v.getName().toString()).collect(Collectors.toList());
        this.instance_variables=  extractInstanceVariable(myClass.getInstance_variables());
        this.methods = myClass.getMethods();
        this.myMethods = extractMethodInfo(myClass.getMethods());
        this.methodInvocations = extractMethodInvocations(myClass.getMethodInvocations(),new ArrayList<>());
        this.implementedTypes = new ArrayList<>(myClass.getImplementedTypes());
        this.extendedTypes = new ArrayList<>(myClass.getExtendedTypes());
        this.dependencies = extractDependencies(myClass.getVariables());
        this.imports =  extractImports(myClass.getImports());
    }


    public List<MethodInvocationDTO> extractMethodInvocations(List<MethodCallExpr> methodInvocations,List<VariableOFMethodDTO> vdtos) {


        List<VariableOFMethodDTO> vdtosCopy = new ArrayList<VariableOFMethodDTO>(vdtos);

        List<MethodInvocationDTO> processedMethods = new ArrayList<>();
        for (MethodCallExpr methodCallExpr : methodInvocations) {
            Optional<Expression> scope = methodCallExpr.getScope();
            if (scope.isPresent()) {
                Expression expression = scope.get();


                try {
                    ResolvedReferenceType resolvedReferenceType = expression.calculateResolvedType().asReferenceType();
                    String methodName = methodCallExpr.getNameAsString();
                    String targetClassName = resolvedReferenceType.getQualifiedName();
                    if (isValidClass(targetClassName)) {
                        // TODO: handle method overloading causing conflicts and getting overwritten
                        String scopeName = expression.toString();
                        processedMethods.add(new MethodInvocationDTO(scopeName, methodName, targetClassName));
                        vdtosCopy.add(new VariableOFMethodDTO(scopeName, methodCallExpr.toString(), targetClassName));
                    }

                } catch (UnsolvedSymbolException e) {


                    for( VariableOFMethodDTO v : vdtosCopy){
                        String scopeName = expression.toString();
                        String methodName = methodCallExpr.getNameAsString();
                        if(scopeName.equals(v.getVariable())) {
                            processedMethods.add(new MethodInvocationDTO(scopeName, methodName, v.getQualifedType()));

                            vdtosCopy.add(new VariableOFMethodDTO(v.getType(), methodCallExpr.toString(), v.getQualifedType()));
                            break;
                        }
                    }


                    // When it tries to resolve a class not explicitly present in the project. We don't care about those.
                } catch (UnsupportedOperationException e) {
                    //e.printStackTrace();
                } catch (RuntimeException e) {
                    // TODO CRITICAL : reevaluate
                }

            }
        }
        return processedMethods;
    }

    public List<String> extractDependencies(List<VariableDeclarator> variableDeclarators) {
        List<String> dependencyList = new ArrayList<>();
        for (VariableDeclarator variableDeclarator : variableDeclarators) {
            try {
                // This is a bit hacky but currently there's no support on javaparser to get directly the typeArguments of a Type
                resolveTargetClassFromSubTypes(dependencyList, variableDeclarator.getType());
            } catch (UnsolvedSymbolException | UnsupportedOperationException e) {
                // When it tries to resolve a class not explicitly present in the project. We don't care about those.
                e.printStackTrace();
            }
        }

        for (MyMethod method : methods.values()) {
            MethodDeclaration methodDeclaration = method.getVisitor();
            resolveTargetClassFromSubTypes(dependencyList, methodDeclaration.getType());

            methodDeclaration.getParameters().forEach(parameter -> {
                List<ClassOrInterfaceType> referencesParametersType = parameter.getType().findAll(ClassOrInterfaceType.class);
                try {
                    for (ClassOrInterfaceType ref : referencesParametersType) {
                        String paramTargetClassName = ref.resolve().getQualifiedName();
                        if (isValidClass(paramTargetClassName))
                            dependencyList.add(paramTargetClassName);
                    }
                } catch (UnsolvedSymbolException | UnsupportedOperationException e) {

                }
            });
        }
        return dependencyList;
    }


    private void resolveTargetClassFromSubTypes(List<String> dependencyList, Type type) {
        List<ClassOrInterfaceType> referencesReturnType = type.findAll(ClassOrInterfaceType.class);
        for (ClassOrInterfaceType ref : referencesReturnType) {
            try {
                String targetClassName = ref.resolve().getQualifiedName();
                if (isValidClass(targetClassName))
                    dependencyList.add(targetClassName);
            } catch (UnsolvedSymbolException | UnsupportedOperationException e) {
            }
        }
    }



    public List<InstanceVariableDTO> extractInstanceVariable(List<FieldDeclaration> fieldsDeclaration){
        List<InstanceVariableDTO> instanceFields = new ArrayList<>();

        for(FieldDeclaration fd : fieldsDeclaration){
            List<String> annotations = new ArrayList<>();
            for (AnnotationExpr an : fd.getAnnotations()){
                String s = an.toString();
                annotations.add(s);
            }
            //List<String> annotations = fd.getAnnotations().stream().map(NodeWithName::getNameAsString).collect(Collectors.toList());
            //List<String> modifier = fd.getModifiers().stream().map(NodeWithName::getNameAsString).collect(Collectors.toList());
            List<String> identifier = new ArrayList<>();
            if(fd.getVariables().get(0).getChildNodes().get(0).getChildNodes().size() > 1) {

                for (Node n :  fd.getVariables().get(0).getChildNodes().get(0).getChildNodes()){
                        identifier.add(n.toString());
                }
            }



            String type = fd.getVariables().get(0).getType().asString();
            String variable = fd.getVariables().get(0).getName().asString();
            String modifier = "private";
            if(fd.getModifiers().size()>0) {
                 modifier = fd.getModifiers().get(0).toString();
            }
            int lineBegin = fd.getBegin().get().line;
            int lineEnd = fd.getEnd().get().line;

            InstanceVariableDTO idto;
                idto = new InstanceVariableDTO(annotations, modifier, identifier, type, variable, lineBegin, lineEnd);
            instanceFields.add(idto);

        }

        return instanceFields;
    }

    public Map<String,MyMethodDTO> extractMethodInfo(Map<String,MyMethod> methods){
        System.out.println("*************" +this.qualifiedName + "************");
        Map<String,MyMethodDTO> methodInfo = new HashMap<>();


        for( MyMethod mm : methods.values()) {
            System.out.println(mm.getName());

            List<MethodCallExpr> methodCallInvocations = new ArrayList<>();
            mm.getVisitor().accept(new MethodCallExprVisitor(), methodCallInvocations);



            List<VariableOFMethodDTO> parametersDataType = new ArrayList<>();
            for (Parameter p : mm.getVisitor().getParameters()) {
                VariableOFMethodDTO v = new VariableOFMethodDTO(p.getType().asString(), p.getName().asString());
                parametersDataType.add(v);
            }
            List<String> identifier = new ArrayList<>();
            List<String> returnDataType = new ArrayList<>();
            if(mm.getVisitor().getType().getChildNodes().size()>1){
                for (Node n :  mm.getVisitor().getType().getChildNodes()){
                    identifier.add(n.toString());
                }
            }
            System.out.println("---" +mm.getVisitor().getType().getChildNodes());
            returnDataType.add(mm.getVisitor().getTypeAsString());
            String name = mm.getName();


                Optional<Range> r = mm.getVisitor().getRange();
                List<VariableDeclarator> variables = new ArrayList<>();
                mm.getVisitor().accept(new VariableDeclaratorVisitor(), variables);
                List<VariableOFMethodDTO> vdtos = new ArrayList<>();
                System.out.println(variables);
                for (VariableDeclarator v : variables) {
                    VariableOFMethodDTO vdto;
                    if(v.getChildNodes().get(0).getChildNodes().size() > 1){
                        try {
                            String qualifiedType = ((ClassOrInterfaceType) v.getChildNodes().get(0).getChildNodes().get(1)).resolve().getQualifiedName();
                            vdto = new VariableOFMethodDTO(v.getType().asString(), v.getName().asString(), qualifiedType);
                            vdtos.add(vdto);
                        } catch (Exception e ) {
                            System.out.println(e);

                        }
                    }
                    else {
                         vdto = new VariableOFMethodDTO(v.getType().asString(), v.getName().asString());
                        vdtos.add(vdto);
                    }
                }
                List<MethodInvocationDTO> methodInvocationDTOS = extractMethodInvocations(methodCallInvocations,vdtos);
                MyMethodDTO mdto;
                List<String> annotations = mm.getVisitor().getAnnotations().stream().map(x -> x.toString()).collect(Collectors.toList());
                if(mm.getVisitor().getBody().isPresent()){
                    mdto = new MyMethodDTO(name, r.get().begin.line, r.get().end.line, returnDataType,identifier, parametersDataType, vdtos, methodInvocationDTOS,annotations,mm.getVisitor().getBody().get().toString());
                }
                else
                 mdto = new MyMethodDTO(name, r.get().begin.line, r.get().end.line, returnDataType,identifier, parametersDataType, vdtos, methodInvocationDTOS,annotations);
                methodInfo.put(mm.getName(), mdto);

        }


        return methodInfo;
    }

    public List<String> extractImports(NodeList<ImportDeclaration> imports){
        List<String> imp = new ArrayList<>();
        for (ImportDeclaration i : imports){
            imp.add(i.getName().asString());
        }
        return imp;
    }


    public List<MethodInvocationDTO> getMethodInvocations() {
        return methodInvocations;
    }

    public Map<String, MyMethod> getMethods() {
        return methods;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public List<String> getVariables() {
        return variables;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public List<String> getImplementedTypes() {
        return implementedTypes;
    }

    public List<String> getExtendedTypes() {
        return extendedTypes;
    }

    public Set<String> getValidClasses() {
        return validClasses;
    }

    private boolean isValidClass(String qualifiedName) {
        return this.validClasses.contains(qualifiedName);
    }

    public List<InstanceVariableDTO> getInstance_variables() {
        return instance_variables;
    }

    public void setInstance_variables(List<InstanceVariableDTO> instance_variables) {
        this.instance_variables = instance_variables;
    }
}


