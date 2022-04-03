package es.gva.dgti.gvgeoportal.web;
 import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.web.datatables.util.DatatablesUtils;
import org.gvnix.web.datatables.util.DatatablesUtilsBean;
import org.gvsig.catalog.exceptions.NotSupportedVersionException;
import org.gvsig.framework.web.ogc.CSWCriteria;
import org.gvsig.framework.web.ogc.CSWResult;
import org.gvsig.framework.web.ogc.CSWSingleResult;
import org.gvsig.framework.web.service.OGCInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.core.exception.ExportException;
import com.github.dandelion.datatables.core.export.CsvExport;
import com.github.dandelion.datatables.core.export.DatatablesExport;
import com.github.dandelion.datatables.core.export.ExportConf;
import com.github.dandelion.datatables.core.export.ExportType;
import com.github.dandelion.datatables.core.export.ExportUtils;
import com.github.dandelion.datatables.core.export.XmlExport;
import com.github.dandelion.datatables.core.html.HtmlTable;
import com.github.dandelion.datatables.extras.export.itext.PdfExport;
import com.github.dandelion.datatables.extras.export.poi.XlsExport;
import com.github.dandelion.datatables.extras.export.poi.XlsxExport;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import es.gva.dgti.gvgeoportal.domain.Catalogo;
import es.gva.dgti.gvgeoportal.service.domain.CatalogoService;
import es.gva.dgti.gvgeoportal.service.domain.GestorCatalogoService;
@RequestMapping("/catalogos")
@Controller
@RooWebScaffold(path = "catalogos", formBackingObject = Catalogo.class, create = false, update = false, delete = false)
@GvNIXDatatables(ajax = true)
public class CatalogoController {

 private  Logger LOGGER;

 private  String VIEW_LIST_CATALOG;

@Autowired(required = false)
 private OGCInfoService ogcInfoService;

@Autowired
 private CatalogoService catalogoService;

@Autowired
 private GestorCatalogoService gestorCatalogoService;

@Autowired
 public  ConversionService conversionService_dtt;

@Autowired
 public  MessageSource messageSource_dtt;

@Autowired
 public  DatatablesUtilsBean datatablesUtilsBean_dtt;

