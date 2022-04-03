package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
public interface ITransferBetweenMembersCashController {


public ResponseEntity<Object> transferBetweenTwoCashAccounts(TransferInDTO info)
;

}