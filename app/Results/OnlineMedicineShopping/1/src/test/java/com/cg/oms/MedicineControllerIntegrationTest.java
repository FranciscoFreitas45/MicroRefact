
package com.cg.oms;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cg.oms.model.Medicine;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedicineControllerIntegrationTest
{

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl()
	{
		return "http://localhost:" + port;
	}

	@Test
	void testAddMedicine()
	{
		Medicine medicine = new Medicine();
		LocalDateTime localDateTime = LocalDateTime.now();
		medicine.setMedicineCategory("Tablet");
		medicine.setMedicineDescription("FEVER TABLETS");
		medicine.setMedicineManufactureDate(localDateTime);
		medicine.setMedicineManufactureDate(localDateTime);
		medicine.setMedicineManufacturerName("pharm productions");
		medicine.setMedicineName("paracetomol");
		medicine.setMedicinePrice(85.7);
		medicine.setMedicineQuantity(100);
		ResponseEntity<Medicine> postResponse = restTemplate.postForEntity(getRootUrl() + "/Medicine/newMedicine",
				medicine, Medicine.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllMedicine()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Medicines/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testMedicineById()
	{
		Medicine Medicine = restTemplate.getForObject(getRootUrl() + "/Medicine/104", Medicine.class);
		System.out.println(Medicine.getMedicineId() + " " + Medicine.getMedicineCategory() + " "
				+ Medicine.getMedicineDescription() + " " + Medicine.getMedicineManufacturerName() + " "
				+ Medicine.getMedicineName() + " " + Medicine.getMedicinePrice() + " " + Medicine.getMedicineQuantity()
				+ " " + Medicine.getMedicineExpiryDate() + " " + Medicine.getMedicineManufactureDate());
		assertNotNull(Medicine);
	}

	@Test
	void testUpdateMedicine()
	{
		Medicine medicine = restTemplate.getForObject(getRootUrl() + "/Medicine/101", Medicine.class);
		// assertEquals(medicine.getMedicineId(), 101);
		System.out.println("medicineId: " + medicine.getMedicineId());
		LocalDateTime localDateTime = LocalDateTime.now();
		medicine.setMedicineCategory("Tablet");
		medicine.setMedicineDescription("SUGAR TABLETS");
		medicine.setMedicineExpiryDate(localDateTime);
		medicine.setMedicineManufactureDate(localDateTime);
		medicine.setMedicineManufacturerName("vino productions");
		medicine.setMedicineName("zyloin");
		medicine.setMedicinePrice(100.7);
		medicine.setMedicineQuantity(100);
		restTemplate.put(getRootUrl() + "/Medicines/updatemedicine/101", medicine);
		Medicine updatedMedicine = restTemplate.getForObject(getRootUrl() + "/Medicine/101", Medicine.class);
		assertNotNull(updatedMedicine);
	}

	@Test
	void testDeleteMedicine()
	{
		Medicine medicine = restTemplate.getForObject(getRootUrl() + "/Medicine/101", Medicine.class);
		// assertNotNull(medicine.getId(),101);
		restTemplate.delete(getRootUrl() + "/Medicines/deletemedicine/101");
		Medicine medicine1 = restTemplate.getForObject(getRootUrl() + "/Medicine/101", Medicine.class);
		System.out.println(medicine);
		assertNotEquals(medicine, medicine1);
	}
}
