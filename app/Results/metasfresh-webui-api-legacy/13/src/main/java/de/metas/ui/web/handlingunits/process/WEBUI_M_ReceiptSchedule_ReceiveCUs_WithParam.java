package de.metas.ui.web.handlingunits.process;
 import java.math.BigDecimal;
import java.util.stream.Stream;
import org.adempiere.exceptions.FillMandatoryException;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.Param;
public class WEBUI_M_ReceiptSchedule_ReceiveCUs_WithParam extends WEBUI_M_ReceiptSchedule_ReceiveCUsimplements IProcessDefaultParametersProvider{

 private  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU, mandatory = true)
 private  BigDecimal p_QtyCU;


public I_M_ReceiptSchedule getM_ReceiptSchedule(){
    return getRecord(I_M_ReceiptSchedule.class);
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_QtyCU.equals(parameter.getColumnName())) {
        final I_M_ReceiptSchedule receiptSchedule = getM_ReceiptSchedule();
        return getDefaultAvailableQtyToReceive(receiptSchedule);
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public Stream<I_M_ReceiptSchedule> streamReceiptSchedulesToReceive(){
    return Stream.of(getM_ReceiptSchedule());
}


@Override
public BigDecimal getEffectiveQtyToReceive(I_M_ReceiptSchedule rs){
    if (p_QtyCU == null || p_QtyCU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyCU);
    }
    return p_QtyCU;
}


}