package com.ec.survey.model;
 import com.ec.survey.model.survey.base.File;
import java.util;
public class FilesByType {

 private  Map<K,List<File>> filesByType;


public boolean hasFiles(){
    for (final Map.Entry<K, List<File>> filesByQuestionUidEntry : filesByType.entrySet()) {
        if (!filesByQuestionUidEntry.getValue().isEmpty()) {
            return true;
        }
    }
    return false;
}


public void applyFunctionOnEachFile(BiFunctionReturningVoid<K,File> function){
    for (final Map.Entry<K, List<File>> filesByQuestionUidEntry : filesByType.entrySet()) {
        for (final File file : filesByQuestionUidEntry.getValue()) {
            K key = filesByQuestionUidEntry.getKey();
            function.apply(key, file);
        }
    }
}


public void apply(In1 in1,In2 in2)


public Map<K,List<File>> getAllFiles(){
    return filesByType;
}


public List<File> getFiles(K key){
    if (filesByType.containsKey(key)) {
        return filesByType.get(key);
    }
    return new ArrayList<>();
}


public void addFile(K key,File file){
    if (!filesByType.containsKey(key)) {
        filesByType.put(key, new ArrayList<>());
    }
    filesByType.get(key).add(file);
}


}