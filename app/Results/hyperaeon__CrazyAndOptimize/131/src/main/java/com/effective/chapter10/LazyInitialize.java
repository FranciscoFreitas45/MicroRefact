package com.effective.chapter10;
 public class LazyInitialize {

 private  FieldType field;

 static  FieldType field;

 private  FieldType type;


public FieldType computStaticFieldValues(){
    // TODO Auto-generated method stub
    return null;
}


public FieldType getFields(){
    return FieldHolder.field;
}


public FieldType getField(){
    if (field == null) {
        field = computFieldValue();
    }
    return field;
}


public FieldType computFieldValue(){
    // TODO Auto-generated method stub
    return null;
}


public FieldType getFieldTypes(){
    FieldType result = type;
    if (result == null) {
        synchronized (this) {
            result = type;
            if (result == null) {
                type = result = computFieldValue();
            }
        }
    }
    return result;
}


}