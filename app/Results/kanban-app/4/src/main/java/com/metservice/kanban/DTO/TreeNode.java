package com.metservice.kanban.DTO;
 import java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.util.Assert;
public class TreeNode {

 private  Class<T> valueClass;

 private  T value;

 private  TreeNode<T>[] children;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

private TreeNode(Class<T> valueClass, T value, TreeNode<?>[] children) {
    Assert.notNull(value, "value is null");
    this.valueClass = valueClass;
    this.value = value;
    this.children = cloneArray(valueClass, children);
}
public int getNumberOfChildren(){
    return children.length;
}


public T getValue(){
    return value;
}


public List<TreeNode<T>> getChildren(){
    return new ArrayList<TreeNode<T>>(asList(children));
}


public int getNumberOfNodes(){
    return 0;
}


public TreeNode<T> getChild(int index){
    return children[index];
}


public boolean hasChildren(){
    return children.length > 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasChildren"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}