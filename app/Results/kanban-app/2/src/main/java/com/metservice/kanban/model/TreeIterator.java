package com.metservice.kanban.model;
 import java.util.Iterator;
public class TreeIterator implements Iterator<T>{

 private  TraversalPosition<T> position;

 private  int NODE_ITSELF;

 private  TraversalPosition<T> parent;

 private  TreeNode<T> node;

 private  int branchIndex;

public TreeIterator(TreeNode<T> root) {
    this.position = new TraversalPosition<T>(null, root);
}
@Override
public T next(){
    T result = position.getNode().getValue();
    position = position.advance();
    return result;
}


@Override
public boolean hasNext(){
    return position != null;
}


public TreeNode<T> getNode(){
    return node;
}


@Override
public void remove(){
    throw new UnsupportedOperationException();
}


public TraversalPosition<T> advance(){
    int nextIndex = branchIndex + 1;
    if (nextIndex < node.getNumberOfChildren()) {
        branchIndex = nextIndex;
        return new TraversalPosition<T>(this, node.getChild(branchIndex));
    }
    return parent == null ? null : parent.advance();
}


}