import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


public Invoice findByInvoiceId(Long invoiceId)


}