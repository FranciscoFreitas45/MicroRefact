package switchtwentytwenty.project.dto.toservicedto;
 import switchtwentytwenty.project.dto.indto.PaymentInDTO;
public class PaymentDTOMapper {

/**
 * Private constructor.
 */
private PaymentDTOMapper() {
}
public PaymentDTO mapToDTO(PaymentInDTO info,String accountID){
    PaymentDTO newDTO = new PaymentDTO();
    newDTO.setPersonID(info.getPersonID());
    newDTO.setAccountID(accountID);
    newDTO.setAmount(info.getAmount());
    newDTO.setCategoryID(info.getCategoryID());
    newDTO.setDate(info.getDate());
    newDTO.setDesignation(info.getDesignation());
    return newDTO;
}


}