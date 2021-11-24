package graph.entities;

import com.github.javaparser.Range;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.google.gson.annotations.Expose;
import javassist.expr.MethodCall;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MyClass {
    private String simpleName;
    private List<ConstructorDeclaration> constructor;
    private Integer begin;
    private Integer end;
    private String qualifiedName;
    private Boolean isInterface;
    private ClassOrInterfaceDeclaration visitor;
    private NodeList<AnnotationExpr> annotations;
    private List<VariableDeclarator> variables;
    private List<FieldDeclaration> instance_variables;
    private Map<String, MyMethod> methods; // method name, method reference
    private List<MethodCallExpr> methodInvocations;
    private Set<String> operations; // List of methods called from another service, used for metric calculation
    private Set<String> implementedTypes;
    private Set<String> extendedTypes;
    private Service service;
    private NodeList<ImportDeclaration> imports;

    public MyClass(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        this.methods = new HashMap<>();
        this.operations = new HashSet<>();
        this.implementedTypes = new HashSet<>();
        this.extendedTypes = new HashSet<>();
    }

    public MyClass(String qualifiedName, Service service) {
        this(qualifiedName);
        this.service = service;
    }

    public MyClass(ClassOrInterfaceDeclaration visitor, NodeList<ImportDeclaration> imports, Range range) {
        this.constructor = visitor.getConstructors();
        this.methods = new HashMap<>();
        this.operations = new HashSet<>();
        this.implementedTypes = visitor.getImplementedTypes().stream().map(x-> x.toString()).collect(Collectors.toSet());
        this.extendedTypes = visitor.getExtendedTypes().stream().map(x->x.toString()).collect(Collectors.toSet());
        this.visitor = visitor;
        visitor.getFullyQualifiedName().ifPresent(qualifiedName -> this.qualifiedName = qualifiedName);
        this.isInterface = visitor.isInterface();
        this.simpleName = visitor.getName().toString();
        this.service = null;
        this.imports=imports;
        this.begin = range.begin.line;
        this.end = range.end.line;
        //System.out.println(visitor.getImplementedTypes());


    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public ClassOrInterfaceDeclaration getVisitor() {
        return visitor;
    }

    public Map<String, MyMethod> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, MyMethod> methods) {
        this.methods = methods;
    }

    public Set<String> getOperations() {
        return operations;
    }

    public void setOperations(Set<String> operations) {
        this.operations = operations;
    }

    public void setVisitor(ClassOrInterfaceDeclaration visitor) {
        this.visitor = visitor;
    }

    public boolean isServiceInterface() {
        return !operations.isEmpty();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        if (service.getClasses().containsKey(qualifiedName)) {
            this.service = service;
        }
    }

    public Set<String> getImplementedTypes() {
        return implementedTypes;
    }

    public void setImplementedTypes(Set<String> implementedTypes) {
        this.implementedTypes = implementedTypes;
    }

    public Set<String> getExtendedTypes() {
        return extendedTypes;
    }

    public void setExtendedTypes(Set<String> extendedTypes) {
        this.extendedTypes = extendedTypes;
    }

    public List<MethodCallExpr> getMethodInvocations() {
        return methodInvocations;
    }

    public void setMethodInvocations(List<MethodCallExpr> methodInvocations) {
        this.methodInvocations = methodInvocations;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "qualifiedName='" + qualifiedName + '\'' +
                '}';
    }

    public NodeList<AnnotationExpr> getAnnotations() {
        return annotations;
    }

    public List<VariableDeclarator> getVariables() {
        return variables;
    }

    public void setVariables(List<VariableDeclarator> variables) {
        this.variables = variables;
    }

    public List<FieldDeclaration> getInstance_variables() {
        return instance_variables;
    }

    public void setInstance_variables(List<FieldDeclaration> instance_variables) {
        this.instance_variables = instance_variables;
    }

    public void setAnnotations(NodeList<AnnotationExpr> annotations) {
        this.annotations = annotations;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return qualifiedName.equals(myClass.qualifiedName);
    }

    public Boolean getInterface() {
        return isInterface;
    }

    public void setInterface(Boolean anInterface) {
        isInterface = anInterface;
    }

    public NodeList<ImportDeclaration> getImports() {
        return imports;
    }

    public void setImports(NodeList<ImportDeclaration> imports) {
        this.imports = imports;
    }

    public List<ConstructorDeclaration> getConstructor() {
        return constructor;
    }

    public void setConstructor(List<ConstructorDeclaration> constructor) {
        this.constructor = constructor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualifiedName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
