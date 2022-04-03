package de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
 import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.TranslationSource;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout.Displayed;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import de.metas.util.lang.ReferenceListAwareEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.reflect.FieldReference;
import org.compiere.Adempiere;
import org.compiere.util.Env;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Stream;
@Value
@Builder(toBuilder = true)
public class ClassViewColumnDescriptor {

@NonNull
 private  String fieldName;

@NonNull
@Getter(AccessLevel.NONE)
 private  FieldReference fieldReference;

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  DocumentFieldWidgetType widgetType;

 private  int listReferenceId;

@Nullable
 private  WidgetSize widgetSize;

@NonNull
 private  ViewEditorRenderMode editorRenderMode;

 private  boolean allowSorting;

@NonNull
 private  ImmutableMap<JSONViewDataType,ClassViewColumnLayoutDescriptor> layoutsByViewType;

@NonNull
 private  ImmutableSet<MediaType> restrictToMediaTypes;


public boolean isDisplayed(JSONViewDataType viewType){
    final ClassViewColumnLayoutDescriptor layout = layoutsByViewType.get(viewType);
    return layout != null && layout.isDisplayed();
}


public Field getField(){
    return fieldReference.getField();
}


public int getSeqNo(JSONViewDataType viewType){
    final ClassViewColumnLayoutDescriptor layout = layoutsByViewType.get(viewType);
    if (layout == null) {
        return Integer.MAX_VALUE;
    }
    final int seqNo = layout.getSeqNo();
    return seqNo >= 0 ? seqNo : Integer.MAX_VALUE;
}


}