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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PontoControllerTest {

    private int usuarioId = 0;
    private int entradaId = 0;
    private int saidaId = 0;

    @Autowired
    private MockMvc mockMvc;

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
        usuarioId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        MvcResult pontoEntradaResult = mockMvc.perform(post("/api/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"usuario\":{\n" +
                        "    \"id\": "+usuarioId+"\n" +
                        "}\n" +
                        "}"))
                .andReturn();

        entradaId = JsonPath.read(pontoEntradaResult.getResponse().getContentAsString(), "$.id");

        MvcResult pontoSaidaResult = mockMvc.perform(post("/api/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"usuario\":{\n" +
                        "    \"id\": "+usuarioId+"\n" +
                        "}\n" +
                        "}"))
                .andReturn();

        saidaId = JsonPath.read(pontoSaidaResult.getResponse().getContentAsString(), "$.id");
    }


    @Test
    public void criarPontoDoUsuario() throws Exception {
        mockMvc.perform(post("/api/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"" + usuarioId + "\"}"))
                .andDo(print())
                .andExpect(jsonPath("$.usuario", is(usuarioId)))
                .andExpect(jsonPath("$.entrada", is(true)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllPontos() throws Exception {
        mockMvc.perform(get("/api/ponto/lista"))
                .andDo(print())
                .andExpect(jsonPath("$.usuario", is(usuarioId)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllPontosDeUsuarios() throws Exception {
        mockMvc.perform(get("/api/ponto/usuarioId/" + usuarioId))
                .andDo(print())
                .andExpect(jsonPath("$.horasTrabalhadas", containsString("segundo")))
                .andExpect(status().isAccepted());
    }

    @Test
    public void criaDeUsuarioInexistente() throws Exception {
        mockMvc.perform(post("/api/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"" + 92384 + "\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletaPontoInexistente() throws Exception {
        mockMvc.perform(delete("/api/ponto" + 2839124))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
