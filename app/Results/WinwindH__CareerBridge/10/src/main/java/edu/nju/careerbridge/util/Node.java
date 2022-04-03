package edu.nju.careerbridge.util;
 import java.util.HashSet;
import java.util.Set;
public class Node {

 private  char icon;

 private  int postion;

 private  NodeType type;

 private  boolean nullable;

 private  Set<Integer> first;

 private  Set<Integer> last;

 private  Set<Integer> follow;

 private  Node right;

 private  Node left;

 private  Node parent;

 private  int end;

 private  boolean isNull;

 private  boolean isLeaf;

 private  boolean isEnd;

 private  int tag;

public Node() {
    first = new HashSet<Integer>();
    last = new HashSet<Integer>();
    follow = new HashSet<Integer>();
    isNull = false;
    isLeaf = false;
    isEnd = false;
}
public void setLeft(Node left){
    this.left = left;
}


public int getPostion(){
    return postion;
}


public void setPostion(int postion){
    this.postion = postion;
}


public void setType(NodeType type){
    this.type = type;
}


public Set<Integer> getLast(){
    return last;
}


public boolean isLeaf(){
    return isLeaf;
}


public char getIcon(){
    return icon;
}


public void setIcon(char icon){
    this.icon = icon;
}


public Node getRight(){
    return right;
}


public Set<Integer> getFollow(){
    return follow;
}


public void setNullable(boolean nullable){
    this.nullable = nullable;
}


public void setFirst(Set<Integer> first){
    this.first = first;
}


public void setLast(Set<Integer> last){
    this.last = last;
}


public NodeType getType(){
    return type;
}


public Set<Integer> getFirst(){
    return first;
}


public boolean isNullable(){
    return nullable;
}


public void setRight(Node right){
    this.right = right;
}


public void setFollow(Set<Integer> follow){
    this.follow = follow;
}


public Node getLeft(){
    return left;
}


}