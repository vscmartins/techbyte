package br.com.victor.techbyte.service;

import br.com.victor.techbyte.entity.Produto;
import br.com.victor.techbyte.dto.ProdutoRequest;
import br.com.victor.techbyte.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto cadastrarProduto(ProdutoRequest request) {
        Produto produto = new Produto(
                request.nome().trim(),
                request.descricao().trim(),
                request.preco()
        );
        return repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}
