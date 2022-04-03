package com.crazy.chapter8.duplicate.list;
 public class CompModel implements Comparable<CompModel>{

 private  Integer age;

 private  String name;


public void setName(String name){
    this.name = name;
}


public Integer getAge(){
    return age;
}


public String getName(){
    return name;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((age == null) ? 0 : age.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CompModel other = (CompModel) obj;
    if (age == null) {
        if (other.age != null)
            return false;
    } else if (!age.equals(other.age))
        return false;
    if (name == null) {
        if (other.name != null)
            return false;
    } else if (!name.equals(other.name))
        return false;
    return true;
}


@Override
public String toString(){
    return "CompModel [age=" + age + ", name=" + name + "]";
}


@Override
public int compareTo(CompModel o){
    return this.getAge() > o.getAge() ? 1 : this.getAge() < o.getAge() ? -1 : 0;
}


public void setAge(Integer age){
    this.age = age;
}


}