package br.com.bpkedu.produtoJPA.repository;

import br.com.bpkedu.produtoJPA.domain.Produto;
import br.com.bpkedu.produtoJPA.projection.ProdutoResumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByDescricaoContaining(String descricao);
    List<Produto> findByPrecoBetween(Double min, Double max);

    @Query("SELECT p FROM Produto p WHERE p.estoque < 5")
    List<Produto> findProdutosComEstoqueBaixo();

    @Query("SELECT p.descricao AS descricao, p.preco AS preco FROM Produto p WHERE p.estoque > 0")
    List<ProdutoResumo> listarProdutosDisponiveis();
}

