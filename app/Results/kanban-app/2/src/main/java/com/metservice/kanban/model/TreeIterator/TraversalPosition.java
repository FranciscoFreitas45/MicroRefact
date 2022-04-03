package com.metservice.kanban.model.TreeIterator;
 import java.util.Iterator;
public class TraversalPosition {

 private  int NODE_ITSELF;

 private  TraversalPosition<T> parent;

 private  TreeNode<T> node;

 private  int branchIndex;

public TraversalPosition(TraversalPosition<T> parent, TreeNode<T> node) {
    this.parent = parent;
    this.node = node;
    this.branchIndex = NODE_ITSELF;
}
public TreeNode<T> getNode(){
    return node;
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