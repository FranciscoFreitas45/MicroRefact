package com.designpattern.iterator.duplicate;
 public class MyCollection implements Collection{

 private  String[] str;


@Override
public Iterator iterator(){
    return new MyIterator(this);
}


@Override
public int size(){
    return str.length;
}


@Override
public Object get(int index){
    return str[index];
}


}