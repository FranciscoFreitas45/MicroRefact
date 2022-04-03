package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
public interface ITransferFamilyAndMemberCashController {


public ResponseEntity<Object> transferFamilyAndMemberCash(TransferInDTO info)
;

}