package upce.semprace.eshop.controllerTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import upce.semprace.eshop.Main;
import upce.semprace.eshop.entity.Produkt;
import upce.semprace.eshop.repository.ProduktRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class ProduktControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduktRepository produktRepository;

    @BeforeEach
    public void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    public void getProductByID() throws Exception {
        Produkt produkt = new Produkt();
        produkt.setId(1L);
        produkt.setNazev("produkt11");
        produkt.setPopis("popis popis popis 1");
        produkt.setCena(500);
        produkt.setSlevaProcenta(0);
        produkt.setvNabidce(true);
        produkt.setCestaKObrazku("cesta1");
        produktRepository.save(produkt);
        Optional<Produkt> optionalProduct = Optional.of(produkt);

        when(produktRepository.findById(1L)).thenReturn(optionalProduct);

        mockMvc.perform(get("/produkt/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nazev").value("produkt11"));
    }

    @Test
    public void getProducts() throws Exception {
        Produkt produkt = new Produkt();
        produkt.setId(1L);
        produkt.setNazev("produkt11");
        produkt.setPopis("popis popis popis 1");
        produkt.setCena(500);
        produkt.setSlevaProcenta(0);
        produkt.setvNabidce(true);
        produkt.setCestaKObrazku("cesta1");
        produktRepository.save(produkt);
        when(produktRepository.findAll()).thenReturn(Collections.singletonList(produkt));

        mockMvc.perform(get("/produkt/all-products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nazev").value("produkt11"));
    }

}
