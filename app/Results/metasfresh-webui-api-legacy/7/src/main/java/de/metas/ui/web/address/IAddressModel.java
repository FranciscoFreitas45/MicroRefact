package de.metas.ui.web.address;
 import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Postal;
import org.compiere.model.I_C_Region;
public interface IAddressModel {

 private String COLUMNNAME_Address1;

 private String COLUMNNAME_Address2;

 private String COLUMNNAME_Address3;

 private String COLUMNNAME_Address4;

 private String COLUMNNAME_Postal;

 private String COLUMNNAME_City;

 private String COLUMNNAME_C_Region_ID;

 private String COLUMNNAME_C_Country_ID;

 private String COLUMNNAME_HasRegion;

 private String COLUMNNAME_C_Postal_ID;


public String getAddress4()
;

public String getAddress2()
;

public String getAddress3()
;

public String getAddress1()
;

public void setCity(String City)
;

public void setC_Region_ID(int C_Region_ID)
;

public int getC_Country_ID()
;

public I_C_Country getC_Country()
;

public int getC_Postal_ID()
;

public String getPostal()
;

public I_C_Postal getC_Postal()
;

public void setC_Postal_ID(int C_Postal_ID)
;

public void setAddress4(String address)
;

public I_C_Region getC_Region()
;

public void setAddress2(String address)
;

public void setAddress3(String address)
;

public void setPostal(String postal)
;

public void setAddress1(String address)
;

public boolean isHasRegion()
;

public void setHasRegion(boolean HasRegion)
;

public void setC_Country_ID(int C_Country_ID)
;

public int getC_Region_ID()
;

public String getCity()
;

}