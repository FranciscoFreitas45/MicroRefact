package edu.nju.careerbridge.util.KD;
 import java.util;
public class Node {

 private double[] val;

 private int dimension;

 private Node left;

 private Node right;

Node(double[] val) {
    this.val = val.clone();
    left = null;
    right = null;
}
public double getVal(int n){
    return val[n];
}


public int getDimensionNum(){
    return val.length;
}


public int getDimension(){
    return dimension;
}


public void setDimension(int dimension){
    this.dimension = dimension;
}


public String toString(){
    return Arrays.toString(val);
}


}