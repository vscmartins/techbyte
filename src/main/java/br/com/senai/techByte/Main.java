package br.com.senai.techByte;

import br.com.senai.techByte.model.Produto;
import br.com.senai.techByte.service.ProdutoService;
import br.com.senai.techByte.controller.ProdutoController;
import br.com.senai.techByte.repository.ProdutoMemoryRepository;
import br.com.senai.techByte.repository.ProdutoRepository;
import br.com.senai.techByte.view.ProdutoConsoleView;

import java.util.Optional;

public class Main {

    static void main(){
        ProdutoRepository repository = new ProdutoMemoryRepository();

        ProdutoRepository service = new ProdutoService(repository);

        ProdutoController controller = new ProdutoController(service);

        ProdutoConsoleView view = new ProdutoConsoleView(controller);
    }



}
