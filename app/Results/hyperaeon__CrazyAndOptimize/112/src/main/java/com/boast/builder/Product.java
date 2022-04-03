package com.boast.builder;
 import java.util.ArrayList;
import java.util.List;
public class Product {

 private List<String> parts;


public void add(String part){
    parts.add(part);
}


public void show(){
    parts.forEach(part -> System.out.println(part));
}


}