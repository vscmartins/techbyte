package br.com.victor.techbyte.service;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado para o ID " + id);
    }
}
