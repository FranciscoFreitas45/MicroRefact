package com.designpattern.composite;
 public class Tree {

 private TreeNode root;

public Tree(String name) {
    root = new TreeNode(name);
}
public void main(String[] args){
    Tree tree = new Tree("A");
    TreeNode nodeB = new TreeNode("B");
    TreeNode nodeC = new TreeNode("C");
    nodeB.add(nodeC);
    tree.root.add(nodeB);
    System.out.println("Build tree sucessful");
}


}