package com.ec.survey.model;
 import com.ec.survey.model.survey.base.File;
import java.util;
public class FilesByTypes {

 private  Map<K,FilesByType<T>> filesByTypes;


public boolean hasFiles(){
    for (final Map.Entry<K, FilesByType<T>> filesByTypesEntry : filesByTypes.entrySet()) {
        if (filesByTypesEntry.getValue().hasFiles()) {
            return true;
        }
    }
    return false;
}


public void applyFunctionOnEachFile(TriFunctionReturningVoid<K,T,File> function){
    for (final Map.Entry<K, FilesByType<T>> filesByTypesEntry : filesByTypes.entrySet()) {
        final Map<T, List<File>> filesByType = filesByTypesEntry.getValue().getAllFiles();
        for (final Map.Entry<T, List<File>> filesByTypeEntry : filesByType.entrySet()) {
            for (File file : filesByTypeEntry.getValue()) {
                final K key = filesByTypesEntry.getKey();
                final T secondKey = filesByTypeEntry.getKey();
                function.apply(key, secondKey, file);
            }
        }
    }
}


public void apply(In1 in1,In2 in2,In3 in3)


public List<File> getFiles(K key,T secondKey){
    if (filesByTypes.containsKey(key)) {
        return filesByTypes.get(key).getFiles(secondKey);
    }
    return new ArrayList<>();
}


public void addFile(K key,T secondKey,File file){
    if (!filesByTypes.containsKey(key)) {
        filesByTypes.put(key, new FilesByType<>());
    }
    filesByTypes.get(key).addFile(secondKey, file);
}


}