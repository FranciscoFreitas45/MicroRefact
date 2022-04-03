package com.boast.builder;
 public class ConcreteBuilder extends Builder{

 private  Product product;


@Override
public Product getResult(){
    return product;
}


@Override
public void buildPartA(){
    product.add("A");
}


@Override
public void buildPartB(){
    product.add("B");
}


}