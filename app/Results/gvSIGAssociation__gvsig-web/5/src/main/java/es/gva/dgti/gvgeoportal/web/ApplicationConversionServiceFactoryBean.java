package es.gva.dgti.gvgeoportal.web;
 import org.gvnix.addon.geo.annotations.GvNIXGeoConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
@RooConversionService
@GvNIXGeoConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean{


public Converter<SistemaCoordenadas,String> getSistemaCoordenadasToStringConverter(){
    return new org.springframework.core.convert.converter.Converter<es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas, java.lang.String>() {

        public String convert(SistemaCoordenadas sistemaCoordenadas) {
            return new StringBuilder().append(sistemaCoordenadas.getNombre()).toString();
        }
    };
}


public Converter<AgrupadorCapa,String> getAgrupadorCapaToStringConverter(){
    return new org.springframework.core.convert.converter.Converter<es.gva.dgti.gvgeoportal.domain.AgrupadorCapa, java.lang.String>() {

        public String convert(AgrupadorCapa agrupadorCapa) {
            return new StringBuilder().append(agrupadorCapa.getNombre()).toString();
        }
    };
}


public String convert(GeoPortal geoPortal){
    return new StringBuilder().append(geoPortal.getAuditCreation()).append(' ').append(geoPortal.getAuditCreatedBy()).append(' ').append(geoPortal.getAuditLastUpdate()).append(' ').append(geoPortal.getAuditLastUpdatedBy()).toString();
}


@SuppressWarnings("deprecation")
@Override
public void installFormatters(FormatterRegistry registry){
    super.installFormatters(registry);
}


public Converter<GeoPortal,String> getGeoPortalToStringConverter(){
    return new org.springframework.core.convert.converter.Converter<es.gva.dgti.gvgeoportal.domain.GeoPortal, java.lang.String>() {

        public String convert(GeoPortal geoPortal) {
            return new StringBuilder().append(geoPortal.getAuditCreation()).append(' ').append(geoPortal.getAuditCreatedBy()).append(' ').append(geoPortal.getAuditLastUpdate()).append(' ').append(geoPortal.getAuditLastUpdatedBy()).toString();
        }
    };
}


}