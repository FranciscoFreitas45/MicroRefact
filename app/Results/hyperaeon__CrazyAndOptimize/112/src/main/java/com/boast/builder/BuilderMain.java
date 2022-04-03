package com.boast.builder;
 public class BuilderMain {


public void main(String[] args){
    Director director = new Director();
    Builder builder = new ConcreteBuilder();
    director.construct(builder);
    Product p = builder.getResult();
    p.show();
}


}