package com.tech.configurations.tools;
 import java.util.Objects;
public class Pair {

 private  L left;

 private  R right;

public Pair(L left, R right) {
    this.left = left;
    this.right = right;
}
public R getRight(){
    return right;
}


@Override
public int hashCode(){
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.left);
    hash = 37 * hash + Objects.hashCode(this.right);
    return hash;
}


public Pair<L,R> of(L left,R right){
    return new Pair(left, right);
}


@Override
public boolean equals(Object o){
    if (!(o instanceof Pair))
        return false;
    Pair pairo = (Pair) o;
    return this.left.equals(pairo.getLeft()) && this.right.equals((pairo.getRight()));
}


public L getLeft(){
    return left;
}


}