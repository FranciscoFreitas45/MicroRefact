package com.designpattern.iterator;
 public class MyIterator implements Iterator{

 private  Collection collection;

 private  int pos;

public MyIterator(Collection collection) {
    this.collection = collection;
}
@Override
public Object next(){
    if (pos < collection.size() - 1) {
        pos++;
    }
    return collection.get(pos);
}


@Override
public Object previous(){
    if (pos > 0) {
        pos--;
    }
    return collection.get(pos);
}


@Override
public boolean hasNext(){
    if (pos < collection.size() - 1) {
        return true;
    }
    return false;
}


@Override
public Object first(){
    pos = 0;
    return collection.get(pos);
}


}