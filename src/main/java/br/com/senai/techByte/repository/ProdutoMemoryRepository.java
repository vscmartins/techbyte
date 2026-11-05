package br.com.senai.techByte.repository;

import br.com.senai.techByte.model.Produto;

import java.util.*;

public abstract class ProdutoMemoryRepository implements ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();
    private long proximoID = 1;

    @Override
    public Produto salvar(Produto produto){
        Produto novo = new Produto(
                proximoID++,
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        );
        produtos.add(novo);
        return novo;
    }

    @Override
    public List<Produto> listarTodos(){
        return Collections.unmodifiableList(produtos);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id){
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public  boolean atualizar(Produto produtoAtualizado){
        for(int i = 0; i < produtos.size(); i++){
            if (produtos.get(i).getId().equals(produtoAtualizado.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Long id){
        return  produtos.removeIf(p -> p.getId().equals(id));
    }
}
