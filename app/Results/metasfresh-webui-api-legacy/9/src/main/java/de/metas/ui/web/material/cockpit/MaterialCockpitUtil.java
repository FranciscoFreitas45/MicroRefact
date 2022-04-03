package de.metas.ui.web.material.cockpit;
 import org.adempiere.service.ISysConfigBL;
import org.compiere.util.Env;
import de.metas.dimension.DimensionSpec;
import de.metas.dimension.IDimensionspecDAO;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
public class MaterialCockpitUtil {

 public  String WINDOWID_MaterialCockpitView_String;

 public  WindowId WINDOWID_MaterialCockpitView;

 public  String WINDOWID_MaterialCockpit_Detail_String;

 public  WindowId WINDOWID_MaterialCockpit_DetailView;

 public  String WINDOW_MaterialCockpit_StockDetail_String;

 public  WindowId WINDOW_MaterialCockpit_StockDetailView;

 public  String SYSCONFIG_DIM_SPEC_INTERNAL_NAME;

 public  String DEFAULT_DIM_SPEC_INTERNAL_NAME;

 public  String SYSCONFIG_INCLUDE_PER_PLANT_DETAIL_ROWS;

 public  String DONT_FILTER;

 public  String NON_EMPTY;


public DimensionSpec retrieveDimensionSpec(){
    final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
    final IDimensionspecDAO dimensionspecDAO = Services.get(IDimensionspecDAO.class);
    final String dimSpecName = sysConfigBL.getValue(SYSCONFIG_DIM_SPEC_INTERNAL_NAME, DEFAULT_DIM_SPEC_INTERNAL_NAME, Env.getAD_Client_ID(), Env.getAD_Org_ID(Env.getCtx()));
    final DimensionSpec dimensionSpec = dimensionspecDAO.retrieveForInternalNameOrNull(CoalesceUtil.firstNotEmptyTrimmed(dimSpecName, DEFAULT_DIM_SPEC_INTERNAL_NAME));
    return Check.assumeNotNull(dimensionSpec, "Unable to load DIM_Dimension_Spec record with InternalName={}", dimSpecName);
}


}