package br.com.victor.techbyte.controller;

import br.com.victor.techbyte.entity.Produto;
import br.com.victor.techbyte.dto.ProdutoRequest;
import br.com.victor.techbyte.service.ProdutoNaoEncontradoException;
import br.com.victor.techbyte.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    @Test
    void deveListarProdutos() throws Exception {
        when(service.listarProdutos()).thenReturn(List.of(
                new Produto("Notebook", "Notebook para trabalho", new BigDecimal("3500.00"))
        ));

        mockMvc.perform(get("/api/produtos/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Notebook"))
                .andExpect(jsonPath("$[0].preco").value(3500.00));
    }

    @Test
    void deveCadastrarProduto() throws Exception {
        when(service.cadastrarProduto(any(ProdutoRequest.class)))
                .thenReturn(new Produto("Teclado", "Teclado mecânico", new BigDecimal("299.90")));

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Teclado",
                                  "descricao": "Teclado mecânico",
                                  "preco": 299.90
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Teclado"))
                .andExpect(jsonPath("$.preco").value(299.90));
    }

    @Test
    void deveRecusarProdutoSemDadosObrigatorios() throws Exception {
        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "",
                                  "descricao": "",
                                  "preco": -1
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").exists());
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        when(service.buscarPorId(1L))
                .thenReturn(new Produto("Mouse", "Mouse sem fio", new BigDecimal("99.90")));

        mockMvc.perform(get("/api/produtos/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mouse"))
                .andExpect(jsonPath("$.descricao").value("Mouse sem fio"));
    }

    @Test
    void deveRetornar404QuandoProdutoNaoExiste() throws Exception {
        when(service.buscarPorId(99L)).thenThrow(new ProdutoNaoEncontradoException(99L));

        mockMvc.perform(get("/api/produtos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Produto não encontrado para o ID 99"));
    }
}
