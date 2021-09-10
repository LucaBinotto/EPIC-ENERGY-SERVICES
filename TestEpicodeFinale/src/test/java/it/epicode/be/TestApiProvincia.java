package it.epicode.be;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.model.Provincia;
import it.epicode.be.service.ProvinciaService;

@SuppressWarnings("unused")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class TestApiProvincia {

	@Autowired
	private MockMvc mock;
//	@Autowired
//	private ProvinciaController prc;
	@Autowired
	ProvinciaService prs;
	
	private static Long provinciaSalvata;
	
	private String prova = 	"""
			 					ciao prova
			 				fafa
			 				fafaf""";
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(1)
	void salvataggioProvincia() throws Exception {
		System.out.println(prova);
		mock.perform(post("/api/provincia")
		.contentType(MediaType.APPLICATION_JSON)
		.content("""
				{
				    "nome": "FakeProvincia",
				    "sigla": "FKP",
				    "regione": "FakeLand"
				}"""))
		.andExpect(status().isCreated());
		
		Provincia prov = prs.findByNome("FakeProvincia");
		provinciaSalvata = prov.getId();
		System.out.println(provinciaSalvata);
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(2)
	void getProvinciaById() throws Exception {
		mock.perform(get("/api/provincia/"+provinciaSalvata))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("FKP")));
		
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(3)
	void getAllProvincia() throws Exception {
		mock.perform(get("/api/provincia?pageNum=11&pageSize=10"))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("FKP")));
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(4)
	void updateProvincia() throws Exception {
		mock.perform(put("/api/provincia/"+provinciaSalvata)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"id\": \""+ provinciaSalvata +"\",\n"
						+ "    \"nome\": \"FakestProvincia\",\n"
						+ "    \"sigla\": \"FKP\",\n"
						+ "    \"regione\": \"FakeLand\"\n"
						+ "}"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(5)
	void updateProvinciaBadRequest() throws Exception {
		mock.perform(put("/api/provincia/"+provinciaSalvata)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"id\": \"0\",\n"
						+ "    \"nome\": \"FakestProvincia\",\n"
						+ "    \"sigla\": \"FKP\",\n"
						+ "    \"regione\": \"FakeLand\"\n"
						+ "}"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	@Order(6)
	void deleteProvincia() throws Exception {
		mock.perform(delete("/api/provincia/"+provinciaSalvata))
		.andExpect(status().isNoContent());
	}

}
