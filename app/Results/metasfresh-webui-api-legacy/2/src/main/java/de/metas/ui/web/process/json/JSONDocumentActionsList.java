package de.metas.ui.web.process.json;
 import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
public class JSONDocumentActionsList {

@JsonProperty("actions")
 private  List<JSONDocumentAction> actions;


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("actions", actions.isEmpty() ? null : actions).toString();
}


public List<JSONDocumentAction> getActions(){
    return actions;
}


public Collector<WebuiRelatedProcessDescriptor,?,JSONDocumentActionsList> collect(JSONOptions jsonOpts){
    final Supplier<List<WebuiRelatedProcessDescriptor>> supplier = ArrayList::new;
    final BiConsumer<List<WebuiRelatedProcessDescriptor>, WebuiRelatedProcessDescriptor> accumulator = List::add;
    final BinaryOperator<List<WebuiRelatedProcessDescriptor>> combiner = (l, r) -> {
        l.addAll(r);
        return l;
    };
    final Function<List<WebuiRelatedProcessDescriptor>, JSONDocumentActionsList> finisher = processDescriptors -> new JSONDocumentActionsList(processDescriptors, jsonOpts);
    return Collector.of(supplier, accumulator, combiner, finisher);
}


public JSONDocumentActionsList ofList(List<WebuiRelatedProcessDescriptor> processDescriptors,JSONOptions jsonOpts){
    return new JSONDocumentActionsList(processDescriptors, jsonOpts);
}


}