package br.com.victor.techbyte.repository;

import br.com.victor.techbyte.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
