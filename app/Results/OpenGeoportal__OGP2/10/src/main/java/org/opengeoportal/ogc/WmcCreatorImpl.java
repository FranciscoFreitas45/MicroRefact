package org.opengeoportal.ogc;
 import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.metadata.LayerInfoRetriever;
import org.opengeoportal.ogc.OwsInfo.OwsType;
import org.opengeoportal.ogc.wmc.jaxb.BoundingBoxType;
import org.opengeoportal.ogc.wmc.jaxb.GeneralType;
import org.opengeoportal.ogc.wmc.jaxb.LayerListType;
import org.opengeoportal.ogc.wmc.jaxb.LayerType;
import org.opengeoportal.ogc.wmc.jaxb.OnlineResourceType;
import org.opengeoportal.ogc.wmc.jaxb.ServerType;
import org.opengeoportal.ogc.wmc.jaxb.ServiceType;
import org.opengeoportal.ogc.wmc.jaxb.TypeType;
import org.opengeoportal.ogc.wmc.jaxb.ViewContextType;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.LocationFieldUtils;
import org.opengeoportal.utilities.OgpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.opengeoportal.Interface.LayerInfoRetriever;
public class WmcCreatorImpl implements WmcCreator{

@Autowired
 private  LayerInfoRetriever layerInfoRetriever;

@Autowired
 private  Marshaller marshaller;

