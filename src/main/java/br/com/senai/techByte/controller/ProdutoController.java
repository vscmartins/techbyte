package br.com.senai.techByte.controller;
import br.com.senai.techByte.model.Produto;
import br.com.senai.techByte.service.ProdutoService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    public Produto cadastrarProduto(String nome, String descricao, BigDecimal preco) {
        return service.cadastrarProduto(nome, descricao, preco);
    }
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }
    public Optional<Produto> buscarPorId(Long id) {
        return service.buscarPorId(id);
    }
    public boolean atualizarProduto(Long id, String nome, String descricao, BigDecimal preco) {
        return service.atualizarProduto(id, nome, descricao, preco);
    }
    public boolean removerProduto(Long id) {
        return service.removerProdruto(id);
    }
}
