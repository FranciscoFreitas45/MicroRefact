package de.metas.ui.web.window.descriptor;
 import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
public class DocumentFieldDependencyMap {

 private  DocumentFieldDependencyMap EMPTY;

 public  EnumSet<DependencyType> DEPENDENCYTYPES_DocumentLevel;

 public  EnumSet<DependencyType> DEPENDENCYTYPES_FieldLevel;

 public  String DOCUMENT_Readonly;

 public  Set<String> DOCUMENT_ALL_FIELDS;

 private  ImmutableMap<DependencyType,Multimap<String,String>> type2name2dependencies;

 private  Map<DependencyType,ImmutableSetMultimap.Builder<String,String>> type2name2dependencies;


public Builder add(DocumentFieldDependencyMap dependencies){
    if (dependencies == null || dependencies == EMPTY) {
        return this;
    }
    for (final Map.Entry<DependencyType, Multimap<String, String>> l1 : dependencies.type2name2dependencies.entrySet()) {
        final DependencyType dependencyType = l1.getKey();
        ImmutableSetMultimap.Builder<String, String> name2dependencies = type2name2dependencies.get(dependencyType);
        if (name2dependencies == null) {
            name2dependencies = ImmutableSetMultimap.builder();
            type2name2dependencies.put(dependencyType, name2dependencies);
        }
        name2dependencies.putAll(l1.getValue());
    }
    return this;
}


public ImmutableMap<DependencyType,Multimap<String,String>> getType2Name2DependenciesMap(){
    final ImmutableMap.Builder<DependencyType, Multimap<String, String>> builder = ImmutableMap.builder();
    for (final Entry<DependencyType, ImmutableSetMultimap.Builder<String, String>> e : type2name2dependencies.entrySet()) {
        final DependencyType dependencyType = e.getKey();
        final Multimap<String, String> name2dependencies = e.getValue().build();
        if (name2dependencies.isEmpty()) {
            continue;
        }
        builder.put(dependencyType, name2dependencies);
    }
    return builder.build();
}


public DocumentFieldDependencyMap build(){
    if (type2name2dependencies.isEmpty()) {
        return EMPTY;
    }
    return new DocumentFieldDependencyMap(this);
}


public void consumeForChangedFieldName(String changedFieldName,IDependencyConsumer consumer){
    for (final DependencyType dependencyType : DependencyType.values()) {
        final Multimap<String, String> name2dependencies = type2name2dependencies.get(dependencyType);
        if (name2dependencies == null || name2dependencies.isEmpty()) {
            continue;
        }
        for (final String dependentFieldName : name2dependencies.get(changedFieldName)) {
            consumer.consume(dependentFieldName, dependencyType);
        }
    }
}


public Builder builder(){
    return new Builder();
}


public void consume(String dependentFieldName,DependencyType dependencyType)


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(type2name2dependencies).toString();
}


public String toStringX(){
    final StringBuilder sb = new StringBuilder();
    for (final Map.Entry<DependencyType, Multimap<String, String>> l1 : type2name2dependencies.entrySet()) {
        final DependencyType dependencyType = l1.getKey();
        final Multimap<String, String> name2dependencies = l1.getValue();
        for (final Map.Entry<String, String> l2 : name2dependencies.entries()) {
            final String dependsOnFieldName = l2.getKey();
            final String fieldName = l2.getValue();
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(dependencyType).append(": ").append(dependsOnFieldName).append(" -> ").append(fieldName);
        }
    }
    return sb.toString();
}


}