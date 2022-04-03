package org.opengeoportal.download.methods;
 import org.opengeoportal.download.types.generated.ogc.wms_describelayer.WMSDescribeLayerResponse;
public interface WmsDescribeLayer {


public WMSDescribeLayerResponse describeLayer(String baseUrl,String qualifiedLayerName)
;

}