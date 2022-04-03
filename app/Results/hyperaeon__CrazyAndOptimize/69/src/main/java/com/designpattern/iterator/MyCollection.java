package com.designpattern.iterator;
 public class MyCollection implements Collection{

 public  String[] string;


@Override
public Iterator iterator(){
    return new MyIterator(this);
}


@Override
public int size(){
    return string.length;
}


@Override
public Object get(int index){
    return string[index];
}


}