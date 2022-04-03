package com.metservice.kanban.model;
 import java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.util.Assert;
public class TreeNode {

 private  Class<T> valueClass;

 private  T value;

 private  TreeNode<T>[] children;

private TreeNode(Class<T> valueClass, T value, TreeNode<?>[] children) {
    Assert.notNull(value, "value is null");
    this.valueClass = valueClass;
    this.value = value;
    this.children = cloneArray(valueClass, children);
}
public int getNumberOfChildren(){
    return children.length;
}


public T getValue(){
    return value;
}


public List<TreeNode<T>> getChildren(){
    return new ArrayList<TreeNode<T>>(asList(children));
}


public int getNumberOfNodes(){
    return 0;
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public TreeNode<T>[] cloneArray(Class<T> valueClass,TreeNode<?>[] newChildren){
    TreeNode[] clonedChildren = new TreeNode[newChildren.length];
    for (int i = 0; i < newChildren.length; i++) {
        TreeNode<T> child = (TreeNode<T>) newChildren[i];
        clonedChildren[i] = child;
        if (!valueClass.equals(child.valueClass)) {
            throw new ClassCastException("cannot add a " + child.valueClass + " to a tree of " + valueClass);
        }
    }
    return clonedChildren;
}


public boolean hasChildren(){
    return children.length > 0;
}


public TreeNode<T> getChild(int index){
    return children[index];
}


public TreeNode<T> create(Class<T> valueClass,T value,Collection<TreeNode<T>> children){
    return new TreeNode<T>(valueClass, value, children.toArray(new TreeNode<?>[children.size()]));
}


@Override
public String toString(){
    return value.toString();
}


public Iterator<T> depthFirstIterator(){
    return new TreeIterator<T>(this);
}


}