 final  Logger logger;


public GeneralType getGeneralInfo(BoundingBoxType bounds){
    /*
		 * 	<xs:complexType name="GeneralType">
		<xs:sequence>
			<xs:element name="Window" type="context:WindowType" minOccurs="0"/>
			<xs:element name="BoundingBox" type="context:BoundingBoxType"/>
			<xs:element name="Title" type="xs:string"/>
			<xs:element name="KeywordList" type="context:KeywordListType" minOccurs="0"/>
			<xs:element name="Abstract" type="xs:string" minOccurs="0"/>
			<xs:element name="LogoURL" type="context:URLType" minOccurs="0"/>
			<xs:element name="DescriptionURL" type="context:URLType" minOccurs="0"/>
			<xs:element name="ContactInformation" type="context:ContactInformationType" minOccurs="0"/>
			<xs:element name="Extension" type="context:ExtensionType" minOccurs="0"/>
		</xs:sequence>
	</xs:complexType>
		 */
    GeneralType generalInfo = new GeneralType();
    generalInfo.setTitle("OpenGeoportal Web Map Context");
    // generalInfo.setContactInformation(value);
    /*
		 * 		<BoundingBox SRS="EPSG:4326" minx="-180.000000" miny="-90.000000" maxx="180.000000" maxy="90.000000"/>

		 */
    generalInfo.setBoundingBox(bounds);
    return generalInfo;
}


public LayerType populateLayer(SolrRecord record,OwsType format){
    /*
		 * 	<xs:complexType name="LayerType">
		<xs:sequence>
			<xs:element name="Server" type="context:ServerType"/>
			<xs:element name="Name" type="xs:string"/>
			<xs:element name="Title" type="xs:string"/>
			<xs:element name="Abstract" type="xs:string" minOccurs="0"/>
			<xs:element name="DataURL" type="context:URLType" minOccurs="0"/>
			<xs:element name="MetadataURL" type="context:URLType" minOccurs="0"/>
			<xs:element ref="sld:MinScaleDenominator" minOccurs="0"/>
			<xs:element ref="sld:MaxScaleDenominator" minOccurs="0"/>
			<xs:element name="SRS" type="xs:string" minOccurs="0" maxOccurs="unbounded"/>
			<xs:element name="FormatList" type="context:FormatListType" minOccurs="0"/>
			<xs:element name="StyleList" type="context:StyleListType" minOccurs="0"/>
			<xs:element name="DimensionList" type="context:DimensionListType" minOccurs="0"/>
			<xs:element name="Extension" type="context:ExtensionType" minOccurs="0"/>
		</xs:sequence>
		<xs:attribute name="queryable" type="xs:boolean" use="required"/>
		<xs:attribute name="hidden" type="xs:boolean" use="required"/>
	</xs:complexType>
		 */
    LayerType layer = new LayerType();
    layer.setHidden(false);
    layer.setQueryable(true);
    /*
		 * 	<Server service="OGC:WMS" version="1.1.1" title="Canada Centre for Remote Sensing Web Map Service">
				<OnlineResource xlink:type="simple" xlink:href="http://ceoware2.ccrs.nrcan.gc.ca/cubewerx/cubeserv/cubeserv.cgi"/>
			</Server>
		 */
    ServerType server = new ServerType();
    OnlineResourceType olresource = new OnlineResourceType();
    String location = record.getLocation();
    ServiceType serviceType;
    String serviceVersion;
    TypeType resourceType;
    String resourceHref;
    if (format == OwsType.DISPLAY) {
        if (LocationFieldUtils.hasWmsUrl(location)) {
            serviceType = ServiceType.OGC_WMS;
            serviceVersion = "1.1.1";
            resourceType = TypeType.SIMPLE;
            resourceHref = LocationFieldUtils.getWmsUrl(location);
        } else {
            throw new Exception("No OGC Web Map Services associated with this layer.");
        }
    } else {
        // OwsType.DATA
        if (LocationFieldUtils.hasWfsUrl(location)) {
            serviceType = ServiceType.OGC_WFS;
            serviceVersion = "1.1.0";
            resourceType = TypeType.SIMPLE;
            resourceHref = LocationFieldUtils.getWfsUrl(location);
        } else if (LocationFieldUtils.hasWmsUrl(location)) {
            // fall back to WMS
            serviceType = ServiceType.OGC_WMS;
            serviceVersion = "1.1.1";
            resourceType = TypeType.SIMPLE;
            resourceHref = LocationFieldUtils.getWmsUrl(location);
        } else {
            throw new Exception("No OGC Web Map Services associated with this layer.");
        }
    }
    server.setService(serviceType);
    server.setVersion(serviceVersion);
    olresource.setType(resourceType);
    olresource.setHref(resourceHref);
    server.setOnlineResource(olresource);
    layer.setServer(server);
    server.setTitle(record.getLayerDisplayName());
    String name = OgpUtils.getLayerNameNS(record.getWorkspaceName(), record.getName());
    layer.setName(name);
    layer.setTitle(record.getLayerDisplayName());
    layer.setAbstract(record.getDescription());
    return layer;
}


public ViewContextType createViewContext(Map<String,OwsType> idsAndFormats,BoundingBox bounds){
    ViewContextType wmcResponse = new ViewContextType();
    wmcResponse.setGeneral(getGeneralInfo(convertOGPBoundsToWMCBounds(bounds)));
    wmcResponse.setLayerList(getLayerList(idsAndFormats));
    wmcResponse.setId(UUID.randomUUID().toString());
    wmcResponse.setVersion("1.1.0");
    return wmcResponse;
}


@Override
public Result getWmcResponse(Map<String,OwsType> idsAndFormats,BoundingBox bounds,OutputStream os){
    ViewContextType viewContext = createViewContext(idsAndFormats, bounds);
    Result result = new StreamResult(os);
    try {
        marshaller.marshal(viewContext, result);
    } catch (XmlMappingException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


public BoundingBoxType convertOGPBoundsToWMCBounds(BoundingBox bounds){
    BoundingBoxType bboxType = new BoundingBoxType();
    Double minx;
    Double miny;
    Double maxx;
    Double maxy;
    if (bounds != null) {
        minx = bounds.getMinX();
        miny = bounds.getMinY();
        maxx = bounds.getMaxX();
        maxy = bounds.getMaxY();
    } else {
        minx = -180.000000;
        miny = -90.000000;
        maxx = 180.000000;
        maxy = 90.000000;
    }
    String srs = "EPSG:4326";
    bboxType.setMinx(new BigDecimal(minx));
    bboxType.setMiny(new BigDecimal(miny));
    bboxType.setMaxx(new BigDecimal(maxx));
    bboxType.setMaxy(new BigDecimal(maxy));
    bboxType.setSRS(srs);
    return bboxType;
}


public LayerListType getLayerList(Map<String,OwsType> idsAndFormats){
    List<SolrRecord> records = layerInfoRetriever.fetchAllLayerInfo(idsAndFormats.keySet());
    LayerListType layerList = new LayerListType();
    for (SolrRecord record : records) {
        try {
            layerList.getLayer().add(populateLayer(record, idsAndFormats.get(record.getLayerId())));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    return layerList;
}


}