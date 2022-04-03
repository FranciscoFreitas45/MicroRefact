package DTO;
 public interface DocumentFieldDataBindingDescriptor {


public T cast(Class<T> bindingClass){
    @SuppressWarnings("unchecked")
    final T thisCasted = (T) this;
    return thisCasted;
}
;

public boolean isDefaultOrderBy(){
    return false;
}
;

public int getDefaultOrderByPriority(){
    return 0;
}
;

public String getColumnName()
;

public boolean isMandatory()
;

public boolean isDefaultOrderByAscending(){
    return true;
}
;

}