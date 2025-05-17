package br.com.bpkedu.produtoJPA.service;

import br.com.bpkedu.produtoJPA.domain.Produto;
import br.com.bpkedu.produtoJPA.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void atualizarEstoqueEmLote(List<Produto> produtos) {
        for (Produto produto : produtos) {
            Produto existente = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produto.getId()));
            existente.setEstoque(produto.getEstoque());
        }
        produtoRepository.saveAll(produtos);
    }
}
