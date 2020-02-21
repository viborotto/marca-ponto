package com.example.marcaponto;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private int idUsuarioCriado = 0;

    @Before
    public void init() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"4.7.932.697.04\",\n" +
                        "  \"email\": \"dorime@gmail.com\",\n" +
                        "  \"nomeCompleto\": \"Ameno Dorime\",\n" +
                        "  \"dataCadastro\": \"2020-02-20\"\n" +
                        "}"))
                .andReturn();
        idUsuarioCriado = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    }

    @Test
    public void quandoSucessoNaCriacaoUsuario() throws Exception {
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"4.7.932.697.04\",\n" +
                        "  \"email\": \"dorime@gmail.com\",\n" +
                        "  \"nomeCompleto\": \"Ameno Dorime\",\n" +
                        "  \"dataCadastro\": \"2020-02-20\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(jsonPath("$.nomeCompleto", is("Ameno Dorime")))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(status().isCreated());
    }

    @Test
    public void quandoSucessoNaEdicaoUsuario() throws Exception {
        mockMvc.perform(put("/api/usuarios" + idUsuarioCriado)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"4.7.932.697.04\",\n" +
                        "  \"email\": \"dorime@gmail.com\",\n" +
                        "  \"nomeCompleto\": \"Ameno Dorime\",\n" +
                        "  \"dataCadastro\": \"2020-02-20\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(jsonPath("$.nomeCompleto", is("Ameno Dorime")))
                .andExpect(jsonPath("$.cpf", is("4.7.932.697.04")))
                .andExpect(jsonPath("$.email", is("dorime@gmail.com")))
                .andExpect(status().isCreated());
    }

    @Test
    public void quandoUsuarioNaoExiste() throws Exception {
        mockMvc.perform(put("/api/usuarios/" + 2313214)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"4.7.932.697.04\",\n" +
                        "  \"email\": \"dorime@gmail.com\",\n" +
                        "  \"nomeCompleto\": \"Ameno Dorime\",\n" +
                        "  \"dataCadastro\": \"2020-02-20\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void allUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios"))
                .andDo(print())
                .andExpect(jsonPath("$.[0].nome", is("Ameno Dorime")))
                .andExpect(status().isAccepted());
    }

    @Test
    public void sucessoNoCadastroDoUsuario() throws Exception {
        mockMvc.perform(get("/api/usuarios" + idUsuarioCriado))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(idUsuarioCriado)))
                .andExpect(jsonPath("$.nome", is("Ameno Dorime")))
                .andExpect(jsonPath("$.cpf", is("4.7.932.697.04")))
                .andExpect(jsonPath("$.email", is("dorime@gmail.com")))
                .andExpect(status().isCreated());
    }

    @Test
    public void usuarioInexist() throws Exception {
        mockMvc.perform(get("/api/usuarios" + 83249))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
