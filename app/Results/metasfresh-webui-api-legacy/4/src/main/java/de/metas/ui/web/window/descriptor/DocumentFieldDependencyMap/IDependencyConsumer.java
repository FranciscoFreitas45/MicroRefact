package de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap;
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
@FunctionalInterface
public interface IDependencyConsumer {


public void consume(String dependentFieldName,DependencyType dependencyType)
;

}