 public  BeanWrapper beanWrapper_dtt;


@RequestMapping(produces = "text/html", method = RequestMethod.PUT, params = "datatablesRedirect")
public String updateDatatablesDetail(String redirect,Catalogo catalogo,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return VIEW_LIST_CATALOG;
}


@RequestMapping(produces = "text/html", method = RequestMethod.POST, params = "datatablesRedirect")
public String createDatatablesDetail(String redirect,Catalogo catalogo,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return VIEW_LIST_CATALOG;
}


@RequestMapping(produces = "text/html", method = RequestMethod.DELETE, params = "datatablesRedirect", value = "/{id}")
public String deleteDatatablesDetail(String redirect,Long id,Integer page,Integer size,Model uiModel){
    return VIEW_LIST_CATALOG;
}


@RequestMapping(method = RequestMethod.GET, produces = "text/html")
public String listDatatables(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    // Get parentId information for details render
    String parentId = params.remove("_dt_parentId");
    if (StringUtils.isNotBlank(parentId)) {
        uiModel.addAttribute("parentId", parentId);
    }
    String rowOnTopIds = params.remove("dtt_row_on_top_ids");
    if (StringUtils.isNotBlank(rowOnTopIds)) {
        uiModel.addAttribute("dtt_row_on_top_ids", rowOnTopIds);
    }
    String tableHashId = params.remove("dtt_parent_table_id_hash");
    if (StringUtils.isNotBlank(tableHashId)) {
        uiModel.addAttribute("dtt_parent_table_id_hash", tableHashId);
    }
    if (!params.isEmpty()) {
        // TODO Ver si se puede configurar a nivel de aplicación.
        // Se añade la codificación utf_8, ya que los parámetros llegan en
        // ISO_8859_1
        if (params.containsKey("textoLibreFinder")) {
            String textoLibreFinder = (String) params.get("textoLibreFinder");
            if (!textoLibreFinder.isEmpty()) {
                String textoLibreUtf8 = new String(textoLibreFinder.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
                params.put("textoLibreFinder", textoLibreUtf8);
            }
        }
        if (params.containsKey("tituloFinder")) {
            String tituloFinder = (String) params.get("tituloFinder");
            if (!tituloFinder.isEmpty()) {
                String tituloUtf8 = new String(tituloFinder.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
                params.put("tituloFinder", tituloUtf8);
            }
        }
        if (params.containsKey("resumenFinder")) {
            String resumenFinder = (String) params.get("resumenFinder");
            if (!resumenFinder.isEmpty()) {
                String resumenUtf8 = new String(resumenFinder.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
                params.put("resumenFinder", resumenUtf8);
            }
        }
        uiModel.addAttribute("baseFilter", params);
        uiModel.addAttribute("params", params);
    }
    if (params.containsKey("nombreServicio")) {
        String nombreServicio = (String) params.get("nombreServicio");
        if (!nombreServicio.isEmpty()) {
            uiModel.addAttribute("nombreServicio", nombreServicio);
        }
    }
    if (params.containsKey("descripcionServicio")) {
        String descripcionServicio = (String) params.get("descripcionServicio");
        if (!descripcionServicio.isEmpty()) {
            uiModel.addAttribute("descripcionServicio", descripcionServicio);
        }
    }
    uiModel.addAttribute("showResult", true);
    uiModel.addAttribute("gestorCatalogos", gestorCatalogoService.findAllGestorCatalogos("nombre", "ASC"));
    // Add patterns
    addDateTimeFormatPatterns(uiModel);
    return "catalogos/list";
}


@ModelAttribute
public void populateDatatablesConfig(Model uiModel){
    uiModel.addAttribute("datatablesHasBatchSupport", true);
    uiModel.addAttribute("datatablesUseAjax", true);
    uiModel.addAttribute("datatablesInlineEditing", false);
    uiModel.addAttribute("datatablesInlineCreating", false);
    uiModel.addAttribute("datatablesSecurityApplied", true);
    uiModel.addAttribute("datatablesStandardMode", true);
    uiModel.addAttribute("finderNameParam", "ajax_find");
}


@RequestMapping(value = "/exportxls", produces = "text/xls")
public void exportXls(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request,HttpServletResponse response){
    export(criterias, catalogo, ExportType.XLS, new XlsExport(), request, response);
}


@ResponseBody
@RequestMapping(headers = "Accept=application/json", params = "getColumnType")
public String getColumnType(Model uiModel,HttpServletRequest request,String columnName){
    // Getting all declared fields
    boolean fieldExists = false;
    Field attr = null;
    for (Field field : Catalogo.class.getDeclaredFields()) {
        if (field.getName().equals(columnName)) {
            attr = field;
            fieldExists = true;
            break;
        }
    }
    // If current field not exists on entity, find on superclass
    if (!fieldExists) {
        if (Catalogo.class.getSuperclass() != null) {
            for (Field field : Catalogo.class.getSuperclass().getDeclaredFields()) {
                if (field.getName().equals(columnName)) {
                    attr = field;
                    fieldExists = true;
                    break;
                }
            }
        }
    }
    if (fieldExists) {
        // Getting field type
        Object fieldType = null;
        fieldType = attr.getType();
        String type = fieldType.toString();
        // Returning value based on type
        if ("String".equals(type)) {
            return "{\"columnType\": \"string\"}";
        } else if ("float".equals(type) || type.contains("Float")) {
            return "{\"columnType\": \"number\"}";
        } else if ("short".equals(type) || type.contains("Short")) {
            return "{\"columnType\": \"number\"}";
        } else if ("long".equals(type) || type.contains("Long")) {
            return "{\"columnType\": \"number\"}";
        } else if ("double".equals(type) || type.contains("Double")) {
            return "{\"columnType\": \"number\"}";
        } else if ("int".equals(type) || type.contains("Integer")) {
            return "{\"columnType\": \"number\"}";
        } else if ("Date".equals(type)) {
            return "{\"columnType\": \"date\"}";
        } else if ("boolean".equals(type) || type.contains("Boolean")) {
            return "{\"columnType\": \"boolean\"}";
        } else {
            // Returning by default
            return "{\"columnType\": \"undefined\"}";
        }
    }
    // Returning by default
    return "{\"columnType\": \"undefined\"}";
}


@RequestMapping(produces = "text/html")
public String list(Integer page,Integer size,String sortFieldName,String sortOrder,Model uiModel){
    // overrides the standard Roo list method and
    // delegates on datatables list method
    return listDatatables(uiModel, null);
}


@RequestMapping(headers = "Accept=application/json", params = "checkFilters")
@ResponseBody
public ResponseEntity<String> checkFilterExpressions(WebRequest request,String property,String expression){
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json; charset=utf-8");
    if (beanWrapper_dtt == null) {
        beanWrapper_dtt = new BeanWrapperImpl(Catalogo.class);
    }
    Class type = beanWrapper_dtt.getPropertyType(property);
    boolean response = datatablesUtilsBean_dtt.checkFilterExpressions(type, expression);
    return new ResponseEntity<String>(String.format("{ \"response\": %s, \"property\": \"%s\"}", response, property), headers, org.springframework.http.HttpStatus.OK);
}


@RequestMapping(value = "/exportxml", produces = "text/xml")
public void exportXml(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request,HttpServletResponse response){
    export(criterias, catalogo, ExportType.XML, new XmlExport(), request, response);
}


public Map<String,Object> getPropertyMap(Catalogo catalogo,Enumeration<Map<String,String>> propertyNames){
    Map<String, Object> propertyValuesMap = new HashMap<String, Object>();
    // If no entity or properties given, return empty Map
    if (catalogo == null || propertyNames == null) {
        return propertyValuesMap;
    }
    List<String> properties = new ArrayList<String>();
    CollectionUtils.addAll(properties, propertyNames);
    // There must be at least one property name, otherwise return empty Map
    if (properties.isEmpty()) {
        return propertyValuesMap;
    }
    // Iterate over given properties to get each property value
    BeanWrapper entityBean = new BeanWrapperImpl(catalogo);
    for (String propertyName : properties) {
        if (entityBean.isReadableProperty(propertyName)) {
            Object propertyValue = null;
            try {
                propertyValue = entityBean.getPropertyValue(propertyName);
            } catch (Exception e) {
                // TODO log warning
                continue;
            }
            propertyValuesMap.put(propertyName, propertyValue);
        }
    }
    return propertyValuesMap;
}


@ResponseBody
@RequestMapping(headers = "Accept=application/json", params = "geti18nText")
public String geti18nText(Model uiModel,HttpServletRequest request,String locale){
    // Getting current locale
    Locale defaultLocale = new Locale(locale);
    // Building JSON response
    StringBuilder json = new StringBuilder();
    json.append("\"all_isnull\": \"" + messageSource_dtt.getMessage("global.filters.operations.all.isnull", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"all_notnull\": \"" + messageSource_dtt.getMessage("global.filters.operations.all.notnull", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"boolean_false\": \"" + messageSource_dtt.getMessage("global.filters.operations.boolean.false", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"boolean_true\": \"" + messageSource_dtt.getMessage("global.filters.operations.boolean.true", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"date_between\": \"" + messageSource_dtt.getMessage("global.filters.operations.date.between", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"date_date\": \"" + messageSource_dtt.getMessage("global.filters.operations.date.date", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"date_day\": \"" + messageSource_dtt.getMessage("global.filters.operations.date.day", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"date_month\": \"" + messageSource_dtt.getMessage("global.filters.operations.date.month", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"date_year\": \"" + messageSource_dtt.getMessage("global.filters.operations.date.year", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"number_between\": \"" + messageSource_dtt.getMessage("global.filters.operations.number.between", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"string_contains\": \"" + messageSource_dtt.getMessage("global.filters.operations.string.contains", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"string_ends\": \"" + messageSource_dtt.getMessage("global.filters.operations.string.ends", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"string_isempty\": \"" + messageSource_dtt.getMessage("global.filters.operations.string.isempty", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"string_isnotempty\": \"" + messageSource_dtt.getMessage("global.filters.operations.string.isnotempty", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"string_starts\": \"" + messageSource_dtt.getMessage("global.filters.operations.string.starts", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"button_find\": \"" + messageSource_dtt.getMessage("button_find", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_all_isnull\": \"" + messageSource_dtt.getMessage("help.all.isnull", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_all_notnull\": \"" + messageSource_dtt.getMessage("help.all.notnull", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_boolean_false\": \"" + messageSource_dtt.getMessage("help.boolean.false", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_boolean_true\": \"" + messageSource_dtt.getMessage("help.boolean.true", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_date_between\": \"" + messageSource_dtt.getMessage("help.date.between", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_date_date\": \"" + messageSource_dtt.getMessage("help.date.date", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_date_day\": \"" + messageSource_dtt.getMessage("help.date.day", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_date_month\": \"" + messageSource_dtt.getMessage("help.date.month", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_date_year\": \"" + messageSource_dtt.getMessage("help.date.year", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_between\": \"" + messageSource_dtt.getMessage("help.number.between", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_eq\": \"" + messageSource_dtt.getMessage("help.number.eq", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_neq\": \"" + messageSource_dtt.getMessage("help.number.neq", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_gt\": \"" + messageSource_dtt.getMessage("help.number.gt", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_lt\": \"" + messageSource_dtt.getMessage("help.number.lt", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_goe\": \"" + messageSource_dtt.getMessage("help.number.goe", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_number_loe\": \"" + messageSource_dtt.getMessage("help.number.loe", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_string_contains\": \"" + messageSource_dtt.getMessage("help.string.contains", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_string_ends\": \"" + messageSource_dtt.getMessage("help.string.ends", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_string_isempty\": \"" + messageSource_dtt.getMessage("help.string.isempty", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_string_isnotempty\": \"" + messageSource_dtt.getMessage("help.string.isnotempty", null, defaultLocale) + "\"");
    json.append(",");
    json.append("\"help_string_starts\": \"" + messageSource_dtt.getMessage("help.string.starts", null, defaultLocale) + "\"");
    json.append("}");
    // return JSON with locale strings
    return json.toString();
}


public Map<String,String> populateParametersMap(HttpServletRequest request){
    Map<String, Object> params;
    if (request == null) {
        params = Collections.emptyMap();
    } else {
        params = new HashMap<String, Object>(request.getParameterMap());
    }
    Map<String, String> allParams = new HashMap<String, String>(params.size());
    String value;
    for (Map.Entry<String, Object> entry : params.entrySet()) {
        String key = entry.getKey();
        Object objValue = entry.getValue();
        if (objValue instanceof String[]) {
            value = ((String[]) objValue)[0];
        } else {
            value = (String) objValue;
        }
        allParams.put(key, value);
    }
    return allParams;
}


public DatatablesResponse<Map<String,String>> findAllCatalogos(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request){
    // DON'T USE IT: use findAllIncidenciasWithHttpStatus to support send
    // HTTP
    // status to client
    return null;
}


@RequestMapping(value = "/exportpdf", produces = "text/pdf")
public void exportPdf(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request,HttpServletResponse response){
    export(criterias, catalogo, ExportType.PDF, new PdfExport(), request, response);
}


@RequestMapping(value = "/exportxlsx", produces = "text/xlsx")
public void exportXlsx(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request,HttpServletResponse response){
    export(criterias, catalogo, ExportType.XLSX, new XlsxExport(), request, response);
}


public List<Map<String,String>> retrieveData(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request){
    // Cloned criteria in order to not paginate the results
    DatatablesCriterias noPaginationCriteria = new DatatablesCriterias(criterias.getSearch(), 0, null, criterias.getColumnDefs(), criterias.getSortingColumnDefs(), criterias.getInternalCounter());
    // Do the search to obtain the data
    Map<String, Object> baseSearchValuesMap = getPropertyMap(catalogo, request);
    setDatatablesBaseFilter(baseSearchValuesMap);
    org.gvnix.web.datatables.query.SearchResults<es.gva.dgti.gvgeoportal.domain.Catalogo> searchResult = datatablesUtilsBean_dtt.findByCriteria(Catalogo.class, noPaginationCriteria, baseSearchValuesMap);
    // Use ConversionService with the obtained data
    return datatablesUtilsBean_dtt.populateDataSet(searchResult.getResults(), "id", searchResult.getTotalCount(), searchResult.getResultsCount(), criterias.getColumnDefs(), null).getRows();
}


@RequestMapping(headers = "Accept=application/json", value = "/datatables/ajax", params = "ajax_find=ByAll", produces = "application/json")
@ResponseBody
public ResponseEntity<DatatablesResponse<Map<String,String>>> findByAll(DatatablesCriterias criterias,HttpServletRequest request){
    // Entity pk field name
    String pkFieldName = "id";
    // Se obtienen los patterns
    org.springframework.ui.Model uiModel = new org.springframework.ui.ExtendedModelMap();
    addDateTimeFormatPatterns(uiModel);
    Map<String, Object> datePattern = uiModel.asMap();
    Map<String, String> params = populateParametersMap(request);
    String titulo = null;
    String resumen = null;
    String gestorCatalogoFinder = "";
    if (params.containsKey("gestorCatalogoFinder")) {
        gestorCatalogoFinder = (String) params.get("gestorCatalogoFinder");
        if (gestorCatalogoFinder.isEmpty()) {
            return new ResponseEntity<DatatablesResponse<Map<String, String>>>(HttpStatus.BAD_REQUEST);
        }
    } else {
        return new ResponseEntity<DatatablesResponse<Map<String, String>>>(HttpStatus.BAD_REQUEST);
    }
    if (params.containsKey("textoLibreFinder")) {
        String textoLibreFinder = (String) params.get("textoLibreFinder");
        if (!textoLibreFinder.isEmpty()) {
            titulo = textoLibreFinder;
            resumen = textoLibreFinder;
        }
    } else {
        if (params.containsKey("tituloFinder")) {
            titulo = (String) params.get("tituloFinder");
        }
        if (params.containsKey("resumenFinder")) {
            resumen = (String) params.get("resumenFinder");
            if (!resumen.isEmpty() && params.containsKey("exactAbstract") && params.get("exactAbstract").equals("active")) {
                resumen = "\"".concat(resumen).concat("\"");
            }
        }
    }
    // TODO Actualmente no se están utilizando las búsquedas por fecha en el
    // buscador. En caso de utilizarlas hay que tener en cuenta que las
    // búsquedas se hacen por > y <, para que tenga un comportamiento de >=,
    // <=,
    // habría que restar un día a las fechas que llegan. También faltan las
    // validaciones por fecha.
    /*
         * Calendar fechaFinderFrom = null; Calendar fechaFinderTo = null; if
         * (params.containsKey("fechaFinderFrom") &&
         * !params.get("fechaFinderFrom").isEmpty()) { String fechaFinderFromString
         * = (String) params.get("fechaFinderFrom"); if
         * (!fechaFinderFromString.isEmpty()) { DateFormat df = new
         * SimpleDateFormat("dd/MM/yyyy"); fechaFinderFrom = Calendar.getInstance();
         * try { fechaFinderFrom.setTime(df.parse(fechaFinderFromString)); } catch
         * (Exception ex) { fechaFinderFrom = null;
         * LOGGER.warn(ex.getLocalizedMessage(), ex); } } } if
         * (params.containsKey("fechaFinderTo") &&
         * !params.get("fechaFinderTo").isEmpty()) { String fechaFinderToString =
         * (String) params.get("fechaFinderTo"); if (!fechaFinderToString.isEmpty())
         * { DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); fechaFinderTo =
         * Calendar.getInstance(); try {
         * fechaFinderTo.setTime(df.parse(fechaFinderToString)); } catch (Exception
         * ex) { fechaFinderTo = null; LOGGER.warn(ex.getLocalizedMessage(), ex); }
         * } }
         */
    // Llamada al servicio que interactúa con libreria de consulta
    CSWCriteria cswCriteria = new CSWCriteria();
    cswCriteria.setLanguage("spa");
    cswCriteria.setStartAt(criterias.getDisplayStart() + 1);
    cswCriteria.setTitle(titulo);
    cswCriteria.setAbstract_(resumen);
    /*
         * TODO cswCriteria.setDateFrom(fechaFinderFrom);
         * cswCriteria.setDateTo(fechaFinderTo);
         */
    List<CSWSingleResult> results = new ArrayList<CSWSingleResult>();
    CSWResult cswResult = null;
    try {
        cswResult = ogcInfoService.getCswRecords(gestorCatalogoFinder, cswCriteria);
        if (cswResult != null) {
            results = cswResult.getResults();
        }
    } catch (NotSupportedVersionException ex) {
        LOGGER.warn(ex.getLocalizedMessage(), ex);
        return new ResponseEntity<DatatablesResponse<Map<String, String>>>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    } catch (URISyntaxException ex) {
        LOGGER.warn(ex.getLocalizedMessage(), ex);
        return new ResponseEntity<DatatablesResponse<Map<String, String>>>(HttpStatus.BAD_REQUEST);
    } catch (NullPointerException ex) {
        LOGGER.warn(ex.getLocalizedMessage(), ex);
        return new ResponseEntity<DatatablesResponse<Map<String, String>>>(HttpStatus.BAD_REQUEST);
    }
    List<Catalogo> respuestaCatalogo = new LinkedList<Catalogo>();
    for (CSWSingleResult result : results) {
        Catalogo catalogo = new Catalogo();
        catalogo.setId(result.getFileIdentifier());
        catalogo.setTitulo(result.getTitle());
        catalogo.setResumen(result.getAbstract_());
        catalogo.setImagen(result.getImage());
        catalogo.setUrl(result.getServiceUrl());
        catalogo.setTipoServicio(result.getServiceType());
        respuestaCatalogo.add(catalogo);
    }
    long totalRecords = 0;
    if (cswResult != null) {
        totalRecords = (long) cswResult.getTotalRecords();
    }
    if (criterias.getDisplayStart() != null && criterias.getDisplayStart() >= 0) {
        long displayStart = criterias.getDisplayStart().longValue();
        if (displayStart > totalRecords) {
            // Sets displayStart = 0
            criterias = new DatatablesCriterias(criterias.getSearch(), 0, criterias.getDisplaySize(), criterias.getColumnDefs(), criterias.getSortingColumnDefs(), criterias.getInternalCounter());
        }
    }
    DataSet<Map<String, String>> dataSet = DatatablesUtils.populateDataSet(respuestaCatalogo, pkFieldName, totalRecords, totalRecords, criterias.getColumnDefs(), datePattern, conversionService_dtt);
    return new ResponseEntity<DatatablesResponse<Map<String, String>>>(DatatablesResponse.build(dataSet, criterias), HttpStatus.OK);
}


public void addDateTimeFormatPatterns(Model uiModel){
    uiModel.addAttribute("catalogo_fechafinder_date_format", "dd/MM/yyyy");
}


public void setDatatablesBaseFilter(Map<String,Object> propertyMap){
// Add here your baseFilters to propertyMap.
}


@RequestMapping(produces = "text/html", value = "/list")
public String listDatatablesDetail(Model uiModel,HttpServletRequest request,Catalogo catalogo){
    // Do common datatables operations: get entity list filtered by request
    // parameters
    listDatatables(uiModel, request);
    // Show only the list fragment (without footer, header, menu, etc.)
    return "forward:/WEB-INF/views/catalogos/list.jspx";
}


public void export(DatatablesCriterias criterias,Catalogo catalogo,ExportType exportType,DatatablesExport datatablesExport,HttpServletRequest request,HttpServletResponse response){
    // Does the export process as is explained in
    // http://dandelion.github.io/datatables/tutorials/export/controller-based-exports.html
    // 1. Retrieve the data
    List<Map<String, String>> data = retrieveData(criterias, catalogo, request);
    // 2. Build an instance of "ExportConf"
    ExportConf exportConf = new ExportConf.Builder(exportType).header(true).exportClass(datatablesExport).autoSize(true).fileName(catalogo.getClass().getSimpleName()).build();
    // 3. Build an instance of "HtmlTable"
    HtmlTable table = datatablesUtilsBean_dtt.makeHtmlTable(data, criterias, exportConf, request);
    // 4. Render the generated export file
    ExportUtils.renderExport(table, exportConf, response);
}


@RequestMapping(value = "/exportcsv", produces = "text/csv")
public void exportCsv(DatatablesCriterias criterias,Catalogo catalogo,HttpServletRequest request,HttpServletResponse response){
    export(criterias, catalogo, ExportType.CSV, new CsvExport(), request, response);
}


}